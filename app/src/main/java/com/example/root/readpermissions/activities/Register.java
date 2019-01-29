package com.example.root.readpermissions.activities;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.root.readpermissions.R;
import com.example.root.readpermissions.db.DbHelper;
import com.example.root.readpermissions.model.User;

public class Register extends AppCompatActivity {
    EditText name,email,password,confirmPassword;
    TextView back_login;
    Button register;
    DbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);



        name=(EditText) findViewById(R.id.name);
        email=(EditText) findViewById(R.id.email);
        password=(EditText) findViewById(R.id.password);
        confirmPassword=(EditText) findViewById(R.id.password2);

        dbHelper=new DbHelper(getApplicationContext());


        register=(Button) findViewById(R.id.register);
        back_login=(TextView) findViewById(R.id.back_login);


        back_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Register.this,LoginActivity.class));
            }
        });


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               String myName=name.getText().toString();
               String myEmail=email.getText().toString();
               String myPassword=password.getText().toString();
               String cPassword=confirmPassword.getText().toString();




               if(myName.equals("")||myEmail.equals("")||myPassword.equals("") ||cPassword.equals("")){
                   Toast.makeText(getApplicationContext(),"Please fill in all fields",Toast.LENGTH_SHORT).show();
               }
               else{
                   if(myPassword.equals(cPassword)){
                       if(myPassword.length()>=7){
                           //store to DB

                           boolean checkUser=dbHelper.doesUserExist(myEmail);
                           if(checkUser){
                               Toast.makeText(getApplicationContext(),"User already registered, you can press the login to go to the login screen",Toast.LENGTH_SHORT).show();
                               clearText();
                           }
                           else{
                               saveUserToLocalStorage(new User(myName,myEmail,myPassword));
                               clearText();
                               startActivity(new Intent(Register.this,LoginActivity.class));

                           }
                       }
                       else{
                           Toast.makeText(getApplicationContext(),"Password is too short",Toast.LENGTH_SHORT).show();
                       }
                   }
                   else{
                       Toast.makeText(getApplicationContext(),"Passwords do not match, please try again",Toast.LENGTH_SHORT).show();
                   }
               }
            }
        });


    }

    private void clearText(){
        name.setText("");
        email.setText("");
        password.setText("");
        confirmPassword.setText("");

    }



    private void saveUserToLocalStorage(User user){
        DbHelper dbHelper=new DbHelper(getApplicationContext());
        SQLiteDatabase database=dbHelper.getWritableDatabase();
            dbHelper.saveCompanyToLocalDatabase(user,database);
            Toast.makeText(getApplicationContext(),"User successfully registered",Toast.LENGTH_SHORT).show();
        dbHelper.close();
    }
}
