package ru.bmstu.akka;

import akka.actor.AbstractActor;
import akka.japi.Pair;
import akka.japi.pf.ReceiveBuilder;
import com.fasterxml.jackson.databind.JsonNode;

import java.util.List;
import java.util.Map;

public class StoreActor extends AbstractActor {

    private static Map<String, Map<String, List<String>>> results;

    @Override
    public Receive createReceive() {
        return ReceiveBuilder.create().matchAny(input -> {
            PackageDefinition pack = (PackageDefinition) input;
            Map<String, List<String>> tests= results.get(pack.getPackageID());
            List<JsonNode> testsName = pack.getTests();
            List<JsonNode> testsValue = pack.get
            for (int i = 0; i < testsName.size(); i++) {
                String name = testsName.get(i).toString();
                String value =
                results.put(pack.getPackageID(), tests.put(j.toString(), ))
            }
        })
        .build();
    }
}
