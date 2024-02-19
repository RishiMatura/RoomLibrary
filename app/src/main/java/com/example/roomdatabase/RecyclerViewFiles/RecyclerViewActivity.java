package com.example.roomdatabase.RecyclerViewFiles;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.roomdatabase.DatabaseHelper;
import com.example.roomdatabase.Expense;
import com.example.roomdatabase.R;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<ExpenseModel> expenseModelArrayList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);

        recyclerView = findViewById(R.id.recyclerExpense);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        DatabaseHelper databaseHelper =  DatabaseHelper.getDB(this);
        RecyclerExpenseAdapter adapter= new RecyclerExpenseAdapter(this, expenseModelArrayList);

        ArrayList<Expense> arrExpense = (ArrayList<Expense>) databaseHelper.expenseDAO().getAllExpense();
        for (Expense expense : arrExpense) {
            expenseModelArrayList.add(new ExpenseModel(expense.getTitle(), expense.getAmount()));
        }
        ExpenseModel item1 = new ExpenseModel("Burger", "60");


        recyclerView.setAdapter(adapter);
    }

}