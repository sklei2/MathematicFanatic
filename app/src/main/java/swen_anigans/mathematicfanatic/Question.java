package swen_anigans.mathematicfanatic;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by mrg18_000 on 11/14/2016.
 */

public class Question implements Serializable {

    private int firstNumber;
    private int secondNumber;
    private int submitedAnswer;
    private int answer;

    public Question(){
        firstNumber = 0;
        secondNumber = 0;
        answer = 0;
    }

    public Question(int first, int second, int a){
        firstNumber = first;
        secondNumber = second;
        answer = a;
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
    public int getSubmitedAnswer(){
        return submitedAnswer;
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
    public void setSubmitedAnswer(int submitedAnswer) {
        this.submitedAnswer = submitedAnswer;
    }

    //returns 1 if the answer is correct
    //returns 0 if the answer is incorrect
    public int checkAnswer(){
        if (submitedAnswer == answer){
            return 1;
        }else{
            return 0;
        }
    }
}
