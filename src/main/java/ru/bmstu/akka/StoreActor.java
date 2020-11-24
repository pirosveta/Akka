package ru.bmstu.akka;

import akka.actor.AbstractActor;
import akka.japi.Pair;
import akka.japi.pf.ReceiveBuilder;
import com.fasterxml.jackson.databind.JsonNode;

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
            TestsDefinition tests = pair.second();
            Map<String, List<String>> results = total.get(pack.getPackageID());
            List<JsonNode> testsName = tests.getTestName();
            List<JsonNode> testsValue = tests.getExpectedResult();
            for (int i = 0; i < testsName.size(); i++) {
                String name = testsName.get(i).toString();
                String value =
                total.put(pack.getPackageID(), tests.put(j.toString(), ))
            }
        })
        .build();
    }
}
