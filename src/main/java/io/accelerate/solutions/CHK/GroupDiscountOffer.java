package io.accelerate.solutions.CHK;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class GroupDiscountOffer implements Offer{
    private final List<Character> eligibleSkuGroup;
    private final BigDecimal groupPrice;

    public GroupDiscountOffer(List<Character> eligibleSkuGroup, BigDecimal groupPrice) {
        this.eligibleSkuGroup = eligibleSkuGroup;
        this.groupPrice = groupPrice;
    }

    @Override
    public BigDecimal apply(Map<SKU, Integer> basket, SKU sku) {
        boolean basketContainsGroupSkus = eligibleSkuGroup.stream().allMatch(basket::containsKey);
        if (basketContainsGroupSkus) {
            return groupPrice;
        }
        return BigDecimal.ZERO;
    }

    @Override
    public int getBundleSize() {
        return 0;
    }
}

