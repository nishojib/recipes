package nishojib.core.models;

import com.google.gson.JsonElement;

/**
 * The Response class that is returned by the REST apis
 */
public class StandardResponse {

    /**
     * The status of the rest api
     */
    private final StatusResponse status;
    /**
     * The message of the rest api
     */
    private String message;

    /**
     * The JSON data that is returned by the rest api
     */
    private JsonElement data;

    /**
     * One of the initializers of the Standard Response
     * @param status The status of the rest api
     */
    public StandardResponse(StatusResponse status) {
        this.status = status;
    }

    /**
     * One of the initializers of the Standard Response
     * @param status The status of the rest api
     * @param message The message of the rest api
     */
    public StandardResponse(StatusResponse status, String message) {
        this.status = status;
        this.message = message;
    }

    /**
     * One of the initializers of the Standard Response
     * @param status The status of the rest api
     * @param data The data of the rest api
     */
    public StandardResponse(StatusResponse status, JsonElement data) {
        this.status = status;
        this.data = data;
    }
}