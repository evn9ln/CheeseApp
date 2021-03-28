package org.cheeseapp;

import org.springframework.web.bind.annotation.GetMapping;


@org.springframework.stereotype.Controller
public class Controller {
    @GetMapping("/hello-world")
    public String sayHello() {
        return "hello_world";
    }

}
