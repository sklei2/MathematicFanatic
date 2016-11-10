package swen_anigans.mathematicfanatic;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

//TODO: Set the input field to the expectedAnswer if it's not 0. Empty if it is 0.
public class QuizActivity extends AppCompatActivity {
    private int pageNumber;
    private int totalPages;
    private ArrayList<ArrayList<Integer>> quizQuestions;
    private ArrayList<Integer> quizNumbers;
    private ArrayList<Integer> answers;
    private ArrayList<Integer> expectedAnswers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        totalPages = 20; //Hardcoding this for now, may change further on.
        setContentView(R.layout.activity_quiz);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        myToolbar.setBackgroundResource(R.color.quizPrimary);
        setSupportActionBar(myToolbar);

        Intent quizIntent = getIntent();
        if (quizIntent.getBooleanExtra("atBeginning", true) == false) {
            pageNumber = totalPages;
            quizNumbers = quizIntent.getIntegerArrayListExtra("quizNumbers");
            answers = quizIntent.getIntegerArrayListExtra("answers");
            expectedAnswers = quizIntent.getIntegerArrayListExtra("expectedAnswers");
        }
        else {
            pageNumber = 1;
            initializeQuizQuestions();

            for (int i = 0; i < totalPages; i++) {
                answers.set(i, 0);
                expectedAnswers.set(i, -1);
            }
        }

        renderPage();
    }

    public void initializeQuizQuestions() {
        quizNumbers = new ArrayList<Integer>(Arrays.asList(5, 6)); //Hardcoding the numbers to be quizzed on.

        for (int i = 0; i < totalPages; i++) {
            ArrayList<Integer> problemNumbers = new ArrayList<Integer>();
            int firstNumber = quizNumbers.get(ThreadLocalRandom.current().nextInt(0, quizNumbers.size())); //Gets a random number from quizNumbers.
            int secondNumber = ThreadLocalRandom.current().nextInt(1, 13); //Gets a random number from 1-12.

            problemNumbers.add(firstNumber);
            problemNumbers.add(secondNumber);
            quizQuestions.add(problemNumbers);

            int answer = firstNumber * secondNumber;
            expectedAnswers.add(answer);
        }
    }

    public void previousPage() {
        pageNumber -= 1;
        saveAnswer();
        renderPage();
    }

    public void nextPage() {
        pageNumber += 1;
        saveAnswer();
        if (pageNumber > totalPages) {
            Intent quizSubmissionIntent = new Intent(QuizActivity.this, QuizSubmissionActivity.class);

            Bundle quizNumbersBundle = new Bundle();
            quizNumbersBundle.putSerializable("quizNumbers", quizNumbers);

            quizSubmissionIntent.putExtra("quizNumbers", quizNumbersBundle);
            quizSubmissionIntent.putExtra("answers", answers);
            quizSubmissionIntent.putExtra("expectedAnswers", expectedAnswers);
            startActivity(quizSubmissionIntent);
        }
        else {
            renderPage();
        }
    }

    public void saveAnswer() {
        EditText quizAnswerInput = (EditText) findViewById(R.id.editQuizAnswer);
        if (quizAnswerInput.getText().toString() != "") {
            int quizAnswer = Integer.parseInt(quizAnswerInput.getText().toString());
            answers.set(pageNumber-1, quizAnswer);
        }
        quizAnswerInput.setText("");
    }

    public void renderPage() {
        TextView quizQuestion = (TextView) findViewById(R.id.quizQuestion);
        ArrayList<Integer> currentQuestion = quizQuestions.get(pageNumber-1);
        String questionString = (Integer.toString(currentQuestion.get(0)) + " X " + Integer.toString(currentQuestion.get(1)));
        quizQuestion.setText(questionString);

        Button quizPreviousButton = (Button) findViewById(R.id.quizPreviousButton);
        if (pageNumber == 1) {
            //Hides the back button on the first page
            quizPreviousButton.setVisibility(View.INVISIBLE);
        }
        else {
            quizPreviousButton.setVisibility(View.VISIBLE);
        }

        final TextView quizTimer = (TextView) findViewById(R.id.quizTimer);
        int startingTime = 120000; //Initializes the timer to 2 minutes.
        new CountDownTimer(startingTime, 0) {
            public void onTick(long milisecondsLeft) {
                long minutes = 0;
                long seconds = milisecondsLeft / 1000;
                while (seconds > 60) {
                    minutes += 1;
                    seconds -= 60;
                }

                String quizTimerString = (Long.toString(minutes) + ":" + Long.toString(seconds));
                quizTimer.setText(quizTimerString);
            }

            public void onFinish() {
                //TODO: Change activity to results screen.
            }
        };

        TextView quizPagesComplete = (TextView) findViewById(R.id.quizPagesComplete);
        quizPagesComplete.setText(pageNumber);
    }
}
