package nishojib.core.exceptions;

public class GatewayException extends Exception {
    public GatewayException(String message) {
        super(message);
        System.out.print("Exception from Gateway");
    }
}
