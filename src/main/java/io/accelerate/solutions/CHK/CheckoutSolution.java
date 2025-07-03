package io.accelerate.solutions.CHK;

import java.math.BigDecimal;
import java.util.*;

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
                        .withOffer(new GetXForYOffer(2, BigDecimal.valueOf(120))));

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
                new SKU('S', skuSUnitPrice)
                        .withOffer(new GroupDiscountOffer(List.of('S', 'T', 'X', 'Y', 'Z'), BigDecimal.valueOf(45))));

        catalogue.put('T',
                new SKU('T', skuTUnitPrice)
                        .withOffer(new GroupDiscountOffer(List.of('S', 'T', 'X', 'Y', 'Z'), BigDecimal.valueOf(45))));

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
                new SKU('X', skuXUnitPrice)
                        .withOffer(new GroupDiscountOffer(List.of('S', 'T', 'X', 'Y', 'Z'), BigDecimal.valueOf(45))));

        catalogue.put('Y',
                new SKU('Y', skuYUnitPrice)
                        .withOffer(new GroupDiscountOffer(List.of('S', 'T', 'X', 'Y', 'Z'), BigDecimal.valueOf(45))));

        catalogue.put('Z',
                new SKU('Z', skuZUnitPrice)
                        .withOffer(new GroupDiscountOffer(List.of('S', 'T', 'X', 'Y', 'Z'), BigDecimal.valueOf(45))));

        return catalogue;
    }

    public Integer checkout(String skus) {
        Map<SKU, Integer> basket = countSkus(skus);

        if (basket==null) {
            return -1;
        }

        if (basket.isEmpty()) {
            return 0;
        }

        // prioritize skus with free item offers
        Map<SKU, Integer> sortedBasket = sortByFreeItemsOffer(basket);

        BigDecimal totalBasketValue = BigDecimal.ZERO;
        for (Map.Entry<SKU, Integer> basketEntry : sortedBasket.entrySet()) {
            SKU sku = basketEntry.getKey();
            List<Offer> offers = sku.getOffers();
            // prioritize offers with higher bundle count
            // i.e apply '5 for 200' before '3 for 130'
            offers.sort((o1, o2) -> Integer.compare(o2.getBundleSize(), o1.getBundleSize()));

            // apply all offers per sku
            for (Offer offer : offers) {
                BigDecimal offerValue = offer.apply(sortedBasket, sku);
                totalBasketValue = totalBasketValue.add(offerValue);
            }
            // if there are remaining quantity once all offers are applied, add them to total at regular unit price (i.e no offers)
            if (basketEntry.getValue() > 0) {
                totalBasketValue = totalBasketValue.add(sku.getUnitPrice().multiply(BigDecimal.valueOf(basketEntry.getValue())));
            }
        }

        return totalBasketValue.intValue();
    }

    private Map<SKU, Integer> sortByFreeItemsOffer(Map<SKU, Integer> basket) {
        List<Map.Entry<SKU, Integer>> basketSkusList = new ArrayList<>(basket.entrySet());
        basketSkusList.sort((e1, e2) -> Boolean.compare(
                e2.getKey().hasFreeItemsOffer(),
                e1.getKey().hasFreeItemsOffer()
        ));

        Map<SKU, Integer> sortedBasket = new LinkedHashMap<>(); // use LinkedHashMap to maintain ordering
        for(Map.Entry<SKU, Integer> basketSku: basketSkusList) {
            sortedBasket.put(basketSku.getKey(), basketSku.getValue());
        }
        return sortedBasket;
    }

    private Map<SKU, Integer> countSkus(String skus) {
        // create hashmap to store sku to count map
        HashMap<SKU, Integer> skuToCount = new HashMap<>();
        if (skus.isEmpty()) {
            return Collections.emptyMap();
        }
        for (char c: skus.toCharArray()) {
            if (c < 'A' || c > 'Z') {
                return null;
            }
            skuToCount.put(catalogue.get(c), skuToCount.getOrDefault(catalogue.get(c), 0) + 1);
        }

        return skuToCount;
    }
}
