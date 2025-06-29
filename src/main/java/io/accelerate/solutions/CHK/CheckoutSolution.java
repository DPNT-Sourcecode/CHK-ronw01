package io.accelerate.solutions.CHK;

import java.util.HashMap;


public class CheckoutSolution {

    private static final int skuAUnitPrice = 50;
    private static final int skuBUnitPrice = 30;
    private static final int skuCUnitPrice = 20;
    private static final int skuDUnitPrice = 15;
    private static final int skuEUnitPrice = 40;

    private static final int skuAFiveForXOfferPrice = 200;
    private static final int skuAThreeForXOfferPrice = 130;
    private static final int skuBTwoForXOfferPrice = 45;

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
        int totalA = skuAOffer1EligibleCount * skuAFiveForXOfferPrice + skuAOffer2EligibleCount * skuAThreeForXOfferPrice + skuAOfferNonEligibleCount * skuAUnitPrice;

        // count sku B for offer eligible count
        int skuBOfferEligibleCount = skuToCount.get('B')/2;
        int skuBOfferNonEligibleCount = skuToCount.get('B')%2;
        int totalB = skuBOfferEligibleCount * skuBTwoForXOfferPrice + skuBOfferNonEligibleCount * skuBUnitPrice;

        // sku C and D do not have offers available
        int totalC = skuToCount.get('C') * skuCUnitPrice;
        int totalD = skuToCount.get('D') * skuDUnitPrice;

        int skuEOfferEligibleCount = skuToCount.get('E')/2;
        // total cost of sku E minus offer of "free B for every 2 E"
        // Math.min(Math.min(skuEOfferEligibleCount, skuToCount.get('B'))) in case we have less Bs than eligible E count
        int totalE = skuToCount.get('E') * skuEUnitPrice - Math.min(skuEOfferEligibleCount, skuToCount.get('B')) * skuBUnitPrice;

        // return total
        return totalA + totalB + totalC + totalD + totalE;
    }
}

