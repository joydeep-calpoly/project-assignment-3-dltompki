package edu.calpoly.dltompki.csc305.project.article;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.calpoly.dltompki.csc305.project.article.newsapi.Response;
import edu.calpoly.dltompki.csc305.project.source.Visitor;

public class NewsApiParser implements Parser<Response> {
    private final GenericParser g;

    /**
     * Allow injection of ObjectMapper to enable the client to configure it beforehand.
     *
     * @param om The ObjectMapper that will be used to parse
     */
    public NewsApiParser(ObjectMapper om) {
        g = new GenericParser(om);
    }

    @Override
    public Response parseFromFileName(String filename) throws GenericParser.ParserException {
        return g.parseFromFileName(filename, Response.class);
    }

    @Override
    public Response parseFromUrl(String urlString) throws GenericParser.ParserException {
        return g.parseFromUrl(urlString, Response.class);
    }

    @Override
    public Response accept(Visitor v) throws GenericParser.ParserException {
        return v.visit(this);
    }
}
