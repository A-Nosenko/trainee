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
     * @param url             Database URL.
     * @param username        Database user name.
     * @param password        Database user password.
     * @param driverClassName Driver to connect the database.
     */
    public Props(String url, String username, String password, String driverClassName) {
        this.url = url;
        this.username = username;
        this.password = password;
        this.driverClassName = driverClassName;
    }

    String getUrl() {
        return url;
    }

    String getUsername() {
        return username;
    }

    String getPassword() {
        return password;
    }

    String getDriverClassName() {
        return driverClassName;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("\n\turl = ");
        builder.append(url);
        builder.append("\n\tusername = ");
        builder.append(username);
        builder.append("\n\tpassword = ");
        for (char ignored : password.toCharArray()) {
            builder.append("*");
        }
        builder.append("\n\tdriverClassName = ");
        builder.append(driverClassName);
        builder.append("\n");

        return builder.toString();
    }
}
