package com.upcns.demo.boundedContext.article.controller;

import com.upcns.demo.boundedContext.article.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
@RequestMapping("/article")
public class ArticleController {

    private final ArticleService articleService;
    @GetMapping("/add")
    @ResponseBody
    public String add(String title, String content) {
        articleService.add(title,content);
        return "입력 완료 : %s : %s".formatted(title,content);
    }

}
