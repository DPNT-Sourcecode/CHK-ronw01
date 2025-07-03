package io.accelerate.solutions.CHK;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class GroupDiscountOffer implements Offer{
    private final Set<Character> eligibleSkus;
    private final int count;
    private final BigDecimal groupPrice;

    public GroupDiscountOffer(List<Character> eligibleSkuGroup, BigDecimal groupPrice) {
        this.eligibleSkuGroup = eligibleSkuGroup;
        this.groupPrice = groupPrice;
    }

    @Override
    public BigDecimal apply(Map<SKU, Integer> basket, SKU sku) {

        return BigDecimal.ZERO;
    }

    @Override
    public int getBundleSize() {
        return 0;
    }
}

