package io.accelerate.solutions.CHK;

import java.math.BigDecimal;
import java.util.Map;

public class FreeItemOffer implements Offer{
    private final int eligibilityCount;
    private final char freeItem;

    public FreeItemOffer(int eligibilityCount, char freeItem) {
        this.eligibilityCount = eligibilityCount;
        this.freeItem = freeItem;
    }

    @Override
    public BigDecimal apply(Map<SKU, Integer> basket, SKU sku) {
        // when free item is same as required item
        if (sku.getName() == freeItem) {
            int count = basket.get(sku);
            if (count/(eligibilityCount+1) >= 1) {
                count -= count/(eligibilityCount+1);
                basket.put(sku, count);
            }
            // when free item is different to required item
        } else {
            int totalBundleCount = basket.get(sku)/eligibilityCount;
            // we need to find the existing count of free item in the basket and decrement it by bundle count
            for (Map.Entry<SKU, Integer> entry : basket.entrySet()) {
                if (entry.getKey().getName() == this.freeItem) {
                    int freeItemCountInBasket = entry.getValue() - totalBundleCount;
                    // make sure we do not extract more than existing quantity of free item in the basket
                    if(freeItemCountInBasket < 0) {
                        freeItemCountInBasket = 0;
                    }
                    entry.setValue(freeItemCountInBasket);
                    break;
                }
            }
        }

        return BigDecimal.ZERO; // free item offer just decrements eligible items from basket
    }

    @Override
    public int getBundleSize() {
        return eligibilityCount;
    }
}


