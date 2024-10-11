package com.meomeo.lap3;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ScreenMenu extends AppCompatActivity {
    private Button btnBai1, btnBai2, btnBai3,btnLap4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_screen_menu);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        btnBai1 = findViewById(R.id.btnBai1);
        btnBai2 = findViewById(R.id.btnBai2);
        btnBai3 = findViewById(R.id.btnBai3);
        btnLap4 = findViewById(R.id.btnLap4);
        btnBai1.setOnClickListener(v -> {
            startActivity(new Intent(ScreenMenu.this, ScreenLinearlayout.class));
        });
        btnBai2.setOnClickListener(v -> {
            startActivity(new Intent(ScreenMenu.this, ScreenRelativeLayout.class));
        });
        btnBai3.setOnClickListener(v -> {
            startActivity(new Intent(ScreenMenu.this, MainActivity.class));
        });

        btnLap4.setOnClickListener(v -> {
            startActivity(new Intent(ScreenMenu.this, MainActivity2.class));
        });


    }
}