package swen_anigans.mathematicfanatic;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by Sean on 11/15/2016.
 */

public class QuestionContent
{
    protected ArrayList<Question> questions; //A 2d arraylist of questions. [[3, 8], [8, 2]] = 3*8, 8*2

    public QuestionContent(int totalPages)
    {
        Set<Question> uniqueQuestions = new HashSet<>();

        ArrayList<Integer> numbers = new ArrayList<Integer>();
        int min = DataManager.getInstance().curStudent.rangeMin;
        int max = DataManager.getInstance().curStudent.rangeMax;
        for(int i = min; i <= max; i++)
        {
            numbers.add(i);
        }

        // using a set to create our questions makes sure we don't have duplicates
        while(uniqueQuestions.size() < totalPages)
        {
            int firstNumber = numbers.get(ThreadLocalRandom.current().nextInt(0, numbers.size())); //Gets a random number from quizNumbers.
            int secondNumber = ThreadLocalRandom.current().nextInt(1, 13); //Gets a random number from 1-12.

            Question newQuestion = new Question(firstNumber, secondNumber);

            if(!uniqueQuestions.contains(newQuestion))
            {
                uniqueQuestions.add(newQuestion);
            }
        }

        questions = new ArrayList<Question>(uniqueQuestions);
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
        for (Question q: questions)
        {
            q.submittedAnswer = 0;
        }
    }
}
