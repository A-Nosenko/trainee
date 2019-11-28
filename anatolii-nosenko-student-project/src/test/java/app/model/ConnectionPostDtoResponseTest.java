package app.model;

import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Test;

public class ConnectionPostDtoResponseTest {

    private static ConnectionPostDtoResponse connectionPostDtoResponse;

    @BeforeClass
    public static void init() {
        connectionPostDtoResponse = new ConnectionPostDtoResponse();
    }

    @Test
    public void getRoot() {
        connectionPostDtoResponse.setRoot(null);
        assertNull(connectionPostDtoResponse.getRoot());
    }

    @Test
    public void getConnector() {
        connectionPostDtoResponse.setConnector(null);
        assertNull(connectionPostDtoResponse.getConnector());
    }

    @AfterClass
    public static void reset() {
        connectionPostDtoResponse = null;
    }
}