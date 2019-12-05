package app.controller;

import app.model.ConnectionPostDto;
import app.model.ConnectionPostDtoResponse;
import app.service.ConnectionService;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ConnectionControllerTest {
    @InjectMocks
    ConnectionController connectionController = new ConnectionController();

    @Mock
    ConnectionService connectionService;

    @Test
    public void connect() {
        ConnectionPostDto connectionPostDto = new ConnectionPostDto();
        connectionPostDto.setIp("localhost");
        connectionPostDto.setPort("1234");
        connectionPostDto.setLogin("login");
        connectionPostDto.setPassword("password");


        when(connectionService.createConnection(connectionPostDto))
            .thenReturn(new ConnectionPostDtoResponse());

        assertNotNull(connectionController.connect(connectionPostDto));
    }
}
