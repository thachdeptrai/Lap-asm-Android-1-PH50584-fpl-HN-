package com.meomeo.lap3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Login extends AppCompatActivity {
private Button btnDangnhap, btnDangky;
private EditText edtTaikhoan, edtMatkhau;
private static final int REQUEST_CODE_REGISTER = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnDangnhap = findViewById(R.id.btnDangnhap);
        btnDangky = findViewById(R.id.btnDangky);
        edtTaikhoan = findViewById(R.id.edtTaikhoan);
        edtMatkhau = findViewById(R.id.edtMatkhau);
        btnDangky.setOnClickListener(v -> {
            Intent intent = new Intent(Login.this, dangky.class);
            startActivity(intent);
        });
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            String user = bundle.getString("username");
            String pass = bundle.getString("password");

            btnDangnhap.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    boolean isUserValid = edtTaikhoan.getText().toString().equals(user);
                    boolean isPasswordValid = edtMatkhau.getText().toString().equals(pass);

                    if (isUserValid && isPasswordValid) {
                        Toast.makeText(Login.this, "Đăng nhập thành công!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Login.this, muoidiem.class);
                        startActivity(intent);
                        // Chuyển đến màn hình chính hoặc tiếp tục
                    } else {
                        Toast.makeText(Login.this, "Sai tài khoản hoặc mật khẩu!", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}