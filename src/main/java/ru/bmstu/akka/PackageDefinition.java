package ru.bmstu.akka;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;

import java.util.List;

public class PackageDefinition {
    private final String packageID;
    private final String jsScript;
    private final String functionName;
    private final List<TestsDefinition> tests;

    public PackageDefinition(@JsonProperty("packageID") String packageID,
                             @JsonProperty("jsScript") String jsScript,
                             @JsonProperty("functionName") String functionName,
                             @JsonProperty("tests") List<TestsDefinition> tests) {
        this.packageID = packageID;
        this.jsScript = jsScript;
        this.functionName = functionName;
        this.tests = tests;
    }

    public String getPackageID() {
        return packageID;
    }

    public String getJsScript() {
        return jsScript;
    }

    public String getFunctionName() {
        return functionName;
    }

    public List<TestsDefinition> getTests() {
        return tests;
    }
}
