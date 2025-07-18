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
        assertThat(checkoutSolution.checkout("ABCDX?"), equalTo(-1));

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

        assertThat(checkoutSolution.checkout("EE"), equalTo(80));

        assertThat(checkoutSolution.checkout("EEEEBB"), equalTo(160));
        assertThat(checkoutSolution.checkout("BEBEEE"), equalTo(160));

        assertThat(checkoutSolution.checkout("F"), equalTo(10));
        assertThat(checkoutSolution.checkout("FF"), equalTo(20));
        assertThat(checkoutSolution.checkout("FFF"), equalTo(20));
        assertThat(checkoutSolution.checkout("FFFF"), equalTo(30));

        assertThat(checkoutSolution.checkout("NNNM"), equalTo(120));
        assertThat(checkoutSolution.checkout("NNNNM"), equalTo(160));
        assertThat(checkoutSolution.checkout("NNNNNNMM"), equalTo(240));

        assertThat(checkoutSolution.checkout(""), equalTo(0));

        assertThat(checkoutSolution.checkout("KK"), equalTo(120));
        // 50 + 30 + 45 = 125
        assertThat(checkoutSolution.checkout("ABXYZ"), equalTo(125));

        // since all 5 are group discount eligible items, we need to select most expensive 3 for discount
        // remaining 2 at regular unit price since it's the most cost-effective for user
        // S = 20, T = 20, X = 17, Y = 20, Z = 21 -> Z + S + T = 45; X + Y = 37
        assertThat(checkoutSolution.checkout("STXYZ"), equalTo(82));
    }
}
