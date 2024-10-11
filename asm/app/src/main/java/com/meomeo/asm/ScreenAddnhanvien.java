package com.meomeo.asm;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.meomeo.asm.model.nhanvien;

public class ScreenAddnhanvien extends AppCompatActivity {
    private EditText edtMaNV, edtHoTen;
    private Spinner spnTenPB;
    private Button btnThem, btnQuayLai;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_screen_addnhanvien);

        // Thiết lập padding cho layout chính
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Khởi tạo các view
        edtMaNV = findViewById(R.id.edtMaNV);
        edtHoTen = findViewById(R.id.edtHoTen);
        spnTenPB = findViewById(R.id.spnTenPB);
        btnThem = findViewById(R.id.btnThem);
        btnQuayLai = findViewById(R.id.btnQuaylai);

        // Khởi tạo Spinner phòng ban
        String[] phongBan = {"Đào Tạo", "Nhân Sự", "Hành Chính"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, phongBan);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnTenPB.setAdapter(adapter);

        // Xử lý sự kiện nút "Thêm"
        btnThem.setOnClickListener(v -> {
            String maNV = edtMaNV.getText().toString().trim(); // Xóa khoảng trắng
            String hoTen = edtHoTen.getText().toString().trim(); // Xóa khoảng trắng
            String phongBanSelected = spnTenPB.getSelectedItem().toString();

            // Kiểm tra thông tin đầu vào
            if (maNV.isEmpty() || hoTen.isEmpty()) {
                Toast.makeText(ScreenAddnhanvien.this, "Vui lòng nhập đủ thông tin!", Toast.LENGTH_SHORT).show();
            } else {
                // Tạo đối tượng nhân viên mới
                nhanvien newNhanVien = new nhanvien(maNV, hoTen, phongBanSelected);

                // Trả về kết quả và đóng Activity
                Intent resultIntent = new Intent();
                resultIntent.putExtra("newNhanVien", newNhanVien);
                setResult(RESULT_OK, resultIntent);
                finish(); // Đóng activity và quay lại
            }
        });

        // Xử lý sự kiện nút "Quay lại"
        btnQuayLai.setOnClickListener(v -> finish()); // Đóng activity
    }
}
