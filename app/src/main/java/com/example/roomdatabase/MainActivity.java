package com.example.roomdatabase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.roomdatabase.Database.DatabaseHelper;
import com.example.roomdatabase.Database.Expense;
import com.example.roomdatabase.RecyclerViewFiles.RecyclerViewActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener {

    EditText edTitle, edAmount;
    Button btnAdd, btnRecyclerView;
    Button btnDelete;
    private List<Expense> allExpenses;
    ConstraintLayout constraintLayout;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edTitle = findViewById(R.id.enterTitle);
        edAmount = findViewById(R.id.enterAmount);
        btnAdd = findViewById(R.id.addButton);
        btnRecyclerView = findViewById(R.id.btnRecyclerView);
        btnDelete = findViewById(R.id.btnDelete);
        constraintLayout = findViewById(R.id.parentLayout);


        // Attach the onTouchListener to each view


        edTitle.setOnTouchListener(this);
        edAmount.setOnTouchListener(this);
        btnAdd.setOnTouchListener(this);
        btnRecyclerView.setOnTouchListener(this);
        btnDelete.setOnTouchListener(this);
        constraintLayout.setOnTouchListener(this);



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

//                This arrayList of Expense named arrExpense will store all the entries entered by the user.
//                stores a collection of Expense objects, each representing a different expense entry.

                ArrayList<Expense> arrExpense = (ArrayList<Expense>) databaseHelper.expenseDAO().getAllExpense();
                for(int i = 0; i<arrExpense.size(); i++){
                    Log.d("Data", "Title "+arrExpense.get(i).getTitle()+" Amount "+arrExpense.get(i).getAmount());
                }

                edTitle.setText("");
                edAmount.setText("");
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                allExpenses = databaseHelper.expenseDAO().getAllExpense();
                if(!allExpenses.isEmpty()){
//                    int lastIndex = allExpenses.size() - 1;
//                    Expense lastExpense = allExpenses.get(lastIndex);
                    databaseHelper.expenseDAO().deleteLastTx();
                    Toast.makeText(MainActivity.this, "Removed", Toast.LENGTH_SHORT).show();

//                    allExpenses.remove(lastIndex);        // Remove the last expense from the list
//                                                          Not needed since while clicking the View ist button the list gets fetched directly from the Room dataset.
//                                                          So no need to update the List Explicitly.
                }
                else {
                    // Handle case where there are no expenses to delete
                    Toast.makeText(MainActivity.this, "No expenses to delete", Toast.LENGTH_SHORT).show();
                }
/*
                  DEV. NOTE FOR BtnDelete (BUTTON TO DELETE THE LAST ENTRY):
                  #Explanation

                   In this code, the arrExpense list is fetching data directly from the Room database using the DAO method getAllExpense().
                   This means that whenever we perform a delete operation on the database and fetch the data again using getAllExpense(),
                   the arrExpense list will automatically reflect the changes made to the database.

                   #Suggestion
                   You can improvise the design to make change in the RecyclerView by adding in image Button to delete in the RecyclerView itself.

                   #Defects
                    The code fetches the whole list while deleting the last element which is not good for a good dataset. Use some other ways

*/
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
    @Override
//    This method is handling the onTouch feature, when the user clicks on anyView or parentLayout, the cursor along with the keyboard disappears.
    public boolean onTouch(View v, MotionEvent event) {
        // Check if the touch event is outside the EditText fields
        if (event.getAction() == MotionEvent.ACTION_DOWN && !(v instanceof EditText)) {
            // Hide soft keyboard
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(v.getWindowToken(), 0);
            // Clear focus from EditText fields
            edTitle.clearFocus();
            edAmount.clearFocus();
        }
        return false;
    }

    @Override
    public void onPause() {
        super.onPause();
            edTitle.setText("");
            edAmount.setText("");
        }



}