package app.literals;

/**
 * Class to hold literals.
 */
public final class Constants {

    private Constants() {
    }

    public static final String[][] XML_SPECIAL_SYMBOLS = {
        {"&", "&amp;"},
        {"<", "&lt;"},
        {">", "&gt;"},
        {"'", "&apos;"},
        {"\"", "&quot;"}
    };

    public static final String NEW_LINE = "\n";
    public static final String EQUALS = "=";
    public static final String SPACE = " ";
    public static final String QUOTE = "\"";
    public static final String START_DECLARATION = "?xml";
    public static final String END_DECLARATION = "?";
    public static final String START_TEG = "<";
    public static final String END_TEG = ">";
    public static final String CLOSE_START_TEG = "</";
    public static final String TEG_BOUNDS = "><";
    public static final String TEG_BOUNDS_TO_NEXT_LINE = ">\n<";

    public static final String NULL_ELEMENT = "<null/>";
    public static final String EMPTY = "";

    public static final String VERSION = "version";
    public static final String DEFAULT_VERSION = "1.0";
    public static final String ENCODING = "encoding";
    public static final String DEFAULT_ENCODING = "utf-8";
    public static final String STANDALONE = "standalone";
    public static final String STANDALONE_YES = "yes";

    public static final String EXCEPTION_BORDER = "\t";
    public static final String EXCEPTION_MARKER =
        "======================================================================";
    public static final String EXCEPTION_CONTENT =
        "Exception: ";

    // Constants to mapping database.
    public static final String DATABASES = "ROOT";
    public static final String DATABASE = "database";
    public static final String DATABASE_NAME = "database_name";
    public static final String VIEWS = "views";
    public static final String VIEW = "view";
    public static final String[] VIEW_ATTRIBUTES = {
        "TABLE_CATALOG", "TABLE_SCHEMA", "TABLE_NAME",
        "VIEW_DEFINITION", "CHECK_OPTION", "IS_UPDATABLE",
        "DEFINER", "SECURITY_TYPE", "CHARACTER_SET_CLIENT",
        "COLLATION_CONNECTION"};
    public static final String STORED_PROCEDURES = "stored_procedures";
    public static final String STORED_PROCEDURE = "stored_procedure";
    public static final String[] STORED_PROCEDURE_ATTRIBUTES = {
        "Db", "Name", "Type", "Definer",
        "Modified", "Created", "Security_type", "Comment",
        "character_set_client", "collation_connection",
        "Database Collation"};
    public static final String FUNCTIONS = "functions";
    public static final String FUNCTION = "function";
    public static final String[] FUNCTION_ATTRIBUTES = {
        "SPECIFIC_NAME", "ROUTINE_CATALOG", "ROUTINE_SCHEMA",
        "ROUTINE_NAME", "ROUTINE_TYPE", "DATA_TYPE",
        "CHARACTER_MAXIMUM_LENGTH", "CHARACTER_OCTET_LENGTH",
        "NUMERIC_PRECISION", "NUMERIC_SCALE", "DATETIME_PRECISION",
        "CHARACTER_SET_NAME", "COLLATION_NAME", "DTD_IDENTIFIER",
        "ROUTINE_BODY", "DEFINER", "EXTERNAL_NAME",
        "EXTERNAL_LANGUAGE", "PARAMETER_STYLE", "IS_DETERMINISTIC",
        "SQL_DATA_ACCESS", "SQL_PATH", "SECURITY_TYPE",
        "CREATED", "LAST_ALTERED", "SQL_MODE",
        "ROUTINE_COMMENT", "CHARACTER_SET_CLIENT",
        "COLLATION_CONNECTION", "DATABASE_COLLATION"
    };
    public static final String TABLES = "tables";
    public static final String TABLE = "table";
    public static final String TABLE_NAME = "table_name";
    public static final String LAST_INSERT_ID = "LAST_INSERT_ID";
    public static final String COLUMNS = "columns";
    public static final String COLUMN = "column";
    public static final String[] COLUMN_ATTRIBUTES = {
        "Field", "Type", "Null", "Key", "Default", "Extra"
    };
    public static final String KEY = "key";
    public static final String PRIMARY_KEY = "PRIMARY";
    public static final String[] KEY_ATTRIBUTES = {
        "CONSTRAINT_NAME", "REFERENCED_TABLE_NAME", "REFERENCED_COLUMN_NAME", "ORDINAL_POSITION"
    };
    public static final String DDL = "ddl_script";
    public static final String TRIGGERS = "triggers";
    public static final String TRIGGER = "trigger";
    public static final String[] TRIGGER_ATTRIBUTES = {
        "TRIGGER_CATALOG", "TRIGGER_SCHEMA", "TRIGGER_NAME",
        "EVENT_MANIPULATION", "EVENT_OBJECT_CATALOG", "EVENT_OBJECT_SCHEMA",
        "EVENT_OBJECT_TABLE", "ACTION_ORDER", "ACTION_CONDITION",
        "ACTION_STATEMENT", "ACTION_ORIENTATION", "ACTION_TIMING",
        "ACTION_REFERENCE_OLD_TABLE", "ACTION_REFERENCE_NEW_TABLE", "ACTION_REFERENCE_OLD_ROW",
        "ACTION_REFERENCE_NEW_ROW", "CREATED", "SQL_MODE", "DEFINER",
        "CHARACTER_SET_CLIENT", "COLLATION_CONNECTION", "DATABASE_COLLATION"
    };

    // Constants to represent JSON.
    public static final String START_JSON_ARRAY = "[";
    public static final String FINISH_JSON_ARRAY = "]";

    // Connection default attributes.
    public static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    public static final String URL_PREFIX = "jdbc:mysql://";
    public static final String URL_DIVIDER = ":";
    public static final String URL_SUFFIX =
        "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

    public static final String TEMP_XML = "temp_data/temp.xml";
    public static final String LOADED_FROM_XML_STATUS = "Information about database received from XML.";
}
