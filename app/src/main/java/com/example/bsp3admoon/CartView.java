package com.example.bsp3admoon;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class CartView extends AppCompatActivity {
    String userEmail=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_view);
        StoreDBHelper cartHelper;
        ListView listView= (ListView) findViewById(R.id.listviewCart);
        ArrayAdapter<String> adapter= new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_multiple_choice );
        listView.setAdapter(adapter);
        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        userEmail= getIntent().getStringExtra("useremail");
        cartHelper= new StoreDBHelper(this);
        Cursor cursor2= cartHelper.AllCart(userEmail);

        while (!cursor2.isAfterLast()) {
            adapter.add(cursor2.getString(1));
            cursor2.moveToNext();
        }

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {


                new AlertDialog.Builder(CartView.this)
                        .setTitle("Do You Want To Remove Selected Item?")
                        .setMessage("")
                        .setPositiveButton("Remove Item", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                Cursor cursor3= cartHelper.fetchOneCartItem(listView.getItemAtPosition(i).toString());

                                cartHelper.removeOneCart(Integer.parseInt(cursor3.getString(0)));
                                adapter.remove(adapter.getItem(i));

                            }
                        })
                        // A null listener allows the button to dismiss the dialog and take no further action.
                        .setNegativeButton("Cancel", null)
                        .setIcon(android.R.drawable.ic_dialog_info)
                        .show();

            }
        });
        Button goPayment= (Button) findViewById(R.id.proceedtopaymentbtn);
        goPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent (CartView.this, PaymentXML.class);
                startActivity(i);
            }
        });

    }
}