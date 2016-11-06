package swen_anigans.mathematicfanatic;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

public class QuizSubmissionActivity extends AppCompatActivity {

    private ArrayList<ArrayList<Integer>> quizNumbers;
    private ArrayList<Integer> answers;
    private ArrayList<Integer> expectedAnswers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_submission);

        Intent quizSubmissionIntent = getIntent();

        quizNumbers = (ArrayList<ArrayList<Integer>>) quizSubmissionIntent.getSerializableExtra("quizNumbers");
        answers = quizSubmissionIntent.getIntegerArrayListExtra("answers");
        expectedAnswers = quizSubmissionIntent.getIntegerArrayListExtra("expectedAnswers");
    }

    public void quizSubmission(View v) {
        //TODO: Go to quiz results page.
        //TODO: Figure out how submission's gonna go down.
        int correct = 0;
        int total = answers.size();

        int i = 0;
        while (i < total) {
            if (answers.get(i) == expectedAnswers.get(i)) {
                correct += 1;
            }

            i++;
        }

        /*
        Intent resultsIntent = new Intent(QuizSubmissionActivity.this, ); //TODO: Change ResultsActivity to the name of Alec's thing.
        resultsIntent.putExtra("numberCorrect", correct);
        resultsIntent.putExtra("numberTotal", total);
        */
    }

    public void previousPage() {
        Intent quizIntent = new Intent(QuizSubmissionActivity.this, QuizActivity.class);
        quizIntent.putExtra("atBeginning", false);
        quizIntent.putExtra("quizNumbers", quizNumbers);
        quizIntent.putExtra("answers", answers);
        quizIntent.putExtra("expectedAnswers", expectedAnswers);
    }
}
