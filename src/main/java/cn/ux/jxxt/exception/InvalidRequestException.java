package cn.ux.jxxt.exception;

import org.springframework.validation.BindingResult;

public class InvalidRequestException extends RuntimeException {

    private static final long serialVersionUID = 5010051724625337125L;

    private String message;
    private BindingResult bindingResult;
    private Boolean isValidationError = false;

    public InvalidRequestException(String message) {
        super(message);
        this.message = message;
    }

    public InvalidRequestException(String message, BindingResult bindingResult) {
        this.message = message;
        this.bindingResult = bindingResult;
        this.isValidationError = true;
    }

    public String getMessage() {
        return message;
    }

    public Boolean getIsValidationError() {
        return isValidationError;
    }

    public BindingResult getBindingResult() {
        return bindingResult;
    }
}
