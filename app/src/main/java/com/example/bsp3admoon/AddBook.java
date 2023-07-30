package com.example.bsp3admoon;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddBook extends AppCompatActivity {
    StoreDBHelper addindb= new StoreDBHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);

        Button addbook = (Button) findViewById(R.id.addbookbtn);
        addbook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StoreDBHelper dbHelper= new StoreDBHelper(AddBook.this);
                EditText titletxtbox= (EditText)findViewById(R.id.titletxtbox) ;
                EditText authortxtbox= (EditText)findViewById(R.id.authortxtbox) ;
                EditText genretxtbox= (EditText)findViewById(R.id.genretxtbox) ;
                EditText pricetxtbox= (EditText)findViewById(R.id.pricetxtbox) ;
                EditText ratingstxtbox= (EditText)findViewById(R.id.ratingstxtbox) ;
                EditText quantitytxtbox= (EditText)findViewById(R.id.copiestxtbox) ;

                Book book = new Book();
                book.setTitle(titletxtbox.getText().toString());
                book.setAuthor(authortxtbox.getText().toString());
                book.setGenre(genretxtbox.getText().toString());
                book.setPrice(Double.parseDouble(pricetxtbox.getText().toString()));
                book.setRatings(Double.parseDouble(ratingstxtbox.getText().toString()));
                book.setQuantity(Integer.parseInt(quantitytxtbox.getText().toString()));

                addindb.addOne(book);
                Toast.makeText(AddBook.this, "Book Added Successfully!", Toast.LENGTH_SHORT).show();
            }
        });


    }
}