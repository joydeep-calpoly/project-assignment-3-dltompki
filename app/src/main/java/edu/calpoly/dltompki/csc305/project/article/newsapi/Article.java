package edu.calpoly.dltompki.csc305.project.article.newsapi;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import edu.calpoly.dltompki.csc305.project.article.Base;
import edu.calpoly.dltompki.csc305.project.article.OutputUtil;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

@Value
@Builder
@JsonDeserialize(builder = Article.ArticleBuilder.class)
public class Article implements Base {
    /*
     * Builder knows about the NotNull annotation and will throw an exception if those fields are null when the build
     * method is called
     */
    @NonNull
    String title;

    @NonNull
    String description;

    @NonNull
    String url;

    @NonNull
    String publishedAt;

    String urlToImage;

    Source source;

    String author;

    String content;

    /**
     * Override toString to conform to format described in project spec. See root-level README for format spec.
     *
     * @return a multi-line string containing title, description, publishedAt, and url
     */
    @Override
    public String toString() {
        return OutputUtil.articleToString(this);
    }

    @Value
    @Builder
    @JsonDeserialize(builder = Source.SourceBuilder.class)
    static class Source {
        String id;
        String name;

        @JsonPOJOBuilder(withPrefix = "")
        static class SourceBuilder {
        }
    }

    // tells jackson about the builder
    @JsonPOJOBuilder(withPrefix = "")
    static class ArticleBuilder {
    }
}
