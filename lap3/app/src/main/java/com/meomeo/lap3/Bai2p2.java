package com.meomeo.lap3;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Bai2p2 extends AppCompatActivity {
private TextView tvHienThiGia;
private EditText edtGia;
private Button btnBaoGia;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_bai2p2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        tvHienThiGia = findViewById(R.id.tvHienThiGia);
        edtGia = findViewById(R.id.edtGia);
        btnBaoGia = findViewById(R.id.btnBaoGia);
        Intent intent = getIntent();
        String canMua = intent.getStringExtra("canMua");
        tvHienThiGia.setText(canMua);
        btnBaoGia.setOnClickListener(v -> {
            Intent intent1 = new Intent();
            intent1.putExtra("gia", edtGia.getText().toString());
            setResult(RESULT_OK, intent1);
            finish();
            });
    }
}