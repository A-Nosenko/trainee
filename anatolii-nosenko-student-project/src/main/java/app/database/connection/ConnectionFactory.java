package app.database.connection;

import app.structure.exception.AppException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    public static Connection getConnection(Props props) {
        Connection connection;
        try {
            Class.forName(props.driverClassName);
            connection = DriverManager.getConnection(
                props.url, props.username, props.password);
        } catch (ClassNotFoundException | SQLException e) {
            throw new AppException(e.getMessage());
        }
        return connection;
    }
}
