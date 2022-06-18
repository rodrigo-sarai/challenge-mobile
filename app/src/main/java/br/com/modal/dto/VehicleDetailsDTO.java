package br.com.modal.dto;

import java.util.List;

public class VehicleDetailsDTO {
    private List<String> image_url;
    private int vehicle_year;
    private String vehicle_brand;
    private String vehicle_model;
    private String engine;
    private String engine_type;
    private String fuel_type;
    private int doors_qtd;
    private int delivery_delay;
    private int km;

    public List<String> getImage_url() {
        return image_url;
    }

    public void setImage_url(List<String> image_url) {
        this.image_url = image_url;
    }

    public int getVehicle_year() {
        return vehicle_year;
    }

    public void setVehicle_year(int vehicle_year) {
        this.vehicle_year = vehicle_year;
    }

    public String getVehicle_brand() {
        return vehicle_brand;
    }

    public void setVehicle_brand(String vehicle_brand) {
        this.vehicle_brand = vehicle_brand;
    }

    public String getVehicle_model() {
        return vehicle_model;
    }

    public void setVehicle_model(String vehicle_model) {
        this.vehicle_model = vehicle_model;
    }

    public String getEngine() {
        return engine;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }

    public String getEngine_type() {
        return engine_type;
    }

    public void setEngine_type(String engine_type) {
        this.engine_type = engine_type;
    }

    public String getFuel_type() {
        return fuel_type;
    }

    public void setFuel_type(String fuel_type) {
        this.fuel_type = fuel_type;
    }

    public int getDoors_qtd() {
        return doors_qtd;
    }

    public void setDoors_qtd(int doors_qtd) {
        this.doors_qtd = doors_qtd;
    }

    public int getDelivery_delay() {
        return delivery_delay;
    }

    public void setDelivery_delay(int delivery_delay) {
        this.delivery_delay = delivery_delay;
    }

    public int getKm() {
        return km;
    }

    public void setKm(int km) {
        this.km = km;
    }
}
