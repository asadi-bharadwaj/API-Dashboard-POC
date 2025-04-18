package com.example.Dashboard_POC.controller;


import com.example.Dashboard_POC.model.ApiInfo;
import com.example.Dashboard_POC.service.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/apis")
public class ApiController {

    @Autowired
    private ApiService apiService;

    @GetMapping
    public List<ApiInfo> getAllApis() {
        return apiService.getAllApis();
    }

    @PostMapping("/invoke/{id}")
    public ResponseEntity<String> invokeApi(@PathVariable String id) {
        return apiService.getApiById(id)
                .map(apiService::invokeApi)
                .orElse(ResponseEntity.notFound().build());
    }
}