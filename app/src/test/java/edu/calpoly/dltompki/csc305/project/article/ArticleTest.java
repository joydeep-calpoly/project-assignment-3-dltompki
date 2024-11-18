package edu.calpoly.dltompki.csc305.project.article;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import edu.calpoly.dltompki.csc305.project.article.newsapi.Article;
import edu.calpoly.dltompki.csc305.project.article.newsapi.Response;
import edu.calpoly.dltompki.csc305.project.article.newsapi.ResponseDeserializer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ArticleTest {
    @Mock
    Logger logger;
    private GenericParser parser;

    /**
     * Instantiate a new parser for each test
     */
    @BeforeEach
    void setUp() {
        parser = new GenericParser(new ObjectMapper()
                .enable(DeserializationFeature.FAIL_ON_MISSING_CREATOR_PROPERTIES)
                .registerModule(new SimpleModule().addDeserializer(Response.class, new ResponseDeserializer(logger))));
    }

    /**
     * Valid example input from assignment 1
     */
    @Test
    void example() {
        Response response = parseFromFileNameOrFail(TestFiles.EXAMPLE.getName(), Response.class);
        assertNotNull(response);
        List<Article> articles = response.getArticles();
        assertNotNull(articles);
        assertEquals(20, articles.size());
        assertEquals(
                "Slack Connect Now Lets You DM Anyone. So Long, Work-Life Balance - " + "WIRED",
                articles.get(12).getTitle());
    }

    /**
     * Example input with some bad articles from assignment 1
     */
    @Test
    void bad() {
        Response response = parseFromFileNameOrFail(TestFiles.BAD.getName(), Response.class);
        assertNotNull(response);
        List<Article> articles = response.getArticles();
        assertNotNull(articles);
        articles.forEach(article -> {
            assertNotNull(article.getTitle(), "title");
            assertNotNull(article.getDescription(), "description");
            assertNotNull(article.getUrl(), "url");
            assertNotNull(article.getPublishedAt(), "publishedAt");
        });
        verify(logger, times(3)).warning(anyString());
    }

    /**
     * A single valid article
     */
    @Test
    void valid() {
        Response response = parseFromFileNameOrFail(TestFiles.VALID.getName(), Response.class);
        assertNotNull(response);
        List<Article> articles = response.getArticles();
        assertNotNull(articles);
        assertEquals(1, articles.size());
        Article article = articles.getFirst();
        assertEquals("The latest on the coronavirus pandemic and vaccines: Live updates - CNN", article.getTitle());
        assertEquals(
                "The coronavirus pandemic has brought countries to a standstill. Meanwhile, vaccinations have " + "already started in some countries as cases continue to rise. Follow here for the latest.",
                article.getDescription());
        assertEquals("2021-03-24T22:32:00Z", article.getPublishedAt());
        assertEquals(
                "https://www.cnn.com/world/live-news/coronavirus-pandemic-vaccine-updates-03-24-21/index.html",
                article.getUrl());
    }

    /**
     * A single article with a single required attribute missing
     *
     * @param fileName A file for each required attribute
     */
    @ParameterizedTest
    @ValueSource(strings = {"description", "publishedAt", "title", "url"})
    void missing(final String fileName) {
        Response response = parseFromFileNameOrFail("missing/" + fileName + ".json", Response.class);
        assertNotNull(response);
        assertTrue(response.getArticles().isEmpty());
    }

    /**
     * Example input provided with assignment 2 for valid simple format
     */
    @Test
    void simple() {
        Simple simple = parseFromFileNameOrFail(TestFiles.SIMPLE.getName(), Simple.class);
        assertNotNull(simple);
        assertEquals(
                "Extend Assignment #1 to support multiple sources and to introduce source processor.",
                simple.getDescription());
        assertEquals("2021-04-16 09:53:23.709229", simple.getPublishedAt());
        assertEquals("Assignment #2", simple.getTitle());
        assertEquals("https://canvas.calpoly.edu/courses/55411/assignments/274503", simple.getUrl());
    }

    /**
     * The simple format with each of the fields missing.
     *
     * @param fileName A file for each required attribute
     */
    @ParameterizedTest
    @ValueSource(strings = {"description", "publishedAt", "title", "url"})
    void simpleMissing(final String fileName) {
        assertThrows(GenericParser.ParserException.class, () -> {
            parser.parseFromFileName("simpleMissing/" + fileName + ".json", Simple.class);
        });
    }

    private <T> T parseFromFileNameOrFail(String s, Class<T> clazz) {
        T result = null;
        try {
            result = parser.parseFromFileName(s, clazz);
        } catch (GenericParser.ParserException e) {
            fail();
        }
        return result;
    }
}