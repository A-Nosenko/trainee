package app.database.query;

import app.exception.AppException;
import app.literals.Constants;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
    public synchronized List<String> getTablesNames(String databaseName, Connection connection) {
        makeQuery("USE ".concat(databaseName), connection);
        return getStringLines("SHOW TABLES", connection);
    }

    /**
     * Method to fetch table DDL script.
     *
     * @param databaseName Database name.
     * @param type         Target type to fetch DDL.
     * @param targetName   Target name.
     * @param connection   Connection to database server.
     * @return Script to create table.
     */
    public synchronized String getDDL(String databaseName, DDL type, String targetName, Connection connection) {
        String result = null;

        makeQuery("USE ".concat(databaseName), connection);

        builder = new StringBuilder();
        builder.append("SHOW CREATE ");
        switch (type) {
            case TABLE:
                builder.append(DDL.TABLE.name());
                break;
            case TRIGGER:
                builder.append(DDL.TRIGGER.name());
                break;
            case PROCEDURE:
                builder.append(DDL.PROCEDURE.name());
                break;
            case FUNCTION:
                builder.append(DDL.FUNCTION.name());
                break;
            default:
                throw new AppException("Incorrect target to fetch DDL.");
        }
        builder.append(Constants.SPACE);
        builder.append(targetName);

        try (PreparedStatement statement = connection.prepareStatement(builder.toString())) {
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                if (type == DDL.TABLE) {
                    result = resultSet.getString(2);
                } else {
                    result = resultSet.getString(type.getKey());
                }
            }
        } catch (SQLException | NullPointerException e) {
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
    private synchronized List<String> getStringLines(String query, Connection connection) {
        List<String> result = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                result.add(resultSet.getString(1));
            }
        } catch (SQLException | NullPointerException e) {
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

        return getMapsOfArguments(builder.toString(), Constants.COLUMN_ATTRIBUTES, connection);
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

        return getMapsOfArguments(builder.toString(), Constants.VIEW_ATTRIBUTES, connection);
    }

    /**
     * Method to fetch stored procedures.
     *
     * @param databaseName Database name.
     * @param connection   Connection to database server.
     * @return List of maps with stored procedure attributes.
     */
    public List<Map<String, String>> getDatabaseStoredProcedures(String databaseName, Connection connection) {
        builder = new StringBuilder();
        builder.append("SHOW PROCEDURE STATUS where Db = \'");
        builder.append(databaseName);
        builder.append("\'");

        return getMapsOfArguments(builder.toString(), Constants.STORED_PROCEDURE_ATTRIBUTES, connection);
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

        return getMapsOfArguments(builder.toString(), Constants.FUNCTION_ATTRIBUTES, connection);
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
    public Map<String, String> getColumnKeyAttributes(String databaseName, String tableName,
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

        Map<String, String> result = getMapOfArguments(builder.toString(), Constants.KEY_ATTRIBUTES, connection);

        String typeName = result.get(Constants.KEY_ATTRIBUTES[0]);
        if (typeName != null && !typeName.equals(Constants.PRIMARY_KEY)) {
            String tableDDL = getDDL(databaseName, DDL.TABLE, tableName, connection);
            String[] lines = tableDDL.split(Constants.NEW_LINE);
            for (String lineSource : lines) {
                String line = lineSource.trim();
                if (line.contains("CONSTRAINT") && line.contains(typeName)) {
                    if (line.endsWith(",")) {
                        line = line.substring(0, line.length() - 1);
                    }
                    result.put(Constants.DDL, "ALTER TABLE "
                        .concat(tableName)
                        .concat(" ADD ")
                        .concat(line));
                }
            }
        }

        return result;
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

        return getMapsOfArguments(builder.toString(), Constants.TRIGGER_ATTRIBUTES, connection);
    }

    /**
     * Method to get last insert id.
     *
     * @param databaseName Database name.
     * @param tableName    Table name.
     * @param connection   Connection to database server.
     * @return Last insert id.
     */
    public synchronized long getLastInsertId(String databaseName, String tableName, Connection connection) {
        long result = 0;
        builder = new StringBuilder();
        builder.append("SELECT COUNT(LAST_INSERT_ID()) FROM ");
        builder.append(databaseName);
        builder.append(".");
        builder.append(tableName);

        try (PreparedStatement statement = connection.prepareStatement(builder.toString())) {
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                result = resultSet.getInt(1);
            }
        } catch (SQLException | NullPointerException e) {
            throw new AppException(e.getMessage());
        }

        return result;
    }

    private synchronized void makeQuery(String query, Connection connection) {
        try (Statement statement = connection.createStatement()) {
            statement.executeQuery(query);
        } catch (SQLException | NullPointerException e) {
            throw new AppException(e.getMessage());
        }
    }

    private synchronized List<Map<String, String>> getMapsOfArguments(String query, String[] arguments,
                                                                      Connection connection) {
        List<Map<String, String>> result = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Map<String, String> map = new HashMap<>();
                for (String attribute : arguments) {
                    map.put(attribute.replace(" ", "_"),
                        resultSet.getString(attribute));
                }
                result.add(map);
            }
        } catch (SQLException | NullPointerException e) {
            throw new AppException(e.getMessage());
        }

        return result;
    }

    private synchronized Map<String, String> getMapOfArguments(String query, String[] arguments,
                                                               Connection connection) {
        Map<String, String> map = new HashMap<>();
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                for (String attribute : arguments) {
                    map.put(attribute.replace(" ", "_"),
                        resultSet.getString(attribute));
                }
            }
        } catch (SQLException | NullPointerException e) {
            throw new AppException(e.getMessage());
        }
        return map;
    }
}
