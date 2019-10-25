package app.model;

import java.sql.Connection;

/**
 * Class to hold connection to database.
 */
public class ConnectionHolder {

    private Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}
