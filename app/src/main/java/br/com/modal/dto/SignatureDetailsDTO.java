package br.com.modal.dto;

import java.util.List;

public class SignatureDetailsDTO {
    private int months;
    private int plan_type;
    private String additional_franchise;

    public int getMonths() {
        return months;
    }

    public void setMonths(int months) {
        this.months = months;
    }

    public int getPlan_type() {
        return plan_type;
    }

    public void setPlan_type(int plan_type) {
        this.plan_type = plan_type;
    }

    public String getAdditional_franchise() {
        return additional_franchise;
    }

    public void setAdditional_franchise(String additional_franchise) {
        this.additional_franchise = additional_franchise;
    }
}
