package PolicyProject.policyService.infrastructure.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serial;

public class DuplicateWeightKeyException extends RuntimeException {


    @Serial
    private static final long serialVersionUID = 1L;
    private static final Logger logger = LoggerFactory.getLogger(DuplicateWeightKeyException.class);

    public DuplicateWeightKeyException(String message, String key) {
        super(message);
        logger.error("Key duplicated: {} - {}", key, message);

    }
}
