package swen_anigans.mathematicfanatic;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

public class QuizActivity extends AppCompatActivity {
    private int pageNumber; //The current page number
    private int totalPages; //The total # of pages

    private QuestionContent quizContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        totalPages = 20; //Hardcoding this for now, may change further on.
        setContentView(R.layout.activity_quiz);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        myToolbar.setBackgroundResource(R.color.quizPrimary);
        setSupportActionBar(myToolbar);

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

        //Timer functionality, will continute to ttempt to get working.
        /*
        final TextView quizTimer = (TextView) findViewById(R.id.quizTimer);
        long startingTime = 120000; //Initializes the timer to 2 minutes.
        new CountDownTimer(startingTime, 0) {
            public void onTick(long milisecondsLeft) {
                System.out.println(Long.toString(milisecondsLeft));
                long minutes = 0;
                long seconds = milisecondsLeft / 1000;
                if (seconds > 60) {
                    minutes += 1;
                    seconds -= 60;
                }

                String quizTimerString = (Long.toString(minutes) + ":" + Long.toString(seconds));
                quizTimer.setText(quizTimerString);
            }

            public void onFinish() {
                //TODO: Change activity to results screen.
                System.out.println("one yo");
            }
        }.start();
        */

        renderPage();
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
            quizContent.questions.get(pageNumber-1).submittedAnswer = 0;
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
        String pagesCompleteText = String.valueOf(pageNumber) + " / " + String.valueOf(totalPages);
        quizPagesComplete.setText(pagesCompleteText);

        int currentAnswer = quizContent.questions.get(pageNumber-1).submittedAnswer;
        if (currentAnswer != 0) {
            EditText editQuizAnswer = (EditText) findViewById(R.id.editQuizAnswer);
            editQuizAnswer.setText(Integer.toString(currentAnswer));
        }
    }
}
