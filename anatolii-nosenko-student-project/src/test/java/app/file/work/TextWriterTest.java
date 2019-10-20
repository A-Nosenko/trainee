package app.file.work;

import app.exception.AppException;
import java.io.File;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Test;

public class TextWriterTest {

    private static File file = new File("src/test/test_data/test.txt");
    private static File file_IOException = new File("src/test/test_data/test_IOException_2");
    private static TextReader reader = new TextReader();
    private static TextWriter writer = new TextWriter();
    private static final String LINE = "testing test";

    @Test
    public void write() {
        writer.write(LINE, file);
        assertEquals(LINE, reader.read(file).trim());
    }

    @Test(expected = AppException.class)
    public void writeToNullFileException() {
        writer.write(null, null);
    }

    @Test(expected = AppException.class)
    public void creationFileException() {
        writer.write("", new File("\r\r"));
    }

    @AfterClass
    public static void methodAfterClass() {
        file.delete();
        file_IOException.delete();
    }
}