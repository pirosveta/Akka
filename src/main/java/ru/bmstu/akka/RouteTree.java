package ru.bmstu.akka;

import akka.actor.ActorSystem;
import akka.http.javadsl.Http;
import akka.stream.ActorMaterializer;

public class RouteTree {
    ActorSystem system = ActorSystem.create("routes");
    final Http http = Http.get(system);
    final ActorMaterializer materializer = ActorMaterializer.create(system);
    MainHttp instance = new MainHttp(system);
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
