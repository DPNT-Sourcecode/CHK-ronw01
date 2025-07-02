package io.accelerate.solutions.CHK;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.accelerate.solutions.CHK.Constants.*;


public class CheckoutSolution {

    private final Map<Character, SKU> catalogue;

    public CheckoutSolution() {
        this.catalogue = initSkusWithOffers();
    }

    private Map<Character, SKU> initSkusWithOffers() {
        Map<Character, SKU> catalogue = new HashMap<>();

        catalogue.put('A',
                new SKU('A', skuAUnitPrice)
                        .withOffer(new GetXForYOffer(3, BigDecimal.valueOf(130))) // 3 A for 130
                        .withOffer(new GetXForYOffer(5, BigDecimal.valueOf(200)))); // 5 A for 200

        catalogue.put('B',
                new SKU('B', skuBUnitPrice)
                        .withOffer(new GetXForYOffer(2, BigDecimal.valueOf(45))));

        catalogue.put('C',
                new SKU('C', skuCUnitPrice));

        catalogue.put('D',
                new SKU('D', skuDUnitPrice));

        catalogue.put('E',
                new SKU('E', skuEUnitPrice)
                        .withOffer(new FreeItemOffer(2, 'B'))); // buy 2 E, get B free

        catalogue.put('F',
                new SKU('F', skuFUnitPrice)
                        .withOffer(new FreeItemOffer(2, 'F'))); // buy 2 F, get another free

        catalogue.put('G',
                new SKU('G', skuGUnitPrice));

        catalogue.put('H',
                new SKU('H', skuHUnitPrice)
                        .withOffer(new GetXForYOffer(5, BigDecimal.valueOf(45)))
                        .withOffer(new GetXForYOffer(10, BigDecimal.valueOf(80))));

        catalogue.put('I',
                new SKU('I', skuIUnitPrice));

        catalogue.put('J',
                new SKU('J', skuJUnitPrice));

        catalogue.put('K',
                new SKU('K', skuKUnitPrice)
                        .withOffer(new GetXForYOffer(2, BigDecimal.valueOf(160))));

        catalogue.put('L',
                new SKU('L', skuLUnitPrice));

        catalogue.put('M',
                new SKU('M', skuMUnitPrice));

        catalogue.put('N',
                new SKU('N', skuNUnitPrice)
                        .withOffer(new FreeItemOffer(3, 'M')));

        catalogue.put('O',
                new SKU('O', skuOUnitPrice));

        catalogue.put('P',
                new SKU('P', skuPUnitPrice)
                        .withOffer(new GetXForYOffer(5, BigDecimal.valueOf(200))));

        catalogue.put('Q',
                new SKU('Q', skuQUnitPrice)
                        .withOffer(new GetXForYOffer(3, BigDecimal.valueOf(80))));

        catalogue.put('R',
                new SKU('R', skuRUnitPrice)
                        .withOffer(new FreeItemOffer(3, 'Q')));

        catalogue.put('S',
                new SKU('S', skuSUnitPrice));

        catalogue.put('T',
                new SKU('T', skuTUnitPrice));

        catalogue.put('U',
                new SKU('U', skuUUnitPrice)
                        .withOffer(new FreeItemOffer(3, 'U')));

        catalogue.put('V',
                new SKU('V', skuVUnitPrice)
                        .withOffer(new GetXForYOffer(2, BigDecimal.valueOf(90)))
                        .withOffer(new GetXForYOffer(3, BigDecimal.valueOf(130))));

        catalogue.put('W',
                new SKU('W', skuWUnitPrice));

        catalogue.put('X',
                new SKU('X', skuXUnitPrice));

        catalogue.put('Y',
                new SKU('Y', skuYUnitPrice));

        catalogue.put('Z',
                new SKU('Z', skuZUnitPrice));

        return catalogue;
    }

    public Integer checkout(String skus) {
        Map<SKU, Integer> basket = countSkus(skus);

        if (basket.isEmpty()) {
            return -1;
        }

        BigDecimal totalBasketValue = BigDecimal.ZERO;
        for (Map.Entry<SKU, Integer> basketEntry : basket.entrySet()) {
            SKU sku = basketEntry.getKey();
            List<Offer> offers = sku.getOffers();

            // apply all offers per sku
            for (Offer offer : offers) {
                BigDecimal offerValue = offer.apply(basket, sku);
                totalBasketValue = totalBasketValue.add(offerValue);
            }

            // if there are remaining quantity once all offers are applied, add them to total at regular unit price (i.e no offers)
            if (basketEntry.getValue() > 0) {
                totalBasketValue = totalBasketValue.add(sku.getUnitPrice().multiply(BigDecimal.valueOf(basketEntry.getValue())));
            }
        }






//        // recalculate count of sku B due to sku E offer
//        int skuEOfferEligibleCount = skuECount/2;
//        int currentSkuBCount = skuBCount;
//        currentSkuBCount -= skuEOfferEligibleCount;
//        if (currentSkuBCount < 0) {
//            currentSkuBCount = 0;
//        }
//        skuBCount = currentSkuBCount;

//        // count sku A for offer eligible count
//        // first count if we have 5 for 200 offer eligibility
//        int skuAOffer1EligibleCount = skuACount/5;
//        // check 3 for 130 eligibility on remaining count
//        int remainingCount = skuACount%5;
//        int skuAOffer2EligibleCount = remainingCount/3;
//        // remaining count not eligible for offer
//        int skuAOfferNonEligibleCount = remainingCount%3;
//        int totalA = skuAOffer1EligibleCount * skuAFiveForXOfferPrice + skuAOffer2EligibleCount * skuAThreeForXOfferPrice + skuAOfferNonEligibleCount * skuAUnitPrice;
//
//        // count sku B for offer eligible count
//        int skuBOfferEligibleCount = skuBCount/2;
//        int skuBOfferNonEligibleCount = skuBCount%2;
//        int totalB = skuBOfferEligibleCount * skuBTwoForXOfferPrice + skuBOfferNonEligibleCount * skuBUnitPrice;
//
//        // sku C and D do not have offers available
//        int totalC = skuCCount * skuCUnitPrice;
//        int totalD = skuDCount * skuDUnitPrice;
//
//        // total cost of sku E
//        int totalE = skuECount * skuEUnitPrice;
//
//        // account for 2F get 1 F free
//        if(skuFCount/3 >= 1) {
//            skuFCount -= skuFCount/3;
//        }
//        int totalF = skuFCount * skuFUnitPrice;

        return totalBasketValue.intValue();
    }

    private Map<SKU, Integer> countSkus(String skus) {
        // create hashmap to store sku to count map
        HashMap<SKU, Integer> skuToCount = new HashMap<>();
        for (char c: skus.toCharArray()) {
            if (c < 'A' || c > 'Z') {
                return Collections.emptyMap();
            }
            skuToCount.put(catalogue.get(c), skuToCount.getOrDefault(catalogue.get(c), 0) + 1);
        }

        return skuToCount;
    }
}
