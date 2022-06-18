package br.com.modal.ui.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.BitmapRequestListener;
import com.androidnetworking.interfaces.ParsedRequestListener;
import com.google.gson.Gson;

import java.util.ArrayList;

import br.com.modal.R;
import br.com.modal.core.Utils;
import br.com.modal.databinding.ActivityHomeBinding;
import br.com.modal.dto.ErrorResponseDTO;
import br.com.modal.dto.OrderDTO;
import br.com.modal.dto.ProfileDTO;
import br.com.modal.service.ImageLoaderService;
import br.com.modal.service.ProfileService;
import br.com.modal.ui.adapter.OrderStatusListAdapter;

public class HomeActivity extends AppCompatActivity {

    private ActivityHomeBinding binding;
    private ProfileDTO profileInfo;
    private int itemLoaded = 0;

    private final ParsedRequestListener<ProfileDTO> profileRequestListener = new ParsedRequestListener<ProfileDTO>() {
        @Override
        public void onResponse(ProfileDTO profileRequestResponse) {
            profileInfo = profileRequestResponse;
            loadProfileInfo();
        }

        @Override
        public void onError(ANError anError) {

            String errorMessage;

            try {
                ErrorResponseDTO errorResponse = new Gson().fromJson(anError.getErrorBody(), ErrorResponseDTO.class);
                errorMessage = errorResponse.getError_message();
            }catch(Exception e){
                errorMessage = getString(R.string.error_getting_profile);
            }

            Toast.makeText(getApplicationContext(), errorMessage, Toast.LENGTH_LONG).show();
            navigateLogin();
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.lststatus.setDivider(null);

        binding.txtprioritem.setOnClickListener(view -> loadItem(itemLoaded - 1));
        binding.txtnextitem.setOnClickListener(view -> loadItem(itemLoaded + 1));

        binding.viewassinaturas.setOnClickListener(view -> navigateOrderList());
        binding.viewsair.setOnClickListener(view -> navigateLogin());

        ProfileService.getUserProfile(Utils.getLoginToken(this), profileRequestListener);
    }

    private void loadItem(int index){
        int blackColor = getResources().getColor(R.color.black);
        int grayColor = getResources().getColor(R.color.light_gray);

        binding.txtprioritem.setTextColor(profileInfo.getOrders().size() > 0 && index > 0 ? blackColor : grayColor);
        binding.txtnextitem.setTextColor(profileInfo.getOrders().size() > 0 && index < profileInfo.getOrders().size() - 1 ? blackColor : grayColor);

        if(profileInfo.getOrders().isEmpty() || index < 0 || index > profileInfo.getOrders().size() - 1){
            return;
        }

        OrderDTO order = profileInfo.getOrders().get(index);

        binding.txtitemdescription.setText(getString(R.string.home_order_description, order.getOrder_id(), order.getSubmodel_name()));

        OrderStatusListAdapter statusListAdapter = new OrderStatusListAdapter(this, new ArrayList<>(order.getStatuses()));
        binding.lststatus.setAdapter(statusListAdapter);

        itemLoaded = index;
    }

    private void loadProfileInfo(){
        binding.txtusername.setText(profileInfo.getFullname());
        binding.txtusercity.setText(getString(R.string.home_user_address, profileInfo.getCity(), profileInfo.getState_abbr()));
        if(!profileInfo.getAvatar_url().trim().isEmpty()) {
            ImageLoaderService.getImage(profileInfo.getAvatar_url(),
                    new BitmapRequestListener() {
                            @Override
                            public void onResponse(Bitmap bitmap) {
                                binding.imgusericon.setImageBitmap(bitmap);
                            }
                            @Override
                            public void onError(ANError error) {}
                        });
        }

        loadItem(0);
    }

    private void navigateLogin(){
        Utils.setLoginToken("", this);
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);

        finish();
    }

    private void navigateOrderList(){
        Intent intent = new Intent(this, OrderListActivity.class);
        startActivity(intent);
    }
}