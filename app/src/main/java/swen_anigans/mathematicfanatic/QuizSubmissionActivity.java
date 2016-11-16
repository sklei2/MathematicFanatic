package swen_anigans.mathematicfanatic;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.ArrayList;

public class QuizSubmissionActivity extends AppCompatActivity {

    ArrayList<Question> questions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_submission);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        myToolbar.setBackgroundResource(R.color.quizPrimary);
        setSupportActionBar(myToolbar);

        questions = DataManager.getInstance().questionsContent.questions;
    }

    public void quizSubmission(View v) {
        int correct = 0;

        for(int i = 0; i < questions.size(); i++)
        {
            if (questions.get(i).checkAnswer())
            {
                correct += 1;
            }
        }

        Intent resultsIntent = new Intent(QuizSubmissionActivity.this, QuizResultsActivity.class);
        resultsIntent.putExtra("numberCorrect", correct);
        resultsIntent.putExtra("numberTotal", questions.size());
        startActivity(resultsIntent);
    }

    public void previousPage(View view) {
        Intent quizIntent = new Intent(QuizSubmissionActivity.this, QuizActivity.class);
        quizIntent.putExtra("atBeginning", false);

        startActivity(quizIntent);
    }
}
