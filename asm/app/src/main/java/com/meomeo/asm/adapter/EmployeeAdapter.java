package com.meomeo.asm.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.meomeo.asm.R;
import com.meomeo.asm.ScreenSuaNhanvien;
import com.meomeo.asm.model.nhanvien;

import java.util.List;

public class EmployeeAdapter extends ArrayAdapter<nhanvien> {
    private final List<nhanvien> employees;

    public EmployeeAdapter(Context context, List<nhanvien> employees) {
        super(context, 0, employees);
        this.employees = employees;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.layout_item_listview, parent, false);
        }

        nhanvien employee = getItem(position);

        TextView employeeMaNv = convertView.findViewById(R.id.tvMaNV);
        TextView employeeName = convertView.findViewById(R.id.tvHoTen);
        TextView employeePhongban = convertView.findViewById(R.id.tvPb);
        ImageView imgEdit = convertView.findViewById(R.id.imgSua);
        ImageView imgDelete = convertView.findViewById(R.id.imgDelete);

        employeeMaNv.setText(employee.getMaNv());
        employeeName.setText(employee.getName());
        employeePhongban.setText(employee.getPhongBan());
        // Sự kiện nút Sửa
        imgEdit.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), ScreenSuaNhanvien.class);
            intent.putExtra("nhanVien", employee); // Truyền đối tượng nhân viên
            ((Activity) getContext()).startActivityForResult(intent, 101); // Sử dụng startActivityForResult
        });

        // Sự kiện nút Xóa
        imgDelete.setOnClickListener(v -> {
            employees.remove(position);
            notifyDataSetChanged();
            Toast.makeText(getContext(), "Đã xóa nhân viên: " + employee.getName(), Toast.LENGTH_SHORT).show();
        });
        return convertView;
    }

    public void updateList(List<nhanvien> newEmployees) {
        clear();
        addAll(newEmployees);
        notifyDataSetChanged();
    }
}
