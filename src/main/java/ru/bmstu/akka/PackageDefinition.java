package ru.bmstu.akka;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;

import java.util.List;

public class PackageDefinition {
    private final String packageId;
    private final String jsScript;
    private final String functionName;
    private final List<JsonNode> tests;

    public PackageDefinition(@JsonProperty("packageId") String packageId,
                             @JsonProperty("jsScript") String jsScript,
                             @JsonProperty("functionName") String functionName,
                             @JsonProperty("tests") List<JsonNode> tests) {
        this.packageId = packageId;
        this.jsScript = jsScript;
        this.functionName = functionName;
        this.tests = tests;
    }

    public String getPackageId() {
        return packageId;
    }

    public String getJsScript() {
        return jsScript;
    }

    public String getFunctionName() {
        return functionName;
    }

    public List<JsonNode> getTests() {
        return tests;
    }
}
