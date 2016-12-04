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

    private String equationString = "Think about this picture!";
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

        // show "think about it" text in thought cloud.
        TextView equationView = (TextView) findViewById(R.id.equationView);
        equationString = DataManager.getInstance().supplier.getItemText(b).replaceAll("_", a.toString());
        equationView.setText(equationString);
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
