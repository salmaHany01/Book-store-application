package com.example.bsp3admoon;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;

public class EditBook extends AppCompatActivity {
    StoreDBHelper booksdb ;
    Cursor cursor;
    Cursor viewquick;
    Book bookedit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_book);
        ListView listView= (ListView) findViewById(R.id.listviewedit);
        ArrayAdapter<String> adapter= new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_multiple_choice );
        listView.setAdapter(adapter);
        EditText editText= (EditText)findViewById(R.id.searchedittxt);
        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                viewquick = booksdb.fetchOneBook(listView.getItemAtPosition(i).toString());
               bookedit= new Book();
               bookedit.setTitle(viewquick.getString(1));
                bookedit.setAuthor(viewquick.getString(2));
                bookedit.setGenre(viewquick.getString(3));
                bookedit.setPrice(Double.parseDouble(viewquick.getString(4)));
                bookedit.setRatings(Double.parseDouble(viewquick.getString(5)));
                bookedit.setQuantity(Integer.parseInt(viewquick.getString(6)));
            }
        });
        Button btnsearch= (Button)findViewById(R.id.searcheditbtn);
        booksdb = new StoreDBHelper(EditBook.this);
        btnsearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (editText.getText().toString().isEmpty()) {
                    if  (!adapter.isEmpty() )
                        adapter.clear();

                    cursor = booksdb.fetchAllBooks();
                    while (!cursor.isAfterLast()) {

                        adapter.add(cursor.getString(1));
                        cursor.moveToNext();
                    }
                }
                else {

                    try {
                        cursor = booksdb.fetchOneBook(editText.getText().toString());
                        if (!adapter.isEmpty())
                            adapter.clear();
                        adapter.add(cursor.getString(1));

                    }
                    catch(Exception e)
                    {
                        Toast.makeText(EditBook.this, "Can't Find Book", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        Button editbtn= (Button)findViewById(R.id.editbookbtn);
        editbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent editOne = new Intent(EditBook.this, EditBookValues.class);
                editOne.putExtra("oldtitle", bookedit.getTitle());
                editOne.putExtra("oldauthor", bookedit.getAuthor());
                editOne.putExtra("oldgenre", bookedit.getGenre());
                editOne.putExtra("oldprice", bookedit.getPrice());
                editOne.putExtra("oldquantity", bookedit.getQuantity());
                editOne.putExtra("oldratings", bookedit.getRatings());
                startActivity(editOne);
            }
        });
    }

}
