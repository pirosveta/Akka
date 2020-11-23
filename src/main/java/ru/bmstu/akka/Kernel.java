package ru.bmstu.akka;

import akka.actor.AbstractActor;
import akka.japi.pf.ReceiveBuilder;

public class Router extends AbstractActor {
    @Override
    public Receive createReceive() {
        return ReceiveBuilder.create()
                .match(PackageDefinition.class, pack -> {
                    
                })
                .match(String.class, packageID -> {

                })
                .build();
    }
}
