package com.example.travelagency.web;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@Controller
public class MainController {

    private static final Logger LOGGER = LoggerFactory.getLogger(MainController.class);

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("common/complete")
    public String complete() {
        return "common/complete";
    }


    @GetMapping("/predict")
    public String predict() {
        return "test";
    }

    // python-django api test
    @PostMapping("/predict")
    public String predict(@RequestParam double x, Model model) throws Exception {
        String djangoUrl = "http://localhost:8000/api/predict/";

        JSONObject json = new JSONObject();
        json.put("x", x);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(json.toString(), headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.postForEntity(djangoUrl, entity, String.class);

        JSONObject result = new JSONObject(response.getBody());
        model.addAttribute("result", result.getDouble("prediction"));;

        return "/test";
    }

}
