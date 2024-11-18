package edu.calpoly.dltompki.csc305.project.article;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.calpoly.dltompki.csc305.project.types.Format;
import edu.calpoly.dltompki.csc305.project.types.Source;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ParserBuilder {
    private final Format f;
    private final Source s;
    private final ObjectMapper om;
    private final GenericParser g = new GenericParser(om);

    Parser<?> build() {
        if (f == Format.SIMPLE && s == Source.URL) {
            throw new RuntimeException("combination not allowed");
        } else if (f == Format.SIMPLE) {
            return new SimpleParser(g);
        } else {
            return new NewsApiParser(g);
        }
    }
}
