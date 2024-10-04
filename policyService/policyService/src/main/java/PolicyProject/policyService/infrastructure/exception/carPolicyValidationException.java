package PolicyProject.policyService.infrastructure.exception;

import java.io.Serial;

public class carPolicyValidationException extends RuntimeException{

    @Serial
    private static final long serialVersionUID = 1L;

    public carPolicyValidationException(String message) {
        super(message);
    }
}
