package com.osama.product_service.exceptions;

import com.osama.product_service.common.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {
    private final MessageSource messageSource;

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Map<String, String>>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        ApiResponse<Map<String, String>> response = ApiResponse.<Map<String, String>>builder()
                .status(HttpStatus.BAD_REQUEST)
                .message(getMessage(
                        "validation.failed",
                        new Object[]{ex.getMessage()})
                )
                .content(errors)
                .build();
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(CustomExceptions.ProductNotFoundException.class)
    public ResponseEntity<ApiResponse<Void>> handleProductNotFound(CustomExceptions.ProductNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ApiResponse.error(getMessage(
                        "product.not.found",
                                new Object[]{ex.getMessage()})
                        , HttpStatus.NOT_FOUND));
    }

    @ExceptionHandler(CustomExceptions.ProductAlreadyExistsException.class)
    public ResponseEntity<ApiResponse<Void>> handleProductAlreadyExists(CustomExceptions.ProductAlreadyExistsException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(ApiResponse.error(getMessage(
                        "product.already.exists",
                                new Object[]{ex.getMessage()})
                        , HttpStatus.CONFLICT));
    }

    @ExceptionHandler(CustomExceptions.InvalidCategoryException.class)
    public ResponseEntity<ApiResponse<Void>> handleInvalidCategory(CustomExceptions.InvalidCategoryException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ApiResponse.error(getMessage(
                        "invalid.category",
                                new Object[]{ex.getMessage()})
                        , HttpStatus.BAD_REQUEST));
    }

    @ExceptionHandler(CustomExceptions.OutOfStockException.class)
    public ResponseEntity<ApiResponse<Void>> handleOutOfStock(CustomExceptions.OutOfStockException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ApiResponse.error(getMessage(
                        "out.of.stock",
                                new Object[]{ex.getMessage()})
                        , HttpStatus.BAD_REQUEST));
    }

    @ExceptionHandler(CustomExceptions.InvalidDiscountException.class)
    public ResponseEntity<ApiResponse<Void>> handleInvalidDiscount(CustomExceptions.InvalidDiscountException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ApiResponse.error(getMessage(
                        "invalid.discount",
                                new Object[]{ex.getMessage()})
                        , HttpStatus.BAD_REQUEST));
    }

    @ExceptionHandler(CustomExceptions.ProductValidationException.class)
    public ResponseEntity<ApiResponse<Void>> handleValidationError(CustomExceptions.ProductValidationException ex) {
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
                .body(ApiResponse.error(getMessage(
                        "product.validation.failed",
                                new Object[]{ex.getMessage()}),
                        HttpStatus.UNPROCESSABLE_ENTITY));
    }

    @ExceptionHandler(CustomExceptions.InvalidLocalizedFieldException.class)
    public ResponseEntity<ApiResponse<Void>> handleInvalidLocalizedField(CustomExceptions.InvalidLocalizedFieldException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ApiResponse.error(getMessage(
                        "invalid.localized.field",
                                new Object[]{ex.getMessage()})
                        , HttpStatus.BAD_REQUEST));
    }
    private  String getMessage(String messageKey, Object[] args ) {
        return messageSource.getMessage(messageKey, args, LocaleContextHolder.getLocale());
    }

}
