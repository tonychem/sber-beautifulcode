package ru.yandex.tonychem.beautifulcodesberproject.unit;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.yandex.tonychem.beautifulcodesberproject.service.BracketUtils;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BracketUtilsTest {
    @ParameterizedTest(name = "{1}")
    @MethodSource("bracketsTestValidValuesSupplier")
    public void bracesValidationAlgorithmTestValidValues(String input, String message) {
        assertTrue(BracketUtils.validateBrackets(input), message);
    }

    @ParameterizedTest(name = "{1}")
    @MethodSource("bracketsTestInvalidValuesSupplier")
    public void bracesValidationAlgorithmTestInvalidValues(String input, String message) {
        assertFalse(BracketUtils.validateBrackets(input), message);
    }

    private static Stream<Arguments> bracketsTestValidValuesSupplier() {
        return Stream.of(
                Arguments.of("some text (another some text)",
                        "Should pass when brackets enclose valid string"),
                Arguments.of("some text (.another text)",
                        "Should pass when text in brackets begin with a non-alphabetic character"),
                Arguments.of("some text (  another some text   )",
                        "Should pass when text in brackets contains leading and trailing spaces"),
                Arguments.of("some text (...another some text...)",
                        "Should pass when text in brackets contains leading and trailing non-alphabetic characters"),
                Arguments.of("(another some text) some text",
                        "Should pass when text begins with bracket-enclosed text"),
                Arguments.of("some (1) text",
                        "Should pass when brackets enclose a single digit"),
                Arguments.of("Some text (first braces text) another text (second braces text)",
                        "Should pass when several valid bracket-enclosed subtexts are present"),
                Arguments.of("some text",
                        "Should pass when text without parenthesis is passed"),
                Arguments.of("some (nested (parenthesis))",
                        "Should pass when valid nested parenthesis is passed"),
                Arguments.of("((some text))",
                        "Should pass when outer nested brackets enclose inner nested brackets with text")
        );
    }

    @MethodSource
    private static Stream<Arguments> bracketsTestInvalidValuesSupplier() {
        return Stream.of(
                Arguments.of("some text ()",
                        "Should fail when brackets are empty"),
                Arguments.of("some text (  )",
                        "Should fail when brackets are blank"),
                Arguments.of("some text )(",
                        "Should fail when bracket order is invalid"),
                Arguments.of("some text (.)",
                        "Should fail when brackets enclose a non-alphabetic character"),
                Arguments.of(")some text(",
                        "Should fail when bracket order is invalid for brace-enclosed text"),
                Arguments.of("some text (another some text) another (some text",
                        "Should fail when text contains unclosed bracket order")
        );
    }
}