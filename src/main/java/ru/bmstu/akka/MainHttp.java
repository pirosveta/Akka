package ru.bmstu.akka;

import akka.NotUsed;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.http.javadsl.ConnectHttp;
import akka.http.javadsl.Http;
import akka.http.javadsl.ServerBinding;
import akka.http.javadsl.marshallers.jackson.Jackson;
import akka.http.javadsl.model.HttpRequest;
import akka.http.javadsl.model.HttpResponse;
import akka.http.javadsl.server.AllDirectives;
import akka.http.javadsl.server.Route;
import akka.japi.Pair;
import akka.pattern.Patterns;
import akka.stream.ActorMaterializer;
import akka.stream.javadsl.Flow;
import scala.concurrent.Future;

import java.util.concurrent.CompletionStage;

public class MainHttp extends AllDirectives {

    public static void main(String[] args) throws Exception {
        ActorSystem system = ActorSystem.create("routes");
        final Http http = Http.get(system);
        final ActorMaterializer materializer = ActorMaterializer.create(system);
        MainHttp instance = new MainHttp();
        final Flow<HttpRequest, HttpResponse, NotUsed> routeFlow =
                instance.createRoute(system).flow(system, materializer);
        final CompletionStage<ServerBinding> binding = http.bindAndHandle(
            routeFlow,
            ConnectHttp.toHost("localhost", 8080),
            materializer
        );
        System.out.println("Server online at http://localhost:8080/\nPress RETURN to stop...");
        System.in.read();
        binding
            .thenCompose(ServerBinding::unbind)
            .thenAccept(unbound -> system.terminate());
    }

    private Route createRoute(ActorSystem system) {
        ActorRef kernel = system.actorOf(Props.create(Kernel.class));
        return route(
                post(() ->
                    entity(Jackson.unmarshaller(PackageDefinition.class), pack ->
                            pack.getTests(Jackson.unmarshaller(TestsDefinition.class), tests -> {
                                Pair<PackageDefinition, TestsDefinition> pair = (Pair<PackageDefinition, TestsDefinition>) new Pair<>(pack, tests);
                                kernel.tell(pair, ActorRef.noSender());
                                return complete("Tests started");
                            }))
                ),
                get(() ->
                    parameter("packageID", (packageID) -> {
                        Future<Object> result = Patterns.ask(kernel, packageID, 5000);
                        return completeOKWithFuture(result, Jackson.marshaller());
                    })
                ));
    }
}
