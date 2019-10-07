package app.structure.exception;

import static app.literals.Constants.EXCEPTION_BORDER;
import static app.literals.Constants.EXCEPTION_CONTENT;
import static app.literals.Constants.EXCEPTION_MARKER;
import static app.literals.Constants.NEW_LINE;

public class AppException extends RuntimeException {
    private final String message;

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
        char[] messageContent = message.toCharArray();
        for (int i = 1; i <= messageContent.length; i++) {
            builder.append(messageContent[i - 1]);
            if ( i % EXCEPTION_MARKER.length() == 0) {
                builder.append(NEW_LINE);
                builder.append(EXCEPTION_BORDER);
            }
        }
        builder.append(NEW_LINE);

        builder.append(EXCEPTION_BORDER);
        builder.append(EXCEPTION_MARKER);
        builder.append(NEW_LINE);
        this.message = builder.toString();
    }

    @Override
    public String getMessage() {
        return message;
    }
}
