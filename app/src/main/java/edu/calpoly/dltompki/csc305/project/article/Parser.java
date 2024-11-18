package edu.calpoly.dltompki.csc305.project.article;

import edu.calpoly.dltompki.csc305.project.source.Visitor;

public interface Parser<T> {
    T parseFromFileName(String filename) throws GenericParser.ParserException;

    T parseFromUrl(String urlString) throws GenericParser.ParserException;

    T accept(Visitor v) throws GenericParser.ParserException;
}
