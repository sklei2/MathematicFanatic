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
import java.util.concurrent.TimeUnit;

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
            quizQuestions = (ArrayList<ArrayList<Integer>>) quizIntent.getSerializableExtra("quizQuestions");
            answers = quizIntent.getIntegerArrayListExtra("answers");
            expectedAnswers = quizIntent.getIntegerArrayListExtra("expectedAnswers");
        }
        else {
            pageNumber = 1;
            initializeQuizQuestions();

            for (int i = 0; i < totalPages; i++) {
                answers.add(0);
                expectedAnswers.add(-1);
            }
        }

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

    public void initializeQuizQuestions() {
        quizQuestions = new ArrayList<>();
        answers = new ArrayList<>();
        expectedAnswers = new ArrayList<>();
        quizNumbers = new ArrayList<Integer>(Arrays.asList(3, 8)); //Hardcoding the numbers to be quizzed on.

        /*
        ArrayList<Integer> problemNumbers = new ArrayList<Integer>();
        int firstNumber = quizNumbers.get(ThreadLocalRandom.current().nextInt(0, quizNumbers.size())); //Gets a random number from quizNumbers.
        int secondNumber = ThreadLocalRandom.current().nextInt(1, 13); //Gets a random number from 1-12.
        problemNumbers.add(firstNumber);
        problemNumbers.add(secondNumber);
        quizQuestions.add(problemNumbers);
        int answer = firstNumber * secondNumber;
        expectedAnswers.add(answer);
        */

        //TODO: Change this to not have duplicates.
        for (int i = 1; i < totalPages; i++) {
            ArrayList<Integer> problemNumbers = new ArrayList<Integer>();
            int firstNumber = quizNumbers.get(ThreadLocalRandom.current().nextInt(0, quizNumbers.size())); //Gets a random number from quizNumbers.
            int secondNumber = ThreadLocalRandom.current().nextInt(1, 13); //Gets a random number from 1-12.

            /*
            for (int j = 0; j < quizNumbers.size(); j++) {
                while (quizQuestions.get(j).get(0) == firstNumber && quizQuestions.get(j).get(1) == secondNumber) {
                    firstNumber = quizNumbers.get(ThreadLocalRandom.current().nextInt(0, quizNumbers.size())); //Gets a random number from quizNumbers.
                    secondNumber = ThreadLocalRandom.current().nextInt(1, 13); //Gets a random number from 1-12.
                }
            }
            */

            problemNumbers.add(firstNumber);
            problemNumbers.add(secondNumber);
            quizQuestions.add(problemNumbers);

            int answer = firstNumber * secondNumber;
            expectedAnswers.add(answer);
        }
    }

    public void previousPage(View view) {
        saveAnswer();
        pageNumber -= 1;
        renderPage();
    }

    public void nextPage(View view) {
        saveAnswer();
        pageNumber += 1;
        if (pageNumber >= totalPages) {
            Intent quizSubmissionIntent = new Intent(QuizActivity.this, QuizSubmissionActivity.class);

            Bundle quizNumbersBundle = new Bundle();
            quizNumbersBundle.putSerializable("quizQuestions", quizNumbers);

            //quizSubmissionIntent.putExtra("quizNumbers", quizNumbersBundle);
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
        if (!quizAnswerInput.getText().toString().isEmpty()) {
            int quizAnswer = Integer.parseInt(quizAnswerInput.getText().toString());
            answers.set(pageNumber-1, quizAnswer);
        }
        else {
            answers.set(pageNumber-1, 0);
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

        TextView quizPagesComplete = (TextView) findViewById(R.id.quizPagesComplete);
        String pagesCompleteText = String.valueOf(pageNumber) + " / " + String.valueOf(totalPages);
        quizPagesComplete.setText(pagesCompleteText);

        int currentAnswer = answers.get(pageNumber-1);
        if (currentAnswer != 0) {
            EditText editQuizAnswer = (EditText) findViewById(R.id.editQuizAnswer);
            editQuizAnswer.setText(Integer.toString(currentAnswer));
        }
    }
}
