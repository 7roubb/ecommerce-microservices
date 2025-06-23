package com.osama.product_service.common;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Map;
import java.util.Optional;

public class LocalizedEntriesValidator implements ConstraintValidator<ValidLocalizedEntries, Map<String,String>> {
    @Override
    public boolean isValid(Map<String, String> value, ConstraintValidatorContext context) {

        return Optional.
                ofNullable(value)
                .map(m-> m.entrySet().stream()
                        .noneMatch(
                                e -> e.getKey() == null || e.getKey().isBlank()
                                || e.getValue() == null || e.getValue().isBlank()
                        )).orElse(true);
    }
}
