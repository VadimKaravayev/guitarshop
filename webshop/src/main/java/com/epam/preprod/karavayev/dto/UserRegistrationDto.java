package com.epam.preprod.karavayev.dto;

import javax.servlet.http.Part;

public class UserRegistrationDto {

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String confirmPassword;
    private String captchaValue;
    private String subscription;
    private Part image;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getCaptchaValue() {
        return captchaValue;
    }

    public void setCaptchaValue(String captchaValue) {
        this.captchaValue = captchaValue;
    }

    public String getSubscription() {
        return subscription;
    }

    public void setSubscription(String subscription) {
        this.subscription = subscription;
    }

    public Part getImage() {
        return image;
    }

    public void setImage(Part image) {
        this.image = image;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        UserRegistrationDto that = (UserRegistrationDto) o;

        if (firstName != null ? !firstName.equals(that.firstName) : that.firstName != null) {
            return false;
        }
        if (lastName != null ? !lastName.equals(that.lastName) : that.lastName != null) {
            return false;
        }
        if (email != null ? !email.equals(that.email) : that.email != null) {
            return false;
        }
        if (password != null ? !password.equals(that.password) : that.password != null) {
            return false;
        }
        if (confirmPassword != null ? !confirmPassword.equals(that.confirmPassword) : that.confirmPassword != null) {
            return false;
        }
        if (captchaValue != null ? !captchaValue.equals(that.captchaValue) : that.captchaValue != null) {
            return false;
        }
        if (subscription != null ? !subscription.equals(that.subscription) : that.subscription != null) {
            return false;
        }
        return image != null ? image.equals(that.image) : that.image == null;
    }

    @Override
    public int hashCode() {
        int result = firstName != null ? firstName.hashCode() : 0;
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (confirmPassword != null ? confirmPassword.hashCode() : 0);
        result = 31 * result + (captchaValue != null ? captchaValue.hashCode() : 0);
        result = 31 * result + (subscription != null ? subscription.hashCode() : 0);
        result = 31 * result + (image != null ? image.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "UserRegistrationDto{" +
               "firstName='" + firstName + '\'' +
               ", lastName='" + lastName + '\'' +
               ", email='" + email + '\'' +
               ", password='" + password + '\'' +
               ", confirmPassword='" + confirmPassword + '\'' +
               ", captchaValue='" + captchaValue + '\'' +
               ", subscription='" + subscription + '\'' +
               ", image=" + image +
               '}';
    }
}