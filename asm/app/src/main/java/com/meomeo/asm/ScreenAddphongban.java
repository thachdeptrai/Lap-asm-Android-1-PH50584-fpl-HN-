package com.meomeo.asm;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class ScreenAddphongban extends AppCompatActivity {
    private EditText edtPhongban;
    private Button btnThem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_addphongban);

        edtPhongban = findViewById(R.id.edtPhongban);
        btnThem = findViewById(R.id.btnThem);

        // Xử lý sự kiện nút "Thêm"
        btnThem.setOnClickListener(v -> {
            String tenPhongBan = edtPhongban.getText().toString().trim();
            if (tenPhongBan.isEmpty()) {
                Toast.makeText(ScreenAddphongban.this, "Vui lòng nhập tên phòng ban!", Toast.LENGTH_SHORT).show();
            } else {
                // Lưu phòng ban vào file
                boolean isSuccess = savePhongBanToFile(tenPhongBan);
                if (isSuccess) {
                    Toast.makeText(ScreenAddphongban.this, "Đã thêm phòng ban: " + tenPhongBan, Toast.LENGTH_SHORT).show();
                    // Quay lại màn hình trước đó
                    setResult(RESULT_OK);
                    finish();
                } else {
                    Toast.makeText(ScreenAddphongban.this, "Có lỗi xảy ra khi thêm phòng ban!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean savePhongBanToFile(String tenPhongBan) {
        try {
            // Mở file để ghi thêm tên phòng ban mới
            BufferedWriter writer = new BufferedWriter(new FileWriter(getFilesDir() + "/phongban.txt", true));
            writer.write(tenPhongBan);
            writer.newLine(); // Thêm dòng mới
            writer.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false; // Trả về false nếu có lỗi xảy ra
        }
    }
}
