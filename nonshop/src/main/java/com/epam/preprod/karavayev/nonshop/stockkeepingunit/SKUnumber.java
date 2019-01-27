package com.epam.preprod.karavayev.nonshop.stockkeepingunit;

public abstract class SKUnumber {
    private String key;

    public SKUnumber(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SKUnumber skUnumber = (SKUnumber) o;

        return key != null ? key.equals(skUnumber.key) : skUnumber.key == null;
    }


    @Override
    public String toString() {
        return "SKUnumber{" +
                "key='" + key + '\'' +
                '}';
    }
}
