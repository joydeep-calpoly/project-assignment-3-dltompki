package edu.calpoly.dltompki.csc305.project.source;

import edu.calpoly.dltompki.csc305.project.article.GenericParser;
import edu.calpoly.dltompki.csc305.project.article.NewsApiParser;
import edu.calpoly.dltompki.csc305.project.article.Simple;
import edu.calpoly.dltompki.csc305.project.article.SimpleParser;
import edu.calpoly.dltompki.csc305.project.article.newsapi.Response;

public interface Visitor {
    /**
     * Use a SimpleParser to get a Simple from the data provided at construction
     *
     * @param p The parser implementation
     * @return parsed data
     * @throws GenericParser.ParserException if parsing fails
     */
    Simple visit(SimpleParser p) throws GenericParser.ParserException;

    /**
     * Use a NewsApiParser to get a Response from the data provided at construction
     *
     * @param p The parser implementation
     * @return parsed data
     * @throws GenericParser.ParserException if parsing fails
     */
    Response visit(NewsApiParser p) throws GenericParser.ParserException;
}
