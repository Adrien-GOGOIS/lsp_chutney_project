package com.chutneytesting.infra;

import org.assertj.core.api.Assertions;
import org.eclipse.lsp4j.CompletionItem;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;


class SuggestionMapperTest {

    @Test
    public void should_take_a_list_of_Suggestion_and_return_list_of_CompletionItem() {
        List<Suggestion> suggestions = new ArrayList<>();
        suggestions.add(new Suggestion("TOTO", "TOTOLABEL", "TOTODETAILS"));
        suggestions.add(new Suggestion("TATA", "TATALABEL", "TATADETAILS"));
        SuggestionMapper converter = new SuggestionMapper();

        List<CompletionItem> convertedList = converter.toCompletionItem(suggestions);

        List<CompletionItem> exampleList = new ArrayList<>();
        CompletionItem firstItem = new CompletionItem("TOTOLABEL");
        firstItem.setDetail("TOTODETAILS");
        firstItem.setInsertText("TOTO");
        CompletionItem secondItem = new CompletionItem("TATALABEL");
        secondItem.setDetail("TATADETAILS");
        secondItem.setInsertText("TATA");

        exampleList.add(firstItem);
        exampleList.add(secondItem);

        Assertions.assertThat(convertedList).isEqualTo(exampleList);
    }
}