package edu.calpoly.dltompki.csc305.project.article;

import java.io.PrintWriter;
import java.io.StringWriter;

public class OutputUtil {
    /**
     * A unified toString method for both article formats
     *
     * @param b The article
     * @return a String describing the content of b
     */
    public static String articleToString(Base b) {
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        printWriter.print("title: ");
        printWriter.println(b.getTitle());
        printWriter.print("at: ");
        printWriter.println(b.getPublishedAt());
        printWriter.print("url: ");
        printWriter.println(b.getUrl());
        printWriter.println(b.getDescription());
        return stringWriter.toString();
    }
}
