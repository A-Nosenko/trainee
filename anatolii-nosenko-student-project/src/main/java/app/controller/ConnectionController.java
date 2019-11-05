package app.controller;

import app.model.ConnectionPostDto;
import app.model.ConnectionPostDtoResponse;
import app.service.ConnectionService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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
     * @param connectionPostDto Object with parameters to establish database connection.
     * @return ConnectionPostDtoResponse with tree root and information about connection driver.
     */
    @PostMapping(value = "/connect",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ConnectionPostDtoResponse connect(@RequestBody ConnectionPostDto connectionPostDto) {

        LOGGER.info("New connection to database: ip = "
            .concat(connectionPostDto.getIp())
            .concat(" port = ")
            .concat(connectionPostDto.getPort()));

        return connectionService.createConnection(connectionPostDto);
    }
}
