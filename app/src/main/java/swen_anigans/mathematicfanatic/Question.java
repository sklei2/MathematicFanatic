package swen_anigans.mathematicfanatic;

import java.io.Serializable;
import java.util.Objects;

/**
 * Created by Matthew Gallagher on 11/14/2016.
 */

public class Question implements Serializable {

    public int firstNumber;
    public int secondNumber;
    public int submittedAnswer;
    public int answer;

    public Question(){
        this(0,0);
    }

    public Question(int first, int second){
        firstNumber = first;
        secondNumber = second;
        answer = firstNumber * secondNumber;
        submittedAnswer = -1;
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
        String s = "(" + firstNumber + " x " + secondNumber + ")";
        return s;
    }

    //region Overridden methods for the hashes/sets

    @Override
    public boolean equals(Object obj)
    {
        if(!(obj instanceof Question))
        {
            return false;
        }
        if(obj == this)
        {
            return true;
        }

        Question quest = (Question) obj;
        return (this.firstNumber == quest.firstNumber) &&
               (this.secondNumber == quest.secondNumber) &&
               (this.submittedAnswer == quest.submittedAnswer);

    }

    @Override
    public int hashCode()
    {
        return Objects.hash(firstNumber, secondNumber, submittedAnswer);
    }

    //endregion
}
