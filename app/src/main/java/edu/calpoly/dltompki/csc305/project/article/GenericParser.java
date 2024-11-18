package edu.calpoly.dltompki.csc305.project.article;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

@RequiredArgsConstructor
public class GenericParser {
    private final ObjectMapper mapper;

    /**
     * Parse the specified class from a file in the "resources" directory.
     *
     * @param fileName Path to file relative to "resources" directory
     * @param clazz    The class to parse from the specified file
     * @param <T>      The type of the class passed
     * @return The object parsed from the file
     * @throws ParserException if something goes wrong during parsing
     */
    public <T> T parseFromFileName(String fileName, Class<T> clazz) throws ParserException {
        String fileContent = null;
        try {
            String stringPath = Objects.requireNonNull(clazz.getClassLoader().getResource(fileName)).getFile();
            Path path = Paths.get(stringPath);
            byte[] bytes = Files.readAllBytes(path);
            fileContent = new String(bytes);
        } catch (IOException e) {
            throw new ParserException("failed to get file content", e);
        }
        return parseFromString(clazz, fileContent);
    }

    /**
     * Parse the specified class from a String.
     *
     * @param clazz   The class to parse from the specified file
     * @param content The data from which to parse the class
     * @param <T>     The type of the class passed
     * @return The object parsed from the file
     * @throws ParserException if something goes wrong during parsing
     */
    public <T> T parseFromString(Class<T> clazz, String content) throws ParserException {
        T response;
        try {
            response = mapper.readValue(content, clazz);
        } catch (JsonProcessingException e) {
            throw new ParserException("failed to parser json", e);
        }
        return response;
    }

    public <T> T parseFromUrl(String urlString, Class<T> clazz) throws ParserException {
        HttpURLConnection conn;
        int responseCode;
        try {
            URL url = new URL(urlString);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            responseCode = conn.getResponseCode();
        } catch (IOException e) {
            throw new ParserException("exception thrown while getting endpoint", e);
        }
        if (responseCode != 200) {
            throw new ParserException("HTTP Error: " + responseCode, null);
        }
        String line;
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            line = reader.readLine();
        } catch (IOException e) {
            throw new ParserException("exception thrown while reading endpoint response", e);
        }
        T out;
        try {
            out = parseFromString(clazz, line);
        } catch (GenericParser.ParserException e) {
            throw new ParserException("exception thrown while parsing:", e);
        }
        return out;
    }

    public static class ParserException extends Exception {
        /**
         * Describe the problem occurred during parsing.
         *
         * @param message What happened
         * @param cause   Why it happened
         */
        ParserException(String message, Throwable cause) {
            super(message, cause);
        }
    }
}
