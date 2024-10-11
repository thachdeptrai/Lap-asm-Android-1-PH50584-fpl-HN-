package com.meomeo.asm;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ScreenPhongban extends AppCompatActivity {
private TextView tvNhansu, tvHanhchinh,tvDaotao;
private Button btnAddphongban;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_screen_phongban);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        tvNhansu = findViewById(R.id.tvNhansu);
        tvHanhchinh = findViewById(R.id.tvHanhchinh);
        tvDaotao = findViewById(R.id.tvDaotao);
        tvNhansu.setOnClickListener(v -> {
            Intent intent = new Intent(ScreenPhongban.this, ScreenNhanSu.class);
            intent.putExtra("phongBan", "Nhân Sự");
            startActivity(intent);

        });
        ImageView imgBack = findViewById(R.id.imgBack); // Lấy nút back từ layout
        imgBack.setOnClickListener(v -> getOnBackPressedDispatcher().onBackPressed());

        tvHanhchinh.setOnClickListener(v -> {
            Intent intent = new Intent(ScreenPhongban.this, ScreenHanhchinh.class);
            intent.putExtra("phongBan", "Hành Chính");
            startActivity(intent);

        });
        tvDaotao.setOnClickListener(v -> {
            Intent intent = new Intent(ScreenPhongban.this, ScreenDaotap.class);
            intent.putExtra("phongBan", "Đào Tạo");
            startActivity(intent);
        });

        btnAddphongban = findViewById(R.id.btnAddphongban);
        btnAddphongban.setOnClickListener(v -> {
            Intent intent = new Intent(ScreenPhongban.this,ScreenAddphongban.class);
            startActivity(intent);
        });
    }
    private void openNhanVienActivity(String phongBan) {
        Intent intent = new Intent(ScreenPhongban.this, ScreenNhanvien.class);
        intent.putExtra("phongBan", phongBan);
        startActivity(intent);
    }

}