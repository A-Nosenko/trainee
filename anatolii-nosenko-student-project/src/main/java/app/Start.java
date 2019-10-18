package app;

import org.apache.log4j.Logger;

/**
 * Class to launch application.
 */
public class Start {
    private static final Logger LOGGER = Logger.getLogger(Start.class.getName());

    /**
     * Entry point.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        LOGGER.info("Starting the application.");
    }
}
