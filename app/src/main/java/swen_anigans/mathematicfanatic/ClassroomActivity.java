package swen_anigans.mathematicfanatic;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class ClassroomActivity extends AppCompatActivity {
    int a = 1;
    int b = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.ClssroomTheme);
        setContentView(R.layout.activity_classroom);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setBackgroundResource(R.color.classroomPrimary);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);


    }

    public void goToHelp(View view) {
        Intent intent = new Intent(this, activity_help.class);
        int[] temp = {a,b};
        intent.putExtra("abValues",temp);
        startActivity(intent);
    }
}
