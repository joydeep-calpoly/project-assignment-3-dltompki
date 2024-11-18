package edu.calpoly.dltompki.csc305.project.article;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SimpleParser implements Parser<Simple> {
    private final GenericParser g;

    @Override
    public Simple parseFromFileName(String filename) throws GenericParser.ParserException {
        return g.parseFromFileName(filename, Simple.class);
    }

    @Override
    public Simple parseFromString(String content) throws GenericParser.ParserException {
        return g.parseFromString(Simple.class, content);
    }
}
