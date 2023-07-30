package com.example.bsp3admoon;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Placeholder;

import android.os.Build;
import android.view.View;
import android.widget.Button;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;
import android.content.Intent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

import static java.time.LocalDate.now;

public class PaymentXML extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_xml);
        Button placeOrder = (Button) findViewById(R.id.button2);
        Button Pay = (Button) findViewById(R.id.Paymentbutton);
        EditText cardNo = (EditText) findViewById(R.id.CardNumber);
        EditText cvv = (EditText) findViewById(R.id.CVV);
        EditText date = (EditText) findViewById(R.id.ExpiryDate);
        EditText name = (EditText) findViewById(R.id.holder);

        Pay.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                String cardno = cardNo.getText().toString();
                String Cvv = cvv.getText().toString();
                String ExpDate = date.getText().toString();
                int Cardlength = cardNo.length();
                int cvvLen = Cvv.length();

                if (Cardlength != 16) {
                    Toast.makeText(getApplicationContext(), "Please enter the 16 digits of your card", Toast.LENGTH_LONG).show();
                    return;
                }
                if (cvvLen != 3) {
                    Toast.makeText(getApplicationContext(), "Please enter the 3 digits of your CVV", Toast.LENGTH_LONG).show();
                    return;
                }
                if (!(ExpDate.matches("\\d{2}-\\d{2}"))) {
                    Toast.makeText(getApplicationContext(), "Please enter the correct format of date", Toast.LENGTH_LONG).show();
                    return;
                }
                if (name.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please enter the card holder name", Toast.LENGTH_LONG).show();
                    return;
                }

                Toast.makeText(getApplicationContext(), "Order Paid and Placed Successfully", Toast.LENGTH_LONG).show();


    }



});
        placeOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(PaymentXML.this,"Order Placed Successfully",Toast.LENGTH_LONG).show();

            }
        });
    }
}


