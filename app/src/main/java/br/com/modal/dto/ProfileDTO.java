package br.com.modal.dto;

import java.util.List;

public class ProfileDTO {
    private boolean success;
    private String avatar_url;
    private String username;
    private String fullname;
    private String city;
    private String state_abbr;
    private List<OrderDTO> orders;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getAvatar_url() {
        return avatar_url;
    }

    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState_abbr() {
        return state_abbr;
    }

    public void setState_abbr(String state_abbr) {
        this.state_abbr = state_abbr;
    }

    public List<OrderDTO> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderDTO> orders) {
        this.orders = orders;
    }
}
