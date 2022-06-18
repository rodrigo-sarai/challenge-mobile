package br.com.modal.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import br.com.modal.R;

import androidx.annotation.NonNull;

import java.util.ArrayList;

import br.com.modal.dto.OrderStatusDTO;

public class OrderStatusListAdapter extends ArrayAdapter<OrderStatusDTO> {

    public OrderStatusListAdapter(@NonNull Context context, ArrayList<OrderStatusDTO> statusList) {
        super(context, 0, statusList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        OrderStatusDTO orderStatus = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.order_status_list_item, parent, false);
        }

        ImageView imgCheck = (ImageView) convertView.findViewById(R.id.imgcheck);
        TextView txtStatus = (TextView) convertView.findViewById(R.id.txtstatus);

        txtStatus.setText(orderStatus.getLabel());
        imgCheck.setImageResource(orderStatus.isChecked() ? R.drawable.checkmark_circle_icon : R.drawable.circle_icon);
        return convertView;
    }
}
