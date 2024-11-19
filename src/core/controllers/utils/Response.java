package core.controllers.utils;

public class Response {
    
    private String message;
    private int status;
    private Object object;

    public Response(String message, int status, Object object) {
        this.message = message;
        this.status = status;
        this.object = object;
    }
    
    public Response(String message, int status) {
        this.message = message;
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public Object getObject() {
        return object;
    }

    public int getStatus() {
        return status;
    }

}
