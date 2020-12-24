package ru.bmstu.akka;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class PackageDefinition {
    private final String PACKAGE_ID_PATH = "packageId", JS_SCRIPT_PATH = "jsScript",
            FUNCTION_NAME_PATH = "functionName", TESTS_PATH = "tests";

    private final String packageId;
    private final String jsScript;
    private final String functionName;
    private final List<TestsDefinition> tests;

    public PackageDefinition(@JsonProperty(PACKAGE_ID_PATH) String packageId,
                             @JsonProperty(JS_SCRIPT_PATH) String jsScript,
                             @JsonProperty(FUNCTION_NAME_PATH) String functionName,
                             @JsonProperty(TESTS_PATH) List<TestsDefinition> tests) {
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
