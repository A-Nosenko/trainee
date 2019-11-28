package app.controller;

import org.junit.Test;
import static org.mockito.Mockito.mock;

public class ConnectionControllerTest {

    @Test
    public void connect() {
        ConnectionController connectionController = mock(ConnectionController.class);
        connectionController.connect(null);
    }
}