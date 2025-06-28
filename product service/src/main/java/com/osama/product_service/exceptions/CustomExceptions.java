package com.osama.product_service.exceptions;

public class CustomExceptions {

    public static class ProductNotFoundException extends RuntimeException {
        public ProductNotFoundException(String message) {
            super(message);
        }
    }

    public static class ProductAlreadyExistsException extends RuntimeException {
        public ProductAlreadyExistsException(String message) {
            super(message);
        }
    }

    public static class InvalidCategoryException extends RuntimeException {
        public InvalidCategoryException(String message) {
            super(message);
        }
    }

    public static class OutOfStockException extends RuntimeException {
        public OutOfStockException(String message) {
            super(message);
        }
    }

    public static class InvalidDiscountException extends RuntimeException {
        public InvalidDiscountException(String message) {
            super(message);
        }
    }

    public static class InvalidLocalizedFieldException extends RuntimeException {
        public InvalidLocalizedFieldException(String message) {
            super(message);
        }
    }

    public static class ProductValidationException extends RuntimeException {
        public ProductValidationException(String message) {
            super(message);
        }
    }
    public static class ImageProcessingException extends RuntimeException {
        public ImageProcessingException(String message) {
            super(message);
        }
    }
    public static class ImageStorageException extends RuntimeException {
        public ImageStorageException(String message) {
            super(message);
        }
    }

}
