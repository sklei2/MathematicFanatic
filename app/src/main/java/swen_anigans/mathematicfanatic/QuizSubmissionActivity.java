package swen_anigans.mathematicfanatic;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.ArrayList;

public class QuizSubmissionActivity extends AppCompatActivity {

    private ArrayList<ArrayList<Integer>> quizQuestions;
    private ArrayList<Integer> answers;
    private ArrayList<Integer> expectedAnswers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_submission);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        myToolbar.setBackgroundResource(R.color.quizPrimary);
        setSupportActionBar(myToolbar);

        Intent quizSubmissionIntent = getIntent();

        quizQuestions = DataManager.getInstance().questionsContent.questions;
        answers = DataManager.getInstance().questionsContent.answers;
        expectedAnswers = DataManager.getInstance().questionsContent.expectedAnswers;
    }

    public void quizSubmission(View v) {
        int correct = 0;
        int total = answers.size();

        int i = 0;
        while (i < total) {
            if (answers.get(i) == expectedAnswers.get(i)) {
                correct += 1;
            }

            i++;
        }

        Intent resultsIntent = new Intent(QuizSubmissionActivity.this, QuizResultsActivity.class);
        resultsIntent.putExtra("numberCorrect", correct);
        resultsIntent.putExtra("numberTotal", total);
        startActivity(resultsIntent);
    }

    public void previousPage(View view) {
        Intent quizIntent = new Intent(QuizSubmissionActivity.this, QuizActivity.class);
        quizIntent.putExtra("atBeginning", false);

        startActivity(quizIntent);
    }
}
