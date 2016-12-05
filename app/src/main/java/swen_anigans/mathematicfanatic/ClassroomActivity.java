package swen_anigans.mathematicfanatic;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.droidbyme.toastlib.ToastEnum;
import com.droidbyme.toastlib.ToastLib;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;


public class ClassroomActivity extends AppCompatActivity implements View.OnClickListener {

    int min = DataManager.getInstance().curStudent.rangeMin;
    int max = DataManager.getInstance().curStudent.rangeMax;
    ArrayList<Integer> numbers = new ArrayList<Integer>();

    Question question;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.ClassroomTheme);
        setContentView(R.layout.activity_classroom);

        //back button and header
        Toolbar toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        toolbar.setBackgroundResource(R.color.classroomPrimary);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

        // add the action listener onto the edit text
        EditText answer = (EditText) findViewById(R.id.editClassroomAnswer);
        answer.setOnClickListener(this);

        for(int i = min; i <= max; i++)
        {
            numbers.add(i);
        }

        renderPage();
    }

    public void renderPage(){
        TextView questionDisplay = (TextView) findViewById(R.id.classroomQuestion);

        // generate the question
        int firstNumber = numbers.get(ThreadLocalRandom.current().nextInt(0, numbers.size())); //Gets a random number from user's range.
        int secondNumber = ThreadLocalRandom.current().nextInt(1, 13); //Gets a random number from 1-12.

        question = new Question(firstNumber, secondNumber);

        String questionString = question.firstNumber + " x " + question.secondNumber;
        questionDisplay.setText(questionString);

        if (question.submittedAnswer != -1){
            EditText editQuizAnswer = (EditText) findViewById(R.id.editClassroomAnswer);
            editQuizAnswer.setText(Integer.toString(question.submittedAnswer));
        }
    }

    public void nextPage(View view) {
        checkAnswer(view);
    }

    public void saveAnswer(){
        TextView answerInput = (TextView) findViewById(R.id.editClassroomAnswer);
        String answerText = answerInput.getText().toString();
        if(!answerText.isEmpty()){
            int answer = Integer.parseInt(answerText);
            question.submittedAnswer = answer;
        }else{
            question.submittedAnswer = -1;
        }

    }

    public void goToHelp(View view){
        Intent intent = new Intent(this, activity_help.class);
        int[] temp = {question.firstNumber, question.secondNumber};
        intent.putExtra("abValues", temp);
        startActivity(intent);
    }

    public void goToLocker(View view){
        Intent intent = new Intent(this, StudentLocker.class);
        startActivity(intent);
    }

    public void checkAnswer(View view){
        String text;
        saveAnswer();
        if(question.submittedAnswer == -1){
            text = "Please enter an answer first";
            ToastLib.error(this, text, Typeface.create("Helvetica", 0));
        }else if(question.checkAnswer()){
            text = "Correct!";
            ToastLib.success(this, text, Typeface.create("Helvetica", 0));

            // I don't know where to activate the student's ability to recess,
            // so this place seems as good as any
            DataManager.getInstance().curStudent.canRecess = true;
            this.goToNextPage();
        }else{
            // Custom incorrect toast message
            text = "Try Again, You can do it!";
            ToastLib.Builder message = new ToastLib.Builder(this, text);
            message.duration(ToastEnum.SHORT);
            message.corner(8);
            message.margin(56);
            message.padding(36);

            // color for the message (THIS CAN CHANGE!!)
            message.textColor(ContextCompat.getColor(this, R.color.white));
            message.backgroundColor(ContextCompat.getColor(this, R.color.helpPrimaryDark));

            message.show();
        }
    }

    //region private functions

    private void goToNextPage()
    {
        saveAnswer();
        TextView answerInput = (TextView) findViewById(R.id.editClassroomAnswer);
        answerInput.setText("");
        renderPage();
    }

    //endregion

    //region OnClick Interface

    @Override
    public void onClick(View view)
    {
        EditText editText = (EditText) findViewById(R.id.editClassroomAnswer);
        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView view, int actionId, KeyEvent event) {
                int result = actionId & EditorInfo.IME_MASK_ACTION;
                switch(result)
                {
                    case EditorInfo.IME_ACTION_NEXT:
                        if(1 == 1)
                        {
                            checkAnswer(view);
                        }
                        return true;
                }
                return false;
            }
        });
    }

    //endregion
}
