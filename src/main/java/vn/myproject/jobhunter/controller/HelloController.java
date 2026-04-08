package vn.myproject.jobhunter.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/")
    public ResponseEntity<Object> getHelloWorld() {
        return ResponseEntity.ok().body(null);
    }
}