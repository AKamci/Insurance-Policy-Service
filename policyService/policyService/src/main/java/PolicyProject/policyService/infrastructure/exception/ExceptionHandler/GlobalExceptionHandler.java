package PolicyProject.policyService.infrastructure.exception.ExceptionHandler;


import PolicyProject.policyService.infrastructure.exception.*;

import PolicyProject.policyService.infrastructure.exception.AlreadyExistsException.CarPolicyAlreadyExistsException;
import PolicyProject.policyService.infrastructure.exception.AlreadyExistsException.EarthquakePolicyAlreadyExistsException;
import PolicyProject.policyService.infrastructure.exception.AlreadyExistsException.HealthPolicyAlreadyExistsException;
import PolicyProject.policyService.infrastructure.exception.DuplicateException.DuplicateTcknException;
import PolicyProject.policyService.infrastructure.exception.DuplicateException.DuplicateWeightKeyException;
import PolicyProject.policyService.infrastructure.exception.ExpiredException.ExpiredMedicalReportException;
import PolicyProject.policyService.infrastructure.exception.ValidationException.CarPolicyValidationException;
import PolicyProject.policyService.infrastructure.exception.ValidationException.CustomerValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CarPolicyValidationException.class)
    public ResponseEntity<ErrorDetails> handleCarPolicyValidationException(CarPolicyValidationException exception) {
        ErrorDetails errorDetails = new ErrorDetails(exception.getMessage(), HttpStatus.BAD_REQUEST.value());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDetails);
    }

    @ExceptionHandler(CustomerValidationException.class)
    public ResponseEntity<ErrorDetails> handleCustomerValidationException(CustomerValidationException exception) {
        ErrorDetails errorDetails = new ErrorDetails(exception.getMessage(), HttpStatus.BAD_REQUEST.value());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDetails);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleEntityNotFoundValidationException(EntityNotFoundException exception) {
        ErrorDetails errorDetails = new ErrorDetails(exception.getMessage(), HttpStatus.NOT_FOUND.value());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDetails);
    }

    @ExceptionHandler(DuplicateTcknException.class)
    public ResponseEntity<ErrorDetails> handleDuplicateTcknException(DuplicateTcknException exception) {
        ErrorDetails errorDetails = new ErrorDetails(exception.getMessage(), HttpStatus.BAD_REQUEST.value());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorDetails);
    }

    @ExceptionHandler(DuplicateWeightKeyException.class)
    public ResponseEntity<ErrorDetails> handleDuplicateWeightKeyException(DuplicateWeightKeyException exception) {
        ErrorDetails errorDetails = new ErrorDetails(exception.getMessage(), HttpStatus.BAD_REQUEST.value());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorDetails);
    }

    @ExceptionHandler(ExpiredMedicalReportException.class)
    public ResponseEntity<ErrorDetails> handleExpiredMedicalReportException(ExpiredMedicalReportException exception) {
        ErrorDetails errorDetails = new ErrorDetails(exception.getMessage(), HttpStatus.FORBIDDEN.value());
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(errorDetails);
    }
    @ExceptionHandler(HealthPolicyAlreadyExistsException.class)
    public ResponseEntity<ErrorDetails> handleHealthPolicyAlreadyExistsException(HealthPolicyAlreadyExistsException exception) {
        ErrorDetails errorDetails = new ErrorDetails(exception.getMessage(), HttpStatus.CONFLICT.value());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorDetails);
    }

    @ExceptionHandler(CarPolicyAlreadyExistsException.class)
    public ResponseEntity<ErrorDetails> handleHealthPolicyAlreadyExistsException(CarPolicyAlreadyExistsException exception) {
        ErrorDetails errorDetails = new ErrorDetails(exception.getMessage(), HttpStatus.CONFLICT.value());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorDetails);
    }

    @ExceptionHandler(EarthquakePolicyAlreadyExistsException.class)
    public ResponseEntity<ErrorDetails> handleHealthPolicyAlreadyExistsException(EarthquakePolicyAlreadyExistsException exception) {
        ErrorDetails errorDetails = new ErrorDetails(exception.getMessage(), HttpStatus.CONFLICT.value());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorDetails);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage()));
        return ResponseEntity.badRequest().body(errors);
    }

}
