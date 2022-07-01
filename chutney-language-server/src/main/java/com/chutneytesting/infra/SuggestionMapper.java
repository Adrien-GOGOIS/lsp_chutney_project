package com.chutneytesting.infra;

import org.eclipse.lsp4j.CompletionItem;

import java.util.ArrayList;
import java.util.List;

public class SuggestionMapper {

    public static List<CompletionItem> toCompletionItem(List<Suggestion> suggestions) {
        List<CompletionItem> convertedList = new ArrayList<>();
        for (Suggestion suggestion : suggestions) {
            CompletionItem completionItem = new CompletionItem(suggestion.label);
            completionItem.setInsertText(suggestion.insertText);
            completionItem.setDetail(suggestion.details);
            convertedList.add(completionItem);
        }

        return convertedList;
    }
}
