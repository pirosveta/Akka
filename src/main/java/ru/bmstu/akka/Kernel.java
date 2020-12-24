package ru.bmstu.akka;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.japi.Pair;
import akka.japi.pf.ReceiveBuilder;
import akka.routing.SmallestMailboxPool;

import java.util.Map;

public class Kernel extends AbstractActor {

    @Override
    public Receive createReceive() {
        ActorRef storeRouter = getContext().actorOf(new SmallestMailboxPool(5)
                .props(Props.create(StoreActor.class)), "store");
        ActorRef executeRouter = getContext().actorOf(new SmallestMailboxPool(5)
                .props(Props.create(ExecuteActor.class)), "execute");
        return ReceiveBuilder.create()
                .match(Pair.class, pair -> {
                    storeRouter.tell(pair, ActorRef.noSender());
                    executeRouter.tell(pair, ActorRef.noSender());
                })
                .match(String.class, packageId -> {
                    storeRouter.tell(packageId, getSelf());
                })
                .match(Map.class, input -> {
                    getSender().tell(input, ActorRef.noSender());
                })
                .build();
    }
}
