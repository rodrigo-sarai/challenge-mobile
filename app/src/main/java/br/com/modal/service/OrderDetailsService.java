package br.com.modal.service;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.interfaces.ParsedRequestListener;

import br.com.modal.core.Utils;
import br.com.modal.dto.OrderDetailsDTO;

public abstract class OrderDetailsService {
    private static final String METHOD_URI = Utils.WEB_SERVICE_URI + "api/v1/user/profile/order_details";

    public static void getOrderDetails(String loginToken, int orderId, ParsedRequestListener<OrderDetailsDTO> requestListener){

        AndroidNetworking.get(METHOD_URI)
                .addQueryParameter("token", loginToken)
                .addQueryParameter("order_id", String.valueOf(orderId))
                .setTag("OrderDetailsService_getOrderDetails")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsObject(OrderDetailsDTO.class, requestListener);
    }
}
