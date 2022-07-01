package com.chutneytesting.infra;

import org.eclipse.lsp4j.CompletionParams;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class UserEntries {
    
    public static String getCharacter(CompletionParams position) {
        String line;
        try {
            // TODO : Get entire word or expression instead of unique character
            line = Files.readAllLines(Paths.get(position.getTextDocument().getUri())).get(position.getPosition().getLine());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return String.valueOf(line.charAt(position.getPosition().getCharacter()));
    }
}
