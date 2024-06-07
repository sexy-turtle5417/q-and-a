package dev.jaypee;

import dev.jaypee.question.Questionnaire;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;



public class QuestionAndAnswersApplication {

    public static void main(String[] args)  {

        ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfiguration.class);
        Questionnaire questionnaire = context.getBean(Questionnaire.class);

        questionnaire.ask();
    }
}