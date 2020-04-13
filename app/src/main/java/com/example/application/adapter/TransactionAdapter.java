package com.example.application.adapter;

import android.graphics.Color;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.application.R;
import com.example.application.model.Transaction;

import java.util.List;

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.ViewHolder>{

    public TransactionAdapter() {

    }

    public interface OnItemTransactionListener{
        void onTransactionClicked(int index, Transaction item);
    }

    private List<Transaction> items;
    private OnItemTransactionListener listener;

    public TransactionAdapter(List<Transaction> items, OnItemTransactionListener listener) {
        this.items = items;
        this.listener = listener;
    }

    @NonNull
    @Override
    public TransactionAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_transaction, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TransactionAdapter.ViewHolder holder, int position) {
        Transaction item = items.get(position);
        holder.bind(position, item);
    }

    @Override
    public int getItemCount() {
        return (items != null) ? items.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public View linearLayout;
        public TextView nama;
        public TextView harga;
        public ImageView img;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nama = itemView.findViewById(R.id.namaTv);
            harga = itemView.findViewById(R.id.hargaTv);
            img = itemView.findViewById(R.id.imageView);
        }

        public void bind(final int index, final Transaction item){
            nama.setText(item.getNama());
            if(item.getType() == Transaction.Type.CARDIGAN){
                harga.setText("Rp 100.000");
                Uri imgUri = Uri.parse("android.resource://com.example.application/drawable/clothes");
                img.setImageURI(imgUri);
            }else if(item.getType() == Transaction.Type.HOODIE){
                harga.setText("Rp 75.000");
                Uri imgUri = Uri.parse("android.resource://com.example.application/drawable/clothes1");
                img.setImageURI(imgUri);
            }else if(item.getType() == Transaction.Type.DRESS){
                harga.setText("Rp 175.000");
                Uri imgUri = Uri.parse("android.resource://com.example.application/drawable/clothes2");
                img.setImageURI(imgUri);
            }else{
                harga.setText("Rp 125.000");
                Uri imgUri = Uri.parse("android.resource://com.example.application/drawable/clothes3");
                img.setImageURI(imgUri);
            }

            //interaksi click
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onTransactionClicked(index,item);
                }
            });
        }
    }
}
