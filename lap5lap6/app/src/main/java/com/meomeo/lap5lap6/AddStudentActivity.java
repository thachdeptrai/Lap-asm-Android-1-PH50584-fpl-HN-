package com.meomeo.lap5lap6;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.meomeo.lap5lap6.Adapter.SchoolSpinnerAdapter;

public class AddStudentActivity extends AppCompatActivity {

    EditText etStudentName, etStudentAddress;
    Spinner spnBranch;
    Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

        etStudentName = findViewById(R.id.etStudentName);
        etStudentAddress = findViewById(R.id.etStudentAddress);
        spnBranch = findViewById(R.id.spnBranch);
        btnSubmit = findViewById(R.id.btnSubmit);

        // Mảng tên chi nhánh
        String[] branches = {
                "FPoly Hà Nội",
                "FPoly Đà Nẵng",
                "FPoly Tây Nguyên",
                "FPoly Cần Thơ",
                "FPoly Hồ Chí Minh"
        };

        // Mảng ảnh tương ứng với các chi nhánh
        int[] images = {
                R.drawable.hanoi,
                R.drawable.danang,
                R.drawable.taynguyen,
                R.drawable.cantho,
                R.drawable.hcm
        };

        // Tạo adapter và gán nó cho Spinner
        SchoolSpinnerAdapter adapter = new SchoolSpinnerAdapter(this, branches, images);
        spnBranch.setAdapter(adapter);

        btnSubmit.setOnClickListener(v -> {
            String name = etStudentName.getText().toString();
            String address = etStudentAddress.getText().toString();
            String branch = spnBranch.getSelectedItem().toString();

            if (name.isEmpty() || address.isEmpty()) {
                Toast.makeText(AddStudentActivity.this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            } else {
                // Trả về kết quả khi nhấn SUBMIT
                Intent resultIntent = new Intent();
                resultIntent.putExtra("name", name);
                resultIntent.putExtra("address", address);
                resultIntent.putExtra("branch", branch);
                setResult(RESULT_OK, resultIntent);
                finish(); // Quay lại HomeActivity
            }
        });
    }
}
