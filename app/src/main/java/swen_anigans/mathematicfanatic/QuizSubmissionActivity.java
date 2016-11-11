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

        quizQuestions = new ArrayList<>();
        ArrayList<Integer> firstMult = quizSubmissionIntent.getIntegerArrayListExtra("firstMult");
        ArrayList<Integer> secondMult = quizSubmissionIntent.getIntegerArrayListExtra("secondMult");

        for (int i = 0; i < firstMult.size(); i++) {
            ArrayList<Integer> problem = new ArrayList<>();
            problem.add(firstMult.get(i));
            problem.add(secondMult.get(i));
            quizQuestions.add(problem);
        }


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

        Intent resultsIntent = new Intent(QuizSubmissionActivity.this, QuizResultsActivity.class);
        resultsIntent.putExtra("numberCorrect", correct);
        resultsIntent.putExtra("numberTotal", total);
    }

    public void previousPage(View view) {
        Intent quizIntent = new Intent(QuizSubmissionActivity.this, QuizActivity.class);
        quizIntent.putExtra("atBeginning", false);

        ArrayList<Integer> firstMult = new ArrayList<>();
        ArrayList<Integer> secondMult = new ArrayList<>();
        for (int i = 0; i < quizQuestions.size(); i++) {
            firstMult.add(quizQuestions.get(i).get(0));
            secondMult.add(quizQuestions.get(i).get(1));
        }

        quizIntent.putExtra("firstMult", firstMult);
        quizIntent.putExtra("secondMult", secondMult);
        quizIntent.putExtra("answers", answers);
        quizIntent.putExtra("expectedAnswers", expectedAnswers);
        startActivity(quizIntent);
    }
}
