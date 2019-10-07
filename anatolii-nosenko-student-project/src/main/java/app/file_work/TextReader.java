package app.file_work;

import static app.literals.Constants.NEW_LINE;
import app.structure.exception.AppException;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class TextReader {

    public String read(File file) {
        if (file == null || !file.exists() || !file.canRead()) {
            return null;
        }
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
