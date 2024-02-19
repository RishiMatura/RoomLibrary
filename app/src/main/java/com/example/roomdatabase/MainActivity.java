package com.example.roomdatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.roomdatabase.RecyclerViewFiles.RecyclerViewActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText edTitle, edAmount;
    Button btnAdd, btnRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edTitle = findViewById(R.id.enterTitle);
        edAmount = findViewById(R.id.enterAmount);
        btnAdd = findViewById(R.id.addButton);
        btnRecyclerView = findViewById(R.id.btnRecyclerView);

        DatabaseHelper databaseHelper = DatabaseHelper.getDB(MainActivity.this);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = edTitle.getText().toString();
                String amount = edAmount.getText().toString();


                databaseHelper.expenseDAO().addTx(
                        new Expense(title,amount)
                );
                Toast.makeText(MainActivity.this, "Added", Toast.LENGTH_SHORT).show();

//                This arrayLis t of Expense named arrExpense will store all the entries entered by the user.
//                stores a collection of Expense objects, each representing a different expense entry.
                ArrayList<Expense> arrExpense = (ArrayList<Expense>) databaseHelper.expenseDAO().getAllExpense();
                for(int i = 0; i<arrExpense.size(); i++){
                    Log.d("Data", "Title "+arrExpense.get(i).getTitle()+" Amount "+arrExpense.get(i).getAmount());
                }
            }
        });
        btnRecyclerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RecyclerViewActivity.class);
                startActivity(intent);
            }
        });





    }
}