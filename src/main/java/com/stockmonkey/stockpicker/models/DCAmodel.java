package com.stockmonkey.stockpicker.models;

import java.time.LocalDate;


public class DCAmodel {
    //! input values from form
    private String ticker;
    private LocalDate startDate;
    private LocalDate endDate = LocalDate.now();
    private int investInterval;
    private double investAmount;
    private double initialAmount = 0.0;    
    //! derived values
    private int duration;    
    private int repetitions;
    private double investTotal;    
    private double quantity;
    private double lastPrice;
    private double avgCost;
    private double portValue;
    private double profitLoss;
    
        
    //! Constructors
    public DCAmodel() {}    
    public DCAmodel(String ticker, LocalDate startDate, LocalDate endDate, int investInterval, double investAmount,
            double initialAmount, int duration, int repetitions, double investTotal, double quantity, double lastPrice,
            double avgCost, double portValue, double profitLoss) {
        this.ticker = ticker;
        this.startDate = startDate;
        this.endDate = endDate;
        this.investInterval = investInterval;
        this.investAmount = investAmount;
        this.initialAmount = initialAmount;
        this.duration = duration;
        this.repetitions = repetitions;
        this.investTotal = investTotal;
        this.quantity = quantity;
        this.lastPrice = lastPrice;
        this.avgCost = avgCost;
        this.portValue = portValue;
        this.profitLoss = profitLoss;
    }

    //! Getters and setters
    public LocalDate getStartDate() {return startDate;}    
    public void setStartDate(LocalDate startDate) {this.startDate = startDate;}
    
    public LocalDate getEndDate() {return endDate;}
    public void setEndDate(LocalDate endDate) {this.endDate = endDate;}

    public String getTicker() {return ticker;}    
    public void setTicker(String ticker) {this.ticker = ticker;}
    
    public double getInvestTotal() {return investTotal;}    
    public void setInvestTotal(double investTotal) {this.investTotal = investTotal;}

    public double getInitialAmount() {return initialAmount;}
    public void setInitialAmount(double initialAmount) {this.initialAmount = initialAmount;}

    public double getInvestAmount() {return investAmount;}    
    public void setInvestAmount(double investAmount) {this.investAmount = investAmount;}
    
    public int getInvestInterval() {return investInterval;}    
    public void setInvestInterval(int investInterval) {this.investInterval = investInterval;}
    
    public double getQuantity() {return quantity;}    
    public void setQuantity(double quantity) {this.quantity = quantity;}
    
    public double getAvgCost() {return avgCost;}    
    public void setAvgCost(double avgCost) {this.avgCost = avgCost;}
    
    public double getProfitLoss() {return profitLoss;}    
    public void setProfitLoss(double profitLoss) {this.profitLoss = profitLoss;}
    
    public double getPortValue() {return portValue;}    
    public void setPortValue(double portValue) {this.portValue = portValue;}
    
    public double getLastPrice() {return lastPrice;}    
    public void setLastPrice(double lastPrice) {this.lastPrice = lastPrice;}
    
    public int getRepetitions() {return repetitions;}
    public void setRepetitions(int repetitions) {this.repetitions = repetitions;}
    
    public int getDuration() {return duration;}
    public void setDuration(int duration) {this.duration = duration;}

    public String getDurationFormatted(){
        int years = this.duration/365;
        int remainingDays = this.duration%365;
        int months = remainingDays/30;
        remainingDays = remainingDays%30;
        return "%d years, %d months and %d days".formatted(years,months,remainingDays);
    }

    @Override
    public String toString() {
        return 
        "ticker\t\t=" + ticker + 
        "\nstartDate\t=" + startDate+
        "\nendDate\t\t=" + endDate+
        "\nduration\t=" + getDurationFormatted()+
        "\ninitialAmount\t=" + initialAmount+
        "\ninvestAmount\t=" + investAmount +
        "\ninvestInterval\t=" + investInterval+
        "\nrepetitions\t="+repetitions+
        "\nquantity\t=" + quantity+
        "\navgCost\t\t=" + avgCost+
        "\nlastPrice\t="+ lastPrice+
        "\ninvestTotal\t=" + investTotal +
        "\nportValue\t=" + portValue +
        "\nprofitLoss\t=" + profitLoss
        ; 
    }





    
}
