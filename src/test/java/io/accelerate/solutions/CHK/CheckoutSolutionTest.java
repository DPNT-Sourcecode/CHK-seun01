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
    void testAboveOfferPrices1() {
        Integer expectedPrice = 250;

        Integer basketPrice = solution.checkout("AAAAAA");

        assertEquals(expectedPrice, basketPrice);
    }

    @Test
    void testAboveOfferPrices2() {
        Integer expectedPrice = 90;

        Integer basketPrice = solution.checkout("BBBB");

        assertEquals(expectedPrice, basketPrice);
    }

    @Test
    void testAboveOfferPrices3() {
        Integer expectedPrice = 600 + 130 + 50;

        Integer basketPrice = solution.checkout("AAAAAAAAAAAAAAAAAAA");

        assertEquals(expectedPrice, basketPrice);
    }

    @Test
    void testAboveOfferPrices4() {
        Integer expectedPrice = 200;

        Integer basketPrice = solution.checkout("AAAAA");

        assertEquals(expectedPrice, basketPrice);
    }


    @Test
    void testAboveOfferPrices5() {
        Integer expectedPrice = 80;

        Integer basketPrice = solution.checkout("EEB");

        assertEquals(expectedPrice, basketPrice);
    }

    @Test
    void testAboveOfferPrices6() {
        Integer expectedPrice = 120;

        Integer basketPrice = solution.checkout("EEEB");

        assertEquals(expectedPrice, basketPrice);
    }

    @Test
    void testAboveOfferPrices7() {
        Integer expectedPrice = 80;

        Integer basketPrice = solution.checkout("EE");

        assertEquals(expectedPrice, basketPrice);
    }

    @Test
    void testAboveOfferPrices8() {
        Integer expectedPrice = 160;

        Integer basketPrice = solution.checkout("EEEEBB");

        assertEquals(expectedPrice, basketPrice);
    }

    @Test
    void testAboveOfferPrices9() {
        Integer expectedPrice = 160;

        Integer basketPrice = solution.checkout("BEBEEE");

        assertEquals(expectedPrice, basketPrice);
    }

    @Test
    void testAboveOfferPrices10() {
        Integer expectedPrice = 280;

        Integer basketPrice = solution.checkout("ABCDEABCDE");

        assertEquals(expectedPrice, basketPrice);
    }

    @Test
    void testAboveOfferPrices11() {
        Integer expectedPrice = 280;

        Integer basketPrice = solution.checkout("CCADDEEBBA");

        assertEquals(expectedPrice, basketPrice);
    }

    @Test
    void testAboveOfferPrices12() {
        Integer expectedPrice = 10;

        Integer basketPrice = solution.checkout("F");

        assertEquals(expectedPrice, basketPrice);
    }

    @Test
    void testAboveOfferPrices13() {
        Integer expectedPrice = 20;

        Integer basketPrice = solution.checkout("FF");

        assertEquals(expectedPrice, basketPrice);
    }


    @Test
    void testAboveOfferPrices14() {
        Integer expectedPrice = 20;

        Integer basketPrice = solution.checkout("FF");

        assertEquals(expectedPrice, basketPrice);
    }


    @Test
    void testAboveOfferPrices15() {
        Integer expectedPrice = 20;

        Integer basketPrice = solution.checkout("FFF");

        assertEquals(expectedPrice, basketPrice);
    }

    @Test
    void testAboveOfferPrices16() {
        Integer expectedPrice = 40;

        Integer basketPrice = solution.checkout("FFFFFF");
        // 6 * 10 = 60
        // discount = 2 * 10 = 20
        // 60 - 40 = 40

        assertEquals(expectedPrice, basketPrice);
    }

    @Test
    void testAboveOfferPrices17() {
        Integer expectedPrice = 150;

        Integer basketPrice = solution.checkout("RRRQ");
        // 3 x R = 150 - 30 = 120
        // 1 x Q = 30

        assertEquals(expectedPrice, basketPrice);
    }

    @Test
    void testAboveOfferPrices18() {
        Integer expectedPrice = 300;

        Integer basketPrice = solution.checkout("RRRRRRQQ");

        assertEquals(expectedPrice, basketPrice);
    }

    @Test
    void testAboveOfferPrices19() {
        Integer expectedPrice = 300;

        Integer basketPrice = solution.checkout("RRRQRQRR");

        assertEquals(expectedPrice, basketPrice);
    }

    @Test
    void testAboveOfferPrices20() {
        Integer expectedPrice = 120;

        Integer basketPrice = solution.checkout("UUU");
        // 3 x 40 = 120
        // 120 - 40 = 80

        assertEquals(expectedPrice, basketPrice);
    }

    @Test
    void testAboveOfferPrices21() {
        Integer expectedPrice = 110;

        Integer basketPrice = solution.checkout("ZZZZYYY");

        assertEquals(expectedPrice, basketPrice);
    }

}