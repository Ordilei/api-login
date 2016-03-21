package br.com.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    private static final String template = "Hello, %s!";
    private static final String counter = "Hello, %s!";

    @RequestMapping("/login")
    public LoginController login(@RequestParam(value="name", defaultValue="World") String name, String passwd) {
        return new LoginController();
    }
}
