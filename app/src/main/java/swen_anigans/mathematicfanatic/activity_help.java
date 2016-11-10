package swen_anigans.mathematicfanatic;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class activity_help extends AppCompatActivity {

    private String equationString = "2 x 2 = 4";
    private Integer a = 1;
    private Integer b = 1;
    private Integer c = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        // Back Button things
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

        TextView equationView = (TextView) findViewById(R.id.equationView);

        c = a*b;
        equationString = a.toString() + " x " + b.toString() + " = " + c.toString();

        equationView.setText(equationString);

    }

}
