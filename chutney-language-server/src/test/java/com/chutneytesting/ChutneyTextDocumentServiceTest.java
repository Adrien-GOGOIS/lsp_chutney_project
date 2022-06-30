package com.chutneytesting;

import com.chutneytesting.infra.FileCompletionSuggestion;
import com.chutneytesting.infra.Suggestion;
import org.assertj.core.api.Assertions;
import org.eclipse.lsp4j.*;
import org.eclipse.lsp4j.jsonrpc.messages.Either;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;

class ChutneyTextDocumentServiceTest {

    // private final ChutneyLanguageServer languageServer = new ChutneyLanguageServer();
    // ChutneyTextDocumentService textDocumentService = new ChutneyTextDocumentService(languageServer);
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
}