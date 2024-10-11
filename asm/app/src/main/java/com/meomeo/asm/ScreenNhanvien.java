package com.meomeo.asm;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
public class ScreenNhanvien extends AppCompatActivity {
    private Button btnThem;
    private ImageView imgBack; // Khai báo biến cho nút back
    private ListView lvNhanvien;
    private EmployeeAdapter employeeAdapter;
    private List<nhanvien> employeeList;
    private ImageView imgSearch;
    private EditText searchEditText; // Trường tìm kiếm

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_screen_nhanvien);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        imgBack = findViewById(R.id.imgBack); // Lấy nút back từ layout
        // Kiểm tra nếu file nhân viên tồn tại
        if (!isEmployeeFileExists()) {
            initializeAndSaveEmployeeList();
        }

        // Khởi tạo danh sách nhân viên từ file
        loadEmployeeListFromFile();

        btnThem = findViewById(R.id.btnAddnhanvien);
        lvNhanvien = findViewById(R.id.lvNhanvien);
        imgSearch = findViewById(R.id.imgSearch);
        searchEditText = findViewById(R.id.searchEditText);

        // Thiết lập Adapter với danh sách nhân viên đã đọc từ file
        employeeAdapter = new EmployeeAdapter(this, employeeList);
        lvNhanvien.setAdapter(employeeAdapter);

        // Sự kiện nút thêm nhân viên
        btnThem.setOnClickListener(v -> {
            Intent intent1 = new Intent(ScreenNhanvien.this, ScreenAddnhanvien.class);
            startActivityForResult(intent1, 1);
        });

        // Sự kiện tìm kiếm
        imgSearch.setOnClickListener(v -> {
            if (searchEditText.getVisibility() == View.GONE) {
                searchEditText.setVisibility(View.VISIBLE);
            } else {
                searchEditText.setVisibility(View.GONE);
            }
        });

        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filterEmployees(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });
        imgBack.setOnClickListener(v -> getOnBackPressedDispatcher().onBackPressed());
    }

    private boolean isEmployeeFileExists() {
        try {
            FileInputStream fis = openFileInput("nhanvien.txt");
            fis.close();
            return true; // File tồn tại
        } catch (IOException e) {
            return false; // File không tồn tại
        }
    }

    private void initializeAndSaveEmployeeList() {
        employeeList = new ArrayList<>();
        employeeList.add(new nhanvien("001", "Nguyễn Văn A", "Đào Tạo"));
        employeeList.add(new nhanvien("002", "Trần Thị B", "Nhân Sự"));
        employeeList.add(new nhanvien("003", "Lê Văn C", "Hành Chính"));
        employeeList.add(new nhanvien("004", "Phạm Văn D", "Nhân Sự"));
        employeeList.add(new nhanvien("005", "Nguyễn Thị E", "Đào Tạo"));
        employeeList.add(new nhanvien("006", "Lê Văn F", "Hành Chính"));

        // Lưu danh sách nhân viên vào file
        saveEmployeeListToFile();
    }

    private void filterEmployees(String query) {
        List<nhanvien> filteredList = new ArrayList<>();
        for (nhanvien employee : employeeList) {
            if (employee.getName().toLowerCase().contains(query.toLowerCase()) ||
                    employee.getMaNv().toLowerCase().contains(query.toLowerCase()) ||
                    employee.getPhongBan().toLowerCase().contains(query.toLowerCase())) {
                filteredList.add(employee);
            }
        }
        employeeAdapter.updateList(filteredList);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            nhanvien newNhanVien = (nhanvien) data.getSerializableExtra("newNhanVien");
            if (newNhanVien != null) {
                employeeList.add(newNhanVien);
                employeeAdapter.notifyDataSetChanged();
                saveEmployeeListToFile(); // Lưu danh sách nhân viên sau khi thêm
            }
        }
    }

    private void saveEmployeeListToFile() {
        try {
            FileOutputStream fos = openFileOutput("nhanvien.txt", MODE_PRIVATE); // Chỉ ghi đè
            StringBuilder sb = new StringBuilder();

            // Duyệt qua danh sách nhân viên và chuyển từng nhân viên thành chuỗi
            for (nhanvien nv : employeeList) {
                sb.append(nv.getMaNv()).append(",")
                        .append(nv.getName()).append(",")
                        .append(nv.getPhongBan()).append("\n");
            }

            fos.write(sb.toString().getBytes());
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadEmployeeListFromFile() {
        employeeList = new ArrayList<>();
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
                    employeeList.add(new nhanvien(maNv, name, phongBan));
                }
            }

            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
