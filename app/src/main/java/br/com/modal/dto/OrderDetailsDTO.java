package br.com.modal.dto;

public class OrderDetailsDTO {
    private VehicleDetailsDTO vehicle_details;
    private SignatureDetailsDTO signature_details;
    private SummaryValuesDTO summary_values;

    public VehicleDetailsDTO getVehicle_details() {
        return vehicle_details;
    }

    public void setVehicle_details(VehicleDetailsDTO vehicle_details) {
        this.vehicle_details = vehicle_details;
    }

    public SignatureDetailsDTO getSignature_details() {
        return signature_details;
    }

    public void setSignature_details(SignatureDetailsDTO signature_details) {
        this.signature_details = signature_details;
    }

    public SummaryValuesDTO getSummary_values() {
        return summary_values;
    }

    public void setSummary_values(SummaryValuesDTO summary_values) {
        this.summary_values = summary_values;
    }
}
