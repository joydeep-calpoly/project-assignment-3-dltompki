package edu.calpoly.dltompki.csc305.project.article;

import edu.calpoly.dltompki.csc305.project.source.Visitor;

public interface Parser<T> {
    /**
     * Parse a T from a file, given its name
     *
     * @param filename Name of the file to be parsed, relative to the resources directory
     * @return a T, if parsing is successful
     * @throws GenericParser.ParserException if parsing fails for any reason
     */
    T parseFromFileName(String filename) throws GenericParser.ParserException;

    /**
     * Parse a T from the HTTP GET response of the provided URL
     *
     * @param urlString The URL to make a HTTP GET call on
     * @return a T, if parsing is successful
     * @throws GenericParser.ParserException if parsing fails for any reason
     */
    T parseFromUrl(String urlString) throws GenericParser.ParserException;

    /**
     * Provide a Visitor pattern API for parsing
     *
     * @param v The source to parse, wrapped in a Visitor of the appropriate type
     * @return a T, if parsing is successful
     * @throws GenericParser.ParserException if parsing fails for any reason
     */
    T accept(Visitor v) throws GenericParser.ParserException;
}
