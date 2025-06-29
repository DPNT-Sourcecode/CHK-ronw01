package io.accelerate.solutions.CHK;

import java.util.HashMap;


public class CheckoutSolution {

    private static final int skuAUnitPrice = 50;
    private static final int skuBUnitPrice = 30;
    private static final int skuCUnitPrice = 20;
    private static final int skuDUnitPrice = 15;
    private static final int skuEUnitPrice = 40;
    private static final int skuFUnitPrice = 10;

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
        skuToCount.put('F', 0);

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
            } else if (c=='F') {
                int currentCount = skuToCount.get('F');
                skuToCount.put('F', ++currentCount);
            } else return -1;
        }

        int skuACount = skuToCount.get('A');
        int skuBCount = skuToCount.get('B');
        int skuCCount = skuToCount.get('C');
        int skuDCount = skuToCount.get('D');
        int skuECount = skuToCount.get('E');
        int skuFCount = skuToCount.get('F');

        // recalculate count of sku B due to sku E offer
        int skuEOfferEligibleCount = skuECount/2;
        int currentSkuBCount = skuBCount;
        currentSkuBCount -= skuEOfferEligibleCount;
        if (currentSkuBCount < 0) {
            currentSkuBCount = 0;
        }
        skuBCount = currentSkuBCount;


        // count sku A for offer eligible count
        // first count if we have 5 for 200 offer eligibility
        int skuAOffer1EligibleCount = skuACount/5;
        // check 3 for 130 eligibility on remaining count
        int remainingCount = skuACount%5;
        int skuAOffer2EligibleCount = remainingCount/3;
        // remaining count not eligible for offer
        int skuAOfferNonEligibleCount = remainingCount%3;
        int totalA = skuAOffer1EligibleCount * skuAFiveForXOfferPrice + skuAOffer2EligibleCount * skuAThreeForXOfferPrice + skuAOfferNonEligibleCount * skuAUnitPrice;

        // count sku B for offer eligible count
        int skuBOfferEligibleCount = skuBCount/2;
        int skuBOfferNonEligibleCount = skuBCount%2;
        int totalB = skuBOfferEligibleCount * skuBTwoForXOfferPrice + skuBOfferNonEligibleCount * skuBUnitPrice;

        // sku C and D do not have offers available
        int totalC = skuCCount * skuCUnitPrice;
        int totalD = skuDCount * skuDUnitPrice;

        // total cost of sku E
        int totalE = skuECount * skuEUnitPrice;

        // account for 2F get 1 F free
        if(skuFCount/3 >= 1) {
            skuFCount -= skuFCount/3;
        }
        int totalF = skuFCount * skuFUnitPrice;

        // return total
        return totalA + totalB + totalC + totalD + totalE + totalF;
    }
}
