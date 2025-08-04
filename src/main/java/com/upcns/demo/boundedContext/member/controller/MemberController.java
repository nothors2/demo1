package com.upcns.demo.boundedContext.member.controller;

import com.upcns.demo.boundedContext.base.RsData;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MemberController {
    @GetMapping("/member/login")
    @ResponseBody
    public RsData memberLogin(String username,String password){
        return RsData.of("S-1","%s 님 환영합니다.".formatted(username));
    }
}
