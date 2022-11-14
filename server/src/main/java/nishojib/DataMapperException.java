package nishojib;

public class DataMapperException extends Exception {
    public DataMapperException(String message) {
        super(message);
        System.out.print("Exception from Mapper");
    }
}
