package ru.bmstu.akka;

import akka.actor.AbstractActor;
import akka.japi.Pair;
import akka.japi.pf.ReceiveBuilder;

import java.util.List;
import java.util.Map;

public class StoreActor extends AbstractActor {

    @Override
    public Receive createReceive() {
        return ReceiveBuilder.create().matchAny(input -> {
            PackageDefinition pack = (PackageDefinition) input;
            
        })
        .build();
    }
}
