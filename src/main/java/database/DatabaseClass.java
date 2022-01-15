package database;

import lombok.Getter;
import lombok.SneakyThrows;
import model.Message;
import model.Profile;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.HashMap;
import java.util.Map;

@Getter
public class DatabaseClass {

    private final String username = "root";
    private final String password = "root";
    private final String databaseURL = "jdbc:mysql://localhost:3306/app?autoReconnect=true&useSSL=false";
    private final String jdbcDriver = "com.mysql.cj.jdbc.Driver";
    private Connection connection;

    @SneakyThrows
    public DatabaseClass() {
        Class.forName(jdbcDriver);
        connection = DriverManager.getConnection(databaseURL, username, password);
    }

}
