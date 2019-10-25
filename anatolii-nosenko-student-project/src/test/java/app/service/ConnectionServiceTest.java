package app.service;

import app.config.AppConfig;
import app.model.ConnectionHolder;
import app.model.TreeHolder;
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes={AppConfig.class})
public class ConnectionServiceTest {

    @Autowired
    private TreeHolder treeHolder;

    @Autowired
    private ConnectionHolder connectionHolder;

    @Test
    public void createConnection() {
//        ConnectionService connectionService = new ConnectionService();
//        connectionService.connectionHolder = connectionHolder;
//        connectionService.treeHolder = treeHolder;
//        System.out.println(
//        new ConnectionService().createConnection("localhost", "3306", "root", "root")
//        );
    }
}