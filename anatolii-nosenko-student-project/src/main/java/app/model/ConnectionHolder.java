package app.model;

import app.exception.AppException;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Class to hold connection to database.
 */
public class ConnectionHolder {

    private Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        if (this.connection != null) {
            try {
                this.connection.close();
            } catch (SQLException e) {
                throw new AppException(e.getMessage());
            }
        }
        this.connection = connection;
    }
}
