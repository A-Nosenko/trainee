package app;

import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * Class to launch application.
 */
@SpringBootApplication
public class Start extends SpringBootServletInitializer {
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

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(Start.class);
    }
}
