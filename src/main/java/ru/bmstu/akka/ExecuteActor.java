package ru.bmstu.akka;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.japi.Pair;
import akka.japi.pf.ReceiveBuilder;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class ExecuteActor extends AbstractActor {
    private final String ENGINE_NAME = "nashorn";

    @Override
    public Receive createReceive() {
        return ReceiveBuilder.create().matchAny(input -> {
            Pair<PackageDefinition, TestsDefinition> pair = (Pair<PackageDefinition, TestsDefinition>) input;
            PackageDefinition pack = pair.first();
            TestsDefinition test = pair.second();
            ScriptEngine engine = new ScriptEngineManager().getEngineByName(ENGINE_NAME);
            engine.eval(pack.getJsScript());
            Invocable invocable = (Invocable) engine;
            String result = invocable.invokeFunction(pack.getFunctionName(), test.getParams().toArray()).toString();
            String[] output = {pack.getPackageId(), test.getTestName(), result};
            getSender().tell(output, ActorRef.noSender());
        })
        .build();
    }
}
