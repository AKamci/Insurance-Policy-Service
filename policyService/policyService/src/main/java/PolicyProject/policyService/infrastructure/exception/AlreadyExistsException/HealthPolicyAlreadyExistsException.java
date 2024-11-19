package PolicyProject.policyService.infrastructure.exception.AlreadyExistsException;

import PolicyProject.policyService.infrastructure.exception.DuplicateException.DuplicateTcknException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serial;

public class HealthPolicyAlreadyExistsException  extends RuntimeException{
    @Serial
    private static final long serialVersionUID = 1L;
    private static final Logger logger = LoggerFactory.getLogger(DuplicateTcknException.class);

    public HealthPolicyAlreadyExistsException(String message, Long policyId) {
        super(message);
        logger.error("HealthPolicy Already Exists : {} - {}", policyId, message);

    }
}
