package passwordpkg;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PasswordValidatorTest {

    @ParameterizedTest(name = "#{index} - Run test with password = {0}")
    @MethodSource("validPasswordProvider")
    void test_password_regex_valid(String password) {
        assertTrue(PasswordValidator.isValid(password));
    }

    @ParameterizedTest(name = "#{index} - Run test with password = {0}")
    @MethodSource("invalidPasswordProvider")
    void test_password_regex_invalid(String password) {
        assertFalse(PasswordValidator.isValid(password));
    }

    static Stream<String> validPasswordProvider() {
        return Stream.of(
                "Nibedita@123",
                "123Nibedita",
                "Nibedit9" // Testcase should fail as length is 8 char long instead of larger than 8
        );
    }

    // At least
    // one lowercase character,
    // one uppercase character,
    // one digit,
    // and length larger than 8.
    static Stream<String> invalidPasswordProvider() {
        return Stream.of(
                "123456789",           // invalid, only digit
                "abcdefgh",                 // invalid, only lowercase
                "ABCDEFGH",                 // invalid, only uppercase
                "abc123$$$",                // invalid, at least one uppercase
                "ABC123$$$",                // invalid, at least one lowercase
                "ABC$$$$$$",                // invalid, at least one digit
                "12345678",                 // length larger than 8
                " ",                        //empty
                "",                         //empty
                "Nibedita1");               // Valid Value, testcase should fail
    }
}