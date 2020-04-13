package com.example.application.adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.application.R;
import com.example.application.model.Transaction;

import java.util.List;

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.ViewHolder>{

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
        if(item.getType() == Transaction.Type.CARDIGAN){
            holder.linearLayout.setBackgroundColor(Color.parseColor("#E7E6E6"));
        }
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

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nama = itemView.findViewById(R.id.namaTv);
            harga = itemView.findViewById(R.id.hargaTv);
        }

        public void bind(final int index, final Transaction item){
            nama.setText(item.getNama());
            if(item.getType() == Transaction.Type.CARDIGAN){
                harga.setText("Rp 100.000");
            }else if(item.getType() == Transaction.Type.HOODIE){
                harga.setText("Rp 75.000");
            }else if(item.getType() == Transaction.Type.DRESS){
                harga.setText("Rp 175.000");
            }else{
                harga.setText("Rp 125.000");
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
