package io.accelerate.solutions.CHK;

import io.accelerate.runner.SolutionNotImplementedException;

import java.util.HashMap;

public class CheckoutSolution {
    public Integer checkout(String skus) {
        HashMap<Character, Integer> skuToCount = new HashMap<>();
        skuToCount.put('A', 0);
        skuToCount.put('B', 0);
        skuToCount.put('C', 0);
        skuToCount.put('D', 0);
        for (int i = 0; i < skus.length(); i++) {
            char c = skus.charAt(i);
            if (c == 'A') {
                int currentCount = skuToCount.get('A');
                skuToCount.put('A', ++currentCount);
//                skuToCount.compute('A', (k, currentCount) -> currentCount);
            } else if (c=='B') {
                int currentCount = skuToCount.get('B');
                skuToCount.put('B', ++currentCount);
//                skuToCount.compute('B', (k, currentCount) -> currentCount);
            } else if (c=='C') {
                int currentCount = skuToCount.get('C');
                skuToCount.put('C', ++currentCount);
//                skuToCount.compute('C', (k, currentCount) -> currentCount);
            } else if (c=='D') {
                int currentCount = skuToCount.get('D');
                skuToCount.put('D', ++currentCount);
//                skuToCount.compute('D', (k, currentCount) -> currentCount);
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

