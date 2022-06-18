package br.com.alura.loja.vo;

import java.time.LocalDate;

public class SalesReportVO {

    private final String productName;
    private final Long soldAmount;
    private final LocalDate lastSaleDate;

    public SalesReportVO(String productName, Long soldAmount, LocalDate lastSaleDate) {
        this.productName = productName;
        this.soldAmount = soldAmount;
        this.lastSaleDate = lastSaleDate;
    }

    public String getProductName() {
        return productName;
    }

    public Long getSoldAmount() {
        return soldAmount;
    }

    public LocalDate getLastSaleDate() {
        return lastSaleDate;
    }

    @Override
    public String toString() {
        return "Sales Report:" +
                "productName=' " + productName + '\'' +
                ", soldAmount= " + soldAmount +
                ", lastSaleDate= " + lastSaleDate;
    }
}
