package swen_anigans.mathematicfanatic;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.droidbyme.toastlib.ToastEnum;
import com.droidbyme.toastlib.ToastLib;


public class ClassroomActivity extends AppCompatActivity {

    private int pageNumber = 1;
    private int totalPages = 20;

    private QuestionContent questionContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.ClassroomTheme);
        setContentView(R.layout.activity_classroom);

        //back button and header
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setBackgroundResource(R.color.classroomPrimary);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

        questionContent = DataManager.getInstance().questionsContent;

        renderPage();
    }

    public void renderPage(){
        TextView questionDisplay = (TextView) findViewById(R.id.classroomQuestion);
        Question question = questionContent.questions.get(pageNumber-1);

        String questionString = question.firstNumber + " X " + question.secondNumber;
        questionDisplay.setText(questionString);

        if (question.submittedAnswer != 0){
            EditText editQuizAnswer = (EditText) findViewById(R.id.editClassroomAnswer);
            editQuizAnswer.setText(Integer.toString(question.submittedAnswer));
        }

        Button PreviousButton = (Button) findViewById(R.id.prevButton);
        if (pageNumber == 1) {
            //Hides the back button on the first page
            PreviousButton.setVisibility(View.INVISIBLE);
        }else {
            PreviousButton.setVisibility(View.VISIBLE);
        }

        TextView quizPagesComplete = (TextView) findViewById(R.id.quizPagesComplete);
        quizPagesComplete.setText(pageNumber+"/20");
    }

    public void previousPage(View view) {
        saveAnswer();
        TextView answerInput = (TextView) findViewById(R.id.editClassroomAnswer);
        answerInput.setText("");
        pageNumber -= 1;
        renderPage();
    }

    public void nextPage(View view) {

        saveAnswer();
        TextView answerInput = (TextView) findViewById(R.id.editClassroomAnswer);
        answerInput.setText("");
        pageNumber += 1;
        if (pageNumber > totalPages) {
            finishedQuestions();
        } else {
            renderPage();
        }
    }

    public void saveAnswer(){
        TextView answerInput = (TextView) findViewById(R.id.editClassroomAnswer);
        String answerText = answerInput.getText().toString();
        if(!answerText.isEmpty()){
            int answer = Integer.parseInt(answerText);
            questionContent.questions.get(pageNumber - 1).submittedAnswer = answer;
        }else{
            questionContent.questions.get(pageNumber - 1).submittedAnswer = 0;
        }

    }

    public void goToHelp(View view){
        Question question = questionContent.questions.get(pageNumber - 1);
        Intent intent = new Intent(this, activity_help.class);
        int[] temp = {question.firstNumber, question.secondNumber};
        intent.putExtra("abValues", temp);
        startActivity(intent);
    }

    public void finishedQuestions(){
        int[] items = { R.id.prevButton,
                        R.id.nextButton,
                        R.id.checkButton,
                        R.id.editClassroomAnswer,
                        R.id.quizPagesComplete,
                        R.id.goToHelpButton
                        };
        TextView screenItem;
        for(int i = 0; i < items.length; i++) {
            screenItem= (TextView) findViewById(items[i]);
            screenItem.setVisibility(View.GONE);
        }
        screenItem = (TextView) findViewById(R.id.classroomQuestion);
        screenItem.setText("Nice Job you finished 20 questions!");
        screenItem.setPadding(10,400,10,10);
        screenItem = (TextView) findViewById(R.id.returnToLocker);
        screenItem.setVisibility(View.VISIBLE);
        screenItem.setEnabled(true);
        screenItem.setPadding(20,75,20,10);
        screenItem.setHeight(400);
    }

    public void goToLocker(View view){
        Intent intent = new Intent(this, StudentLocker.class);
        startActivity(intent);
    }

    public void checkAnswer(View view){
        String text;
        saveAnswer();
        if(questionContent.questions.get(pageNumber - 1).submittedAnswer == 0){
            text = "Please enter an answer first";
            ToastLib.error(this, text, Typeface.create("Helvetica", 0));
        }else if(questionContent.questions.get(pageNumber - 1).checkAnswer()){
            text = "Correct!";
            ToastLib.success(this, text, Typeface.create("Helvetica", 0));
        }else{
            // Custom incorrect toast message
            text = "Incorrect";
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
}
