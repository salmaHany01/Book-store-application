package com.example.bsp3admoon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AdminHome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button addbutton = (Button) findViewById(R.id.addbtn);
        Button editbutton = (Button) findViewById(R.id.editbtn);
        Button deletebutton = (Button) findViewById(R.id.deletebtn);
        addbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view1) {
                Intent addpage = new Intent(AdminHome.this, AddBook.class);
                startActivity(addpage);
            }
        });

        editbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view2) {
                Intent editpage = new Intent(AdminHome.this, EditBook.class);
                startActivity(editpage);
            }
        });

        deletebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view3) {
                Intent deletepage = new Intent(AdminHome.this, DeleteBook.class);
                startActivity(deletepage);
            }
        });
    }
}