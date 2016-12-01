package swen_anigans.mathematicfanatic;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Sean on 11/30/2016.
 */

public class QuestionListAdapter extends BaseAdapter implements ListAdapter
{

    private ArrayList<Question> questions;
    private Context context;

    public QuestionListAdapter(ArrayList<Question> list, Context context)
    {
        this.questions = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return questions.size();
    }

    @Override
    public Object getItem(int i) {
        return questions.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view == null)
        {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.question_answers, null);
        }

        // let's fill out the labels with the information!
        TextView questionNumber = (TextView) view.findViewById(R.id.quizQuestionNumber);
        TextView questionText = (TextView) view.findViewById(R.id.quizProblem);
        TextView questionAnswer = (TextView) view.findViewById(R.id.quizQuestionSubmitted);

        questionNumber.setText(Integer.toString(i + 1));
        questionText.setText(questions.get(i).toString());

        if(questions.get(i).submittedAnswer != -1)
        {
            questionAnswer.setText(Integer.toString(questions.get(i).submittedAnswer));
        }
        else
        {
            questionAnswer.setText("No Answer");
        }

        return view;
    }
}
