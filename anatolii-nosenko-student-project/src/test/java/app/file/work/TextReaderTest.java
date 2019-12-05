package app.file.work;

import java.io.File;
import java.io.IOException;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class TextReaderTest {
    private static File file = new File("src/test/test_data/test.txt");
    private static TextReader reader = new TextReader();
    private static File file_IOException = new File("src/test/test_data/test_IOException_1");

    @Test
    public void read() {
        assert (reader.read(file) == null);
    }

    @Test
    public void readException() {
        String line = "test";
        try {
            file_IOException.createNewFile();
            line = reader.read(file_IOException);
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertEquals(line, "");
    }

    @AfterClass
    public static void methodAfterClass() {
        file.delete();
        file_IOException.delete();
    }
}
