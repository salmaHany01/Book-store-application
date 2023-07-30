package com.example.bsp3admoon;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class BooksDetails extends Activity{
String userEmail;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_details);
        userEmail= getIntent().getStringExtra("useremail");
        StoreDBHelper bookHelperClass=new StoreDBHelper(this);
        int BookId=getIntent().getExtras().getInt("Book_ID");
        Cursor bookDetails=bookHelperClass.getBookData(BookId);

        ((TextView)findViewById(R.id.bookName)).setText("Name: "+bookDetails.getString(0));
        ((TextView)findViewById(R.id.bookAuthor)).setText("Author: "+bookDetails.getString(1));
        ((TextView)findViewById(R.id.bookGenre)).setText("Genre: "+bookDetails.getString(2));
        ((TextView)findViewById(R.id.bookPrice)).setText("Price: "+bookDetails.getDouble(3));
        ((TextView)findViewById(R.id.bookRating)).setText("Rating: "+bookDetails.getFloat(4));
        ((TextView)findViewById(R.id.bookQuantity)).setText("Quantity in stock: "+bookDetails.getInt(5));
        Button addcart= (Button) findViewById(R.id.AddtocartSearch)  ;
        addcart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bookHelperClass.addtoCart(userEmail,BookId);
                Toast.makeText(BooksDetails.this, "Added To Cart", Toast.LENGTH_SHORT).show();
            }
        });
    }
}