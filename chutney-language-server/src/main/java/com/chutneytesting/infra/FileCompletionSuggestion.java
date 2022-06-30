package com.chutneytesting.infra;

import org.eclipse.lsp4j.CompletionItem;
import org.eclipse.lsp4j.CompletionItemKind;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileCompletionSuggestion {

    final File file;
    public FileCompletionSuggestion(String filePath) {
        ClassLoader cl = this.getClass().getClassLoader();
        this.file = new File(Objects.requireNonNull(cl.getResource(filePath)).getFile());
    }

    public String getFileContent() {
        try {
            return new String(new FileInputStream(file).readAllBytes());
        } catch(FileNotFoundException e){
            System.out.println(e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return "";
    }

    protected List<String> getFileLines() {
        try {
            Stream<String> lines = Files.lines(Paths.get(file.getPath()));
            return lines.collect(Collectors.toList());
        } catch(FileNotFoundException e){
            System.out.println(e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    public List<Suggestion> getSuggestionsList() {
        List<Suggestion> suggestions = new ArrayList<>();

            List<String> fileLines = getFileLines();

            for (String suggestion : fileLines) {
                String[] suggestionAttribute = suggestion.split(",");
                String insertText = suggestionAttribute[0];
                String label = suggestionAttribute[1];
                String details = suggestionAttribute[2];
                Suggestion completeSuggestion = new Suggestion(insertText, label, details);
                suggestions.add(completeSuggestion);
            }

        return suggestions;
    }

    public List<Suggestion> getSuggestion(String userEntry) {
        List<Suggestion> suggestionsList = getSuggestionsList();
        return suggestionsList
                .stream()
                .filter(suggestion -> suggestion.label.contains(userEntry))
                .collect(Collectors.toList());
    }

}
