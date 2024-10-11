package com.meomeo.lap3;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ScreenBai1lap4 extends AppCompatActivity {
    private Button btn1 ,btnThoat2;
    private final String TAG = "ScreenBai1lap4";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_screen_bai1lap4);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        btn1 = findViewById(R.id.btn1);
        btnThoat2 = findViewById(R.id.btnThoat2);
        btn1.setOnClickListener(v -> {
            startActivity(new Intent(ScreenBai1lap4.this, Screen2.class));
        });
        btnThoat2.setOnClickListener(v -> {
            startActivity(new Intent(ScreenBai1lap4.this, MainActivity2.class));

        });
    }
    protected void onStart(){
        super.onStart();
        Log.i(TAG,"onStart");
    }
    protected void onResume(){
        super.onResume();
        Log.i(TAG,"onResume");
    }
    protected void onPause(){
        super.onPause();
        Log.i(TAG,"onPause");

    }
    protected void onStop(){
        super.onStop();
        Log.i(TAG,"onStop");
    }
    protected void onRestart(){
        super.onRestart();
        Log.i(TAG,"onRestart");
    }
    protected void onDestroy(){
        super.onDestroy();
        Log.i(TAG,"onDestroy");
    }
}