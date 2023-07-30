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
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

public class HomePage extends AppCompatActivity {
    StoreDBHelper booksdb ;
    Cursor cursor;
    Cursor viewquick;
    String userEmail=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        ListView listView= (ListView) findViewById(R.id.listviewhomepage);
        ArrayAdapter<String> adapter= new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_multiple_choice );
        listView.setAdapter(adapter);
        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        userEmail= getIntent().getStringExtra("useremail");
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                viewquick = booksdb.fetchOneBook(listView.getItemAtPosition(i).toString());
                AlertDialog.Builder builder;  builder = new AlertDialog.Builder(HomePage.this);
                AlertDialog alert = builder.create();

                new AlertDialog.Builder(HomePage.this)
                        .setTitle(viewquick.getString(1))
                        .setMessage("Author:"+viewquick.getString(2)
                                +"\nGenre:"+viewquick.getString(3)+
                                "\nPrice:"+viewquick.getString(4)
                                +"\nRatings:"+viewquick.getString(5))
                        .setPositiveButton("Add To Cart", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                            booksdb.addtoCart(userEmail, Integer.parseInt(viewquick.getString(0)));
                            Toast.makeText(HomePage.this, "Added To Cart", Toast.LENGTH_SHORT).show();
                            }
                        })
                        // A null listener allows the button to dismiss the dialog and take no further action.
                        .setNegativeButton("Cancel", null)
                        .setIcon(android.R.drawable.ic_dialog_info)
                        .show();

            }
        });
        ImageButton searchPage = (ImageButton) findViewById(R.id.search_btn);
        searchPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent searchp= new Intent(HomePage.this, SearchHomePage.class);
                searchp.putExtra("useremail",userEmail);
                startActivity(searchp);
            }

        });
        ImageButton cartbtn =(ImageButton)findViewById(R.id.cart_btn);
        cartbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cartp= new Intent(HomePage.this, CartView.class);
                cartp.putExtra("useremail",userEmail);
                startActivity(cartp);
            }
        });
        booksdb = new StoreDBHelper(HomePage.this);
        cursor = booksdb.fetchAllBooks();
        while (!cursor.isAfterLast()) {
            adapter.add(cursor.getString(1));
            cursor.moveToNext();
        }

    }
} 