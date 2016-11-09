package swen_anigans.mathematicfanatic;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListAdapter;

import java.util.ArrayList;

/**
 * Created by Sean on 11/8/2016.
 */
public class GuardianSettingsAdapter extends BaseAdapter implements ListAdapter {

    // Update this so that the "String" is the student class
    private ArrayList<String> list = new ArrayList<>();
    private Context context;

    public GuardianSettingsAdapter(ArrayList<String> list, Context context)
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
            view = inflater.inflate(R.layout.guardian_settings_buttons, null);
        }

        // Edit the text on the button
        Button studentButton = (Button)view.findViewById(R.id.student_settings_button);
        studentButton.setText(list.get(i));

        // Add button listeners here
        ImageButton deleteButton = (ImageButton) view.findViewById(R.id.remove_student_button);

        deleteButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                list.remove(i);
                notifyDataSetChanged();
            }
        });

        return view;
    }
}
