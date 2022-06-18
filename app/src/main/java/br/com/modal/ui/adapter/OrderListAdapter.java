package br.com.modal.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;

import br.com.modal.R;
import br.com.modal.dto.OrderDTO;

public class OrderListAdapter extends ArrayAdapter<OrderDTO> {

    private Context context;
    public OrderListAdapter(@NonNull Context context, ArrayList<OrderDTO> orderList) {
        super(context, 0, orderList);
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        OrderDTO order = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.order_list_item, parent, false);
        }

        TextView txtOrderDescription = (TextView) convertView.findViewById(R.id.txtorderdescription);

        txtOrderDescription.setText(order.getSubmodel_name());
        return convertView;
    }
}
