package io.accelerate.solutions.CHK;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class SKU {
    private final Character name;
    private final BigDecimal unitPrice;
    private final List<Offer> offers = new ArrayList<>();

    public SKU(Character name, BigDecimal unitPrice) {
        this.name = name;
        this.unitPrice = unitPrice;
    }
}

