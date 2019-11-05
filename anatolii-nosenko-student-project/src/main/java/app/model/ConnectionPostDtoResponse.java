package app.model;

/**
 * Class to return on frontend the tree root and version of database connector.
 */
public class ConnectionPostDtoResponse {
    private String root;
    private String connector;

    public String getRoot() {
        return root;
    }

    public void setRoot(String root) {
        this.root = root;
    }

    public String getConnector() {
        return connector;
    }

    public void setConnector(String connector) {
        this.connector = connector;
    }
}
