package swen_anigans.mathematicfanatic;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Dictionary;
import java.util.HashMap;
import java.util.Map;

import static android.R.attr.id;

public class activity_help extends AppCompatActivity {

    private String equationString = "2 x 2 = 4";
    private Integer a = 1;
    private Integer b = 1;
    private Integer c = 1;

    private static final Map<Integer, Integer> threes;
    static
    {
        threes = new HashMap<Integer, Integer>();
        threes.put(1,R.drawable.three_01);
        threes.put(2,R.drawable.three_02);
        threes.put(3,R.drawable.three_03);
        threes.put(4,R.drawable.three_04);
        threes.put(5,R.drawable.three_05);
        threes.put(6,R.drawable.three_06);
        threes.put(7,R.drawable.three_07);
        threes.put(8,R.drawable.three_08);
        threes.put(9,R.drawable.three_09);
        threes.put(10,R.drawable.three_10);
        threes.put(11,R.drawable.three_11);
        threes.put(12,R.drawable.three_12);
    }

    private static final Map<Integer, Integer> eights;
    static
    {
        eights = new HashMap<Integer, Integer>();
        eights.put(1,R.drawable.eight_01);
        eights.put(2,R.drawable.eight_02);
        eights.put(3,R.drawable.eight_03);
        eights.put(4,R.drawable.eight_04);
        eights.put(5,R.drawable.eight_05);
        eights.put(6,R.drawable.eight_06);
        eights.put(7,R.drawable.eight_07);
        eights.put(8,R.drawable.eight_08);
        eights.put(9,R.drawable.eight_09);
        eights.put(10,R.drawable.eight_10);
        eights.put(11,R.drawable.eight_11);
        eights.put(12,R.drawable.eight_12);
    }


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
        int[] values = intent.getIntArrayExtra("ab");
        a = values[0];
        b = values[1];

        // Set ImageView visualHelp based on values a and b
        Integer resID = 0;
        if (b == 3)
            resID = threes.get(a);
        else if (b == 8)
            resID = eights.get(a);
        else
            resID = R.drawable.image_not_found;


        ImageView visualHelp = (ImageView)findViewById(R.id.visualization);
        visualHelp.setImageResource(resID);

        // Calculate a*b and set string to display equation in Thought Cloud image
        c = a*b;
        equationString = a.toString() + " x " + b.toString() + " = " + c.toString();
        TextView equationView = (TextView) findViewById(R.id.equationView);
        equationView.setText(equationString);

    }

}
