package com.chutneytesting.infra;

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

            List<String> content = getFileLines();

            for (String proposal : content) {
                String[] proposalAttribute = proposal.split(",");
                String insertText = proposalAttribute[0];
                String label = proposalAttribute[1];
                String details = proposalAttribute[2];
                suggestions.add(new Suggestion(insertText, label, details));
            }

        return suggestions;

    }

    public List<Suggestion> getSuggestion(String userEntry) {
        List<Suggestion> suggestionsList = getSuggestionsList();
        return suggestionsList
                .stream()
                .filter(suggestion -> suggestion.insertText.contains(userEntry))
                .collect(Collectors.toList());
    }

}
