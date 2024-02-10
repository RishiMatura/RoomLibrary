package com.example.roomdatabase.RecyclerViewFiles;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.roomdatabase.R;

import java.util.ArrayList;

public class RecyclerViewActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<ExpenseModel> expenseModelArrayList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);

        recyclerView = findViewById(R.id.recyclerExpense);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ExpenseModel item1 = new ExpenseModel("Burger", "60");

        expenseModelArrayList.add(item1);

    }

}