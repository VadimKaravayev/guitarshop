package com.epam.preprod.karavayev.captcha;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;
import java.util.Objects;

public class Captcha {

    private String value;
    private BufferedImage image;
    private LocalDateTime expirationDate;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public LocalDateTime getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDateTime expirationDate) {
        this.expirationDate = expirationDate;
    }

    public void makeInvalid() {
        expirationDate = LocalDateTime.MIN;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Captcha)) return false;
        Captcha captcha = (Captcha) o;
        return Objects.equals(getValue(), captcha.getValue()) &&
                Objects.equals(getImage(), captcha.getImage()) &&
                Objects.equals(getExpirationDate(), captcha.getExpirationDate());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getValue(), getImage(), getExpirationDate());
    }

    @Override
    public String toString() {
        return "Captcha{" +
                "value='" + value + '\'' +
                ", expirationDate=" + expirationDate +
                '}';
    }
}
