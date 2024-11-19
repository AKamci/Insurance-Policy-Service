package PolicyProject.policyService.infrastructure.exception.ValidationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serial;

public class CarPolicyValidationException extends RuntimeException{

    @Serial
    private static final long serialVersionUID = 1L;
    private static final Logger logger = LoggerFactory.getLogger(CarPolicyValidationException.class);


    public CarPolicyValidationException(String message) {
        super(message);
        logger.error(message);
    }
}
