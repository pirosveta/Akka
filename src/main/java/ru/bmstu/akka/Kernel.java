package ru.bmstu.akka;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.dispatch.OnComplete;
import akka.japi.Pair;
import akka.japi.pf.ReceiveBuilder;
import akka.pattern.Patterns;
import akka.routing.SmallestMailboxPool;
import scala.concurrent.Future;

import java.util.HashMap;

public class Kernel extends AbstractActor {

    @Override
    public Receive createReceive() {
        ActorRef storeRouter = getContext().actorOf(new SmallestMailboxPool(5)
                .props(Props.create(StoreActor.class)), "store");
        ActorRef executeRouter = getContext().actorOf(new SmallestMailboxPool(5)
                .props(Props.create(ExecuteActor.class)), "execute");
        ActorRef mainActor;
        return ReceiveBuilder.create()
                .match(Pair.class, pair -> {
                    storeRouter.tell(pair, ActorRef.noSender());
                    executeRouter.tell(pair, getSelf());
                })
                .match(String[].class, input -> {
                    storeRouter.tell(input, ActorRef.noSender());
                })
                .match(String.class, packageId -> {
                    Future<Object> result = Patterns.ask(storeRouter, packageId, 5000);
                    result.onComplete(new OnComplete<Object>() {
                        @Override
                        public void onComplete(Throwable failure, Object success) throws Throwable {
                            
                        }
                    });
                    getSender().tell(result, ActorRef.noSender());
                })
                .build();
    }
}
