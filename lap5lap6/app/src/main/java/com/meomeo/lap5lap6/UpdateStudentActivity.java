package com.meomeo.lap5lap6;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class UpdateStudentActivity extends AppCompatActivity {

    EditText etStudentName, etStudentAddress;
    Spinner spnBranch;
    Button btnUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_student);

        etStudentName = findViewById(R.id.etStudentName);
        etStudentAddress = findViewById(R.id.etStudentAddress);
        spnBranch = findViewById(R.id.spnBranch);
        btnUpdate = findViewById(R.id.btnUpdate);

        // Lấy dữ liệu từ Intent
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String address = intent.getStringExtra("address");
        String branch = intent.getStringExtra("branch");

        etStudentName.setText(name);
        etStudentAddress.setText(address);

        // Gán dữ liệu cho spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.school_branches, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnBranch.setAdapter(adapter);

        // Đặt vị trí spinner
        int spinnerPosition = adapter.getPosition(branch);
        spnBranch.setSelection(spinnerPosition);

        btnUpdate.setOnClickListener(v -> {
            String updatedName = etStudentName.getText().toString();
            String updatedAddress = etStudentAddress.getText().toString();
            String updatedBranch = spnBranch.getSelectedItem().toString();

            // Trả lại dữ liệu đã cập nhật
            Intent resultIntent = new Intent();
            resultIntent.putExtra("name", updatedName);
            resultIntent.putExtra("address", updatedAddress);
            resultIntent.putExtra("branch", updatedBranch);
            setResult(RESULT_OK, resultIntent);
            finish(); // Quay lại Activity danh sách
        });
    }
}
