package com.example.planningpoker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    DbHelper mydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_screen);
        mydb = new DbHelper(this);
        mydb.InsertData_User("Joska");
        for(int i=0; i<10; i++){
            mydb.InsertData_TASK("Task " + i);
        }
    }

    public void login_check(View view) {
        EditText et_name = (EditText) findViewById(R.id.login_name);
        String name = et_name.getText().toString();

        //name cannot be empty
        if(name.isEmpty()){
            Toast.makeText(this, "Name is empty!", Toast.LENGTH_SHORT).show();
            return;
        }

        //if the user does not exist, we insert it into the database
        if(mydb.is_exist_user(name)){
            Toast.makeText(this, "User exists", Toast.LENGTH_SHORT).show();
        }
        else{
            boolean b = mydb.InsertData_User(name);
            if(!b){
                Toast.makeText(this, "Error at adding new user", Toast.LENGTH_SHORT).show();
                return;
            }
            Toast.makeText(this, "New user added", Toast.LENGTH_SHORT).show();
        }

        //set active user
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(getString(R.string.active_user), name);
        editor.apply();

        //start voting
        Intent intent = new Intent(this, VoteActivity.class);
        startActivity(intent);
    }
}
