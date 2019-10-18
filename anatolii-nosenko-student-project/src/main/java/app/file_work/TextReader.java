package app.file_work;

import static app.literals.Constants.NEW_LINE;
import app.exception.AppException;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import org.apache.log4j.Logger;

/**
 * Class to read text files.
 */
public class TextReader {
    private static final Logger LOGGER = Logger.getLogger(TextReader.class.getName());

    /**
     * Method reads text file content.
     *
     * @param file File to read.
     * @return Text file content.
     */
    public String read(File file) {
        if (file == null || !file.exists() || !file.canRead()) {
            LOGGER.info("Can't read file! "
                .concat((file == null) ? ("File is null.") : (file.toString())));
            return null;
        }

        LOGGER.info("Reading file ".concat(file.toString()));

        StringBuilder builder = new StringBuilder();
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                builder.append(scanner.nextLine());
                builder.append(NEW_LINE);
            }
        } catch (IOException e) {
            throw new AppException(e.getMessage());
        }
        return builder.toString();
    }
}
