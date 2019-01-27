package com.epam.preprod.karavayev.nonshop.stockkeepingunit;

public class SKUnumberOne extends SKUnumber {
    public SKUnumberOne(String key) {
        super(key);
    }

    @Override
    public int hashCode() {
        return getKey() == null ? 0 : getKey().length();
    }
}
