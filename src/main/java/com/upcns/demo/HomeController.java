package com.upcns.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {
    private int counter ;
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
    @GetMapping("/plus")
    @ResponseBody
    public int plus(){
        counter++;
        return counter;
    }
    @GetMapping("/plus2")
    @ResponseBody
    //public int plus2(int a, int b ){ //b가 파라메터로 안오면 에러
    //http://localhost:8080/plus2?a=1&b=20
    //http://localhost:8080/plus2?a=1
    public int plus2(int a,@RequestParam(defaultValue = "0") int b ){
        System.out.println("b:" + b);
        return a+b;
    }
}
