package com.anysound.validator;

import jakarta.faces.validator.ValidatorException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class FlightNumberValidatorTest {
    private FlightNumberValidator validator;

    @BeforeEach
    void setUp() {
        validator = new FlightNumberValidator();
    }

    @Test
    void validate_validFlightNumber_doesNotThrow() {
        assertDoesNotThrow(() -> validator.validate(null, null, "AB-1234"));
    }

    @Test
    void validate_validFlightNumberShort_doesNotThrow() {
        assertDoesNotThrow(() -> validator.validate(null, null, "CD-567"));
    }

    @Test
    void validate_nullValue_doesNotThrow() {
        assertDoesNotThrow(() -> validator.validate(null, null, null), "Null должен обрабатываться атрибутом required");
    }

    @Test
    void validate_emptyValue_doesNotThrow() {
        assertDoesNotThrow(() -> validator.validate(null, null, "   "));
    }

    @Test
    void validate_invalidFormatWithoutDash_throwsException() {
        assertThrows(ValidatorException.class, () -> validator.validate(null, null, "AB1234"));
    }
}