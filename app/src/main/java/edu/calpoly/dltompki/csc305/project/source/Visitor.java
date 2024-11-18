package edu.calpoly.dltompki.csc305.project.source;

import edu.calpoly.dltompki.csc305.project.article.GenericParser;
import edu.calpoly.dltompki.csc305.project.article.NewsApiParser;
import edu.calpoly.dltompki.csc305.project.article.Simple;
import edu.calpoly.dltompki.csc305.project.article.SimpleParser;
import edu.calpoly.dltompki.csc305.project.article.newsapi.Response;

public interface Visitor {
    Simple visit(SimpleParser p) throws GenericParser.ParserException;

    Response visit(NewsApiParser p) throws GenericParser.ParserException;
}
