package service;

import database.DatabaseClass;
import model.Message;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MessageService {

    public static final String username = "root";
    public static final String password = "root";
    public static final String databaseURL = "jdbc:mysql://localhost:3306/app?autoReconnect=true&useSSL=false";
    public static final String jdbcDriver = "com.mysql.cj.jdbc.Driver";
    public Connection connection;

    private Map<Long, Message> messages = DatabaseClass.getMessages();

    public MessageService() throws ClassNotFoundException, SQLException {
        Class.forName(jdbcDriver);
        connection = DriverManager.getConnection(databaseURL, username, password);
    }

    public List<Message> getAllMessages() {
        var messagesList = new ArrayList<Message>();
        String query = "select * from message";
        try {
            var resultSet = connection
                    .createStatement()
                    .executeQuery(query);

            while (resultSet.next()) {
                var message = new Message();


                message.setId(resultSet.getInt("id"));
                message.setMessage(resultSet.getString("message"));
                message.setCreated(resultSet.getDate("created").toLocalDate());
                message.setAuthor(resultSet.getString("author"));

                messagesList.add(message);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return messagesList;
    }

//    public List<Message> getAllMessagesForYear(int year) throws SQLException {
//        var messagesForYear = new ArrayList<Message>();
//        var calendar = Calendar.getInstance();
//        var allMessages = getAllMessages();
//        allMessages.forEach(message -> {
//            calendar.setTime(message.getCreated());
//            if (calendar.get(Calendar.YEAR) == year) {
//                messagesForYear.add(message);
//            }
//        });
//        return messagesForYear;
//    }

    public List<Message> getAllMessagesPaginated(int start, int size) {
        var list = new ArrayList<>(getAllMessages());
        return start + size > list.size() ? new ArrayList<>() : list.subList(start, start + size);
    }

    public Message getMessage(int id){
        var message = new Message();
        String query = "select * from message where id = " + id;

        try {
            var resultSet = connection
                    .createStatement()
                    .executeQuery(query);

            while (resultSet.next()) {
                message.setId(resultSet.getInt("id"));
                message.setMessage(resultSet.getString("message"));
                message.setCreated(resultSet.getDate("created").toLocalDate());
                message.setAuthor(resultSet.getString("author"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return message;
    }


    public Message addMessage(Message message) {
        String query = ("insert into message values (?,?,?,?)");
//        message.setId(messages.size() + 1);
//        messages.put(message.getId(), message);

        try {
            var preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, message.getId());
            preparedStatement.setString(2, message.getMessage());
            preparedStatement.setDate(3, Date.valueOf(message.getCreated()));
            preparedStatement.setString(4, message.getAuthor());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return message;
    }

    public Message updateMessage(Message message) {
        String query = ("update message set message = ?, created = ?, author = ? where id = ?");
//        message.setId(messages.size() + 1);
//        messages.put(message.getId(), message);

        try {
            var preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, message.getMessage());
            preparedStatement.setDate(2, Date.valueOf(message.getCreated()));
            preparedStatement.setString(3, message.getAuthor());
            preparedStatement.setInt(4, message.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return message;

    }

    public void removeMessage(int id) {
        String query = ("delete from message where id = ?");

        try {
            var preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
