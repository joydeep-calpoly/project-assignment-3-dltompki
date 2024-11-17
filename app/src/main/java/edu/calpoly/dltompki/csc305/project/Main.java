package edu.calpoly.dltompki.csc305.project;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import edu.calpoly.dltompki.csc305.project.article.Parser;
import edu.calpoly.dltompki.csc305.project.article.Simple;
import edu.calpoly.dltompki.csc305.project.article.newsapi.Article;
import edu.calpoly.dltompki.csc305.project.article.newsapi.Response;
import edu.calpoly.dltompki.csc305.project.article.newsapi.ResponseDeserializer;
import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Main {
    private static final Parser parser = createParser();

    /**
     * Parse the two input files provided by the assignment and the NewsAPI endpoint specified by the assignment.
     *
     * @param args One argument expected; NewsAPI API key
     */
    public static void main(String[] args) {
        System.out.println("=== NewsAPI JSON File ===");
        try {
            Response response = parser.parseFromFileName("newsapi.json", Response.class);
            List<Article> articles = response.getArticles();
            articles.forEach(System.out::println);
        } catch (Parser.ParserException e) {
            System.err.println("exception thrown while parsing:");
            System.err.println(e);
        }

        System.out.println("=== Simple JSON File ===");
        try {
            Simple simple = parser.parseFromFileName("simple.json", Simple.class);
            System.out.println(simple);
        } catch (Parser.ParserException e) {
            System.err.println("exception thrown while parsing:");
            System.err.println(e);
        }

        System.out.println("=== NewsAPI US Headlines Endpoint ===");
        HttpURLConnection conn;
        int responseCode;
        try {
            URL url = new URL("https://newsapi.org/v2/top-headlines?country=us&apiKey=" + args[0]);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            responseCode = conn.getResponseCode();
        } catch (IOException e) {
            System.err.println("exception thrown while getting endpoint");
            System.err.println(e);
            return;
        }
        if (responseCode != 200) {
            System.out.println("Error: " + responseCode);
            return;
        }
        String line;
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            line = reader.readLine();
        } catch (IOException e) {
            System.err.println("exception thrown while reading endpoint response");
            System.err.println(e);
            return;
        }
        try {
            Response response = parser.parseFromString(Response.class, line);
            response.getArticles().forEach(System.out::println);
        } catch (Parser.ParserException e) {
            System.err.println("exception thrown while parsing:");
            System.err.println(e);
        }
    }

    @SneakyThrows
    private static Parser createParser() {
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
        return new Parser(mapper);
    }
}
