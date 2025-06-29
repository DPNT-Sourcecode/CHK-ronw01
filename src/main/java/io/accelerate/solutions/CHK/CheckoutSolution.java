package io.accelerate.solutions.CHK;

import io.accelerate.runner.SolutionNotImplementedException;

import java.util.HashMap;

public class CheckoutSolution {
    public Integer checkout(String skus) {
        HashMap<Character, Integer> skuToCount = new HashMap<>();
        for (int i = 0; i<skus.length(); i++) {
            char c = skus.charAt(i);
            if (c == 'A') {
                skuToCount.compute('A', (k, currentCount) -> currentCount);
            } else if (c=='B') {
                skuToCount.compute('B', (k, currentCount) -> currentCount);
            } else if (c=='C') {
                skuToCount.compute('C', (k, currentCount) -> currentCount);
            } else if (c=='D') {
                skuToCount.compute('D', (k, currentCount) -> currentCount);
            } else return -1;
        }
        int skuAOfferEligibleCount = skuToCount.get('A')/3;
        int skuAOfferNonEligibleCount = skuToCount.get('A')%3;
        int totalA = skuAOfferEligibleCount * 130 + skuAOfferNonEligibleCount * 50;

        int skuBOfferEligibleCount = skuToCount.get('B')/2;
        int skuBOfferNonEligibleCount = skuToCount.get('B')%2;
        int totalB = skuBOfferEligibleCount * 45 + skuBOfferNonEligibleCount * 30;

        int totalC = skuToCount.get('C') * 20;
        int totalD = skuToCount.get('D') * 15;

        return totalA + totalB + totalC + totalD;
    }
}

