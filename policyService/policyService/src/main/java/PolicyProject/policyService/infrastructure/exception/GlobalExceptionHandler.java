package PolicyProject.policyService.infrastructure.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(carPolicyValidationException.class)
    public ResponseEntity<ErrorDetails> handleUserValidationException(carPolicyValidationException exception) {
        ErrorDetails errorDetails = new ErrorDetails(exception.getMessage(), HttpStatus.BAD_REQUEST.value());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDetails);
    }

}
