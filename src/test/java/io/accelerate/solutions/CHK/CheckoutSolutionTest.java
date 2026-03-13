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

    @Test
    void testOnlyNormalPrices() {
        Integer expectedPrice = 100 + 30 + 40 + 45;

        Integer basketPrice = solution.checkout("A,A,B,C,C,D,D,D");

        assertEquals(expectedPrice, basketPrice);
    }

    @Test
    void testOfferPrices() {
        Integer expectedPrice = 130 + 45;

        Integer basketPrice = solution.checkout("A,A,A,B,B");

        assertEquals(expectedPrice, basketPrice);
    }

    @Test
    void testAboveOfferPrices() {
        Integer expectedPrice = 130 + 45 + 50 + 30;

        Integer basketPrice = solution.checkout("A,A,A,A,B,B,B");

        assertEquals(expectedPrice, basketPrice);
    }
}