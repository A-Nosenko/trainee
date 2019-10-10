package app.file_work;

import app.structure.exception.AppException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

/**
 * Class to write text files.
 */
public class TextWriter {

    /**
     * Method writes text to file.
     *
     * @param content Text to be written.
     * @param file File to write text, if file not exists, it will be created.
     */
    public void write(String content, File file) {
        if (file == null) {
            throw new AppException("Illegal argument, file is null.");
        } else if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new AppException("Can't create file " + file.getAbsolutePath());
            }

        }
        try (Writer writer = new FileWriter(file)) {
            writer.write(content);
        } catch (IOException e) {
            throw new AppException(e.getMessage());
        }
    }
}
