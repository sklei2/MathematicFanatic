package swen_anigans.mathematicfanatic;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

public class QuizStartActivity extends AppCompatActivity {

    private ArrayList<Integer> quizzableNumbers;

    //TODO: Figure out where data on which numbers to quiz on will come from.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ArrayList<Integer> quizNumbersIn = new ArrayList<Integer>(Arrays.asList(5, 6)); //Hardcoded.
        quizzableNumbers = quizNumbersIn;
        setContentView(R.layout.activity_quiz_start);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        myToolbar.setBackgroundResource(R.color.quizPrimary);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        TextView quizDescription = (TextView) findViewById(R.id.quizDescription);
        String quizDescriptionText = "Quiz on ";
        for (int i : quizzableNumbers) {
            quizDescriptionText += (Integer.toString(i) + "'s, ");
        }
        quizDescriptionText = quizDescriptionText.substring(0, quizDescriptionText.length() - 2);
        quizDescription.setText(quizDescriptionText);
    }

    public void startQuizButtonClicked(View v) {
        Intent quizIntent = new Intent(QuizStartActivity.this, QuizActivity.class);
        quizIntent.putExtra("atBeginning", true);
        startActivity(quizIntent);
    }
}