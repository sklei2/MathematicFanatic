package swen_anigans.mathematicfanatic;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.provider.ContactsContract;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;

public class QuizActivity extends AppCompatActivity {
    private int pageNumber; //The current page number
    private int totalPages; //The total # of pages
    public Thread t;


    private QuestionContent quizContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        totalPages = 20; //Hardcoding this for now, may change further on.
        setContentView(R.layout.activity_quiz);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        myToolbar.setBackgroundResource(R.color.quizPrimary);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent quizIntent = getIntent();
        this.quizContent = DataManager.getInstance().questionsContent;

        // is it coming from the beginning?
        if(quizIntent.getBooleanExtra("atBeginning", false))
        {
            pageNumber = 1;
        }
        else{
            // it came from the submission
            pageNumber = totalPages;
        }

        t = new Thread() {
            @Override
            public void run() {
                try {
                    while (!isInterrupted()) {
                        Thread.sleep(1000);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                QuizStartActivity.seconds.incrementAndGet(); //remove?
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
                                updateTime(timeDisplay);
                            }
                        });
                    }
                } catch (InterruptedException e) {
                }
            }
        };
        t.start();

        renderPage();
    }

    public void updateTime(String timeDisplay) {
        TextView quizTimer = (TextView) findViewById(R.id.quizTimer);
        quizTimer.setText(timeDisplay);
    }

    public void previousPage(View view) {
        saveAnswer();
        pageNumber -= 1;
        renderPage();
    }

    public void nextPage(View view) {
        saveAnswer();
        pageNumber += 1;
        if (pageNumber > totalPages) {
            Intent quizSubmissionIntent = new Intent(QuizActivity.this, QuizSubmissionActivity.class);
            t.interrupt();

            // luckily the submissions will make use of the new QuizContent object!
            startActivity(quizSubmissionIntent);
        }
        else {
            renderPage();
        }
    }

    public void saveAnswer() {
        EditText quizAnswerInput = (EditText) findViewById(R.id.editQuizAnswer);
        if (!quizAnswerInput.getText().toString().isEmpty()) {
            int quizAnswer = Integer.parseInt(quizAnswerInput.getText().toString());
            quizContent.questions.get(pageNumber-1).submittedAnswer = quizAnswer;
        }
        else {
            quizContent.questions.get(pageNumber-1).submittedAnswer = -1;
        }
        quizAnswerInput.setText("");
    }

    public void renderPage() {
        TextView quizQuestion = (TextView) findViewById(R.id.quizQuestion);
        Question currentQuestion = quizContent.questions.get(pageNumber-1);
        String questionString = (Integer.toString(currentQuestion.firstNumber) + " x " + Integer.toString(currentQuestion.secondNumber));
        quizQuestion.setText(questionString);

        Button quizPreviousButton = (Button) findViewById(R.id.quizPreviousButton);
        if (pageNumber == 1) {
            //Hides the back button on the first page
            quizPreviousButton.setVisibility(View.INVISIBLE);
        }
        else {
            quizPreviousButton.setVisibility(View.VISIBLE);
        }

        TextView quizPagesComplete = (TextView) findViewById(R.id.quizPagesComplete);
        String pagesCompleteText = String.valueOf(pageNumber) + "/" + String.valueOf(totalPages);
        quizPagesComplete.setText(pagesCompleteText);

        int currentAnswer = quizContent.questions.get(pageNumber-1).submittedAnswer;
        if (currentAnswer != -1) {
            EditText editQuizAnswer = (EditText) findViewById(R.id.editQuizAnswer);
            editQuizAnswer.setText(Integer.toString(currentAnswer));
        }
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
                Intent intent = new Intent(QuizActivity.this, StudentLocker.class);
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
