package app.database.query;

import static app.literals.Constants.COLUMN_ATTRIBUTES;
import static app.literals.Constants.FOREIGN_KEY_ATTRIBUTES;
import static app.literals.Constants.FUNCTION_ATTRIBUTES;
import static app.literals.Constants.STORED_PROCEDURE_ATTRIBUTES;
import static app.literals.Constants.TRIGGER_ATTRIBUTES;
import static app.literals.Constants.VIEW_ATTRIBUTES;
import app.structure.exception.AppException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class to make queries on Database server.
 */
public final class QueryManager {

    private static QueryManager queryManager;

    private StringBuilder builder = new StringBuilder();

    private QueryManager() {
    }

    /**
     * Method to obtain QueryManager instance.
     *
     * @return QueryManager singleton object.
     */
    public static QueryManager getInstance() {
        if (queryManager == null) {
            queryManager = new QueryManager();
        }

        return queryManager;
    }

    /**
     * Method to fetch databases names.
     *
     * @param connection Connection to database server.
     * @return List of databases names.
     */
    public List<String> getDatabasesNames(Connection connection) {
        return getStringLines("SHOW DATABASES", connection);
    }

    /**
     * Method to fetch tables names from specified database.
     *
     * @param databaseName Database to fetch tables.
     * @param connection   Connection to database server.
     * @return List of tables names.
     */
    public List<String> getTablesNames(String databaseName, Connection connection) {
        makeQuery("USE ".concat(databaseName), connection);
        return getStringLines("SHOW TABLES", connection);
    }

