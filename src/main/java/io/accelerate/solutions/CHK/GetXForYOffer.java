package io.accelerate.solutions.CHK;

import java.math.BigDecimal;
import java.util.Map;

public class GetXForYOffer implements Offer{
    private final int X; // bundle item count
    private final BigDecimal Y; // bundle price

    public GetXForYOffer(int X, BigDecimal Y) {
        this.X = X;
        this.Y = Y;
    }

    @Override
    public BigDecimal apply(Map<SKU, Integer> basket, SKU sku) {
        int totalBundleCount = basket.get(sku)/X;
        basket.put(sku, basket.get(sku)%X); // update sku count with remaining quantity as offers are applied
        return Y.multiply(BigDecimal.valueOf(totalBundleCount));
    }

    @Override
    public int getBundleSize() {
        return X;
    }
}
