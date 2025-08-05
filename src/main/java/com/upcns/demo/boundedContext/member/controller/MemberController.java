package com.upcns.demo.boundedContext.member.controller;

import com.upcns.demo.boundedContext.base.Rq;
import com.upcns.demo.boundedContext.base.RsData;
import com.upcns.demo.boundedContext.member.entity.Member;
import com.upcns.demo.boundedContext.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService;
    private final Rq rq;

    @GetMapping("/join")
    public String join() {
        return "usr/member/join";
    }

    @PostMapping("/join")
    @ResponseBody
    public RsData join(String username, String password) {
        if (username == null || username.isBlank()) {
            return RsData.of("F-1", "아이디를 입력해주세요.");
        }

        if (password == null || password.isBlank()) {
            return RsData.of("F-2", "비밀번호를 입력해주세요.");
        }

        if (memberService.findByUsername(username) != null) {
            return RsData.of("F-3", "%s(은)는 이미 존재하는 아이디입니다.".formatted(username));
        }

        memberService.join(username, password);

        return RsData.of("S-1", "%s 님 회원가입이 완료되었습니다.".formatted(username));
    }

    @GetMapping("/login")
    public String login() {
        return "usr/member/login";
    }

    @PostMapping("/login")
    @ResponseBody
    public RsData login(String username, String password) {

        if (username == null || username.isBlank()) {
            return RsData.of("F-1", "아이디를 입력해주세요.");
        }

        if (password == null || password.isBlank()) {
            return RsData.of("F-2", "비밀번호를 입력해주세요.");
        }

        RsData rsData = memberService.tryLogin(username, password);

        if (rsData.isSuccess()) {
            Member member = (Member) rsData.getData();
            rq.setSession("loginedMemberId", member.getId());
        }

        return rsData;
    }

    @GetMapping("/logout")
    @ResponseBody
    public RsData logout() {
        boolean cookieRemoved = rq.removeSession("loginedMemberId");

        if (!cookieRemoved) {
            return RsData.of("F-1", "로그아웃에 실패하였습니다. 이미 로그아웃 상태입니다.");
        }

        return RsData.of("S-1", "로그아웃 되었습니다.");
    }

    @GetMapping("/me")
    public String showMe(Model model) {
        long loginedMemberId = rq.getLoginedMember();

        Member member = memberService.findById(loginedMemberId);
        model.addAttribute("member", member);

        return "usr/member/me";
    }

    @GetMapping("/session")
    @ResponseBody
    public String showSession() {
        return rq.getSessionDebugInfo().replaceAll("\n", "<br>");
    }
}