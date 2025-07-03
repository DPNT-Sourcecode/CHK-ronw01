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
        int counter = 0;
        for (Map.Entry<Character, Integer> entry: basket.entrySet()) {
            if (eligibleSkus.contains(entry.getKey())) {
                seen.add(entry.getKey());
                counter+=entry.getValue();
            }
        }

        // for example if there are 6 basket skus that are eligible for group discount,
        // then 'any 3 for 45' discount should be applied 6/3=2 times
        int groupDiscountCount = counter/this.eligibleCount;

        // total discount value = groupDiscountCount * group discount price
        BigDecimal totalGroupDiscount = groupPrice.multiply(BigDecimal.valueOf(groupDiscountCount));

        // we need to remove skus that have contributed to group discount already so that they are not calculated twice
        int toRemove = groupDiscountCount * this.eligibleCount;
        for (char c: seen) {
            // keep decrementing basket sku count the number of times the group discount was applied
            while(toRemove > 0 && basket.getOrDefault(c, 0) > 0) {
                basket.put(c, basket.get(c) - 1);
                toRemove--;
            }
            if (toRemove == 0) {
                break;
            }
        }

        return totalGroupDiscount;
    }

    @Override
    public int getBundleSize() {
        return 0;
    }
}
