package com.upcns.demo.boundedContext.member.service;


import com.upcns.demo.boundedContext.base.RsData;
import com.upcns.demo.boundedContext.member.entity.Member;
import com.upcns.demo.boundedContext.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

// @Component : 아래 클래스는 Ioc 컨테이너에 의해 생사소멸이 관리된다.
// @Service : @Component 와 같은 의미, 가독성 때문에 이렇게 표기
@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public RsData tryLogin(String username, String password) {
        Member member = memberRepository.findByUsername(username).orElse(null);

        if(member == null) {
            return RsData.of("F-2", "%s(은)는 존재하지 않는 회원입니다.".formatted(username));
        }

        if(!member.getPassword().equals(password)) {
            return RsData.of("F-1", "비밀번호가 일치하지 않습니다.");
        }

        return RsData.of("S-1", "%s 님 환영합니다.".formatted(username), member);
    }

    public Member findByUsername(String username) {
        return memberRepository.findByUsername(username).orElse(null);
    }

    public Member findById(Long id) {
        return memberRepository.findById(id).orElse(null);
    }

    public void join(String username, String password) {
        if(existsByUsername(username)) {
            return;
        }

        Member member = Member.builder()
                .username(username)
                .password(password)
                .build();

        memberRepository.save(member);
    }

    private boolean existsByUsername(String username) {
        return memberRepository.existsByUsername(username);
    }
}