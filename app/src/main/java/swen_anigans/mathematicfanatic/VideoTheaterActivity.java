package swen_anigans.mathematicfanatic;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class VideoTheaterActivity extends AppCompatActivity {
    private int selected;

    String[] videoCategories = {null,
                                "Magic",
                                "Animals",
                                "Medieval Times",
                                "Construction",
                                "Superheroes",
                                "Minecraft"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_theater);
        Intent intent = getIntent();
        selected = intent.getIntExtra(QuizResultsActivity.SELECTED,9); // 0 = none selected
        ((TextView) findViewById(R.id.selected)).setText(""+selected);
    }
}