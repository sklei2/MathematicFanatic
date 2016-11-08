package swen_anigans.mathematicfanatic;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setTheme(R.style.HomeTheme);
        setContentView(R.layout.activity_main);
        Toolbar myToolbar =(Toolbar) findViewById(R.id.my_toolbar);
        myToolbar.setBackgroundResource(R.color.homePrimary);
        setSupportActionBar(myToolbar);


        LinearLayout studentAndProgressButtons = (LinearLayout)findViewById(R.id.StudentButtons);
        studentAndProgressButtons.setOrientation(LinearLayout.VERTICAL);

        // TODO: Determine where we're getting the data for the
        //It will probably be something like....
        //for(Student student: StudentManager.getAllStudents())
        //{
        Button studentButton = new Button(this);
        Button progressReportButton = new Button(this);
        // replace the hardcoded text with the name of the student
        studentButton.setBackgroundResource(R.drawable.long_button);
        studentButton.setTextSize(15);
        studentButton.setText("Student X");

        progressReportButton.setBackgroundResource(R.drawable.short_button);
        progressReportButton.setTextSize(15);
        progressReportButton.setText(R.string.progress_report_button);

        // This is where we would probably hook up the button's intent

        // Create the linear layout and the padding
        LinearLayout buttons = new LinearLayout(this);
        buttons.setPaddingRelative(40, 60, 40, 0);
        buttons.setOrientation(LinearLayout.HORIZONTAL);

        // Bundle the buttons
        buttons.addView(studentButton);
        buttons.addView(progressReportButton);

        // Show in the view
        studentAndProgressButtons.addView(buttons);

        //}

    }
}
