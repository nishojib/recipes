package nishojib.core.exceptions;

/**
 * The exception class that is thrown by the Gateways
 */
public class GatewayException extends Exception {
    /**
     * The initializer for the exception class
     * @param message The error message
     */
    public GatewayException(String message) {
        super(message);
        System.out.print("Exception from Gateway");
    }
}
