package com.meomeo.asm;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.meomeo.asm.model.nhanvien;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class ScreenSuaNhanvien extends AppCompatActivity {
    private EditText edtMaNV, edtHoTen;
    private Spinner spnTenPB;
    private Button btnLuu, btnQuayLai;
    private nhanvien nhanVien;  // Khai báo nhanvien ở cấp lớp để sử dụng toàn cục

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_sua_nhanvien);

        // Khởi tạo các view
        edtMaNV = findViewById(R.id.edtMaNV);
        edtHoTen = findViewById(R.id.edtHoTen);
        spnTenPB = findViewById(R.id.spnTenPB);
        btnLuu = findViewById(R.id.btnSua);
        btnQuayLai = findViewById(R.id.btnQuaylai);

        // Khởi tạo Spinner phòng ban
        String[] phongBan = {"Đào Tạo", "Nhân Sự", "Hành Chính"}; // Có thể thêm các phòng ban khác
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, phongBan);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnTenPB.setAdapter(adapter);

        // Nhận dữ liệu nhân viên từ Intent
        nhanVien = (nhanvien) getIntent().getSerializableExtra("nhanVien");
        if (nhanVien != null) {
            edtMaNV.setText(nhanVien.getMaNv());
            edtHoTen.setText(nhanVien.getName());

            // Thiết lập phòng ban cho Spinner
            String currentPhongBan = nhanVien.getPhongBan();
            if (currentPhongBan != null) {
                int spinnerPosition = adapter.getPosition(currentPhongBan);
                spnTenPB.setSelection(spinnerPosition);
            }
        } else {
            Toast.makeText(this, "Không nhận được thông tin nhân viên!", Toast.LENGTH_SHORT).show();
        }

        // Xử lý sự kiện nút Lưu
        btnLuu.setOnClickListener(v -> {
            String maNV = edtMaNV.getText().toString();
            String hoTen = edtHoTen.getText().toString();
            String phongBanSelected = spnTenPB.getSelectedItem().toString();

            // Kiểm tra thông tin đầu vào
            if (maNV.isEmpty() || hoTen.isEmpty()) {
                Toast.makeText(ScreenSuaNhanvien.this, "Vui lòng nhập đủ thông tin!", Toast.LENGTH_SHORT).show();
            } else {
                // Cập nhật dữ liệu nhân viên
                if (nhanVien != null) {
                    nhanVien.setMaNv(maNV);
                    nhanVien.setName(hoTen);
                    nhanVien.setPhongBan(phongBanSelected);

                    // Ghi dữ liệu mới vào file
                    updateEmployeeInFile(nhanVien);

                    // Sau khi cập nhật thành công, chuyển về màn hình danh sách nhân viên
                    Intent intent = new Intent(ScreenSuaNhanvien.this, ScreenNhanvien.class); // Thay bằng tên activity danh sách nhân viên
                    startActivity(intent);
                    finish(); // Kết thúc activity hiện tại để tránh quay lại
                } else {
                    Toast.makeText(ScreenSuaNhanvien.this, "Lỗi khi cập nhật nhân viên", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Xử lý sự kiện nút Quay lại
        btnQuayLai.setOnClickListener(v -> finish());
    }

    // Hàm cập nhật thông tin nhân viên trong file
    private void updateEmployeeInFile(nhanvien updatedNhanVien) {
        List<nhanvien> employeeList = loadEmployeeListFromFile();  // Đọc dữ liệu hiện tại từ file
        if (employeeList == null) {
            return;  // Nếu không đọc được file, thoát
        }

        // Tìm và cập nhật thông tin của nhân viên
        for (nhanvien employee : employeeList) {
            if (employee.getMaNv().equals(updatedNhanVien.getMaNv())) {
                employee.setName(updatedNhanVien.getName());
                employee.setPhongBan(updatedNhanVien.getPhongBan());
                break;
            }
        }

        // Ghi lại danh sách nhân viên mới vào file
        saveEmployeeListToFile(employeeList);
    }

    // Đọc danh sách nhân viên từ file
    private List<nhanvien> loadEmployeeListFromFile() {
        List<nhanvien> employees = new ArrayList<>();
        try {
            FileInputStream fis = openFileInput("nhanvien.txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
            String line;

            // Đọc từng dòng trong file
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");  // Tách chuỗi theo dấu phẩy
                if (parts.length == 3) {
                    String maNv = parts[0];
                    String name = parts[1];
                    String phongBan = parts[2];
                    // Tạo đối tượng nhân viên từ dữ liệu và thêm vào danh sách
                    employees.add(new nhanvien(maNv, name, phongBan));
                }
            }

            fis.close();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Lỗi khi đọc file", Toast.LENGTH_SHORT).show();
            return null;
        }
        return employees;
    }

    // Lưu danh sách nhân viên vào file
    private void saveEmployeeListToFile(List<nhanvien> employeeList) {
        try {
            FileOutputStream fos = openFileOutput("nhanvien.txt", MODE_PRIVATE);
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(fos));

            // Ghi từng nhân viên vào file
            for (nhanvien employee : employeeList) {
                String line = employee.getMaNv() + "," + employee.getName() + "," + employee.getPhongBan();
                writer.write(line);
                writer.newLine();
            }

            writer.close();
            fos.close();
            Toast.makeText(this, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Lỗi khi ghi file", Toast.LENGTH_SHORT).show();
        }
    }
}
