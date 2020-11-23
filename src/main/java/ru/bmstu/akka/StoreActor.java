package ru.bmstu.akka;

import akka.actor.AbstractActor;
import akka.japi.pf.ReceiveBuilder;

public class StoreActor extends AbstractActor {
    @Override
    public Receive createReceive() {
        return ReceiveBuilder.create().matchAny(input -> {
            PackageDefinition pack = (PackageDefinition) input;
            
        })
        .build();
    }
}
