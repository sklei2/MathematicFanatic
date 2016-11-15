package swen_anigans.mathematicfanatic;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;


public class activity_help extends AppCompatActivity {

    private String equationString = "2 x 2 = 4";
    private Integer a = 1;
    private Integer b = 1;
    private Integer c = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.HelpTheme);
        setContentView(R.layout.activity_help);

        // Back Button things
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setBackgroundResource(R.color.helpPrimary);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

        // Retrieve values a and b from last Activity Intent
        Intent intent = getIntent();
        int[] values = intent.getIntArrayExtra("abValues");
        a = values[0];
        b = values[1];

        // Set ImageView visualHelp based on values a and b
        Integer resID = DataManager.getInstance().supplier.getImageResource(a, b);

        ImageView visualHelp = (ImageView)findViewById(R.id.visualization);
        visualHelp.setImageResource(resID);

        // Calculate a*b and set string to display equation in Thought Cloud image
        c = a*b;
        equationString = a.toString() + " x " + b.toString() + " = " + c.toString();
        TextView equationView = (TextView) findViewById(R.id.equationView);
        equationView.setText(equationString);

        // Create explanation string
        String item = DataManager.getInstance().supplier.getItemText(b);
        String explanation = a.toString() + " bunches of " + b.toString() + " " + item + " gives you\n" + c.toString() + " " + item;
        TextView explanationView = (TextView) findViewById(R.id.visualDescription);
        explanationView.setText(explanation);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home)
        {
            onBackPressed();
            return true;
        }
        return false;
    }

}
