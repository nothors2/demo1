package com.upcns.demo.boundedContext.base.initDate;


import com.upcns.demo.boundedContext.article.service.ArticleService;
import com.upcns.demo.boundedContext.member.service.MemberService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile({"local", "test"})
// dev : 개발 환경
// test : 테스트 환경
// prod : 운영 환경
// NotProd : 운영 환경이 아닌 경우에만 실행
public class NotProd {
    @Bean
    CommandLineRunner initData(MemberService memberService, ArticleService articleService) {
        return args -> {
            // 여기에 초기 데이터를 삽입하는 코드를 작성합니다.
            memberService.join("user1", "1234");
            memberService.join("bbc", "1111");
            memberService.join("love", "5555");
            memberService.join("hello", "123478");
            memberService.join("user2", "12596");
            memberService.join("user3", "33333");
            memberService.join("good", "456732");
            memberService.join("test", "1234");
            memberService.join("test2", "5678");
            memberService.join("test3", "91011");

            articleService.add("제목1", "내용1");
            articleService.add("제목2", "내용2");
        };
    }
}