package cn.ux.jxxt.exception;

import cn.ux.jxxt.vo.GeneralMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @Autowired
    private GeneralMessage generalMessage;

    @ExceptionHandler({InvalidRequestException.class})
    @ResponseBody
    public ResponseEntity<?> handleInvalidRequestException(InvalidRequestException ex) {
        if (ex.getIsValidationError()) {
            BindingResult bindingResult = ex.getBindingResult();
            List<ErrorField> errorFields = bindingResult.getFieldErrors().stream()
                    .map(fieldError -> new ErrorField(fieldError.getField(),
                            fieldError.getDefaultMessage())).collect(Collectors.toList());
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(new ValidationError(ex.getMessage(), errorFields));
        } else {
            generalMessage.setMessage(ex.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(generalMessage);
        }
    }

    @ExceptionHandler({ServerException.class})
    @ResponseBody
    public ResponseEntity<?> handleServerException(ServerException ex) {
        generalMessage.setMessage(ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(generalMessage);
    }
}
