package org.mystore.controller;

import org.mystore.helper.JwtTokenGenerator;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

     @PostMapping("/login")
        public String login(@RequestParam("phone") String phone, @RequestParam("password") String password) {
            // Implement the login logic here, using the email and password provided in the request.
            if(phone.equals("7319") && password.equals("1234"))
            {
                String jwtToken=JwtTokenGenerator.jwtToken(phone);
                return jwtToken;
            }
            return "Invalid Credential";
        }

    @GetMapping("/hello")
    public String hello() {
        return "Hello World!";
    }

}


