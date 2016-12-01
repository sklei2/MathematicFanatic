package swen_anigans.mathematicfanatic;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class QuizActivity extends AppCompatActivity implements View.OnClickListener {
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
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent quizIntent = getIntent();
        this.quizContent = DataManager.getInstance().questionsContent;

        // this will be a temp variable that holds the desired page, if a user selected
        // a specific question from the submission
        int desiredPage = quizIntent.getIntExtra("questionNumber",-1);

        // is it coming from the beginning?
        if(quizIntent.getBooleanExtra("atBeginning", false))
        {
            pageNumber = 1;
        }
        // is it asking for a specific page?
        else if(desiredPage > -1)
        {
            // The quiz submission submits the index the question is in the
            // questions list, which is 0 index. We use 1 index for pages.
            pageNumber = desiredPage + 1;
        }

        // add this class to be the listener of the answer edittext
        EditText answer = (EditText)findViewById(R.id.editQuizAnswer);
        answer.setOnClickListener(this);

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

    //region OnClick Interface

    @Override
    public void onClick(View view)
    {
        EditText editText = (EditText) findViewById(R.id.editQuizAnswer);
        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView view, int actionId, KeyEvent event) {
                int result = actionId & EditorInfo.IME_MASK_ACTION;
                switch(result)
                {
                    case EditorInfo.IME_ACTION_NEXT:
                        if(1 == 1)
                        {
                            nextPage(view);
                        }
                        return true;
                }
                return false;
            }
        });
    }

    //endregion
}
