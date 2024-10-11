package com.meomeo.lap3;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Screen2 extends AppCompatActivity {
private Button btn2,btnThoat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_screen2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
         btn2 = findViewById(R.id.btn2);
         btnThoat = findViewById(R.id.btnThoat);
        btn2.setOnClickListener(v -> {
            startActivity(new Intent(Screen2.this, ScreenBai1lap4.class));

        });
        btnThoat.setOnClickListener(v -> {
            startActivity(new Intent(Screen2.this, MainActivity2.class));

        });

    }
}