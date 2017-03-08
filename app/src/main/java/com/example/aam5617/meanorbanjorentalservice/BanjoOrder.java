package com.example.aam5617.meanorbanjorentalservice;

/**
 * Created by AAM5617 on 1/26/2017.
 */

public class BanjoOrder {

    private double  banjoPrice;

    private double  violinPrice;

    private double  ukulelePrice;

    private double  mandolinPrice;

    private double  insuranceCost;

    private double  insuranceTotal;

    private double  casePrice;

    private double  baseCost;

    private double  totalCost;

    private double  salesTax;

    private double  salesTaxPercent;

    private int     numBanjos;

    private int     numCases;

    public BanjoOrder() {
        banjoPrice      = 9.00;
        violinPrice     = 10.00;
        ukulelePrice    = 7.00;
        mandolinPrice   = 5.00;
        casePrice       = 1.00;
        insuranceCost   = 0.00;
        salesTaxPercent = 0.05;
        numBanjos       = 0;
        numCases        = 0;
    }

    public int getBanjoNum() {
        return numBanjos;
    }

    public int getCaseNum() {
        return numCases;
    }

    public double getBanjoPrice() {
        return banjoPrice;
    }

    public double getCasePrice() {
        return casePrice;
    }

    public double getBaseCost() {
        return baseCost;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public double getSalesTax() {
        return salesTax;
    }

    public double getinsuranceTotal() { return insuranceTotal;}

    public void setInsuranceCost(double insuranceCost) {this.insuranceCost = insuranceCost; }

    public double getSalesTaxPercent() {
        return salesTaxPercent;
    }

    public void setNumBanjos(int numBanjo) {
        this.numBanjos = numBanjo;
    }

    public void setNumCases(int numCases) {
        this.numCases = numCases;
    }

    public void setSalesTaxPercent(double salesTaxPercent) {
        this.salesTaxPercent = salesTaxPercent;
    }

    public void setBanjoPrice(double banjoPrice) {
        this.banjoPrice = banjoPrice;
    }

    public void setBanjoCasePrice(double casePrice) {
        this.casePrice = casePrice;
    }

    public void calculateBaseCost(String instrument) {
        double currentInstrumentPrice = 0;
        switch (instrument) {
            case "Banjos":
                currentInstrumentPrice = banjoPrice;
                break;
            case "Violins":
                currentInstrumentPrice = violinPrice;
                break;
            case "Ukuleles":
                currentInstrumentPrice = ukulelePrice;
                break;
            case "Mandolins":
                currentInstrumentPrice = mandolinPrice;
                break;
        }
        baseCost = (numBanjos * currentInstrumentPrice) + (numCases * casePrice);
    }

    public void calculateInsurance() {
        insuranceTotal = insuranceCost * numBanjos;
    }

    public void calculateSalesTax() {
        salesTax = baseCost * salesTaxPercent;
    }

    public void calculateTotalcost() {
        totalCost = baseCost + salesTax +insuranceTotal;

    }
}
