package edu.calpoly.dltompki.csc305.project.article.newsapi;

import lombok.Data;

import java.util.List;

@Data
public class Response {
    String status;
    Integer totalResults;
    List<Article> articles;
}
