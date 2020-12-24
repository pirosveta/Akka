package ru.bmstu.akka;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.japi.Pair;
import akka.japi.pf.ReceiveBuilder;

import java.util.ArrayList;
import java.util.HashMap;

public class StoreActor extends AbstractActor {

    private static HashMap<String, HashMap<String, ArrayList<String>>> total = new HashMap<>();

    @Override
    public Receive createReceive() throws NullPointerException {
        return ReceiveBuilder.create().match(Pair.class, input -> {
            Pair<PackageDefinition, TestsDefinition> pair = (Pair<PackageDefinition, TestsDefinition>) input;
            PackageDefinition pack = pair.first();
            TestsDefinition test = pair.second();
            if (!total.containsKey(pack.getPackageId())) {
                total.put(pack.getPackageId(), new HashMap<>());
            }
            HashMap<String, ArrayList<String>> results = total.get(pack.getPackageId());
            if (!results.containsKey(test.getTestName())) {
                results.put(test.getTestName(), new ArrayList<>());
            }
            ArrayList<String> values = results.get(test.getTestName());
            values.add(test.getExpectedResult());
            results.replace(test.getTestName(), values);
            total.replace(pack.getPackageId(), results);
            System.out.println("STORE:");
            System.out.println(total);
        })
        .match(String[].class, input -> {
            HashMap<String, ArrayList<String>> results = total.get(input[0]);
            ArrayList<String> values = results.get(input[1]);
            values.add(input[2]);
            results.replace(input[1], values);
            total.replace(input[0], results);
            System.out.println("NEW STORE:");
            System.out.println(total);
        })
        .match(String.class, packageId -> {
            HashMap<String, Boolean> output = new HashMap<>();
            HashMap<String, ArrayList<String>> results = total.get(packageId);
            for (String testName : results.keySet()) {
                ArrayList<String> values = results.get(testName);
                output.put(testName, values.get(0) == values.get(1));
            }
            System.out.println(output.get(0));
            System.out.println(output.get(1));
            getSender().tell(output, ActorRef.noSender());
        })
        .build();
    }
}
