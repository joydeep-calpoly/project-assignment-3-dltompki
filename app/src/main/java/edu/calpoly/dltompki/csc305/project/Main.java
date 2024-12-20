package edu.calpoly.dltompki.csc305.project;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import edu.calpoly.dltompki.csc305.project.article.GenericParser;
import edu.calpoly.dltompki.csc305.project.article.NewsApiParser;
import edu.calpoly.dltompki.csc305.project.article.Simple;
import edu.calpoly.dltompki.csc305.project.article.SimpleParser;
import edu.calpoly.dltompki.csc305.project.article.newsapi.Article;
import edu.calpoly.dltompki.csc305.project.article.newsapi.Response;
import edu.calpoly.dltompki.csc305.project.article.newsapi.ResponseDeserializer;
import edu.calpoly.dltompki.csc305.project.source.Url;
import lombok.SneakyThrows;

import java.io.File;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Main {
    private static final ObjectMapper om = createObjectMapper();

    /**
     * Parse the two input files provided by the assignment and the NewsAPI endpoint specified by the assignment.
     *
     * @param args One argument expected; NewsAPI API key
     */
    public static void main(String[] args) {
        System.out.println("=== NewsAPI JSON File ===");
        try {
            Response response =
                    new NewsApiParser(om).accept(new edu.calpoly.dltompki.csc305.project.source.File("newsapi.json"));
            List<Article> articles = response.getArticles();
            articles.forEach(System.out::println);
        } catch (GenericParser.ParserException e) {
            System.err.println("exception thrown while parsing:");
            System.err.println(e);
        }

        System.out.println("=== Simple JSON File ===");
        try {
            Simple simple =
                    new SimpleParser(om).accept(new edu.calpoly.dltompki.csc305.project.source.File("simple.json"));
            System.out.println(simple);
        } catch (GenericParser.ParserException e) {
            System.err.println("exception thrown while parsing:");
            System.err.println(e);
        }

        System.out.println("=== NewsAPI US Headlines Endpoint ===");
        String url = "https://newsapi.org/v2/top-headlines?country=us&apiKey=" + args[0];
        try {
            Response response = new NewsApiParser(om).accept(new Url(url));
            response.getArticles().forEach(System.out::println);
        } catch (GenericParser.ParserException e) {
            System.err.println("exception thrown while parsing:");
            System.err.println(e);
        }
    }

    @SneakyThrows
    private static ObjectMapper createObjectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        SimpleModule simpleModule = new SimpleModule();
        new File("build/logs").mkdirs();
        new File("build/logs/logs.out").createNewFile();
        Logger logger = Logger.getGlobal();
        FileHandler handler = new FileHandler("build/logs/logs.out");
        handler.setFormatter(new SimpleFormatter());
        logger.addHandler(handler);
        simpleModule.addDeserializer(Response.class, new ResponseDeserializer(logger));
        mapper.registerModule(simpleModule);
        return mapper;
    }
}
