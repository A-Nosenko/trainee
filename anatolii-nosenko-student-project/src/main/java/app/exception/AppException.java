package app.exception;

import app.literals.Constants;
import org.apache.log4j.Logger;

/**
 * Custom unchecked exception to wrap checked exception.
 */
public class AppException extends RuntimeException {
    private static final Logger LOGGER = Logger.getLogger(AppException.class.getName());

    private final String message;

    /**
     * Custom exception constructor to format message.
     *
     * @param message Message about error.
     */
    public AppException(String message) {

        StringBuilder builder = new StringBuilder();
        builder.append(Constants.NEW_LINE);
        builder.append(Constants.EXCEPTION_BORDER);
        builder.append(Constants.EXCEPTION_MARKER);
        builder.append(Constants.NEW_LINE);
        builder.append(Constants.EXCEPTION_BORDER);
        builder.append(Constants.EXCEPTION_CONTENT);
        builder.append(Constants.NEW_LINE);
        builder.append(Constants.EXCEPTION_BORDER);
        char[] messageContent = ((message == null)
            ? ("null")
            : (message.replace("\n", " ")))
            .toCharArray();
        for (int i = 1; i <= messageContent.length; i++) {
            builder.append(messageContent[i - 1]);
            if (i % Constants.EXCEPTION_MARKER.length() == 0) {
                builder.append(Constants.NEW_LINE);
                builder.append(Constants.EXCEPTION_BORDER);
            }
        }
        builder.append(Constants.NEW_LINE);

        builder.append(Constants.EXCEPTION_BORDER);
        builder.append(Constants.EXCEPTION_MARKER);
        builder.append(Constants.NEW_LINE);
        this.message = builder.toString();

        LOGGER.error(getMessage());
    }

    @Override
    public String getMessage() {
        return message;
    }
}
