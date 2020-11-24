package ru.bmstu.akka;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.japi.pf.ReceiveBuilder;
import akka.routing.SmallestMailboxPool;

import java.util.List;
import java.util.Map;

public class Kernel extends AbstractActor {
    Map<String, Map<String, List<String>>> results;

    @Override
    public Receive createReceive() {
        ActorRef storeRouter = getContext().actorOf(new SmallestMailboxPool(5)
                .props(Props.create(StoreActor.class)), "store");
        ActorRef executeRouter = getContext().actorOf(new SmallestMailboxPool(5)
                .props(Props.create(ExecuteActor.class)), "execute");
        return ReceiveBuilder.create()
                .match(PackageDefinition.class, pack -> {
                    storeRouter.tell(pack, getSelf());
                })
                .match(String.class, packageID -> {
                    executeRouter.tell(packageID, getSelf());
                })
                .build();
    }
}
