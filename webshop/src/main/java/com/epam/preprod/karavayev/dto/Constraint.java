package com.epam.preprod.karavayev.dto;

import java.util.List;
import java.util.Objects;

public class Constraint {

    private String pattern;
    private List<String> roles;

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Constraint that = (Constraint) o;
        return Objects.equals(getPattern(), that.getPattern()) &&
                Objects.equals(getRoles(), that.getRoles());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getPattern(), getRoles());
    }

    @Override
    public String toString() {
        return "Constraint{" +
                "pattern='" + pattern + '\'' +
                ", roles=" + roles +
                '}';
    }

}
