package swen_anigans.mathematicfanatic;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

public class StudentSettings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.GuardianTheme);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        myToolbar.setBackgroundResource(R.color.guardianPrimary);
        setSupportActionBar(myToolbar);
        setContentView(R.layout.activity_student_settings);

        // This is where we would set the text on the toolbar
        // to match the student's name
        TextView toolbarText = (TextView) findViewById(R.id.toolbar_text);
        toolbarText.setText("{STUDENT_NAME}");




    }
}
