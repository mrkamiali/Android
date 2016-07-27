package com.android.myorderapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public final int pricePerCup = 15;
    int numberOfCoffees = 0;
    private Button orderButton, incrementButton, decrementButton;
    private TextView quantityTextView, priceTextView;
    private CheckBox mCheckBox_WhippedCream, mCheckBox_Chocolate;

    private EditText nameField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        orderButton = (Button) findViewById(R.id.order_button);
        quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        priceTextView = (TextView) findViewById(R.id.price_text_view);
        incrementButton = (Button) findViewById(R.id.increment_button);
        decrementButton = (Button) findViewById(R.id.decrement_button);
        mCheckBox_WhippedCream = (CheckBox) findViewById(R.id.check_box_WhippedCream);
        mCheckBox_Chocolate = (CheckBox) findViewById(R.id.check_box_chocolate);
        nameField = (EditText) findViewById(R.id.name_Edit_TextField);
    }

    public void decrementOrder(View view) {
        if (numberOfCoffees <= 1) {
            Toast.makeText(MainActivity.this, "You must have to select at least 1 Coffee!", Toast.LENGTH_LONG).show();
        } else {

            Log.e("TAG", "before Dec NumberOfCoffees =" + numberOfCoffees);
            numberOfCoffees = numberOfCoffees - 1;
            Log.e("TAG", "After Dec NumberOfCoffees =" + numberOfCoffees);
            displayQuantity(numberOfCoffees);

        }
    }

    public void incrementOrder(View view) {
        if (numberOfCoffees == 100) {
            Toast.makeText(MainActivity.this, "You can't order above 100 coffees at one time !", Toast.LENGTH_LONG).show();
            return;
        }

        Log.e("TAG", "before INC NumberOfCoffees =" + numberOfCoffees);
        numberOfCoffees = numberOfCoffees + 1;
        Log.e("TAG", "After INC NumberOfCoffees =" + numberOfCoffees);
        displayQuantity(numberOfCoffees);

    }

    private void displayQuantity(int i) {
        quantityTextView.setText("" + i);
    }

    public void submitOrder(View view) {


        boolean whipped_checked = mCheckBox_WhippedCream.isChecked();
        Log.e("TAG", "Checked " + whipped_checked);
        boolean chocolate_checked = mCheckBox_Chocolate.isChecked();
        String name = nameField.getText().toString();
        Log.d("TAG", "NAME " + name);
        int price = calculatePrice(numberOfCoffees, whipped_checked, chocolate_checked);
        String priceMessage = createOrderSummary(name, price, whipped_checked, chocolate_checked);


        dispalyPrice(priceMessage);
        nameField.setText("");

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, "Order_app order summary to " + name);
        intent.putExtra(Intent.EXTRA_TEXT, priceMessage);
        intent.putExtra(Intent.EXTRA_STREAM,"Attachment");
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }

    }

    private String createOrderSummary(String name, int totatlPrice, boolean checked, boolean chocolate_CheckBox_Checking) {


        return "Name: " + name +
                "\nAdd Whipped Cream? " + checked +
                "\nAdd Chocolate ? " + chocolate_CheckBox_Checking +
                "\nQuantity :" + numberOfCoffees +
                "\nTotal :Rs." + totatlPrice +
                "\nThank You!";
    }

    private void dispalyPrice(String i) {

        priceTextView.setText("" + i);

    }

    private int calculatePrice(int quantity, boolean whippedAdded, boolean chocolateAdded) {
        int price = quantity * pricePerCup;

        if (whippedAdded) {
            price = price + (quantity * 5);
        } else if (chocolateAdded) {
            price = price + (quantity * 10);
        }
        Log.e("TAG", "price " + price);

        return price;
    }
}
