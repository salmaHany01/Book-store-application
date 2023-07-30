package com.example.bsp3admoon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity
{
    EditText email,password;
    Button loginbtn, signupbtn;
    DBHelper DB;
    String emailpattern="[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email=(EditText) findViewById(R.id.email);
        password=(EditText) findViewById(R.id.password);
        loginbtn=(Button) findViewById(R.id.loginbtn);
        signupbtn=(Button)findViewById(R.id.signupbtn);
        DB=new DBHelper(this);

        signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {

                Intent intent=new Intent(getApplicationContext(),SignUp.class);
                startActivity(intent);
            }
        });


        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String UserMail = email.getText().toString();
                String Password = password.getText().toString();



                if (UserMail.equals("admin") && Password.equals("admin")) {
                    Toast.makeText(Login.this, "Log In as Admin Successful", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(getApplicationContext(), AdminHome.class);
                    startActivity(intent);

                } else {

                    Boolean check = validation(UserMail, Password);
                    Boolean checkusercredentials = DB.checkemailpassword(UserMail, Password);
                    if (checkusercredentials == true && check == true) {


                        Toast.makeText(Login.this, "Log In Successful", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), HomePage.class);
                        intent.putExtra("useremail",email.getText().toString());
                        startActivity(intent);
                    } else {
                        Toast.makeText(Login.this, "Invalid Email or Password", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
 }
private Boolean validation(String Uemail, String Upassword)
{
    if(Uemail.length()==0)
    {
        email.requestFocus();
        email.setError("Field Cannot be Empty");
        return false;
    }

    else if(!Uemail.matches(emailpattern))
    {
        email.requestFocus();
        email.setError("Enter a valid email");
        return false;
    }else if(Upassword.length()==0)
    {
        password.requestFocus();
        password.setError("Field Cannot be Empty");
        return false;
    }else if(Upassword.length()<=5)
    {
        password.requestFocus();
        password.setError("Minimum 6 characters");
        return false;
    }   else
        return true;
}


}