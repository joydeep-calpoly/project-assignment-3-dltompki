package edu.calpoly.dltompki.csc305.project.article;

public interface Parser<T> {
    T parseFromFileName(String filename) throws GenericParser.ParserException;

    T parseFromUrl(String urlString) throws GenericParser.ParserException;
}
