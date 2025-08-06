package com.upcns.demo.boundedContext.article.service;

import com.upcns.demo.boundedContext.article.entity.Article;
import com.upcns.demo.boundedContext.article.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;
    public void add(String title, String content) {
        Article article = Article.builder()
                .title(title)
                .content(content)
//                .created(LocalDateTime.now())
//                .modified(LocalDateTime.now())
                .build();
        articleRepository.save(article);
    }

}
