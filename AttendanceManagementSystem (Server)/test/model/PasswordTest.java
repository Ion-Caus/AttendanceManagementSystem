package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PasswordTest {
    private Password password;

    @Test
    void lowercasePassword() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> password = new Password("superlower")
        );
        assertEquals("Password must contain at least one uppercase letter, " +
                "at least one lowercase letter and at least one digit", exception.getMessage(),
                "Password is all lowercase.");
    }

    @Test
    void uppercasePassword() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> password = new Password("SUPERUPPER")
        );
        assertEquals("Password must contain at least one uppercase letter, " +
                "at least one lowercase letter and at least one digit", exception.getMessage(),
                "Password is all uppercase.");
    }

    @Test
    void noDigitsPassword() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> password = new Password("SUPdassa_UPPER")
        );
        assertEquals("Password must contain at least one uppercase letter, at least one lowercase letter and at least one digit",
                exception.getMessage(), "Password only contains digits.");
    }

    @Test
    void invalidSpecialCharsPassword() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> password = new Password("@helloJd2")
        );
        assertEquals("Password may only contain letters, digits, hyphens amd underscore characters",
                exception.getMessage(), "Password has an illegal special char.");
    }

    @Test
    void invalidLengthPassword() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> password = new Password("Qw1")
        );
        assertEquals("Password must have at least 6 characters",
                exception.getMessage(), "Password's length is less then 6 chars.");
    }

    @Test
    void validLengthPassword() {
        assertDoesNotThrow(
                () -> password = new Password("Qwert1")
        );

        assertDoesNotThrow(
                () -> password = new Password("daweWWrt_1")
        );
    }
}
