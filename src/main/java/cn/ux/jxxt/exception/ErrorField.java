package cn.ux.jxxt.exception;

public class ErrorField {
    private String field;
    private String error_message;

    public ErrorField(String field, String error_message) {
        this.field = field;
        this.error_message = error_message;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getError_message() {
        return error_message;
    }

    public void setError_message(String error_message) {
        this.error_message = error_message;
    }
}
