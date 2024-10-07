package PolicyProject.policyService.infrastructure.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serial;


public class EntityNotFoundException extends RuntimeException{

    @Serial
    private static final long serialVersionUID = 1L;
    private static final Logger logger = LoggerFactory.getLogger(EntityNotFoundException.class);
    public EntityNotFoundException(Long id,String message) {
        super(message);
        logger.error("Entity not found: {} - {}", id, message);
    }
}