    /**
     * Method to fetch table DDL script.
     *
     * @param tableName  Table name.
     * @param connection Connection to database server.
     * @return Script to create table.
     */
    public String getTableDDL(String tableName, Connection connection) {
        String result = null;
        try {
            builder = new StringBuilder();
            builder.append("SHOW CREATE TABLE ");
            builder.append(tableName);
            PreparedStatement statement = connection.prepareStatement(builder.toString());
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                result = resultSet.getString(2);
            }
        } catch (Exception e) {
            throw new AppException(e.getMessage());
        }
        return result;
    }

    /**
     * Method to retrieve first String elements from query ResultSet.
     * Usable to read databases and tables names.
     *
     * @param query      Query to execute.
     * @param connection Connection to database server.
     * @return List of String elements from ResultSet.
     */
    private List<String> getStringLines(String query, Connection connection) {
        List<String> result = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                result.add(resultSet.getString(1));
            }
        } catch (Exception e) {
            throw new AppException(e.getMessage());
        }
        return result;
    }

    /**
     * Method to get columns attributes.
     *
     * @param databaseName Database name.
     * @param tableName    Table name.
     * @param connection   Connection to database server.
     * @return Columns attributes.
     */
    public List<Map<String, String>> getColumnAttributes(String databaseName,
                                                         String tableName, Connection connection) {
        builder = new StringBuilder();
        builder.append("SHOW COLUMNS FROM ");
        builder.append(databaseName);
        builder.append(".");
        builder.append(tableName);

        return getMapsOfArguments(builder.toString(), COLUMN_ATTRIBUTES, connection);
    }

    /**
     * Method to fetch table views.
     *
     * @param tableName  Table name.
     * @param connection Connection to database server.
     * @return List of maps with view attributes.
     */
    public List<Map<String, String>> getTableViews(String tableName, Connection connection) {
        builder = new StringBuilder();
        builder.append("SELECT * FROM information_schema.VIEWS WHERE TABLE_NAME = \'");
        builder.append(tableName);
        builder.append("\'");

        return getMapsOfArguments(builder.toString(), VIEW_ATTRIBUTES, connection);
    }

    /**
     * Method to fetch stored procedures.
     *
     * @param databaseName Database name.
     * @param connection   Connection to database server.
     * @return List of maps with stored procedure attributes.
     */
    public List<Map<String, String>> getDatabaseProcedures(String databaseName, Connection connection) {
        builder = new StringBuilder();
        builder.append("SHOW PROCEDURE STATUS where Db = \'");
        builder.append(databaseName);
        builder.append("\'");

        return getMapsOfArguments(builder.toString(), STORED_PROCEDURE_ATTRIBUTES, connection);
    }

    /**
     * Method to fetch functions.
     *
     * @param databaseName Database name.
     * @param connection   Connection to database server.
     * @return List of maps with functions attributes.
     */
    public List<Map<String, String>> getDatabaseFunctions(String databaseName, Connection connection) {
        builder = new StringBuilder();
        builder.append("SELECT * FROM INFORMATION_SCHEMA.ROUTINES WHERE ROUTINE_SCHEMA = \'");
        builder.append(databaseName);
        builder.append("\'");

        return getMapsOfArguments(builder.toString(), FUNCTION_ATTRIBUTES, connection);
    }

    /**
     * Method to fetch information about foreign key.
     *
     * @param databaseName Database name.
     * @param tableName    Table name.
     * @param columnName   Column name.
     * @param connection   Connection to database server.
     * @return Information about foreign key.
     */
    public Map<String, String> getForeignKeyAttributes(String databaseName, String tableName,
                                                       String columnName, Connection connection) {

        builder = new StringBuilder();
        builder.append("SELECT * ");
        builder.append(" FROM INFORMATION_SCHEMA.KEY_COLUMN_USAGE WHERE TABLE_SCHEMA = \'");
        builder.append(databaseName);
        builder.append("\' AND TABLE_NAME = \'");
        builder.append(tableName);
        builder.append("\' AND COLUMN_NAME = \'");
        builder.append(columnName);
        builder.append("\'");

        return getMapOfArguments(builder.toString(), FOREIGN_KEY_ATTRIBUTES, connection);
    }

    /**
     * Method to get information about triggers on specific table.
     *
     * @param databaseName Database name.
     * @param tableName    Table name.
     * @param connection   Connection to database server.
     * @return Information about triggers.
     */
    public List<Map<String, String>> getTriggersAttributes(String databaseName, String tableName,
                                                           Connection connection) {
        builder = new StringBuilder();
        builder.append("SELECT * ");
        builder.append("FROM information_schema.triggers WHERE TRIGGER_SCHEMA = \'");
        builder.append(databaseName);
        builder.append("\' AND EVENT_OBJECT_TABLE = \'");
        builder.append(tableName);
        builder.append("\'");

        return getMapsOfArguments(builder.toString(), TRIGGER_ATTRIBUTES, connection);
    }

    /**
     * Method to get last insert id.
     *
     * @param databaseName Database name.
     * @param tableName    Table name.
     * @param connection   Connection to database server.
     * @return Last insert id.
     */
    public long getLastInsertId(String databaseName, String tableName, Connection connection) {
        long result = 0;
        builder = new StringBuilder();
        builder.append("SELECT LAST_INSERT_ID() FROM ");
        builder.append(databaseName);
        builder.append(".");
        builder.append(tableName);
        try {
            PreparedStatement statement = connection.prepareStatement(builder.toString());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                result++;
            }
        } catch (SQLException e) {
            throw new AppException(e.getMessage());
        }

        return result;
    }

    private void makeQuery(String query, Connection connection) {
        try {
            connection.createStatement().executeQuery(query);
        } catch (SQLException e) {
            throw new AppException(e.getMessage());
        }
    }

    private List<Map<String, String>> getMapsOfArguments(String query, String[] arguments,
                                                         Connection connection) {
        List<Map<String, String>> result = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Map<String, String> map = new HashMap<>();
                for (String attribute : arguments) {
                    map.put(attribute.replace(" ", "_"),
                        resultSet.getString(attribute));
                }
                result.add(map);
            }
        } catch (SQLException e) {
            throw new AppException(e.getMessage());
        }

        return result;
    }

    private Map<String, String> getMapOfArguments(String query, String[] arguments,
                                                  Connection connection) {
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Map<String, String> map = new HashMap<>();
                for (String attribute : arguments) {
                    map.put(attribute.replace(" ", "_"),
                        resultSet.getString(attribute));
                }
                return map;
            }
        } catch (SQLException e) {
            throw new AppException(e.getMessage());
        }

        return null;
    }
}
