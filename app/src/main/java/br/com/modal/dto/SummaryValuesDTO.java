package br.com.modal.dto;

public class SummaryValuesDTO {
    private String monthly_price;
    private String extras;
    private String total_price;

    public String getMonthly_price() {
        return monthly_price;
    }

    public void setMonthly_price(String monthly_price) {
        this.monthly_price = monthly_price;
    }

    public String getExtras() {
        return extras;
    }

    public void setExtras(String extras) {
        this.extras = extras;
    }

    public String getTotal_price() {
        return total_price;
    }

    public void setTotal_price(String total_price) {
        this.total_price = total_price;
    }
}
