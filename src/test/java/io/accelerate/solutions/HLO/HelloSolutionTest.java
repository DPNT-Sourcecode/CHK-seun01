package io.accelerate.solutions.HLO;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HelloSolutionTest {

    private HelloSolution solution;

    @BeforeEach
    void setUp() {
        solution = new HelloSolution();
    }

    @Test
    void testItReturnsHello() {
        String response = solution.hello("Jorge");
        assertEquals("Hello Jorge", response);
    }

}