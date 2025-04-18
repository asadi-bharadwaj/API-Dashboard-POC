package com.example.Dashboard_POC.model;

import java.util.Map;

public class ApiInfo {
    private String id;
    private String service;
    private String method;
    private String url;
    private String description;
    private Map<String, Object> requestBody;

    // Getters and setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getService() { return service; }
    public void setService(String service) { this.service = service; }

    public String getMethod() { return method; }
    public void setMethod(String method) { this.method = method; }

    public String getUrl() { return url; }
    public void setUrl(String url) { this.url = url; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Map<String, Object> getRequestBody() { return requestBody; }
    public void setRequestBody(Map<String, Object> requestBody) { this.requestBody = requestBody; }
}
