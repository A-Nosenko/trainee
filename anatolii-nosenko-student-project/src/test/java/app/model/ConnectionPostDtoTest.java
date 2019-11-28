package app.model;

import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Test;

public class ConnectionPostDtoTest {

    private static ConnectionPostDto connectionPostDto;

    @BeforeClass
    public static void init() {
        connectionPostDto = new ConnectionPostDto();
    }

    @Test
    public void getIp() {
        connectionPostDto.setIp(null);
        assertNull(connectionPostDto.getIp());
    }

    @Test
    public void getPort() {
        connectionPostDto.setPort(null);
        assertNull(connectionPostDto.getPort());
    }

    @Test
    public void getLogin() {
        connectionPostDto.setLogin(null);
        assertNull(connectionPostDto.getLogin());
    }

    @Test
    public void getPassword() {
        connectionPostDto.setPassword(null);
        assertNull(connectionPostDto.getPassword());
    }

    @AfterClass
    public static void reset() {
        connectionPostDto = null;
    }
}
