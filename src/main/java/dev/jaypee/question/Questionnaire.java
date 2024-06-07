package dev.jaypee.question;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Questionnaire {


    private final List<Question> questions;
    private final Scanner scanner;

    public Questionnaire(QuestionRepository questionRepository, Scanner scanner) throws SQLException {
        this.questions = questionRepository.findAll();
        this.scanner = scanner;
    }

    private boolean isCorrect(Question question, String answerKey) {
        if(question.isCaseSensitive())
            return question.getAnswerKeys().contains(answerKey);
        return question.getAnswerKeys()
                .stream()
                .map(String::toLowerCase)
                .toList()
                .contains(answerKey.toLowerCase());
    }

    public void ask() {
        Collections.shuffle(questions);
        int index = 0;
        while(index < questions.size()) {
            Question currentQuestion = questions.get(index);
            System.out.println(currentQuestion.getQuestion());
            System.out.print("input: ");
            String answer = scanner.nextLine();
            System.out.printf("\"%s\" are the correct answers.\n", currentQuestion.getAnswerKeys());
            if(isCorrect(currentQuestion, answer)){
                index++;
                System.out.println("You got the answer correct!");
                System.out.println();
                continue;
            }
            System.out.println("You got the answer wrong!");
            System.out.println();
            index = 0;
            Collections.shuffle(questions);
        }
        System.out.println("Congratulations");
        System.out.println("You completed the quiz!");
    }
}
