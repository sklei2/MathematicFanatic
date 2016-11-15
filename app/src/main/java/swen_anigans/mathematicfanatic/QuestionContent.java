package swen_anigans.mathematicfanatic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by Sean on 11/15/2016.
 */

public class QuestionContent
{
    protected ArrayList<ArrayList<Integer>> questions; //A 2d arraylist of questions. [[3, 8], [8, 2]] = 3*8, 8*2
    protected ArrayList<Integer> numbers; //The numbers that the user will be quized on
    protected ArrayList<Integer> answers; //The answers entered by the user
    protected ArrayList<Integer> expectedAnswers; //The calculated answers to the questions

    public QuestionContent(int totalPages)
    {
        questions = new ArrayList<>();
        answers = new ArrayList<>();
        expectedAnswers = new ArrayList<>();

        numbers = new ArrayList<Integer>();
        int min = DataManager.getInstance().curStudent.rangeMin;
        int max = DataManager.getInstance().curStudent.rangeMax;
        for(int i = min; i <= max; i++)
        {
            numbers.add(i);
        }

        //TODO: Change this to not have duplicates.
        for (int i = 0; i < totalPages; i++)
        {
            ArrayList<Integer> problemNumbers = new ArrayList<Integer>();
            int firstNumber = numbers.get(ThreadLocalRandom.current().nextInt(0, numbers.size())); //Gets a random number from quizNumbers.
            int secondNumber = ThreadLocalRandom.current().nextInt(1, 13); //Gets a random number from 1-12.

            //Prevents dups in questions
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

            //Prevents dups in questions
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
            questions.add(problemNumbers);

            int answer = firstNumber * secondNumber;
            expectedAnswers.add(answer);
        }

        for (int i = 0; i < totalPages; i++) {
            answers.add(0);
            expectedAnswers.add(-1);
        }
    }

    // we don't want the questions in the same order everytime
    // we're teaching the math not the order.
    public void ShuffleQuestions()
    {

    }

    // After a quiz or classroom where answers are submitted we need
    // to make sure that they are cleared
    public void ClearAnswers()
    {
        for(int i = 0; i < answers.size(); i++)
        {
            answers.set(i, 0);
        }
    }
}
