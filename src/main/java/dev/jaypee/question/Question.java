package dev.jaypee.question;

import lombok.Getter;
import lombok.ToString;

import java.util.List;

@ToString
@Getter
public class Question {


    private final String id;
    private final boolean isCaseSensitive;
    private final String question;
    private final List<String> answerKeys;

    protected Question(String id, boolean isCaseSensitive, String question, List<String> answerKeys) {
        this.id = id;
        this.isCaseSensitive = isCaseSensitive;
        this.question = question;
        this.answerKeys = answerKeys;
    }
}
