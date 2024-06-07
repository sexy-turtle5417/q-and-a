package dev.jaypee;

import dev.jaypee.question.QuestionRepository;
import dev.jaypee.question.Questionnaire;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

@Configuration
public class ApplicationConfiguration {

    @Bean
    public QuestionRepository questionRepository() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/questions_and_answers";
        String user = "root";
        String password = "password";
        Connection connection = DriverManager.getConnection(url, user, password);
        return new QuestionRepository(connection);
    }

    @Bean
    public Questionnaire questionnaire(QuestionRepository questionRepository) throws SQLException {
        return new Questionnaire(questionRepository, new Scanner(System.in));
    }

}
