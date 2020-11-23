package ru.bmstu.akka;

public class PackageDefinition {
    private final String packageID;
    private final List<JsonNode> jsScript;
    private final List<JsonNode> functionName;

    public MockDefinition(@JsonProperty("path") String path,
                          @JsonProperty("requests") List<JsonNode> requests,
                          @JsonProperty("responses") List<JsonNode> responses) {
        this.path = path;
        this.requests = requests;
        this.responses = responses;
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
