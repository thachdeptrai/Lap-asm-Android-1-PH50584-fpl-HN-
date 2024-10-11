package com.meomeo.asm;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class ScreenLogin extends AppCompatActivity {
    private Button btnDangnhap, btnDangky;
    private EditText edtUser, edtPass;
    private CheckBox cbGhiNho;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_screen_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnDangnhap = findViewById(R.id.btnDangNhap);
        btnDangky = findViewById(R.id.btnDangKy);
        edtUser = findViewById(R.id.edtUser);
        edtPass = findViewById(R.id.edtPass);
        cbGhiNho = findViewById(R.id.chkGhiNho);

        loadPreferences();

        btnDangnhap.setOnClickListener(v -> {
            String username = edtUser.getText().toString();
            String password = edtPass.getText().toString();

            if (validateInput(username, password)) {
                if (checkLogin(username, password)) {
                    if (cbGhiNho.isChecked()) {
                        savePreferences(username, password);
                    }
                    Toast.makeText(ScreenLogin.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(ScreenLogin.this, ScreenHome.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(ScreenLogin.this, "Sai tên đăng nhập hoặc mật khẩu", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnDangky.setOnClickListener(v -> {
            Intent intent = new Intent(ScreenLogin.this, ScreenRegister.class);
            startActivity(intent);
        });
    }

    private void loadPreferences() {
        SharedPreferences sharedPreferences = getSharedPreferences("Info", MODE_PRIVATE);
        boolean rememberMe = sharedPreferences.getBoolean("rememberMe", false);
        if (rememberMe) {
            String username = sharedPreferences.getString("username", "");
            String password = sharedPreferences.getString("password", "");
            edtUser.setText(username);
            edtPass.setText(password);
        }
    }

    private void savePreferences(String username, String password) {
        SharedPreferences preferences = getSharedPreferences("Info", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("username", username);
        editor.putString("password", password);
        editor.putBoolean("rememberMe", true);
        editor.apply();
    }

    private boolean validateInput(String user, String password) {
        if (TextUtils.isEmpty(user)) {
            edtUser.setError("Vui lòng nhập tên đăng nhập");
            return false;
        }
        if (TextUtils.isEmpty(password)) {
            edtPass.setError("Vui lòng nhập mật khẩu");
            return false;
        }
        return true;
    }

    private boolean checkLogin(String username, String password) {
        try (FileInputStream fis = openFileInput("user_data.txt");
             BufferedReader reader = new BufferedReader(new InputStreamReader(fis))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] userData = line.split(",");
                if (userData.length == 2 && userData[0].equals(username) && userData[1].equals(password)) {
                    return true; // Đăng nhập thành công
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false; // Đăng nhập thất bại
    }
}
