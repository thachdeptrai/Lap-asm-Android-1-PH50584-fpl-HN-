package com.meomeo.lap5lap6.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.meomeo.lap5lap6.R;
import com.meomeo.lap5lap6.model.School;
import com.meomeo.lap5lap6.model.Student;

import java.util.ArrayList;

public class SchoolSpinnerAdapter extends BaseAdapter {
    private Context context;
    private String[] branches;
    private int[] images;

    // Constructor để khởi tạo adapter với mảng chi nhánh và mảng hình ảnh tương ứng
    public SchoolSpinnerAdapter(Context context, String[] branches, int[] images) {
        this.context = context;
        this.branches = branches;
        this.images = images;
    }

    @Override
    public int getCount() {
        return branches.length;
    }

    @Override
    public Object getItem(int position) {
        return branches[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.spinner_item, parent, false);
        }

        // Ánh xạ đến các thành phần trong layout item_spinner.xml
        ImageView imgBranchIcon = convertView.findViewById(R.id.imgLogo);
        TextView tvBranchName = convertView.findViewById(R.id.tvName);

        // Gán dữ liệu vào các thành phần
        imgBranchIcon.setImageResource(images[position]); // Gán ảnh từ mảng images
        tvBranchName.setText(branches[position]); // Gán tên chi nhánh

        return convertView;
    }
}
