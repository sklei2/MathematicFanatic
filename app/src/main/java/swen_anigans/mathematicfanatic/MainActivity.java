package swen_anigans.mathematicfanatic;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;
import android.view.View;
import android.widget.EditText;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

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

        // Create the adapter
        MainPageAdapter adapter = new MainPageAdapter(list, this);

        // add the adapter to the list
        studentButtons.setAdapter(adapter);
    }

}
