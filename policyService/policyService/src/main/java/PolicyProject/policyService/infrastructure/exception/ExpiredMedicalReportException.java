package PolicyProject.policyService.infrastructure.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serial;

public class ExpiredMedicalReportException extends RuntimeException{

    @Serial
    private static final long serialVersionUID = 1L;
    private static final Logger logger = LoggerFactory.getLogger(ExpiredMedicalReportException.class);
    public ExpiredMedicalReportException(Long id,String message) {
        super(message);
        logger.error("Expired Medical Report: {} - {}", id, message);
    }
}
