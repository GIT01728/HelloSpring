package com.hellospring.model;

import java.util.Objects;

public class Stock {
    private String company;
    private double units;
    private double price;

    public Stock() {
    }

    public Stock(String company, double units, double price) {
        this.company = company;
        this.units = units;
        this.price = price;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public double getUnits() {
        return units;
    }

    public void setUnits(double units) {
        this.units = units;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Stock stock = (Stock) o;
        return Double.compare(stock.getUnits(), getUnits()) == 0 && Double.compare(stock.getPrice(), getPrice()) == 0 && Objects.equals(getCompany(), stock.getCompany());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCompany(), getUnits(), getPrice());
    }

    @Override
    public String toString() {
        return "Stock{" +
                "company='" + company + '\'' +
                ", units=" + units +
                ", price=" + price +
                '}';
    }
}
