package app.database.connection;

import app.exception.AppException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.apache.log4j.Logger;

/**
 * Class to provide database connection.
 */
public final class ConnectionFactory {
    private static final Logger LOGGER = Logger.getLogger(ConnectionFactory.class.getName());

    private ConnectionFactory() {
    }

    /**
     * Method to create database connection.
     *
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

        LOGGER.info(props);

        return connection;
    }
}
