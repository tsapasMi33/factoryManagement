package be.tsapasmi.factorymanagement;

import be.tsapasmi.factorymanagement.bl.exceptions.*;
import be.tsapasmi.factorymanagement.web.models.dto.ErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class ControllerAdvisor {

    @ExceptionHandler({
            ResourceNotFoundException.class,
            UserNotFoundException.class
    })
    public ResponseEntity<ErrorDTO> handle(ResourceNotFoundException ex) {
        ErrorDTO body = ErrorDTO.builder(LocalDateTime.now(), HttpStatus.NOT_FOUND.value())
                .putError("message", ex.getMessage())
                .build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
    }

    @ExceptionHandler({
            IllegalActionOnStep.class,
            IllegalCollectionException.class,
            NotStartedStepException.class,
            PausedStepException.class
    })
    public ResponseEntity<ErrorDTO> handle(IllegalCollectionException ex) {
        ErrorDTO body = ErrorDTO.builder(LocalDateTime.now(), HttpStatus.UNPROCESSABLE_ENTITY.value())
                .putError("message", ex.getMessage())
                .build();

        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(body);
    }

    @ExceptionHandler({
            BadPathException.class,
            BatchInSingleProductStepException.class,
            FinishedStepException.class,
            SingleProductInBatchStepException.class,
            UserOccupiedException.class,
            UserStateException.class
    })
    public ResponseEntity<ErrorDTO> handle(RuntimeException ex) {
        ErrorDTO body = ErrorDTO.builder(LocalDateTime.now(), HttpStatus.BAD_REQUEST.value())
                .putError("message", ex.getMessage())
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDTO> handle(MethodArgumentNotValidException ex){

        Map<String, Object> errors = new HashMap<>();

        List<Map<String, Object>> globalErrors = new ArrayList<>();

        ex.getGlobalErrors().forEach( globalError -> {
            Map<String, Object> error = new HashMap<>();
            error.put( "message", globalError.getDefaultMessage() );
            error.put( "codes", globalError.getCodes() );
            globalErrors.add(error);
        } );

        errors.put( "global_validation_errors", globalErrors );
        List<Map<String, Object>> fieldErrors = new ArrayList<>();

        ex.getFieldErrors().forEach( fieldError -> {
            Map<String, Object> error = new HashMap<>();
            error.put( "message", fieldError.getDefaultMessage() );
            error.put( "code", fieldError.getCode() );
            error.put( "rejectedValue", fieldError.getRejectedValue());
            error.put( "fieldName", fieldError.getField() );
            fieldErrors.add(error);
        } );

        errors.put( "field_validation_errors", fieldErrors);


        ErrorDTO body = ErrorDTO.builder(LocalDateTime.now(), HttpStatus.UNPROCESSABLE_ENTITY.value())
                .putError("errors", errors)
                .build();

        return ResponseEntity.unprocessableEntity()
                .body(body);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorDTO> handle(BadCredentialsException ex){
        ErrorDTO body = ErrorDTO.builder(LocalDateTime.now(), HttpStatus.UNAUTHORIZED.value())
                .putError("message", ex.getMessage())
                .build();

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(body);
    }
}
