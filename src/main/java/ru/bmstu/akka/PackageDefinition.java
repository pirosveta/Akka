package ru.bmstu.akka;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;

import java.util.List;

public class PackageDefinition {
    private final String packageID;
    private final String jsScript;
    private final String functionName;
    private final List<JsonNode> tests;

    public PackageDefinition(@JsonProperty("packageID") String packageID,
                          @JsonProperty("jsScript") String jsScript,
                          @JsonProperty("functionName") String functionName,
                          @JsonProperty("tests") List<JsonNode> tests) {
        this.packageID = packageID;
        this.jsScript = jsScript;
        this.functionName = functionName;
        this.tests = tests;
    }

    public String getPath() {
        return path;
    }

    public List<JsonNode> getRequests() {
        return requests;
    }

    public List<JsonNode> getResponses() {
        return responses;
    }
}
