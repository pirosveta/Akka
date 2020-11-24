package ru.bmstu.akka;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.japi.pf.ReceiveBuilder;
import akka.routing.SmallestMailboxPool;

import java.util.Map;
import java.util.concurrent.Future;

public class Kernel extends AbstractActor {

    @Override
    public Receive createReceive() {
        ActorRef storeRouter = getContext().actorOf(new SmallestMailboxPool(5)
                .props(Props.create(StoreActor.class)), "store");
        ActorRef executeRouter = getContext().actorOf(new SmallestMailboxPool(5)
                .props(Props.create(ExecuteActor.class)), "execute");
        return ReceiveBuilder.create()
                .match(PackageDefinition.class, pair -> {
                    storeRouter.tell(pair, ActorRef.noSender());
                    executeRouter.tell(pair, ActorRef.noSender());
                })
                .match(String.class, packageID -> {
                    storeRouter.tell(packageID, getSelf());
                })
                .match(Map.class, input -> {
                    getSender().tell(input, ActorRef.noSender());
                })
                .build();
    }
}
