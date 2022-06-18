package br.com.modal.dto;

import java.util.List;

public class OrderDTO {
    private int order_id;
    private String submodel_name;
    private List<OrderStatusDTO> statuses;

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public String getSubmodel_name() {
        return submodel_name;
    }

    public void setSubmodel_name(String submodel_name) {
        this.submodel_name = submodel_name;
    }

    public List<OrderStatusDTO> getStatuses() {
        return statuses;
    }

    public void setStatuses(List<OrderStatusDTO> statuses) {
        this.statuses = statuses;
    }
}
