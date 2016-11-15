package swen_anigans.mathematicfanatic;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;


public class ClassroomActivity extends AppCompatActivity {

    private int pageNumber = 1;
    private int totalPages = 20;
    private ArrayList<Integer> classroomNumbers;

    private ArrayList<Question> questions;

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

        questions = new ArrayList<Question>();

        initializeQuestions();

        renderPage();
    }

    public void initializeQuestions() {
        //TODO later need to make the numbers pick dynamic
        classroomNumbers = new ArrayList<Integer>(Arrays.asList(3, 8)); //Hardcoding the numbers to be quizzed on.

        for(int i = 0; i < totalPages; i++){
            //Gets a random number from 1-12.
            int firstNumber = ThreadLocalRandom.current().nextInt(1, 13);
            //Gets a random number from quizNumbers.
            int secondNumber = classroomNumbers.get(ThreadLocalRandom.current().nextInt(0, classroomNumbers.size()));
            int answer = firstNumber * secondNumber;

            Question q = new Question(firstNumber, secondNumber);
            questions.add(q);
        }
    }

    public void renderPage(){
        TextView questionDisplay = (TextView) findViewById(R.id.classroomQuestion);
        Question question = questions.get(pageNumber - 1);

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
            questions.get(pageNumber - 1).submittedAnswer = answer;
        }else{
            questions.get(pageNumber - 1).submittedAnswer = 0;
        }

    }

    public void goToHelp(View view){
        Question question = questions.get(pageNumber - 1);
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
        if(questions.get(pageNumber - 1).submittedAnswer == 0){
            text = "Please enter an answer first.";
        }else if(questions.get(pageNumber - 1).checkAnswer()){
            text = "Correct";
        }else{
            text = "Incorrect";
        }
        
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }
}
