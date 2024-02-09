package com.example.roomdatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText edTitle, edAmount;
    Button btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edTitle = findViewById(R.id.enterTitle);
        edAmount = findViewById(R.id.enterAmount);
        btnAdd = findViewById(R.id.addButton);

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

                ArrayList<Expense> arrExpense = (ArrayList<Expense>) databaseHelper.expenseDAO().getAllExpense();
                for(int i = 0; i<arrExpense.size(); i++){
                    Log.d("Data", "Title "+arrExpense.get(i).getTitle()+" Amount "+arrExpense.get(i).getAmount());
                }
            }
        });



    }
}