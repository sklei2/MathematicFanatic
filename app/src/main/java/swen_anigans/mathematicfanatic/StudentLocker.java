package swen_anigans.mathematicfanatic;

import android.content.Intent;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class StudentLocker extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setTheme(R.style.LockerTheme);
        setContentView(R.layout.activity_student_locker);
        Toolbar toolbar = (Toolbar) findViewById(R.id.Lockertoolbar);
        setSupportActionBar(toolbar);

        //set the up button
        getActionBar().setDisplayHomeAsUpEnabled(true);

    }

    public void toQuiz(View view){
        Intent intent = new Intent(this, QuizStartActivity.class);
        startActivity(intent);
    }

    public void toClassroom(View view){
        Intent intent = new Intent(this, QuizStartActivity.class);
        startActivity(intent);
    }

    public void toRecess(View view){
        Intent intent = new Intent(this, QuizStartActivity.class);
        startActivity(intent);
    }

}
