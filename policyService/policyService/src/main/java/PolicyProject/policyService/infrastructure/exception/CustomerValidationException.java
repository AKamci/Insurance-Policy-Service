package PolicyProject.policyService.infrastructure.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serial;

public class CustomerValidationException extends RuntimeException{

    @Serial
    private static final long serialVersionUID = 1L;
    private static final Logger logger = LoggerFactory.getLogger(CustomerValidationException.class);

    public CustomerValidationException(String message) {
        super(message);
        logger.error(message);

    }
}
