package br.com.modal.service;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.interfaces.ParsedRequestListener;

import java.util.List;

import br.com.modal.core.Utils;
import br.com.modal.dto.OrderDTO;

public abstract class OrderListService {
    private static final String METHOD_URI = Utils.WEB_SERVICE_URI + "api/v1/user/profile/orders";

    public static void getUserOrders(String loginToken, ParsedRequestListener<List<OrderDTO>> requestListener){

        AndroidNetworking.get(METHOD_URI)
                .addQueryParameter("token", loginToken)
                .setTag("OrderListService_getUserOrders")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsObjectList(OrderDTO.class, requestListener);
    }
}
