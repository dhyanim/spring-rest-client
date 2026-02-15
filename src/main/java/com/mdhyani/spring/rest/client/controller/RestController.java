package com.mdhyani.spring.rest.client.controller;

import com.mdhyani.spring.rest.client.dto.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestClient;

@Controller
@RequiredArgsConstructor
@Slf4j
public class RestController {

    private final RestClient restClient;

    @GetMapping("/userdata")
    public ResponseEntity<User> getUserById() {
        User user = restClient.get()
                .uri("/v1/user/{userId}", "abc")
                .retrieve().body(User.class);
        log.info("User data retrieved {} ", user);
        return ResponseEntity.ok(user);
    }
}
