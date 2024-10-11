package com.meomeo.lap5lap6;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.meomeo.lap5lap6.Adapter.SchoolSpinnerAdapter;

public class Lap5Activity extends AppCompatActivity {
    Spinner spnFpoly;
    EditText edtName, edtAge;
    Button btnSubmit;

    String[] schoolName = {"Fpoly HCM", "Fpoly Da Nang", "Fpoly Can Tho","Fpoly Ha Noi"};

    int[] schoolInts = {R.drawable.hcm, R.drawable.danang, R.drawable.cantho, R.drawable.hanoi};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_lap5);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        spnFpoly = findViewById(R.id.spnFpoly);
        edtName = findViewById(R.id.edtName);
        edtAge = findViewById(R.id.edtAge);
        btnSubmit = findViewById(R.id.btnSubmit);
        SchoolSpinnerAdapter adapter = new SchoolSpinnerAdapter(this, schoolName, schoolInts);
        spnFpoly.setAdapter(adapter);

        spnFpoly.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(Lap5Activity.this, schoolName[i], Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        btnSubmit.setOnClickListener(v -> {
            String name = edtName.getText().toString();
            String address = edtAge.getText().toString();
            Toast.makeText(Lap5Activity.this, "Tên: " + name + ", Địa chỉ: " + address, Toast.LENGTH_SHORT).show();
        });
    }
}