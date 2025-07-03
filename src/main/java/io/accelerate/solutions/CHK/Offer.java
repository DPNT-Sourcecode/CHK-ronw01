package io.accelerate.solutions.CHK;

import java.math.BigDecimal;
import java.util.Map;

public interface Offer {
    BigDecimal apply(Map<Character, Integer> basket, SKU sku);
    int getBundleSize();
}
