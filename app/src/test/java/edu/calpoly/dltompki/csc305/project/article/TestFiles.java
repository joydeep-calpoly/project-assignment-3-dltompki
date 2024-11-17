package edu.calpoly.dltompki.csc305.project.article;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
enum TestFiles {
    BAD("bad"),
    VALID("valid"),
    EXAMPLE("example"),
    SIMPLE("simple");

    private final String name;

    String getName() {
        return name + ".json";
    }
}
