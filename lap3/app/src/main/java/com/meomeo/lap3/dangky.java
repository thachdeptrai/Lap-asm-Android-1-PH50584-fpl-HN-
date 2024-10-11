package com.meomeo.lap3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class dangky extends AppCompatActivity {
    private Button btnDangky;
    private EditText edtTaikhoan, edtMatkhau , edtReMatkhau;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_dangky);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        btnDangky = findViewById(R.id.btnDangky);
        edtTaikhoan = findViewById(R.id.edtTaikhoan);
        edtMatkhau = findViewById(R.id.edtMatkhau);
        edtReMatkhau = findViewById(R.id.edtReMatkhau);
        btnDangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = edtTaikhoan.getText().toString();
                String password = edtMatkhau.getText().toString();
                String confirmPassword = edtReMatkhau.getText().toString();

                if (password.equals(confirmPassword)) {
                    Intent intent = new Intent(dangky.this, Login.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("username", username);
                    bundle.putString("password", password);
                    intent.putExtras(bundle);
                    startActivity(intent);
                    finish();
                    Toast.makeText(dangky.this, "Đăng ký thành công!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(dangky.this, "Mật khẩu không khớp!", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}