package edu.calpoly.dltompki.csc305.project.article;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

@Value
@Builder
@JsonDeserialize(builder = Simple.SimpleBuilder.class)
public class Simple implements Base {
    @NonNull
    String description;
    @NonNull
    String publishedAt;
    @NonNull
    String title;
    @NonNull
    String url;

    /**
     * Override toString to conform to format described in project spec. See root-level README for format spec.
     *
     * @return a multi-line string containing title, description, publishedAt, and url
     */
    @Override
    public String toString() {
        return OutputUtil.articleToString(this);
    }

    @JsonPOJOBuilder(withPrefix = "")
    static class SimpleBuilder {
    }
}
