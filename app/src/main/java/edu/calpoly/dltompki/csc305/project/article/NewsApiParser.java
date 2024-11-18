package edu.calpoly.dltompki.csc305.project.article;

import edu.calpoly.dltompki.csc305.project.article.newsapi.Response;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class NewsApiParser implements Parser<Response> {
    private final GenericParser g;

    @Override
    public Response parseFromFileName(String filename) throws GenericParser.ParserException {
        return g.parseFromFileName(filename, Response.class);
    }

    @Override
    public Response parseFromUrl(String urlString) throws GenericParser.ParserException {
        return g.parseFromUrl(urlString, Response.class);
    }
}
