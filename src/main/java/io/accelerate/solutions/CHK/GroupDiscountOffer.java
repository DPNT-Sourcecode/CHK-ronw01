package io.accelerate.solutions.CHK;

import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

public class GroupDiscountOffer implements Offer{
    private final Set<Character> eligibleSkus;
    private final int eligibleCount;
    private final BigDecimal groupPrice;

    public GroupDiscountOffer(Set<Character> eligibleSkuGroup, int eligibleCount, BigDecimal groupPrice) {
        this.eligibleSkus = eligibleSkuGroup;
        this.eligibleCount = eligibleCount;
        this.groupPrice = groupPrice;
    }

    @Override
    public BigDecimal apply(Map<Character, Integer> basket, SKU sku) {
        Set<Character> seen = new LinkedHashSet<>();

        for (Map.Entry<Character, Integer> entry: basket.entrySet()) {

        }
        return BigDecimal.ZERO;
    }

    @Override
    public int getBundleSize() {
        return 0;
    }
}


