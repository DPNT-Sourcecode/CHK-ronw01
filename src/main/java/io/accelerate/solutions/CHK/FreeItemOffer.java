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
        return null;
    }
}

