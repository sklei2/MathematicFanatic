package swen_anigans.mathematicfanatic;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setTheme(R.style.HomeTheme);
        setContentView(R.layout.activity_main);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        myToolbar.setBackgroundResource(R.color.homePrimary);
        setSupportActionBar(myToolbar);

        // Grab the listview
        ListView studentButtons = (ListView)findViewById(R.id.student_buttons);

        // Create the adapter
        MainPageAdapter adapter = new MainPageAdapter(DataManager.getInstance().students, this);

        // add the adapter to the list
        studentButtons.setAdapter(adapter);

        // Make sure that the DataManager is initialized to have no current use
        DataManager.getInstance().curStudent = null;
    }

    public void ToSettings(View view)
    {
        Intent intent = new Intent(this, GuardianSettings.class);
        startActivity(intent);
    }

    public void ToLocker(View view)
    {
        // Grab the button's text
        Button clickedButton = (Button) view;
        String studentText = clickedButton.getText().toString();

        // This student text would then be used to set the current
        // student from our "database"
        DataManager.getInstance().setCurStudent(studentText);

        // Then jump on over to the locker page!
        Intent intent = new Intent(this, StudentLocker.class);
        startActivity(intent);
    }

    public void NewStudent(View view)
    {
        Intent intent = new Intent(this, StudentSettings.class);
        intent.putExtra("parent", "swen_anigans.mathematicfanatic.MainActivity");
        startActivity(intent);
    }
}
