package app.database.connection;

/**
 * Class to hold database connection properties.
 */
public class Props {
    final String url;
    final String username;
    final String password;
    final String driverClassName;

    public Props(String url, String username, String password, String driverClassName) {
        this.url = url;
        this.username = username;
        this.password = password;
        this.driverClassName = driverClassName;
    }
}
