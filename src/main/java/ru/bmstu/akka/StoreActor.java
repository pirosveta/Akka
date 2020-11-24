package ru.bmstu.akka;

import akka.actor.AbstractActor;
import akka.japi.Pair;
import akka.japi.pf.ReceiveBuilder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StoreActor extends AbstractActor {

    private static Map<String, Map<String, List<String>>> total = new HashMap<>();

    @Override
    public Receive createReceive() {
        return ReceiveBuilder.create().matchAny(input -> {
            Pair<PackageDefinition, TestsDefinition> pair = (Pair<PackageDefinition, TestsDefinition>) input;
            PackageDefinition pack = pair.first();
            TestsDefinition test = pair.second();
            Map<String, List<String>> results = total.get(pack.getPackageID());
            List<String> values = results.get(test.getTestName());
            values.add(test.getExpectedResult());
            results.replace(test.getTestName(), values);
            total.replace(pack.getPackageID(), results);
        })
        .build();
    }
}
