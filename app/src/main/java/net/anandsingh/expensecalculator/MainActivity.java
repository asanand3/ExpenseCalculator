package net.anandsingh.expensecalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    EditText fuel;
    EditText food;
    EditText shopping;
    EditText telephone;
    TextView previousExpense;
    Button calculate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        food = (EditText) findViewById(R.id.etFood);

        fuel = (EditText) findViewById(R.id.etFuel);

        shopping = (EditText) findViewById(R.id.etShopping);

        telephone = (EditText) findViewById(R.id.etTelephone);

        previousExpense = (TextView) findViewById(R.id.prevExpense);

        calculate = (Button) findViewById(R.id.btnCalculate);

        readData();

        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int expense = Integer.valueOf(fuel.getText().toString()) + Integer.valueOf(food.getText().toString())
                        + Integer.valueOf(shopping.getText().toString()) + Integer.valueOf(telephone.getText().toString());

                Toast.makeText(MainActivity.this, "Your Total Expense is: " + String.valueOf(expense), Toast.LENGTH_LONG).show();

                previousExpense.setText("Your Previous Expense was: " + String.valueOf(expense));

                saveData();
            }
        });
    }

    public void saveData() {

        try {
            FileOutputStream fileOutputStream = openFileOutput("expense.txt", MODE_PRIVATE);
            fileOutputStream.write(previousExpense.getText().toString().getBytes());
            fileOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readData() {

        try {
            FileInputStream fileInputStream = openFileInput("expense.txt");
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);

            BufferedReader reader = new BufferedReader(inputStreamReader);

            String data = reader.readLine();
            if (!data.isEmpty()) {
                previousExpense.setText(data);
            } else {
                previousExpense.setText("0");
            }
        } catch (FileNotFoundException e) {
            previousExpense.setText("0");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
