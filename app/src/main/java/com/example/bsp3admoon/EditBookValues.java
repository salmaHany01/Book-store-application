package com.example.bsp3admoon;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditBookValues extends AppCompatActivity {
StoreDBHelper dbEdit=  new StoreDBHelper(EditBookValues.this);;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_book_values);
        EditText title= (EditText) findViewById(R.id.txtboxtitle);
        EditText author= (EditText) findViewById(R.id.txtboxauthor);
        EditText price= (EditText) findViewById(R.id.txtboxprice);
        EditText genre= (EditText) findViewById(R.id.txtboxgenre);
        EditText quantity= (EditText) findViewById(R.id.txtboxquantity);
        EditText ratings= (EditText) findViewById(R.id.txtboxratings);

        title.setText(getIntent().getStringExtra("oldtitle"));
        author.setText(getIntent().getStringExtra("oldauthor"));
        genre.setText(getIntent().getStringExtra("oldgenre"));
        price.setText(String.valueOf(getIntent().getDoubleExtra("oldprice",0.00)));
        quantity.setText(String.valueOf(getIntent().getIntExtra("oldquantity",0)));
        ratings.setText(String.valueOf(getIntent().getDoubleExtra("oldratings",0.00)));

        Button update = (Button)findViewById(R.id.updatebtn);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    dbEdit.updateBookInfo(getIntent().getStringExtra("oldtitle"),
                            title.getText().toString(), author.getText().toString()
                            , genre.getText().toString(), Double.parseDouble(price.getText().toString()),
                            Float.parseFloat(ratings.getText().toString()),
                            Integer.parseInt(quantity.getText().toString()));
                    Toast.makeText(EditBookValues.this, "Book Updated", Toast.LENGTH_LONG).show();

                }
                catch (Exception e)
                {
                    Toast.makeText(EditBookValues.this, "Book isn't Updated", Toast.LENGTH_LONG).show();

                }

            }

        });
    }
}