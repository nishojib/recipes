package nishojib.core.exceptions;

/**
 * The exception class that is thrown by the Data Mappers
 */
public class DataMapperException extends Exception {
    /**
     * The initializer for the exception class
     * @param message The error message
     */
    public DataMapperException(String message) {
        super(message);
        System.out.print("Exception from Mapper");
    }
}
