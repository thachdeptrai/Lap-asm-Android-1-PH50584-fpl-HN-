package com.meomeo.lap5lap6;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class LoginActivity extends AppCompatActivity {
    EditText etLoginUsername, etLoginPassword;
    Button btnLogin,btnRegiter;

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

        etLoginUsername = findViewById(R.id.etLoginUsername);
        etLoginPassword = findViewById(R.id.etLoginPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnRegiter = findViewById(R.id.btnRegister);
        btnRegiter.setOnClickListener(v -> {
            startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
        });
        btnLogin.setOnClickListener(v -> {
            String username = etLoginUsername.getText().toString();
            String password = etLoginPassword.getText().toString();

            // Lấy thông tin từ SharedPreferences
            String savedUsername = getSharedPreferences("UserPrefs", MODE_PRIVATE)
                    .getString("username", null);
            String savedPassword = getSharedPreferences("UserPrefs", MODE_PRIVATE)
                    .getString("password", null);

            // Kiểm tra thông tin đăng nhập
            if (username.equals(savedUsername) && password.equals(savedPassword)) {
                // Đăng nhập thành công
                startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                finish();
            } else {
                Toast.makeText(LoginActivity.this, "Sai tên người dùng hoặc mật khẩu", Toast.LENGTH_SHORT).show();
            }
        });

    }
}