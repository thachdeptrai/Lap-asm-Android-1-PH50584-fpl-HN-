package com.meomeo.lap3;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Bai2p1 extends AppCompatActivity {
private Button btnGui;
private EditText edtCanMua;
private TextView tvGia;
private ActivityResultLauncher<Intent> activityResultLauncher;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_bai2p1);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        btnGui = findViewById(R.id.btnGui);
        edtCanMua = findViewById(R.id.edtCanMua);
        tvGia = findViewById(R.id.tvGia);
        btnGui.setOnClickListener(v -> {
            Intent intent = new Intent(Bai2p1.this, Bai2p2.class);
            intent.putExtra("canMua", edtCanMua.getText().toString());
            activityResultLauncher.launch(intent);

        });
        activityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult()
                , result -> {
                    if (result.getResultCode() == RESULT_OK) {
                        tvGia.setText(result.getData().getStringExtra("gia"));
                    }
                }
        );

    }
    }