package ru.bmstu.akka;

import akka.actor.ActorSystem;
import akka.http.javadsl.Http;
import akka.stream.ActorMaterializer;

public class RouteTree {
    public static void main(String[] args) {
        ActorSystem system = ActorSystem.create("routes");
        final ActorMaterializer materializer = ActorMaterializer.create(system);
    }
}
