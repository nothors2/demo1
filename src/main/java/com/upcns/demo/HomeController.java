package com.upcns.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {

    @GetMapping("/home")
    @ResponseBody
    public String home(){
        return "Welcome to Spring MVC";
    }
    @GetMapping("/bye")
    @ResponseBody
    public String bye(){
        return "bye";
    }
}
