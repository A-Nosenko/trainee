package app.exception;

import app.literals.Constants;
import org.junit.Test;

public class AppExceptionTest {

    @Test
    public void getMessage() {
        AppException exception = new AppException(null);
        assert (exception.getMessage().contains(Constants.EXCEPTION_MARKER));
    }

    @Test(expected = RuntimeException.class)
    public void runtimeException() {
        throw new AppException(null);
    }
}
