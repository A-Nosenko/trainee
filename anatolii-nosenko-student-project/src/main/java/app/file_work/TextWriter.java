package app.file_work;

import app.structure.exception.AppException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class TextWriter {
    public void write(String content, File file) throws AppException {
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
