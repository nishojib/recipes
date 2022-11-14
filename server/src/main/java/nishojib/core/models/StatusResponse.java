package nishojib.core.models;

public enum StatusResponse {
    SUCCESS ("Success"),
    ERROR ("Error");

    private final String status;

    StatusResponse(String status) {
        this.status = status;
    }
}