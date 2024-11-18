package PolicyProject.policyService.infrastructure.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serial;

public class DuplicateTcknException extends RuntimeException {


    @Serial
    private static final long serialVersionUID = 1L;
    private static final Logger logger = LoggerFactory.getLogger(DuplicateTcknException.class);

    public DuplicateTcknException(String message, String tckn) {
        super(message);
        logger.error("TCKN duplicated: {} - {}", tckn, message);

    }
}
