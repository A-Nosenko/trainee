package app.service;

import org.junit.Test;
import static org.mockito.Mockito.mock;

public class ConnectionServiceTest {

    @Test
    public void createConnection() {
        ConnectionService connectionService = mock(ConnectionService.class);

        connectionService.createConnection(null);
    }
}
