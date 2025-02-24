package com.example.nhom24_doancuoiky.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.nhom24_doancuoiky.R;
import com.example.nhom24_doancuoiky.constant.ApiConstant;
import com.example.nhom24_doancuoiky.response.OrderDetailApiResponse;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class OrderDetailAdapter extends ArrayAdapter<OrderDetailApiResponse> {
    Context context;
    int resource;
    ArrayList<OrderDetailApiResponse> OrderDetailModels;
    OrderDetailApiResponse orderDetail;

    ImageView ivImage;
    TextView tvName, tvAmount, tvPrice;

    public OrderDetailAdapter(@NonNull Context context, int resource, @NonNull ArrayList<OrderDetailApiResponse> OrderDetailModels) {
        super(context, resource, OrderDetailModels);
        this.context = context;
        this.resource = resource;
        this.OrderDetailModels = OrderDetailModels;
    }

    @Override
    public int getCount() {
        return OrderDetailModels.size();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(resource, null);

        ivImage = convertView.findViewById(R.id.ivImage);
        tvName = convertView.findViewById(R.id.tvName);
        tvAmount = convertView.findViewById(R.id.tvAmount);
        tvPrice = convertView.findViewById(R.id.tvPrice);

        orderDetail = OrderDetailModels.get(position); // lấy vị trí hiện tại để đẩy lên tv và iv
        tvName.setText("Name:        " + orderDetail.getNameProd());
        tvAmount.setText("Amount:    " + orderDetail.getQuantity());
        tvPrice.setText("Price:         " + orderDetail.getTotalMoney() + "$");
        Picasso.get().load(ApiConstant.URL_IMG + orderDetail.getLinkImgProd()).into(ivImage);

//        setEvent();

        return convertView;
    }

//    private void setEvent() {
//        ivImage.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View view) {
//                Intent intent = new Intent(context, Home.class);
//                intent.putExtra("fragment_product_details",3);
//                context.startActivity(intent);
//            }
//        });
//    }
}
