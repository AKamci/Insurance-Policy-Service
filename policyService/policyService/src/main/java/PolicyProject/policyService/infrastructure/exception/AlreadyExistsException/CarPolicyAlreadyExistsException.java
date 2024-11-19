package PolicyProject.policyService.infrastructure.exception.AlreadyExistsException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serial;

public class CarPolicyAlreadyExistsException extends RuntimeException{
    @Serial
    private static final long serialVersionUID = 1L;
    private static final Logger logger = LoggerFactory.getLogger(CarPolicyAlreadyExistsException.class);

    public CarPolicyAlreadyExistsException(String message, Long policyId) {
        super(message);
        logger.error("Car Already Exists : {} - {}", policyId, message);

    }
}
