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

        // 50 + 30 + 20 + 15 = 115
        assertThat(checkoutSolution.checkout("ABCD"), equalTo(115));

        // 100 + 45 + 30 = 175
        assertThat(checkoutSolution.checkout("AABBDD"), equalTo(175));

        // input contains invalid sku
        assertThat(checkoutSolution.checkout("ABXD"), equalTo(-1));

        // 50 + 30 + 20 + 15 + 40 = 155
        assertThat(checkoutSolution.checkout("ABCDE"), equalTo(155));

        // 50 + 30 + 20 + 15 + 80 - 30 = 165
        assertThat(checkoutSolution.checkout("ABCDEE"), equalTo(165));

        // 3 A for 130
        assertThat(checkoutSolution.checkout("AAA"), equalTo(130));

        // 5 A for 200
        assertThat(checkoutSolution.checkout("AAAAA"), equalTo(200));

        // 5 A for 200 + 3 for 130 + 50
        assertThat(checkoutSolution.checkout("AAAAAAAAA"), equalTo(380));

        // 15 As should count as 3 times 5 for 200, because it's the most cost-effective
        assertThat(checkoutSolution.checkout("AAAAAAAAAAAAAAA"), equalTo(600));
    }
}
