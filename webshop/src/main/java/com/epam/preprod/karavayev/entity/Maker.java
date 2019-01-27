package com.epam.preprod.karavayev.entity;

public class Maker {

    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Maker maker = (Maker) o;

        if (id != maker.id) {
            return false;
        }
        return name != null ? name.equals(maker.name) : maker.name == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Maker{" +
               "id=" + id +
               ", name='" + name + '\'' +
               '}';
    }
}
