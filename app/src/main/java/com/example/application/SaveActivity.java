package com.example.application;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.RadioGroup;

import com.example.application.model.Transaction;

public class SaveActivity extends AppCompatActivity {
    private EditText namaInput;
    private RadioGroup rGroup;
    private Transaction item;
    private int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save);

        namaInput = findViewById(R.id.namaInput);
        rGroup = findViewById(R.id.rgroup);

        Bundle extras = getIntent().getExtras();
        if(extras != null){

        }
    }
}
