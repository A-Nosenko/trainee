package app.service;

import app.model.ConnectionHolder;
import app.model.ConnectionPostDto;
import app.model.TreeHolder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ConnectionServiceTest {
    @InjectMocks
    ConnectionService connectionService = new ConnectionService();

    @Mock
    TreeHolder treeHolder;

    @Mock
    ConnectionHolder connectionHolder;

    @Test
    public void createConnection() {
        ConnectionPostDto connectionPostDto = new ConnectionPostDto();
        connectionPostDto.setIp("localhost");
        connectionPostDto.setPort("1234");
        connectionPostDto.setLogin("login");
        connectionPostDto.setPassword("password");

        connectionService.createConnection(connectionPostDto);
    }
}
