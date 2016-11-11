package swen_anigans.mathematicfanatic;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListAdapter;

import java.util.ArrayList;

/**
 * Created by Sean on 11/8/2016.
 */
public class MainPageAdapter extends BaseAdapter implements ListAdapter {

    // Change the string to be the student class
    private ArrayList<student> list = new ArrayList<>();
    private Context context;

    public MainPageAdapter(ArrayList<student> list, Context context)
    {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup)
    {
        if(view == null)
        {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.mainpage_student_buttons, null);
        }

        // Grab the buttons
        Button studentButton = (Button) view.findViewById(R.id.student_button);
        Button progressReportButton = (Button) view.findViewById(R.id.progress_report_button);

        // Set the text
        studentButton.setText(list.get(i).name);

        return view;
    }
}
