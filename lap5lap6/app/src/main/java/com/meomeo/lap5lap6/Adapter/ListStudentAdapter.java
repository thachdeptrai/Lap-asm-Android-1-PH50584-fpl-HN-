package com.meomeo.lap5lap6.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.meomeo.lap5lap6.HomeActivity;
import com.meomeo.lap5lap6.R;
import com.meomeo.lap5lap6.UpdateStudentActivity; // Đảm bảo bạn import Activity này
import com.meomeo.lap5lap6.model.Student;

import java.util.ArrayList;

public class ListStudentAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Student> studentList;

    public ListStudentAdapter(Context context, ArrayList<Student> studentList) {
        this.context = context;
        this.studentList = studentList;
    }

    @Override
    public int getCount() {
        return studentList.size();
    }

    @Override
    public Object getItem(int position) {
        return studentList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_listview, parent, false);
        }

        TextView tvBranch = convertView.findViewById(R.id.tvBranch);
        TextView tvName = convertView.findViewById(R.id.tvName);
        TextView tvAddress = convertView.findViewById(R.id.tvAddress);
        Button btnUpdate = convertView.findViewById(R.id.btnUpdate);
        Button btnDelete = convertView.findViewById(R.id.btnDelete);

        Student student = studentList.get(position);
        tvBranch.setText(student.getBranch());
        tvName.setText("Họ tên: " + student.getName());
        tvAddress.setText("Địa chỉ: " + student.getAddress());

        // Xử lý sự kiện Update
        btnUpdate.setOnClickListener(v -> {
            Intent intent = new Intent(context, UpdateStudentActivity.class);
            // Gửi thông tin sinh viên để sửa
            intent.putExtra("name", student.getName());
            intent.putExtra("address", student.getAddress());
            intent.putExtra("branch", student.getBranch());
            // Khởi động Activity cập nhật
            ((HomeActivity) context).startActivityForResult(intent, 2); // Gọi startActivityForResult từ HomeActivity
        });

        // Xử lý sự kiện Delete
        btnDelete.setOnClickListener(v -> {
            studentList.remove(position);
            notifyDataSetChanged();
            Toast.makeText(context, "Deleted " + student.getName(), Toast.LENGTH_SHORT).show();
        });

        return convertView;
    }
}
