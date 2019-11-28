package app;

import org.junit.Test;

public class StartTest {

    @Test
    public void main() {
        try {
            Start.main(new String[]{"arg1"});
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }
}
