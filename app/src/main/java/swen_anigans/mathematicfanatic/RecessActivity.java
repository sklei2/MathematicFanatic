package swen_anigans.mathematicfanatic;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

public class RecessActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recess);
        ViewGroup vg = (ViewGroup) findViewById(R.id.activity_recess);
        Button ml = (Button) findViewById(R.id.medallibrary);
        ml.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RecessActivity.this,VideoTheaterActivity.class);
                intent.putExtra("selected",1);
                startActivity(intent);
            }
        });

        Button vt = (Button) findViewById(R.id.videotheater);
        vt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RecessActivity.this,VideoTheaterActivity.class);
                intent.putExtra("selected",2);
                startActivity(intent);
            }
        });

        // Back Button things
        Toolbar toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        toolbar.setBackgroundResource(R.color.recessPrimary);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
