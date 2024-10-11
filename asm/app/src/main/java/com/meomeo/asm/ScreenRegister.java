package com.meomeo.asm;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.FileOutputStream;
import java.io.IOException;

public class ScreenRegister extends AppCompatActivity {
    private Button btnDangky, btnQuaylai;
    private EditText edtUser, edtPass, edtCPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_screen_register);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        btnDangky = findViewById(R.id.btnDangKy);
        btnQuaylai = findViewById(R.id.btnQuayLai);
        edtUser = findViewById(R.id.edtUser);
        edtPass = findViewById(R.id.edtPass);
        edtCPass = findViewById(R.id.edtCPass);
        btnDangky.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
            String username = edtUser.getText().toString();
            String password = edtPass.getText().toString();
            String confirmPassword = edtCPass.getText().toString();

            if (validateInput(username, password, confirmPassword)) {
                // Lưu thông tin đăng ký vào file
                saveToFile(username, password);
            }
        }
    });

        btnQuaylai.setOnClickListener(v -> {
            // Xử lý sự kiện khi người dùng nhấn nút Đăng ký
            // ...
            finish();
        });
    }
    // Hàm kiểm tra dữ liệu đầu vào
    private boolean validateInput(String username, String CPass, String password) {
        if (TextUtils.isEmpty(username)) {
            edtUser.setError("Vui lòng nhập tên đăng nhập");
            return false;
        }

        if (TextUtils.isEmpty(password)) {
            edtPass.setError("Vui lòng nhập mật khẩu");
            return false;
        }

        if (password.length() < 6) {
            edtPass.setError("Mật khẩu phải có ít nhất 6 ký tự");
            return false;
        }
        if (TextUtils.isEmpty(CPass)) {
            edtCPass.setError("Vui lòng xác nhận mật khẩu");
            return false;
        }

        if (!password.equals(CPass)) {
            edtCPass.setError("Mật khẩu không khớp");
            return false;
        }

        return true;
    }
    // Hàm lưu thông tin vào file .txt
    private void saveToFile(String username, String password) {
        String data = username + "," + password + "\n";
        FileOutputStream fos = null;

        try {
            // Mở file "user_data.txt" ở chế độ append (thêm dữ liệu)
            fos = openFileOutput("user_data.txt", MODE_APPEND);
            fos.write(data.getBytes());
            fos.flush();
            Toast.makeText(this, "Đăng ký thành công!", Toast.LENGTH_SHORT).show();
            finish();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Lỗi khi lưu dữ liệu!", Toast.LENGTH_SHORT).show();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}