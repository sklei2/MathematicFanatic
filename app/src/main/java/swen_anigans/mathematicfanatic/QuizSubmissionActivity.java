package swen_anigans.mathematicfanatic;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class QuizSubmissionActivity extends AppCompatActivity {

    ArrayList<Question> questions;
    int seconds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_submission);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        myToolbar.setBackgroundResource(R.color.quizPrimary);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        questions = DataManager.getInstance().questionsContent.questions;

        TextView timer = (TextView) findViewById(R.id.quizTimer);
        int displayMinutes = 0;
        int displaySeconds = QuizStartActivity.seconds.get();
        while (displaySeconds >= 60) {
            displayMinutes++;
            displaySeconds -= 60;
        }

        String minutesDisplay = Integer.toString(displayMinutes);
        if (displayMinutes < 10) {
            minutesDisplay = ("0" + Integer.toString(displayMinutes));
        }

        String secondsDisplay = Integer.toString(displaySeconds);
        if (displaySeconds < 10) {
            secondsDisplay = ("0" + Integer.toString(displaySeconds));
        }

        String timeDisplay = (minutesDisplay + ":" + secondsDisplay);
        timer.setText(timeDisplay);

        // Student finished a quiz!!! They can go to recess now!!
        DataManager.getInstance().curStudent.canRecess = true;

        // set the list view and its adapter
        ListView questionsList = (ListView) findViewById(R.id.quizQuestionsList);
        QuestionListAdapter adapter = new QuestionListAdapter(questions, this);
        questionsList.setAdapter(adapter);

        // Add a click listener to the adapter so that it will go to the specifc quesiton
        questionsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(QuizSubmissionActivity.this, QuizActivity.class);
                intent.putExtra("questionNumber", i);
                startActivity(intent);
            }
        });
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
        quizIntent.putExtra("timerStart", true);

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
