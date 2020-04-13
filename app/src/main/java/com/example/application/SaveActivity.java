package com.example.application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.application.model.Transaction;
import static com.example.application.MainActivity.TRANSACTION_KEY;

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
            item = extras.getParcelable(TRANSACTION_KEY);
            index = extras.getInt(MainActivity.INDEX_KEY, 0);
            namaInput.setText(item.getNama());

            if(item.getType() == Transaction.Type.CARDIGAN){
                rGroup.check(R.id.cardigan);
            }else if(item.getType() == Transaction.Type.HOODIE){
                rGroup.check(R.id.hoodie);
            }else if(item.getType() == Transaction.Type.DRESS){
                rGroup.check(R.id.dress);
            }else if(item.getType() == Transaction.Type.OVERALL){
                rGroup.check(R.id.overall);
            }
        }
    }

    private Transaction.Type getCheckedType(){
        if(rGroup.getCheckedRadioButtonId() == R.id.cardigan){
            return  Transaction.Type.CARDIGAN;
        }else if(rGroup.getCheckedRadioButtonId() == R.id.hoodie){
            return Transaction.Type.HOODIE;
        }else if(rGroup.getCheckedRadioButtonId() == R.id.dress){
            return Transaction.Type.DRESS;
        }else if(rGroup.getCheckedRadioButtonId() == R.id.overall){
            return Transaction.Type.OVERALL;
        }
        return Transaction.Type.EMPTY;
    }

    public void handleSubmit(View view) {
        if (namaInput.getText().toString().isEmpty()) {
            namaInput.setError("Isi Nama Deskripsi!");
        } else if (rGroup.getCheckedRadioButtonId() == -1) {
            Toast.makeText(this, "Isi jenis!", Toast.LENGTH_SHORT).show();
        }else{
            String nama = namaInput.getText().toString();
            Transaction.Type type = getCheckedType();

            item.setNama(nama);
            item.setType(type);

            Intent in = new Intent();
            in.putExtra(TRANSACTION_KEY, item);
            in.putExtra(MainActivity.INDEX_KEY, index);
            setResult(RESULT_OK, in);
            finish();
        }
    }
}
