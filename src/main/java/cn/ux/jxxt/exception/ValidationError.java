package cn.ux.jxxt.exception;

import java.util.List;

public class ValidationError {
    private String message;
    private List<ErrorField> errorFields;

    public ValidationError(String message, List<ErrorField> errorFields) {
        super();
        this.message = message;
        this.errorFields = errorFields;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<ErrorField> getErrorFields() {
        return errorFields;
    }

    public void setErrorFields(List<ErrorField> errorFields) {
        this.errorFields = errorFields;
    }

}
