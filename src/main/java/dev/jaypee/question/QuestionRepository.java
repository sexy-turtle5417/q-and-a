package dev.jaypee.question;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class QuestionRepository {

    private final Connection connection;

    public QuestionRepository(Connection connection) {
        this.connection = connection;
    }

    public Question add(Question question) throws SQLException {
        insertToQuestions(question);
        insertToAnswerKeys(question);
        return findById(question.getId());
    }

    private void insertToQuestions(Question question) throws SQLException {
        String SQL = "INSERT INTO questions VALUES (?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(SQL);
        preparedStatement.setString(1, question.getId());
        preparedStatement.setString(2, question.getQuestion());
        preparedStatement.setBoolean(3, question.isCaseSensitive());
        preparedStatement.execute();
    }

    private void insertToAnswerKeys(Question question) throws SQLException {
        List<String> answerKeys = question.getAnswerKeys();
        String SQL = "INSERT INTO answer_keys (answer_key, question_id) VALUES (?, ?)";
        for(String answerKey : answerKeys) {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setString(1, answerKey);
            preparedStatement.setString(2, question.getId());
            preparedStatement.execute();
        }
    }

    public Question findById(String id) throws SQLException {
        QuestionBuilder questionBuilder = new QuestionBuilder();
        buildQuestion(id, questionBuilder);
        buildAnswerKeys(id, questionBuilder);
        return questionBuilder.build();
    }

    private void buildQuestion(String id, QuestionBuilder questionBuilder) throws SQLException {
        String SQL = "SELECT * FROM questions WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(SQL);
        preparedStatement.setString(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next())
            questionBuilder
                    .id(resultSet.getString("id"))
                    .isCaseSensitive(resultSet.getBoolean("is_case_sensitive"))
                    .question(resultSet.getString("question"));

    }

    private void buildAnswerKeys(String id, QuestionBuilder questionBuilder) throws SQLException {
        String SQL = "SELECT answer_key FROM answer_keys WHERE question_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(SQL);
        preparedStatement.setString(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        while(resultSet.next())
            questionBuilder.answerKey(resultSet.getString("answer_key"));
    }

    public List<Question> findAll() throws SQLException{
        List<Question> questions = new ArrayList<>();
        String SQL = "SELECT id FROM questions";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(SQL);
        while(resultSet.next())
            questions.add(findById(resultSet.getString("id")));
        return questions;
    }

}
