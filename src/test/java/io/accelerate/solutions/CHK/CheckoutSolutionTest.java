package io.accelerate.solutions.CHK;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class CheckoutSolutionTest {
    private CheckoutSolution checkoutSolution;

    @BeforeEach
    public void setup() {
        checkoutSolution = new CheckoutSolution();
    }

    @Test
    public void checkout_test() {
        // 3 As for 130 + 2 Bs for 45 + 1 C + 1 D = 210
        assertThat(checkoutSolution.checkout("AAABBCD"), equalTo(210));
    }
}
