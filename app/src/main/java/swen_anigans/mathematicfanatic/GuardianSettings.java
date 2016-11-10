package swen_anigans.mathematicfanatic;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;

public class GuardianSettings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.GuardianTheme);
        setContentView(R.layout.activity_guardian_settings);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        myToolbar.setBackgroundResource(R.color.guardianPrimary);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ArrayList<String> list = new ArrayList<String>();
        list.add("student 1");
        list.add("student 2");

        // create adapter class that will deal with all the student shenanigans
        GuardianSettingsAdapter adapter = new GuardianSettingsAdapter(list, this);

        // set the list view adapter
        ListView studentList = (ListView) findViewById(R.id.student_list_view);
        studentList.setAdapter(adapter);
    }
}
