package ru.bmstu.akka;

import akka.actor.ActorSystem;
import akka.http.javadsl.Http;
import akka.http.javadsl.server.Route;
import akka.stream.ActorMaterializer;

public class RouteTree {
    public static void main(String[] args) {
        ActorSystem system = ActorSystem.create("routes");
        final ActorMaterializer materializer = ActorMaterializer.create(system);

        Route route = path("semaphore", () ->
                route(
                        get( () -> {
                            Future<Object> result = Patterns.ask(testPackageActor,
                                    SemaphoreActor.makeRequest(), 5000);
                            return completeOKWithFuture(result, Jackson.marshaller());
                        }))),
        path("test", () ->
                route(
                        post(() ->
                                entity(Jackson.unmarshaller(TestPackageMsg.class), msg -> {
                                    testPackageActor.tell(msg, ActorRef.noSender());
                                    return complete("Test started!");
                                })))),
                path("put", () ->
                        get(() ->
                                parameter("key", (key) ->
                                        parameter("value", (value) ->
                                        {
                                            storeActor.tell(new StoreActor.StoreMessage(key, value), ActorRef.noSender());
                                            return complete("value saved to store ! key=" + key + " value=" + value);
                                        }))));
    }
}
