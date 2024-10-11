package com.meomeo.asm;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ScreenHome extends AppCompatActivity {
private Button btnPhongban,btnNhanvien,btnThoat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_screen_home);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        btnPhongban = findViewById(R.id.btnPhongBan);
        btnNhanvien = findViewById(R.id.btnNhanvien);
        btnThoat = findViewById(R.id.btnThoat);

        btnPhongban.setOnClickListener(v -> {
            Intent intent = new Intent(ScreenHome.this,ScreenPhongban.class);
            startActivity(intent);

        });
        btnNhanvien.setOnClickListener(v -> {
            Intent intent = new Intent(ScreenHome.this,ScreenNhanvien.class);
            startActivity(intent);

        });
        btnThoat.setOnClickListener(v -> {
            Intent intent = new Intent(ScreenHome.this,ScreenLogin.class);
            startActivity(intent);
            finish();
        });
        }
}
