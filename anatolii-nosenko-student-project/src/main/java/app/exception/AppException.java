package app.exception;

import static app.literals.Constants.EXCEPTION_BORDER;
import static app.literals.Constants.EXCEPTION_CONTENT;
import static app.literals.Constants.EXCEPTION_MARKER;
import static app.literals.Constants.NEW_LINE;
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
        builder.append(NEW_LINE);
        builder.append(EXCEPTION_BORDER);
        builder.append(EXCEPTION_MARKER);
        builder.append(NEW_LINE);
        builder.append(EXCEPTION_BORDER);
        builder.append(EXCEPTION_CONTENT);
        builder.append(NEW_LINE);
        builder.append(EXCEPTION_BORDER);
        char[] messageContent = message
            .replace("\n", " ")
            .toCharArray();
        for (int i = 1; i <= messageContent.length; i++) {
            builder.append(messageContent[i - 1]);
            if (i % EXCEPTION_MARKER.length() == 0) {
                builder.append(NEW_LINE);
                builder.append(EXCEPTION_BORDER);
            }
        }
        builder.append(NEW_LINE);

        builder.append(EXCEPTION_BORDER);
        builder.append(EXCEPTION_MARKER);
        builder.append(NEW_LINE);
        this.message = builder.toString();

        LOGGER.error(getMessage());
    }

    @Override
    public String getMessage() {
        return message;
    }
}
