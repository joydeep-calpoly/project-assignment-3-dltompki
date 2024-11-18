package edu.calpoly.dltompki.csc305.project.source;

import edu.calpoly.dltompki.csc305.project.article.GenericParser;
import edu.calpoly.dltompki.csc305.project.article.NewsApiParser;
import edu.calpoly.dltompki.csc305.project.article.Simple;
import edu.calpoly.dltompki.csc305.project.article.SimpleParser;
import edu.calpoly.dltompki.csc305.project.article.newsapi.Response;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Url implements Visitor {
    private final String urlString;

    @Override
    public Simple visit(SimpleParser p) throws GenericParser.ParserException {
        return p.parseFromUrl(urlString);
    }

    @Override
    public Response visit(NewsApiParser p) throws GenericParser.ParserException {
        return p.parseFromUrl(urlString);
    }
}
