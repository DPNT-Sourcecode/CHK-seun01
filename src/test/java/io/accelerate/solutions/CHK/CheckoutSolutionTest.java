package io.accelerate.solutions.CHK;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CheckoutSolutionTest {

    private static CheckoutSolution solution;

    @BeforeAll
    static void setUp() {
        solution = new CheckoutSolution();
    }

    @Test
    void testEmptyInput() {
        Integer basketPrice = solution.checkout("");

        assertEquals(0, basketPrice);
    }

    @Test
    void testInvalidInput() {
        Integer basketPrice = solution.checkout("a");

        assertEquals(-1, basketPrice);
    }

    @Test
    void testOnlyNormalPrices() {
        Integer expectedPrice = 115;

        Integer basketPrice = solution.checkout("ABCD");

        assertEquals(expectedPrice, basketPrice);
    }

    @Test
    void testOfferPrices() {
        Integer expectedPrice = 130 + 45;

        Integer basketPrice = solution.checkout("AAABB");

        assertEquals(expectedPrice, basketPrice);
    }

    @Test
    void testAboveOfferPrices() {
        Integer expectedPrice = 130 + 45 + 50 + 30;

        Integer basketPrice = solution.checkout("AAAABBB");

        assertEquals(expectedPrice, basketPrice);
    }

}
