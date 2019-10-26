package com.example.planningpoker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class VoteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vote);

        FragmentTransaction frag_trans = getSupportFragmentManager().beginTransaction();
        frag_trans.add(R.id.fragment_container,new FragmentGrid());
        frag_trans.commit();
    }



    public void change_frag(View view)
    {
        //pass the current task name to next fragment
        FragmentResult fragment = new FragmentResult();
        Bundle arguments = new Bundle();
        TextView tv_task = (TextView) findViewById(R.id.tv_task);
        String task = tv_task.getText().toString();
        arguments.putString( "task" , task);
        fragment.setArguments(arguments);

        //insert vote into db
        DbHelper mydb = new DbHelper(this);
        //SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        String user = sharedPref.getString(getString(R.string.active_user),"Active User");
        String vote = sharedPref.getString(getString(R.string.user_vote),"");
        Cursor resUser = mydb.getUserId(user);
        Cursor resTask = mydb.getTaskId(task);
        if(resUser.getCount()==0 || resTask.getCount()==0 || vote.equals(""))
        {
            Toast.makeText(this, "Did not select anything", Toast.LENGTH_SHORT).show();
            //Toast.makeText(this, user + " - "  + task + " - "+ vote, Toast.LENGTH_SHORT).show();
            return;
        }
        resUser.moveToNext();
        resTask.moveToNext();
        int userId = resUser.getInt(0);
        int taskId = resTask.getInt(0);
        mydb.InsertData_Voting(userId, taskId, vote);

        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(getString(R.string.user_vote), "");
        editor.apply();

        FragmentTransaction frag_trans = getSupportFragmentManager().beginTransaction();
        frag_trans.replace(R.id.fragment_container, fragment);
        frag_trans.commit();
    }


    public void change_frag_back(View view)
    {
        FragmentTransaction frag_trans = getSupportFragmentManager().beginTransaction();
        frag_trans.replace(R.id.fragment_container,new FragmentGrid());
        frag_trans.commit();
    }

}
