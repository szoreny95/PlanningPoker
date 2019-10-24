package com.example.planningpoker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    DbHelper mydb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_screen);
        mydb = new DbHelper(this);
        mydb.InsertData_User("Joska");
        mydb.InsertData_TASK("Jo kerdes");
    }

    public void login_check(View view) {


        //ellenorzes
        Intent intent = new Intent(this, VoteActivity.class);
        //EditText editText = (EditText) findViewById(R.id.editText);
        //String message = editText.getText().toString();
        startActivity(intent);

    }




}
