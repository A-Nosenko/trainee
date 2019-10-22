package app;

import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Class to launch application.
 */
@SpringBootApplication
public class Start {
    private static final Logger LOGGER = Logger.getLogger(Start.class.getName());

    /**
     * Entry point.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        LOGGER.info("Starting the application.");

        SpringApplication.run(Start.class, args);
    }
}
