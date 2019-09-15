package cn.ux.jxxt.exception;

public class ServerException extends RuntimeException {

    private static final long serialVersionUID = -448345479106139177L;

    private String message;

    public ServerException(String message) {
        super();
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
