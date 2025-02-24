package com.example.nhom24_doancuoiky.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.nhom24_doancuoiky.Home;
import com.example.nhom24_doancuoiky.R;
import com.example.nhom24_doancuoiky.adapter.OrderDetailAdapter;
import com.example.nhom24_doancuoiky.constant.ApiConstant;
import com.example.nhom24_doancuoiky.converter.OrderDetailConverter;
import com.example.nhom24_doancuoiky.response.OrderDetailApiResponse;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class OrderDetailFragment extends Fragment {
    View view;
    ListView lvOrderDetail;
    ArrayList<OrderDetailApiResponse> listOrderDetail;
    TextView tvTotalMoney,tvDate;
    Button btnOK;

    public OrderDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_order_detail, container, false);
        setControl();
        setEvent();

        return view;
    }

    private void setEvent() {
        addData();

    }

    private void addData() {
        Long idOrder = getActivity().getIntent().getLongExtra("idOrder", 0);
        if (idOrder == 0) {
            Intent intent = new Intent(getActivity(), Home.class);
            intent.putExtra("fragment_product_details", 2);
            getActivity().startActivity(intent);
        }
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        String url = ApiConstant.URL_API + "orderdetails?idOrder=" + idOrder;
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            listOrderDetail = new ArrayList<>();
                            for (int i = 0; i < response.length(); i++) {
                                listOrderDetail.add(new OrderDetailConverter().toApiResponse(response.getJSONObject(i)));
                            }
                            OrderDetailAdapter orderDetailAdapter = new OrderDetailAdapter(getContext(), R.layout.item_order_detail, listOrderDetail);
                            lvOrderDetail.setAdapter(orderDetailAdapter);
                            float sum = 0F;
                            for (int i = 0; i < listOrderDetail.size(); i++) {
                                sum += listOrderDetail.get(i).getTotalMoney();
                            }
                            tvTotalMoney.setText("TOTAL Money: " + sum);
                            btnOK.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    Intent intent = new Intent(getActivity(), Home.class);
                                    intent.putExtra("fragment_product_details", 2);
                                    getActivity().startActivity(intent);
                                }
                            });
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getContext(), "Danh sách hiện không thể tải !", Toast.LENGTH_SHORT).show();
                    }
                });
        requestQueue.add(jsonArrayRequest);
    }

    private void setControl() {
        lvOrderDetail = view.findViewById(R.id.lvOrderDetail);
        btnOK = view.findViewById(R.id.btnOK);
        tvTotalMoney = view.findViewById(R.id.tvTotalMoney);
    }
}
