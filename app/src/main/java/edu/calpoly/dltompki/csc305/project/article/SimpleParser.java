package edu.calpoly.dltompki.csc305.project.article;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.calpoly.dltompki.csc305.project.source.Visitor;

public class SimpleParser implements Parser<Simple> {
    private final GenericParser g;

    /**
     * Allow injection of ObjectMapper to enable the client to configure it beforehand.
     *
     * @param om The ObjectMapper that will be used to parse
     */
    public SimpleParser(ObjectMapper om) {
        g = new GenericParser(om);
    }

    @Override
    public Simple parseFromFileName(String filename) throws GenericParser.ParserException {
        return g.parseFromFileName(filename, Simple.class);
    }

    @Override
    public Simple parseFromUrl(String urlString) throws GenericParser.ParserException {
        return g.parseFromUrl(urlString, Simple.class);
    }

    @Override
    public Simple accept(Visitor v) throws GenericParser.ParserException {
        return v.visit(this);
    }
}
