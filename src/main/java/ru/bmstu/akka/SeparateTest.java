package ru.bmstu.akka;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.List;

public class SeparateTest {
    private final String packageId;
    private final String jsScript;
    private final String functionName;
    private final String testName;
    private final String expectedResult;
    private final List<JsonNode> params;

    public SeparateTest(PackageDefinition pack, TestsDefinition test) {
        this.packageId = pack.getPackageId();
        this.jsScript = pack.getJsScript();
        this.functionName = pack.getFunctionName();
        this.testName = test.getTestName();
        this.expectedResult = test.getExpectedResult();
        this.params = test.getParams();
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

    public String getTestName() {
        return testName;
    }

    public String getExpectedResult() {
        return expectedResult;
    }

    public List<JsonNode> getParams() {
        return params;
    }
}
