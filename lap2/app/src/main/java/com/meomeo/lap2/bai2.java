package com.meomeo.lap2;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class bai2 extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_bai2);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        TextView txtResult = findViewById(R.id.txtResult);
        EditText edtTen =findViewById(R.id.edtTen);
        EditText edtMsv =findViewById(R.id.edtMsv);
        EditText edtTuoi =findViewById(R.id.edtTuoi);
        RadioButton rdbNam =findViewById(R.id.rdbNam);
        RadioButton rdbNu =findViewById(R.id.rdbNu);
        CheckBox cbDabong = findViewById(R.id.cbDabong);
        CheckBox cbGame = findViewById(R.id.cbGame);
        Button btnSave = findViewById(R.id.btnSave);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = edtTen.getText().toString().trim();
                String msv = edtMsv.getText().toString().trim();
                String tuoi = edtTuoi.getText().toString().trim();

                if (name.isEmpty() || msv.isEmpty() || tuoi.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Vui lòng điền đầy đủ thông tin!", Toast.LENGTH_SHORT).show();
                    return;
                }

                String gender = rdbNam.isChecked() ? rdbNam.getText().toString() :
                        rdbNu.isChecked() ? rdbNu.getText().toString() :
                                "Chưa Chọn Giới Tính";

                String interest = "";
                if (cbDabong.isChecked() && cbGame.isChecked()) {
                    interest = "Đá Bóng Và Chơi Game";
                } else if (cbDabong.isChecked()) {
                    interest = cbDabong.getText().toString();
                } else if (cbGame.isChecked()) {
                    interest = cbGame.getText().toString();
                } else {
                    interest = "Không Thích Gì Cả";
                }

                txtResult.setText("Tôi Tên: " + name + "\n" +
                        "Msv: " + msv + "\n" +
                        "Tuổi: " + tuoi + "\n" +
                        "Giới Tính: " + gender + "\n" +
                        "Sở Thích: " + interest);
            }
        });

    }
}