package swen_anigans.mathematicfanatic;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
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

        DataManager.getInstance().curStudent = null;

        // create adapter class that will deal with all the student shenanigans
        GuardianSettingsAdapter adapter = new GuardianSettingsAdapter(DataManager.getInstance().students, this);

        // set the list view adapter
        ListView studentList = (ListView) findViewById(R.id.student_list_view);
        studentList.setAdapter(adapter);
    }

    public void ToStudentSettings(View view)
    {
        Button clickedButton = (Button)view;
        String studentName = clickedButton.getText().toString();

        DataManager.getInstance().setCurStudent(studentName);

        Intent intent = new Intent(this, StudentSettings.class);
        startActivity(intent);
    }

    public void NewStudent(View view)
    {
        Intent intent = new Intent(this, StudentSettings.class);
        intent.putExtra("parent", "swen_anigans.mathematicfanatic.GuardianSettings");
        startActivity(intent);
    }

}
