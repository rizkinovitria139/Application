package com.example.application;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.application.adapter.TransactionAdapter;
import com.example.application.model.Account;
import com.example.application.model.Transaction;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.NumberFormat;
import java.util.Locale;

public class MainActivity extends AppCompatActivity
        implements TransactionAdapter.OnItemTransactionListener{
    public static final String TRANSACTION_KEY = "TRANSACTION";
    public static final String INDEX_KEY = "INDEX";
    public static final int INSERT_REQUEST = 1;
    public static final int UPDATE_REQUEST = 2;

    private TextView welcomeText;
    private TextView balanceText;
    private RecyclerView transactionsView;
    private TransactionAdapter adapter;
    private Account account;

    Locale localeID = new Locale("in", "ID"); //definisi locale format Indonesia
    NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID); //membuat format rupiah
    private int index;
    private Transaction item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        welcomeText = findViewById(R.id.textWelcome);
        balanceText = findViewById(R.id.textBalance);
        transactionsView = findViewById(R.id.rv_transaction);

        FloatingActionButton fab = findViewById(R.id.fab); //button fab
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { //onClick
                Intent intent = new Intent(MainActivity.this, SaveActivity.class); //intent button fab
                intent.putExtra(TRANSACTION_KEY, new Transaction());
                startActivityForResult(intent, INSERT_REQUEST);
            }
        });

        account = Application.getAccount();
        adapter = new TransactionAdapter(account.getTransactions(),this);
        transactionsView.setAdapter(adapter);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        transactionsView.setLayoutManager(layoutManager);

        ItemTouchHelper.SimpleCallback simpleItemTouchCallBack = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) { //menswipe ke kiri atau ke kanan
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int index = viewHolder.getAdapterPosition();
                account.removeTransaction(index);
                adapter.notifyDataSetChanged();
                balanceText.setText(String.valueOf(account.getBalance()));
            }
        };
        ItemTouchHelper mItemTouchHelper = new ItemTouchHelper(simpleItemTouchCallBack);
        mItemTouchHelper.attachToRecyclerView(transactionsView);

    }


    @Override
    public void onTransactionClicked(int index, Transaction item) {
        welcomeText.setText(String.format("Welcome %s", account.getName()));
        balanceText.setText(formatRupiah.format(account.getBalance()));

//        definisi intent dan pengiriman data transaction serta posisi index data
        Intent intent = new Intent(this, SaveActivity.class);
        intent.putExtra(TRANSACTION_KEY, item);
        intent.putExtra(INDEX_KEY, 0);
        startActivityForResult(intent, UPDATE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            Transaction transaction = data.getParcelableExtra(TRANSACTION_KEY);
            if (requestCode == INSERT_REQUEST) {
                account.addTransaction(transaction); //pemanggilan data
            } else if (requestCode == UPDATE_REQUEST) { //update data
                int index = data.getIntExtra(INDEX_KEY, 0);
                account.updateTransaction(index, transaction);
            }

            adapter.notifyDataSetChanged();//perubahan data
            balanceText.setText(formatRupiah.format(account.getBalance()));
        }
    }
}
