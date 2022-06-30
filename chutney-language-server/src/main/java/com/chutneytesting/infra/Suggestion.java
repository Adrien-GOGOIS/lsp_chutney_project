package com.chutneytesting.infra;

import java.util.Objects;

public class Suggestion {

    public String insertText;
    public String label;
    public String details;

    public Suggestion(String insertText, String label, String details) {
        this.label = label;
        this.insertText = insertText;
        this.details = details;
    }

    @Override
    public String toString() {
        return "Suggestion{" +
                "insertText='" + insertText + '\'' +
                ", label='" + label + '\'' +
                ", details='" + details + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Suggestion that = (Suggestion) o;
        return Objects.equals(insertText, that.insertText) && Objects.equals(label, that.label) && Objects.equals(details, that.details);
    }

    @Override
    public int hashCode() {
        return Objects.hash(insertText, label, details);
    }
}
