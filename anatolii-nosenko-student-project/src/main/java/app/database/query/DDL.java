package app.database.query;

/**
 * Enum of targets to fetch DDL script.
 */
public enum DDL {
    TABLE(null),
    PROCEDURE("Create Procedure"),
    TRIGGER("SQL Original Statement");

    /**
     * Constructor to create target for fetching DDL script with appropriate key.
     *
     * @param key Key to retrieve DDL script from ResultSet.
     */
    DDL(String key) {
        this.key = key;
    }

    private final String key;

    public String getKey() {
        return key;
    }
}
