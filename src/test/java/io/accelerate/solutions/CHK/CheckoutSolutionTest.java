package io.accelerate.solutions.CHK;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class CheckoutSolutionTest {

    private static CheckoutSolution solution;

    @BeforeAll
    static void setUp() {
        solution = new CheckoutSolution();
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {",,,,", "AA,B,C,D", "1,A,B"})
    void testValidatesInvalidInput(String input) {
        Integer basketPrice = solution.checkout(input);

        assertEquals(-1, basketPrice);
    }

}