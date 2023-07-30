package com.example.bsp3admoon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignUp extends AppCompatActivity
{

    Button signup,login;
    EditText username,email,password,confirmpassword;
    DBHelper DB;
    String emailpattern="[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        username =(EditText)findViewById(R.id.username);
        email=(EditText)findViewById(R.id.email);
        password=(EditText) findViewById(R.id.password);
        confirmpassword=(EditText) findViewById(R.id.confirmpass);
        signup=(Button) findViewById(R.id.signupbtn);
        login=(Button) findViewById(R.id.loginbtn);
        DB=new DBHelper(this);



        signup.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                String UserName=username.getText().toString();
                String UserMail=email.getText().toString();
                String Password=password.getText().toString();
                String Confpass=confirmpassword.getText().toString();


                //function for validation
                boolean check= validation(UserName,UserMail,Password,Confpass);
                if(check==true)
                {
                    Boolean checkuser;
                    checkuser = DB.checkusername(UserName);
                    Boolean checkmail=DB.checkusermail(UserMail);
                        if(checkuser==false)
                        {
                            if(checkmail==false)
                            {
                            Boolean insert=DB.insertData(UserName,UserMail,Password);
                            if(insert==true)
                            {


                                Toast.makeText(SignUp.this, "Sign Up Successful!", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), HomePage.class);
                                intent.putExtra("useremail",email.getText().toString());
                                startActivity(intent);
                            }
                            else if(checkmail==true)
                            {
                                email.requestFocus();
                                email.setError("Email already registered");
                            }
                            }
                        }
                        else
                        {
                            username.requestFocus();
                            username.setError("Username already exists");
                        }
                }
                else
                    Toast.makeText(SignUp.this,"Sign Up failed.Please try again",Toast.LENGTH_SHORT).show();

            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent=new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
            }
        });
    }

    private Boolean validation(String userName,String userMail, String PassWord, String ConfPass)
    {
        if(userName.length()==0)
        {
            username.requestFocus();
            username.setError("Field Cannot be Empty");
            return false;
        } else if(userMail.length()==0)
        {
            email.requestFocus();
            email.setError("Field Cannot be Empty");
            return false;
        }

        else if(!userMail.matches(emailpattern))
        {
            email.requestFocus();
            email.setError("Enter a valid email");
            return false;
        }else if(PassWord.length()==0)
        {
            password.requestFocus();
            password.setError("Field Cannot be Empty");
            return false;
        }else if(PassWord.length()<=5)
        {
            password.requestFocus();
            password.setError("Minimum 6 characters");
            return false;
        }else if(ConfPass.length()==0)
        {
            confirmpassword.requestFocus();
            confirmpassword.setError("Please Confirm your Password");
            return false;
        }else if(!ConfPass.equals(PassWord))
        {
            confirmpassword.requestFocus();
            confirmpassword.setError("Passwords Doesn't match");
            return false;
        }
        else
            return true;
    }


}