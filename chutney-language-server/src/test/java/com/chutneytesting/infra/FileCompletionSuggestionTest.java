package com.chutneytesting.infra;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class FileCompletionSuggestionTest {

    @Test
    public void should_read_a_file() {
        FileCompletionSuggestion sut = new FileCompletionSuggestion("completion.txt");
        String content = sut.getFileContent();

        Assertions.assertThat(content).isEqualTo(
                "TOTO,TOTOLABEL,TOTODETAILS\n" +
                        "TATA,TATALABEL,TATADETAILS\n" +
                        "ZOZO,ZOZOLABEL,ZOZODETAILS\n" +
                        "ZAZA,ZAZALABEL,ZAZADETAILS");
    }

    // Test + Méthode : retourne List de lignes
    @Test
    public void should_return_a_list_of_file_lines() {
        FileCompletionSuggestion sut = new FileCompletionSuggestion("completion.txt");
        List<String> content = sut.getFileLines();

        Assertions.assertThat(content.get(0)).isEqualTo("TOTO,TOTOLABEL,TOTODETAILS");
        Assertions.assertThat(content.get(1)).isEqualTo("TATA,TATALABEL,TATADETAILS");
        Assertions.assertThat(content.get(2)).isEqualTo("ZOZO,ZOZOLABEL,ZOZODETAILS");
        Assertions.assertThat(content.get(3)).isEqualTo("ZAZA,ZAZALABEL,ZAZADETAILS");
    }

    @Test
    public void should_return_a_list_of_Suggestion() {
        FileCompletionSuggestion sut = new FileCompletionSuggestion("completion.txt");
        List<Suggestion> content = sut.getSuggestionsList();

        Assertions.assertThat(content.get(0).insertText).isEqualTo("TOTO");
        Assertions.assertThat(content.get(1).label).isEqualTo("TATALABEL");
        Assertions.assertThat(content.get(2).details).isEqualTo("ZOZODETAILS");
    }

    // Test + Methode : Cherche une suggestion en fonction de l'argument
    @Test
    public void should_return_list_of_Suggestion_according_user_entry() {
        FileCompletionSuggestion sut = new FileCompletionSuggestion("completion.txt");
        String userEntry = "T";
        List<Suggestion> content = sut.getSuggestion(userEntry);

        Suggestion expectedTotoSuggestion = new Suggestion("TOTO", "TOTOLABEL", "TOTODETAILS");
        Suggestion expectedTataSuggestion = new Suggestion("TATA", "TATALABEL", "TATADETAILS");

        Assertions.assertThat(content).containsAll(List.of(expectedTotoSuggestion, expectedTataSuggestion));
    }


}