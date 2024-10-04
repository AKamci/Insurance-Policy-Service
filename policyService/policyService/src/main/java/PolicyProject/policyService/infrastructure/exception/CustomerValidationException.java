package PolicyProject.policyService.infrastructure.exception;

import java.io.Serial;

public class CustomerValidationException extends RuntimeException{

    @Serial
    private static final long serialVersionUID = 1L;

    public CustomerValidationException(String message) {
        super(message);
    }
}
