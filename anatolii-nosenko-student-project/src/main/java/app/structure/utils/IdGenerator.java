package app.structure.utils;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Class to generate unique ids.
 */
public final class IdGenerator {
    private AtomicLong id = new AtomicLong();
    private static IdGenerator idGenerator;

    private IdGenerator() {
    }

    /**
     * Method to obtain IdGenerator instance.
     *
     * @return IdGenerator singleton object.
     */
    public static IdGenerator getInstance() {
        if (idGenerator == null) {
            idGenerator = new IdGenerator();
        }
        return idGenerator;
    }

    /**
     * Method to generate unique ids.
     *
     * @return unique id.
     */
    public long getNext() {
        return id.incrementAndGet();
    }
}
