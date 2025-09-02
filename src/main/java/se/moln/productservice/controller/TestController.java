package se.moln.productservice.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/api/test/hello")
    public String hello() {
        return "Hello from Product Service!";
    }

    @GetMapping("/api/test/ping")
    public String ping() {
        return "pong";
    }
}
