package ru.bmstu.akka;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;

import java.util.List;

public class TestsDefinition {
    private final String testName;
    private final String expectedResult;
    private final List<JsonNode> params;

    public TestsDefinition(@JsonProperty("testName") String testName,
                             @JsonProperty("expectedResult") String expectedResult,
                             @JsonProperty("params") List<JsonNode> params) {
        this.testName = testName;
        this.expectedResult = expectedResult;
        this.params = params;
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
