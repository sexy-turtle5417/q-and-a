package dev.jaypee.question;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class QuestionBuilder {

    private String id;
    private String question;
    private boolean isCaseSensitive;
    private final List<String> answerKeys;

    public QuestionBuilder() {
        this.id = UUID.randomUUID().toString();
        this.isCaseSensitive = false;
        this.answerKeys = new ArrayList<>();
    }

    public QuestionBuilder id(String id){
        this.id = id;
        return this;
    }

    public QuestionBuilder isCaseSensitive(boolean isCaseSensitive) {
        this.isCaseSensitive = isCaseSensitive;
        return this;
    }

    public QuestionBuilder question(String question) {
        this.question = question;
        return this;
    }

    public QuestionBuilder answerKey(String answerKey) {
        this.answerKeys.add(answerKey);
        return this;
    }

    public Question build() {
        return new Question(id, isCaseSensitive, question, answerKeys);
    }

}
