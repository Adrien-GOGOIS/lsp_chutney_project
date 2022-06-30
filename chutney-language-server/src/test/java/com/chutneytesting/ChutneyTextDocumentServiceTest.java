package com.chutneytesting;

import com.chutneytesting.infra.FileCompletionSuggestion;
import com.chutneytesting.infra.Suggestion;
import com.chutneytesting.infra.SuggestionIntoCompletionItemConverter;
import org.assertj.core.api.Assertions;
import org.eclipse.lsp4j.*;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

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
    public void completion() throws IOException {
        final ChutneyLanguageServer languageServer = new ChutneyLanguageServer();
        ChutneyTextDocumentService textDocumentService = new ChutneyTextDocumentService(languageServer);

        TextDocumentIdentifier textDocument = new TextDocumentIdentifier("/Users/adriengogois/Desktop/lsp-chutney/chutney-language-server/src/test/resources/sample.txt");
        Position textPosition = new Position(0, 1);
        CompletionParams position = new CompletionParams(textDocument, textPosition);

        textDocumentService.completion(position);

        FileCompletionSuggestion sut = new FileCompletionSuggestion("completion.txt");
        String line = Files.readAllLines(Paths.get(textDocument.getUri())).get(textPosition.getLine());
        String character = String.valueOf(line.charAt(textPosition.getCharacter()));

        List<Suggestion> suggestions = sut.getSuggestion(character);

        SuggestionIntoCompletionItemConverter converter = new SuggestionIntoCompletionItemConverter();
        List<CompletionItem> completionItems = converter.convert(suggestions);

        CompletionItem expectedTotoSuggestion = new CompletionItem("TOTOLABEL");
        expectedTotoSuggestion.setInsertText("TOTO");
        expectedTotoSuggestion.setDetail("TOTODETAILS");
        CompletionItem expectedTataSuggestion = new CompletionItem("TATALABEL");
        expectedTataSuggestion.setInsertText("TATA");
        expectedTataSuggestion.setDetail("TATADETAILS");

        Assertions.assertThat(completionItems).isEqualTo(List.of(expectedTotoSuggestion, expectedTataSuggestion));
    }
}