package swen_anigans.mathematicfanatic;

import android.content.Intent;
import android.support.v7.app.ActionBar;
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

public class ClassroomActivity extends AppCompatActivity {

    private int pageNumber;
    private int totalPages;
    private ArrayList<ArrayList<Integer>> classroomQuestions;
    private ArrayList<Integer> classroomNumbers;
    private ArrayList<Integer> answers;
    private ArrayList<Integer> expectedAnswers;

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

        pageNumber = 1;
        totalPages = 20;
        classroomQuestions = new ArrayList<ArrayList<Integer>>();
        expectedAnswers = new ArrayList<Integer>();
        answers = new ArrayList<Integer>();
        initializeQuestions();

        for (int i = 0; i < totalPages; i++) {
            answers.add(0);

        }

        renderPage();
        
        //finishedQuestions();
    }

    public void initializeQuestions() {
        //TODO later need to make the numbers pick dynamic
        classroomNumbers = new ArrayList<Integer>(Arrays.asList(3, 8)); //Hardcoding the numbers to be quizzed on.


        for (int i = 0; i < totalPages; i++) {
            ArrayList<Integer> problemNumbers = new ArrayList<Integer>();
            int firstNumber = classroomNumbers.get(ThreadLocalRandom.current().nextInt(0, classroomNumbers.size())); //Gets a random number from quizNumbers.
            int secondNumber = ThreadLocalRandom.current().nextInt(1, 13); //Gets a random number from 1-12.

            problemNumbers.add(firstNumber);
            problemNumbers.add(secondNumber);
            classroomQuestions.add(problemNumbers);

            int answer = firstNumber * secondNumber;
            expectedAnswers.add(answer);
        }
    }

    public void renderPage() {
        TextView quizQuestion = (TextView) findViewById(R.id.classroomQuestion);
        ArrayList<Integer> currentQuestion = classroomQuestions.get(pageNumber-1);

        String questionString = (Integer.toString(currentQuestion.get(1)) + " X " + Integer.toString(currentQuestion.get(0)));
        quizQuestion.setText(questionString);

        Button PreviousButton = (Button) findViewById(R.id.prevButton);
        if (pageNumber == 1) {
            //Hides the back button on the first page
            PreviousButton.setVisibility(View.INVISIBLE);
        }else {
            PreviousButton.setVisibility(View.VISIBLE);
        }

        //populate the answer field with the previous answer
        int currentAnswer = answers.get(pageNumber-1);
        if (currentAnswer != 0) {
            EditText editQuizAnswer = (EditText) findViewById(R.id.editClassroomAnswer);
            editQuizAnswer.setText(Integer.toString(currentAnswer));
        }

        TextView quizPagesComplete = (TextView) findViewById(R.id.quizPagesComplete);
        quizPagesComplete.setText(pageNumber+"/20");
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
            finishedQuestions();
        } else {
            renderPage();
        }
    }

    public void saveAnswer() {
        EditText quizAnswerInput = (EditText) findViewById(R.id.editClassroomAnswer);
        String answer = quizAnswerInput.getText().toString();
        if (!answer.isEmpty()) {
            int quizAnswer = Integer.parseInt(answer);
            answers.set(pageNumber-1, quizAnswer);
        }else{
            answers.set(pageNumber-1, 0);
        }
        quizAnswerInput.setText("");
    }

    public void goToHelp(View view) {
        ArrayList<Integer> currentQuestion = classroomQuestions.get(pageNumber-1);
        int a = currentQuestion.get(1);
        int b = currentQuestion.get(0);
        Intent intent = new Intent(this, activity_help.class);
        int[] temp = {a,b};
        intent.putExtra("abValues",temp);
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

}
