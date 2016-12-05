package swen_anigans.mathematicfanatic;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

public class QuizStartActivity extends AppCompatActivity {

    private ArrayList<Integer> quizzableNumbers;
    public static AtomicInteger seconds = new AtomicInteger(0);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_start);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        myToolbar.setBackgroundResource(R.color.quizPrimary);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        int min = DataManager.getInstance().curStudent.rangeMin;
        int max = DataManager.getInstance().curStudent.rangeMax;

        TextView quizDescription = (TextView) findViewById(R.id.quizDescription);
        String quizDescriptionText = "Quiz on ";

        if((max - min) < 2)
        {
            quizDescriptionText += (Integer.toString(min) + "'s and ");
            quizDescriptionText += (Integer.toString(max) + "'s");
        }
        else
        {
            quizDescriptionText += (Integer.toString(min) + "'s - ");
            quizDescriptionText += (Integer.toString(max) + "'s");
        }

        quizDescription.setText(quizDescriptionText);
    }

    public void startQuizButtonClicked(View v) {
        Intent quizIntent = new Intent(QuizStartActivity.this, QuizActivity.class);
        quizIntent.putExtra("atBeginning", true);
        startActivity(quizIntent);
    }
}