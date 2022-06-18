package br.com.modal.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import br.com.modal.R;
import br.com.modal.core.Utils;
import br.com.modal.databinding.ActivityOrderListBinding;
import br.com.modal.dto.ErrorResponseDTO;
import br.com.modal.dto.OrderDTO;
import br.com.modal.service.OrderListService;
import br.com.modal.ui.adapter.OrderListAdapter;

public class OrderListActivity extends AppCompatActivity {

    private ActivityOrderListBinding binding;

    private final ParsedRequestListener<List<OrderDTO>> orderListRequestListener = new ParsedRequestListener<List<OrderDTO>>() {
        @Override
        public void onResponse(List<OrderDTO> profileRequestResponse) {
            initializeAdapter(profileRequestResponse);
        }

        @Override
        public void onError(ANError anError) {

            String errorMessage;

            try {
                ErrorResponseDTO errorResponse = new Gson().fromJson(anError.getErrorBody(), ErrorResponseDTO.class);
                errorMessage = errorResponse.getError_message();
            }catch(Exception e){
                errorMessage = getString(R.string.error_getting_orderlist);
            }

            Toast.makeText(getApplicationContext(), errorMessage, Toast.LENGTH_LONG).show();
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityOrderListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.lstorders.setDivider(null);

        OrderListService.getUserOrders(Utils.getLoginToken(this), orderListRequestListener);
    }

    private void initializeAdapter(List<OrderDTO> orderList){
        OrderListAdapter orderListAdapter = new OrderListAdapter(this, new ArrayList<>(orderList));
        binding.lstorders.setAdapter(orderListAdapter);
        binding.lstorders.setOnItemClickListener((adapterView, view, i, l) -> {

            OrderDTO item = (OrderDTO) adapterView.getAdapter().getItem(i);
            navigateOrderDetail(item.getOrder_id());
        });
    }

    private void navigateOrderDetail(int orderId){
        Intent intent = new Intent(this, OrderDetailsActivity.class);
        intent.putExtra("orderId", orderId);
        startActivity(intent);
    }
}