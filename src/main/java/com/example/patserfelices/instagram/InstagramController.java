package com.example.patserfelices.instagram;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.List;

@RestController
public class InstagramController {

    private RestTemplate restTemplate;

    public InstagramController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/candidates")
    public List<String> getMedia() throws IOException {
       Data data = this.restTemplate.getForObject("https://api.instagram.com/v1/users/self/media/recent/?access_token=836526229.f5f15b4.f01bab538e8245afb3c5cc02a2ffd30d", Data.class);
        assert data != null;
        return data.getLinks();

    }
}
