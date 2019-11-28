package app.model;

import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Test;

public class ConnectionHolderTest {

    private static ConnectionHolder holder;

    @BeforeClass
    public static void init() {
        holder = new ConnectionHolder();
    }

    @Test
    public void getConnection() {
        holder.setConnection(null);
        assertNull(holder.getConnection());
    }

    @AfterClass
    public static void reset() {
        holder = null;
    }
}
