package swen_anigans.mathematicfanatic;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Matthew Gallagher on 11/14/2016.
 */

public class Question implements Serializable {

    private int firstNumber;
    private int secondNumber;
    private int submittedAnswer;
    private int answer;

    public Question(){
        firstNumber = 0;
        secondNumber = 0;
        answer = 0;
        submittedAnswer = 0;
    }

    public Question(int first, int second, int a){
        firstNumber = first;
        secondNumber = second;
        answer = a;
        submittedAnswer = 0;
    }

    public int getFirstNumber(){
        return firstNumber;
    }
    public int getSecondNumber(){
        return secondNumber;
    }
    public int getAnswer(){
        return answer;
    }
    public int getSubmittedAnswer(){
        return submittedAnswer;
    }

    public void setFirstNumber(int num){
        firstNumber = num;
    }
    public void setSecondNumber(int num){
        secondNumber = num;
    }
    public void setAnswer(int answer) {
        this.answer = answer;
    }
    public void setSubmittedAnswer(int submittedAnswer) {
        this.submittedAnswer = submittedAnswer;
    }

    //returns 1 if the answer is correct
    //returns 0 if the answer is incorrect
    public boolean checkAnswer(){
        if (submittedAnswer == answer){
            return true;
        }else{
            return false;
        }
    }

    public String toString(){
        String s = "(" + firstNumber + ", " + secondNumber + ", " + answer + ", " + submittedAnswer + ")\n";
        return s;
    }
}
