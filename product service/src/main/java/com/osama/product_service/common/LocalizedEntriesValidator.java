package com.osama.product_service.common;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Map;

public class LocalizedEntriesValidator implements ConstraintValidator<ValidLocalizedEntries, Map<String, String>> {

    private Class<?>[] groups;

    @Override
    public void initialize(ValidLocalizedEntries constraintAnnotation) {
        this.groups = constraintAnnotation.groups();
    }

    @Override
    public boolean isValid(Map<String, String> value, ConstraintValidatorContext context) {
        if (value == null) return true;
        boolean isUpdate = groupsContain("OnUpdate");
        boolean isCreate = groupsContain("OnCreate");

        for (Map.Entry<String, String> entry : value.entrySet()) {
            String key = entry.getKey();
            String val = entry.getValue();

            if (isCreate) {
                if (key == null || key.isBlank() || val == null || val.isBlank()) return false;
            }

            if (isUpdate) {
                boolean keyBlank = key == null || key.isBlank();
                boolean valBlank = val == null || val.isBlank();
                if ((keyBlank && !valBlank) || (!keyBlank && valBlank)) return false;
            }
        }

        return true;
    }

    private boolean groupsContain(String name) {
        for (Class<?> group : groups) {
            if (group.getSimpleName().equals(name)) return true;
        }
        return false;
    }
}
