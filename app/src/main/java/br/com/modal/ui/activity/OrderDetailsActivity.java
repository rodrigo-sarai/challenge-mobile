package br.com.modal.ui.activity;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;
import com.google.gson.Gson;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;

import br.com.modal.R;
import br.com.modal.core.Utils;
import br.com.modal.databinding.ActivityOrderDetailsBinding;
import br.com.modal.dto.ErrorResponseDTO;
import br.com.modal.dto.OrderDetailsDTO;
import br.com.modal.service.OrderDetailsService;
import br.com.modal.ui.adapter.SliderAdapter;
import br.com.modal.ui.adapter.SliderAdapterData;

public class OrderDetailsActivity extends AppCompatActivity {

    private ActivityOrderDetailsBinding binding;
    private OrderDetailsDTO orderDetailsInfo;

    private final ParsedRequestListener<OrderDetailsDTO> orderDetailRequestListener = new ParsedRequestListener<OrderDetailsDTO>() {
        @Override
        public void onResponse(OrderDetailsDTO profileRequestResponse) {
            orderDetailsInfo = profileRequestResponse;
            loadOrderDetails();
        }

        @Override
        public void onError(ANError anError) {

            String errorMessage;

            try {
                ErrorResponseDTO errorResponse = new Gson().fromJson(anError.getErrorBody(), ErrorResponseDTO.class);
                errorMessage = errorResponse.getError_message();
            }catch(Exception e){
                errorMessage = getString(R.string.error_getting_orderdetails);
            }

            Toast.makeText(getApplicationContext(), errorMessage, Toast.LENGTH_LONG).show();
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityOrderDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        OrderDetailsService.getOrderDetails(Utils.getLoginToken(this), getIntent().getIntExtra("orderId", 0), orderDetailRequestListener);
    }

    private void loadOrderDetails(){
        binding.txtmarca.setText(getString(R.string.orderdetails_modelyear, orderDetailsInfo.getVehicle_details().getVehicle_year(), orderDetailsInfo.getVehicle_details().getVehicle_brand()));
        binding.txtmodelo.setText(orderDetailsInfo.getVehicle_details().getVehicle_model());
        binding.txtmensalidade.setText(getString(R.string.orderdetails_montlyprice, orderDetailsInfo.getSummary_values().getMonthly_price()));
        binding.txtfuel.setText(orderDetailsInfo.getVehicle_details().getFuel_type());
        binding.txtdoors.setText(getString(R.string.orderdetails_vehicledoors, orderDetailsInfo.getVehicle_details().getDoors_qtd()));
        binding.txtenginetype.setText(orderDetailsInfo.getVehicle_details().getEngine_type());
        binding.txtengine.setText(getString(R.string.orderdetails_vehicleengine, orderDetailsInfo.getVehicle_details().getEngine()));
        binding.txtmonths.setText(String.valueOf(orderDetailsInfo.getSignature_details().getMonths()));
        binding.txtplantype.setText(String.valueOf(orderDetailsInfo.getSignature_details().getPlan_type()));
        binding.txtadditionalfranchise.setText(getString(R.string.orderdetails_additionalfranchisevalue, orderDetailsInfo.getSignature_details().getAdditional_franchise()));
        binding.txtmontlyprice.setText(getString(R.string.orderdetails_pricepresentation, orderDetailsInfo.getSummary_values().getMonthly_price()));
        binding.txtextrasprice.setText(getString(R.string.orderdetails_pricepresentation, orderDetailsInfo.getSummary_values().getExtras()));
        binding.txttotalprice.setText(getString(R.string.orderdetails_pricepresentation, orderDetailsInfo.getSummary_values().getTotal_price()));

        initializeImageSlider(orderDetailsInfo.getVehicle_details().getImage_url());

    }

    private void initializeImageSlider(List<String> imageList){

        ArrayList<SliderAdapterData> sliderData = new ArrayList<>();
        for(String url : imageList){
            sliderData.add(new SliderAdapterData(url));
        }

        SliderAdapter adapter = new SliderAdapter(sliderData);

        binding.imgSlider.setAutoCycleDirection(SliderView.LAYOUT_DIRECTION_LTR);
        binding.imgSlider.setSliderAdapter(adapter);
        binding.imgSlider.setScrollTimeInSec(3);
        binding.imgSlider.setAutoCycle(true);
        binding.imgSlider.startAutoCycle();
    }
}