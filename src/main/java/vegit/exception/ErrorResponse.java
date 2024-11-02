package vegit.exception;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Ihan vaan basic viesti erroreista, sisällön voi määritellä vapaasti
public class ErrorResponse {
    private String message;
    private int status;
    private LocalDateTime timestamp;
    private String path;
    private List<Map<String, String>> validationErrors; // Validaatiovirheiden listaus
    
    public List<Map<String, String>> getValidationErrors() {
        return validationErrors;
    }

    public void setValidationErrors(List<Map<String, String>> validationErrors) {
        this.validationErrors = validationErrors;
    }

    public ErrorResponse(String message) {
        this.message = message;
    }

    // Lisätty bodyyn lisätietoja messagen lisäksi
    public ErrorResponse(String message, int status, LocalDateTime timestamp, String path) {
        this.message = message;
        this.status = status;
        this.timestamp = timestamp;
        this.path = path;
        this.validationErrors = new ArrayList<>();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    // Validaatiovirheiden viesti
    public void addValidationError(String field, String errorMessage) {
        Map<String, String> errorDetail = new HashMap<>();
        errorDetail.put("field", field);
        errorDetail.put("errorMessage", errorMessage);
        this.validationErrors.add(errorDetail);
    }

}