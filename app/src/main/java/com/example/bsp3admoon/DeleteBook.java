package com.example.bsp3admoon;
import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class DeleteBook extends AppCompatActivity {
   int h;
    int bookid;
   StoreDBHelper booksdb ;
    Cursor cursor;
    Cursor viewquick;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_book);
        ListView listView= (ListView) findViewById(R.id.listviewbooks);
        ArrayAdapter<String> adapter= new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_multiple_choice );
        listView.setAdapter(adapter);
        EditText editText= (EditText)findViewById(R.id.searchtxtbox);
         listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                viewquick = booksdb.fetchOneBook(listView.getItemAtPosition(i).toString());
                AlertDialog.Builder builder;  builder = new AlertDialog.Builder(DeleteBook.this);
                AlertDialog alert = builder.create();

                alert.setTitle(viewquick.getString(1));
                alert.setMessage(viewquick.getString(2));
                h=i;
                bookid=Integer.parseInt( viewquick.getString(0));
                alert.show();
            }
        });
      Button btndelete= (Button) findViewById(R.id.deletebookbtn);
      btndelete.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              new AlertDialog.Builder(DeleteBook.this)
                      .setTitle("Delete entry")
                      .setMessage("Are you sure you want to delete this entry?")
                      .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                          public void onClick(DialogInterface dialog, int which) {
                              adapter.remove(adapter.getItem(h));
                              booksdb.removeOne(bookid);
                              Toast.makeText(DeleteBook.this, "Book Deleted", Toast.LENGTH_LONG).show();
                          }
                      })

                      // A null listener allows the button to dismiss the dialog and take no further action.
                      .setNegativeButton(android.R.string.no, null)

                      .setIcon(android.R.drawable.ic_dialog_alert)
                      .show();


          }
      });
      Button btnsearch= (Button)findViewById(R.id.searchbooktodelete);
      booksdb = new StoreDBHelper(DeleteBook.this);
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
                      Toast.makeText(DeleteBook.this, "Can't Find Book", Toast.LENGTH_SHORT).show();
                  }
              }
          }
      });
    }

}
