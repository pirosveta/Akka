package ru.bmstu.akka;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.japi.Pair;
import akka.japi.pf.ReceiveBuilder;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

public class ExecuteActor extends AbstractActor {
    @Override
    public Receive createReceive() {
        return ReceiveBuilder.create().matchAny(input -> {
            Pair<PackageDefinition, TestsDefinition> pair = (Pair<PackageDefinition, TestsDefinition>) input;
            PackageDefinition pack = pair.first();
            TestsDefinition test = pair.second();
            ScriptEngine engine = new ScriptEngineManager().getEngineByName("executeTests");
            engine.eval(pack.getJsScript());
            Invocable invocable = (Invocable) engine;
            String result = invocable.invokeFunction(pack.getFunctionName(), test.getParams().toArray()).toString();
            String[] output = {pack.getPackageId(), test.getTestName(), result};
            System.out.println("EXECUTE:");
            System.out.println(output);
            getSender().tell(output, ActorRef.noSender());
        })
        .build();
    }
}
