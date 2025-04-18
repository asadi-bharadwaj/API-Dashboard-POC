package com.example.Dashboard_POC.service;


import com.example.Dashboard_POC.model.ApiInfo;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.InputStream;
import java.util.List;
import java.util.Optional;

@Service
public class ApiService {
    private List<ApiInfo> apiList;

    @PostConstruct
    public void loadApiList() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            InputStream is = getClass().getClassLoader().getResourceAsStream("apis.json");
            apiList = mapper.readValue(is, new TypeReference<List<ApiInfo>>() {});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<ApiInfo> getAllApis() {
        return apiList;
    }

    public Optional<ApiInfo> getApiById(String id) {
        return apiList.stream().filter(api -> api.getId().equals(id)).findFirst();
    }

    public ResponseEntity<String> invokeApi(ApiInfo apiInfo) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<?> request = (apiInfo.getRequestBody() != null)
                ? new HttpEntity<>(apiInfo.getRequestBody(), headers)
                : new HttpEntity<>(headers);

        try {
            if ("GET".equalsIgnoreCase(apiInfo.getMethod())) {
                return restTemplate.exchange(apiInfo.getUrl(), HttpMethod.GET, request, String.class);
            } else if ("POST".equalsIgnoreCase(apiInfo.getMethod())) {
                return restTemplate.exchange(apiInfo.getUrl(), HttpMethod.POST, request, String.class);
            } else {
                return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body("Unsupported Method");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }
}