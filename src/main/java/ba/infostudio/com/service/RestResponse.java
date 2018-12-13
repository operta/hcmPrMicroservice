package ba.infostudio.com.service;

public class RestResponse {

    private String message;

    private String subject;

    public RestResponse(){}

    public RestResponse(String message, String subject){
        this.subject = subject;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
