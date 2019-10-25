package app.controller;

import app.service.ConnectionService;
import java.util.HashMap;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller to handle requests from connection form.
 */
@CrossOrigin("http://localhost:3000")
@RestController
public class ConnectionController {
    private static final Logger LOGGER = Logger.getLogger(ConnectionController.class.getName());

    @Autowired
    private ConnectionService connectionService;

    /**
     * Method receives JSON object with parameters to establish database connection,
     * and handle it.
     *
     * @param map JSON object with parameters to establish database connection.
     * @return Response with tree root and information about connection driver.
     */
    @PostMapping(value = "/connect",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity connect(@RequestBody HashMap<String, String> map) {

        String ip = map.get("ip");
        String port = map.get("port");
        String login = map.get("login");
        String password = map.get("password");
        LOGGER.info("New connection to database: ip = ".concat(ip).concat(" port = ").concat(port));
        String result = connectionService.createConnection(ip, port, login, password);

        return ResponseEntity.ok(result);
    }
}
