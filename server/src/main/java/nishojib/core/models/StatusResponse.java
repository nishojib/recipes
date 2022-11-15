package nishojib.core.models;

/**
 * The Enum for the Status
 */
public enum StatusResponse {
    SUCCESS ("Success"),
    ERROR ("Error");

    /**
     * The status of the status response
     */
    private final String status;

    /**
     * The initializer for the StatusResponse Enum
     */
    StatusResponse(String status) {
        this.status = status;
    }
}