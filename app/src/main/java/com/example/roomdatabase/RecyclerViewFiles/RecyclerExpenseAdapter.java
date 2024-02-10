package com.example.roomdatabase.RecyclerViewFiles;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.roomdatabase.R;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class RecyclerExpenseAdapter extends RecyclerView.Adapter<RecyclerExpenseAdapter.ViewHolder> {

    Context context;
    ArrayList<ExpenseModel> expenseModelArrayList;

    public RecyclerExpenseAdapter(Context context, ArrayList<ExpenseModel> expenseModelArrayList) {
        this.context = context;
        this.expenseModelArrayList = expenseModelArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.rows, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtTitle.setText(expenseModelArrayList.get(position).title);
        holder.txtAmount.setText(expenseModelArrayList.get(position).amount);
    }

    @Override
    public int getItemCount() {
        return expenseModelArrayList.size()-1;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
    TextView txtTitle, txtAmount;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtTitle = itemView.findViewById(R.id.rowsTitle);
            txtAmount = itemView.findViewById(R.id.rowsAmount);

        }
    }
}
