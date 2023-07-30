package com.example.bsp3admoon;


import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SearchHomePage extends AppCompatActivity{

    Cursor matchedBooks;
    ArrayAdapter<String> booksNameAdapter;
    String userEmail = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_homepage);
userEmail= getIntent().getStringExtra("useremail");
        final StoreDBHelper booksClassHelper=new StoreDBHelper(this);
        EditText bookNameText= findViewById(R.id.bookNameText);
        Button searchBtn= findViewById(R.id.searchBtn);
        final ListView booksNamesList= findViewById(R.id.booksNamesList);

        booksNameAdapter= new ArrayAdapter<>(SearchHomePage.this, android.R.layout.simple_list_item_1);
        booksNamesList.setAdapter(booksNameAdapter);

        searchBtn.setOnClickListener(view -> {
            String bookName = bookNameText.getText().toString();
            matchedBooks=booksClassHelper.getBooks(bookName);

            booksNameAdapter.clear();
            if(matchedBooks!=null)
            {
                while(!matchedBooks.isAfterLast()) {
                    booksNameAdapter.add(matchedBooks.getString(1));
                    matchedBooks.moveToNext();
                }
            }
            else
            {
                Toast.makeText(getApplicationContext(),"No match",Toast.LENGTH_LONG).show();
            }
        });

        booksNamesList.setOnItemClickListener((parent, view, position, id) -> {
            Intent i=new Intent(SearchHomePage.this,BooksDetails.class);
            matchedBooks.moveToPosition(position);
            i.putExtra("Book_ID",matchedBooks.getInt(0));
            i.putExtra("useremail",userEmail);
            startActivity(i);
        });

    }
}