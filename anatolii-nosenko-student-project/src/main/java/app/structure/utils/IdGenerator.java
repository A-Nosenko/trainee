package app.structure.utils;

public class IdGenerator {

    private int id;

    private static IdGenerator idGenerator;

    private IdGenerator() {
    }

    public static IdGenerator getInstance() {
        if (idGenerator == null) {
            idGenerator = new IdGenerator();
        }
        return idGenerator;
    }

    public int getNext() {
        return ++id;
    }
}
