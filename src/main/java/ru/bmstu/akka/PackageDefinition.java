package ru.bmstu.akka;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class PackageDefinition {
    private final String packageId;
    private final String jsScript;
    private final String functionName;
    private final List<TestsDefinition> tests;

    public PackageDefinition(@JsonProperty("packageId") String packageId,
                             @JsonProperty("jsScript") String jsScript,
                             @JsonProperty("functionName") String functionName,
                             @JsonProperty("tests") List<TestsDefinition> tests) {
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

    public List<TestsDefinition> getTests() {
        return tests;
    }
}
