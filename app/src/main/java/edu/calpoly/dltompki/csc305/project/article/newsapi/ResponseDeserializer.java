package edu.calpoly.dltompki.csc305.project.article.newsapi;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class ResponseDeserializer extends StdDeserializer<Response> {
    private final Logger log;

    /**
     * Provide a logger to this instance of the deserializer.
     *
     * @param log The Logger
     */
    public ResponseDeserializer(Logger log) {
        super((Class<?>) null);
        this.log = log;
    }

    /**
     * Parse a Response from some JSON
     *
     * @param jsonParser              The current state of the parsing process
     * @param _deserializationContext Unused
     * @return A fully parsed Response object.
     * @throws IOException Something went wrong inside readTree
     */
    @Override
    public Response deserialize(JsonParser jsonParser, DeserializationContext _deserializationContext) throws IOException {
        Response response = new Response();
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        response.setStatus(node.get("status").asText());
        response.setTotalResults(node.get("totalResults").asInt());
        List<Article> articles = new ArrayList<>();
        node.get("articles").elements().forEachRemaining(articleJson -> {
            Article article;
            try {
                article = jsonParser.getCodec().treeToValue(articleJson, Article.class);
            } catch (JsonProcessingException e) {
                log.warning("failed to parse: " + articleJson.toString() + "\nwith exception: " + e.getMessage());
                return;
            }
            articles.add(article);
        });
        response.setArticles(articles);
        return response;
    }
}
