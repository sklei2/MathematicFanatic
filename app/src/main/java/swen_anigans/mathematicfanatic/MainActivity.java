package swen_anigans.mathematicfanatic;

import android.content.Intent;
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

        // this would be replaced with the list from the singleton
        ArrayList<String> list = new ArrayList<>();
        list.add("Student 1");
        list.add("Student 2");
        list.add("Student 3");

        // Create the adapter
        MainPageAdapter adapter = new MainPageAdapter(list, this);

        // add the adapter to the list
        studentButtons.setAdapter(adapter);
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

        // Then jump on over to the locker page!
        Intent intent = new Intent(this, StudentLocker.class);
        startActivity(intent);
    }
}
