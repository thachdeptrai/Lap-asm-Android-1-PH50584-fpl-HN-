package com.meomeo.asm;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.meomeo.asm.adapter.EmployeeAdapter;
import com.meomeo.asm.model.nhanvien;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ScreenDaotap extends AppCompatActivity {
    private ListView lvNhanVien;
    private EmployeeAdapter employeeAdapter;
    private List<nhanvien> employeeList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_daotap);
        ImageView imgBack = findViewById(R.id.imgBack); // Lấy nút back từ layout
        imgBack.setOnClickListener(v -> getOnBackPressedDispatcher().onBackPressed());

        lvNhanVien = findViewById(R.id.lvNhanvien);

        // Lấy danh sách nhân viên từ file
        employeeList = loadEmployeeListFromFile();

        // Lọc danh sách chỉ lấy nhân viên có phòng ban là "Nhân Sự"
        List<nhanvien> filteredEmployeeList = filterEmployeesByPhongBan("Đào Tạo");

        // Thiết lập adapter với danh sách nhân viên đã lọc
        employeeAdapter = new EmployeeAdapter(this, filteredEmployeeList);
        lvNhanVien.setAdapter(employeeAdapter);
    }

    private List<nhanvien> loadEmployeeListFromFile() {
        List<nhanvien> employees = new ArrayList<>();
        try {
            FileInputStream fis = openFileInput("nhanvien.txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
            String line;

            // Đọc từng dòng trong file
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(","); // Tách chuỗi theo dấu phẩy
                if (parts.length == 3) {
                    String maNv = parts[0];
                    String name = parts[1];
                    String phongBan = parts[2];
                    // Tạo đối tượng nhân viên từ dữ liệu và thêm vào danh sách
                    employees.add(new nhanvien(maNv, name, phongBan));
                }
            }

            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return employees;
    }

    private List<nhanvien> filterEmployeesByPhongBan(String phongBan) {
        List<nhanvien> filteredList = new ArrayList<>();
        for (nhanvien employee : employeeList) {
            if (employee.getPhongBan().equalsIgnoreCase(phongBan)) {
                filteredList.add(employee);
            }
        }
        return filteredList;
    }
}
