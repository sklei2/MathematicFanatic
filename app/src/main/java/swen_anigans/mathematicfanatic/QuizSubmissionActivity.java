package swen_anigans.mathematicfanatic;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
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
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

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

    // Override the back button to first ask for confirmation
    @Override
    public boolean onOptionsItemSelected(final MenuItem item)
    {
        AlertDialog.Builder alert;
        alert = new AlertDialog.Builder(this);

        alert.setMessage("Leave the quiz?");
        alert.setPositiveButton("Leave", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent(QuizSubmissionActivity.this, StudentLocker.class);
                startActivity(intent);
            }
        });

        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                // canceled going back.
            }
        });

        AlertDialog dialog = alert.create();
        dialog.show();
        return true;
    }
}
