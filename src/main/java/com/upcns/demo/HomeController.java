package com.upcns.demo;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

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


    @GetMapping("/mapList")
    @ResponseBody
    //
    public Map<String,Object> mapList(){
        Map<String,Object> map = new LinkedHashMap<>(){{
            put("james","test");
            put("james",new Car(1,"james","blue"));
            put("james2",new Car(2,"james2","red"));
            put("james3",new Car2(3,"james3","white"));
        }};
        return map;
    }

    class Car{
        private final int id;
        private final String name;
        private final String color;

        public Car(int id, String name, String color) {
            this.id = id;
            this.name = name;
            this.color = color;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getColor() {
            return color;
        }
    }
    @AllArgsConstructor
    @Getter
    //Lombok이 있으면 생략가능 (생성자,getter)
    class Car2{
        private final int id;
        private final String name;
        private final String color;

    }

    @GetMapping("/cookie/increase")
    @ResponseBody
    //
    public void cookieIncrease(HttpServletRequest req, HttpServletResponse resp){
        int count = 0;
        if(req.getCookies()!=null){
            count = Arrays.stream(req.getCookies())
                    .filter(cookie -> cookie.getName().equals("count"))
                    .map(cookie -> cookie.getValue())
                    .mapToInt(Integer::parseInt)
                    .findFirst()
                    .orElse(0);
        }
        resp.addCookie(new Cookie("count",String.valueOf(count+1)));
    }
}
