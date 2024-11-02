package vegit.exception;

import java.time.LocalDateTime;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;

@ControllerAdvice
public class GlobalExceptionHandler {

   
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFoundException(
            ResourceNotFoundException ex, WebRequest request) {
         // Luo ErrorResponse, jossa on viesti, statuskoodi, aikaleima sekä path
        ErrorResponse errorResponse = new ErrorResponse(
            ex.getMessage(),
            HttpStatus.NOT_FOUND.value(),
            LocalDateTime.now(),
            request.getDescription(false) 
        );
        // Palautetaan se
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {
        ErrorResponse errorResponse = new ErrorResponse(
            "Virheellinen syöte",
            HttpStatus.BAD_REQUEST.value(),
            LocalDateTime.now(),
            "Validaatiovirhe"
        );

        // Validaatiovirheen yksityiskohdat (mikä kenttä, mikä vialla)
        for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
            errorResponse.addValidationError(fieldError.getField(), fieldError.getDefaultMessage());
        }

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> handleConstraintViolationException(ConstraintViolationException ex, WebRequest request) {
    // Viestin muodostaminen
    StringBuilder errorMessage = new StringBuilder("Validaatiovirhe: ");
    for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
        String fieldName = violation.getPropertyPath().toString();
        String message = violation.getMessage();
        errorMessage.append(fieldName).append(": ").append(message).append(", ");
    }

    // Viimeisestä pilkku pois
    if (errorMessage.length() > 0) {
        errorMessage.setLength(errorMessage.length() - 2);
    }

    // Virheviestin luominen
    ErrorResponse errorResponse = new ErrorResponse(
        errorMessage.toString(),
        HttpStatus.BAD_REQUEST.value(),
        LocalDateTime.now(),
        request.getDescription(false)
        );

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(JsonParseException.class)
    public ResponseEntity<ErrorResponse> handleJsonParseException(JsonParseException ex, WebRequest request) {
    ErrorResponse errorResponse = new ErrorResponse(
        "Virheellinen JSON-muoto",
        HttpStatus.BAD_REQUEST.value(),
        LocalDateTime.now(),
        request.getDescription(false)
        );

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(JsonMappingException.class)
    public ResponseEntity<ErrorResponse> handleJsonMappingException(JsonMappingException ex, WebRequest request) {
    String errorMessage = "Virheellinen JSON-muoto: Tarkista syötteen rakenne.";
    
    ErrorResponse errorResponse = new ErrorResponse(
        errorMessage,
        HttpStatus.BAD_REQUEST.value(),
        LocalDateTime.now(),
        request.getDescription(false)
        );
    
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    
    
    
    
}