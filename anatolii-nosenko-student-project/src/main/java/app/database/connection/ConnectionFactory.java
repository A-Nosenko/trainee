package app.database.connection;

import app.structure.exception.AppException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Class to provide database connection.
 */
public final class ConnectionFactory {

    private ConnectionFactory() {}

    /**
     * Method to create database connection.
     * @param props Connection properties.
     * @return Connection to database.
     */
    public static Connection getConnection(Props props) {
        Connection connection;
        try {
            Class.forName(props.getDriverClassName());
            connection = DriverManager.getConnection(
                props.getUrl(), props.getUsername(), props.getPassword());
        } catch (ClassNotFoundException | SQLException e) {
            throw new AppException(e.getMessage());
        }
        return connection;
    }
}
