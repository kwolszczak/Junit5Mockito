package pl.kwolszczak.calculator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import pl.kwolszczak.calculator.Calculator;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


class CalculatorTest {

    public static Stream<Arguments> dataProvider() {
        return Stream.of(
                Arguments.of(5, 2, 3),
                Arguments.of(10, 10, 0),
                Arguments.of(8, 10, -2)
        );
    }

    @RepeatedTest(3)
    void test_calculator_positiveValues(RepetitionInfo repetitionInfo) {
        System.out.println(repetitionInfo.getCurrentRepetition()+"/"+repetitionInfo.getTotalRepetitions());
        int a = 2;
        int b = 1;
        int expectedResult = 2;
        Calculator calculator = new Calculator();

        var result = calculator.integerDivision(a, b);

        assertEquals(expectedResult, result, "fail");
    }

    @ParameterizedTest
    @MethodSource("dataProvider")
    void test_integerSubtraction_positiveResult(int a, int b, int expectedResult) {
        Calculator calculator = new Calculator();

        var result = calculator.integerSubtraction(a, b);

        assertEquals(expectedResult, result, () -> "failed once again");
    }

    @Test
    @DisplayName("A-B")
    void testIntegerSubtraction_whenAgreatherThenB_shouldReturnPositive() {
        int a = 5;
        int b = 2;
        int expectedResult = 3;
        Calculator calculator = new Calculator();

        var result = calculator.integerSubtraction(a, b);

        assertEquals(expectedResult, result, () -> "failed once again");
    }

    //  @Disabled("To do later")
    @Test
    @DisplayName("Diveded by zero")
    void testintegerDivision_whenDivededByZero_shouldThrowException() {
        int a = 5;
        int b = 777899978;
        Calculator calculator = new Calculator();


        ArithmeticException exception = assertThrows(ArithmeticException.class,
                () -> calculator.integerDivision(a, b),
                () -> "fail. Check if you have exception , check if you divide by zero"
        );

        assertEquals("/ by zero", exception.getMessage());
    }

}