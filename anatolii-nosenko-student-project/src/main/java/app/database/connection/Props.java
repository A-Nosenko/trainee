package app.database.connection;

/**
 * Class to hold database connection properties.
 */
public class Props {
    private final String url;
    private final String username;
    private final String password;
    private final String driverClassName;

    /**
     * Constructor to specify connection properties.
     *
     * @param url Database URL.
     * @param username Database user name.
     * @param password Database user password.
     * @param driverClassName Driver to connect the database.
     */
    public Props(String url, String username, String password, String driverClassName) {
        this.url = url;
        this.username = username;
        this.password = password;
        this.driverClassName = driverClassName;
    }

    public String getUrl() {
        return url;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getDriverClassName() {
        return driverClassName;
    }
}
