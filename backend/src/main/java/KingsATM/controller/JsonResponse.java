package KingsATM.controller;

public class JsonResponse<T> {

    /**
     * True if the request succeeded, or the
     * error field should be examined.
     */
    private boolean success = true;

    /**
     * A generic placeholder for a number of
     * different types of response object. This
     * could be a String or a populated DTO.
     */
    private T result = null;

    /**
     * A descriptive error string, only set
     * when the status value is "FAIL".
     */
    private String error = null;

    /**
     * Constructor.
     */
    public JsonResponse() {
    }

    /**
     * Complex Constructor.
     *
     * @param success The state of the request operation.
     * @param result  The data being returned as the request result.
     * @param error   A string filled in to describe errors when success is false.
     */
    public JsonResponse(boolean success, T result, String error) {
        this.success = success;
        this.result = result;
        this.error = error;
    }

    /**
     * Create a success response with the result and success automatically set to true.
     * @param result the result to return.
     */
    public JsonResponse(T result) {
        this.result = result;
    }

    /**
     * Create an error response with a success flag always set to false and the error string.
     * @param success the success flag (always set to false).
     * @param error the error string.
     */
    public JsonResponse(boolean success, String error) {
        this.success = success;
        this.error = error;
    }

    /**
     * @return the success
     */
    public boolean isSuccess() {
        return success;
    }

    /**
     * @param success the success to set
     */
    public void setSuccess(boolean success) {
        this.success = success;
    }

    /**
     * @return the result
     */
    public T getResult() {
        return result;
    }

    /**
     * @param result the result to set
     */
    public void setResult(T result) {
        this.result = result;
    }

    /**
     * @return the error
     */
    public String getError() {
        return error;
    }

    /**
     * @param error the error to set
     */
    public void setError(String error) {
        this.error = error;
    }
}
