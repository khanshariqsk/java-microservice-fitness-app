package com.fitness.userservice.exception;

import com.fitness.userservice.config.app.AppProperties;
import com.fitness.userservice.constants.message.common.CommonErrorMessages;
import com.fitness.userservice.dto.common.ApiResponse;
import jakarta.validation.ConstraintViolationException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.naming.AuthenticationException;

@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

    private final AppProperties appProperties;

    //  <========================== Client Side Exceptions ===============================>

    // 🔹 Handle ALL custom API exceptions
    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ApiResponse<Void>> handleApiException(ApiException ex) {
        HttpStatus status = ex.getStatus();

        return ResponseEntity.status(status).body(errorResponse(ex.getMessage(), status));
    }

    // 🔹 Validation errors (@Valid on request DTOs)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Void>> handleValidationException(MethodArgumentNotValidException ex) {

        FieldError error = ex.getBindingResult().getFieldError();
        String message = error != null ? resolveValidationMessage(error) : CommonErrorMessages.VALIDATION_FAILED;

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse(message, HttpStatus.BAD_REQUEST));
    }

    // 🔹 Constraint violations (e.g. @RequestParam,@PathVariable,@RequestHeader,@Validated validation)
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ApiResponse<Void>> handleConstraintViolation(ConstraintViolationException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse(ex.getMessage(), HttpStatus.BAD_REQUEST));
    }

    // 🔹 Request body is missing, empty, or contains malformed JSON (Triggered when @RequestBody fails during deserialization (before validation runs))
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiResponse<Void>> handleHttpMessageNotReadable(HttpMessageNotReadableException ex) {

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse(CommonErrorMessages.INVALID_REQUEST_BODY, HttpStatus.BAD_REQUEST));
    }


    // 🔹 No matching API endpoint found for the requested URL (Triggered when the client calls a non-existing route (404 Not Found))
    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<ApiResponse<Void>> handleNotFound(NoHandlerFoundException ex) {

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse(CommonErrorMessages.ENDPOINT_NOT_FOUND, HttpStatus.NOT_FOUND));
    }

    // 🔹 HTTP method not allowed (wrong HTTP verb for the endpoint)
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ApiResponse<Void>> handleMethodNotSupported(
            HttpRequestMethodNotSupportedException ex) {

        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(errorResponse(CommonErrorMessages.METHOD_NOT_ALLOWED, HttpStatus.METHOD_NOT_ALLOWED));
    }


    // 🔹 Authentication failure (disabled user, locked account, expired credentials)(Covers all Spring Security authentication error)
    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ApiResponse<Void>> handleAuthenticationException(
            AuthenticationException ex) {

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(errorResponse(CommonErrorMessages.AUTHENTICATION_FAILED, HttpStatus.UNAUTHORIZED));
    }

    // 🔹 Database unique / integrity constraint violation (e.g. duplicate email, unique key conflict)((Triggered when a request conflicts with existing database state — client can fix this))
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ApiResponse<Void>> handleDataIntegrityViolation(
            DataIntegrityViolationException ex) {

        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse(CommonErrorMessages.RESOURCE_ALREADY_EXISTS, HttpStatus.CONFLICT));
    }


    //  <========================== Server Side Exceptions ===============================>

    // 🔹 Database / persistence layer errors (SQL, constraints, connectivity, timeouts)
    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<ApiResponse<Void>> handleDatabaseException(DataAccessException ex) {
        ex.printStackTrace(); // NOTE:: needs to update this with logger

        String message = appProperties.isDebugErrors() ? ex.getMostSpecificCause().getMessage() : CommonErrorMessages.DATABASE_ERROR;

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse(message, HttpStatus.INTERNAL_SERVER_ERROR));
    }

    // 🔹 Fallback: INTERNAL SERVER ERROR (500)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Void>> handleGenericException(Exception ex) {

        ex.printStackTrace(); // NOTE:: needs to update this with logger

        String message = appProperties.isDebugErrors() ? ex.getMessage() : CommonErrorMessages.INTERNAL_SERVER_ERROR;

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse(message, HttpStatus.INTERNAL_SERVER_ERROR));
    }


    //  <========================== Static Methods from here ===============================>

    private static String resolveValidationMessage(FieldError err) {
        String defaultMessage = err.getDefaultMessage();
        if (defaultMessage != null && defaultMessage.toLowerCase().contains(err.getField().toLowerCase())) {
            return defaultMessage;
        }

        return capitalizeString(err.getField()) + " " + defaultMessage;
    }

    private static String capitalizeString(String fieldName) {
        if (fieldName == null || fieldName.isBlank()) return fieldName;
        return Character.toUpperCase(fieldName.charAt(0)) + fieldName.substring(1);
    }

    private static <T> ApiResponse<T> errorResponse(String message, HttpStatus status) {
        return new ApiResponse<>(false, status.value(), message, null);
    }
}
