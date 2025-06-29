package io.accelerate.solutions.CHK;

import io.accelerate.runner.SolutionNotImplementedException;

import java.util.HashMap;

public class CheckoutSolution {
    public Integer checkout(String skus) {
        // create hashmap to store sku to count map and initialize with zero values
        HashMap<Character, Integer> skuToCount = new HashMap<>();
        skuToCount.put('A', 0);
        skuToCount.put('B', 0);
        skuToCount.put('C', 0);
        skuToCount.put('D', 0);
        skuToCount.put('E', 0);

        // count each sku
        for (int i = 0; i < skus.length(); i++) {
            char c = skus.charAt(i);
            if (c == 'A') {
                int currentCount = skuToCount.get('A');
                skuToCount.put('A', ++currentCount);
            } else if (c=='B') {
                int currentCount = skuToCount.get('B');
                skuToCount.put('B', ++currentCount);
            } else if (c=='C') {
                int currentCount = skuToCount.get('C');
                skuToCount.put('C', ++currentCount);
            } else if (c=='D') {
                int currentCount = skuToCount.get('D');
                skuToCount.put('D', ++currentCount);
            } else if (c=='E') {
                int currentCount = skuToCount.get('E');
                skuToCount.put('E', ++currentCount);
            } else return -1;
        }

        // count sku A for offer eligible count
        // first count if we have 5 for 200 offer eligibility
        int skuAOffer1EligibleCount = skuToCount.get('A')/5;
        // need to check 3 for 130 eligibility on remaining count
        int remainingCount = skuToCount.get('A')%5;
        int skuAOffer2EligibleCount = remainingCount/3;
        // remaining count not eligible for offer
        int skuAOfferNonEligibleCount = remainingCount%3;
        int totalA = skuAOffer1EligibleCount * 200 + skuAOffer2EligibleCount * 130 + skuAOfferNonEligibleCount * 50;

        // count sku B for offer eligible count
        int skuBOfferEligibleCount = skuToCount.get('B')/2;
        int skuBOfferNonEligibleCount = skuToCount.get('B')%2;
        int totalB = skuBOfferEligibleCount * 45 + skuBOfferNonEligibleCount * 30;

        // sku C and D do not have offers available
        int totalC = skuToCount.get('C') * 20;
        int totalD = skuToCount.get('D') * 15;

        int skuEOfferEligibleCount = skuToCount.get('E')/2;
        int totalE = skuToCount.get('E') * 40 - skuEOfferEligibleCount * 30;

        // return total
        return totalA + totalB + totalC + totalD + totalE;
    }
}




