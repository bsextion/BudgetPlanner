package com.cal.budget.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @RequestMapping(value = "/home", produces = "text/plan", method = RequestMethod.GET)
    public ResponseEntity<String> get() {
        return ResponseEntity.ok("Hello World!");
    }
}
