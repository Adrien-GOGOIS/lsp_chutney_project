package com.chutneytesting;

import org.assertj.core.api.Assertions;
import org.eclipse.lsp4j.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class ChutneyTextDocumentServiceTest {

    @Test
    public void should_get_text_change() {

        VersionedTextDocumentIdentifier textDocument = new VersionedTextDocumentIdentifier();
        TextDocumentContentChangeEvent userChange = new TextDocumentContentChangeEvent("T");
        List<TextDocumentContentChangeEvent> contentChange = new ArrayList<>();
        contentChange.add(userChange);
        DidChangeTextDocumentParams  didChangeTextDocumentParams = new DidChangeTextDocumentParams(textDocument, contentChange);

        String userEntry = didChangeTextDocumentParams.getContentChanges().get(0).getText();

        Assertions.assertThat(userEntry).isEqualTo("T");
    }

    @Test
    public void completion() {
        final ChutneyLanguageServer languageServer = new ChutneyLanguageServer();
        ChutneyTextDocumentService textDocumentService = new ChutneyTextDocumentService(languageServer);

        TextDocumentIdentifier textDocument = new TextDocumentIdentifier("file://Users/adriengogois/Desktop/lsp-chutney/chutney-language-server/src/test/resources/sample.java");
        Position textPosition = new Position(0, 1);
        CompletionParams position = new CompletionParams(textDocument, textPosition);

        textDocumentService.completion(position);

        CompletionItem expectedTotoSuggestion = new CompletionItem("TOTOLABEL");
        expectedTotoSuggestion.setInsertText("TOTO");
        expectedTotoSuggestion.setDetail("TOTODETAILS");
        expectedTotoSuggestion.setKind(CompletionItemKind.Snippet);
        CompletionItem expectedTataSuggestion = new CompletionItem("TATALABEL");
        expectedTataSuggestion.setInsertText("TATA");
        expectedTataSuggestion.setDetail("TATADETAILS");
        expectedTataSuggestion.setKind(CompletionItemKind.Snippet);

        // Assertions.assertThat(content).containsAll(List.of(expectedTotoSuggestion, expectedTataSuggestion));
    }
}