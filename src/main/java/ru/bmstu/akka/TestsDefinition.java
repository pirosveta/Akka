package ru.bmstu.akka;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;

import java.util.List;

public class TestsDefinition {
    private final String testName;
    private final String expectedResult;
    private final List<JsonNode> params;

    public PackageDefinition(@JsonProperty("packageID") String packageID,
                             @JsonProperty("jsScript") String jsScript,
                             @JsonProperty("functionName") String functionName,
                             @JsonProperty("tests") List<JsonNode> tests) {
        this.packageID = packageID;
        this.jsScript = jsScript;
        this.functionName = functionName;
        this.tests = tests;
    }
}
