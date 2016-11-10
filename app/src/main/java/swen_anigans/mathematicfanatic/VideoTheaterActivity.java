package swen_anigans.mathematicfanatic;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.widget.SlidingPaneLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Space;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.Arrays;

public class VideoTheaterActivity extends AppCompatActivity {
    private int selected;

    private static String[] videoCategories = {
            "Magic",
            "Animals",
            "Medieval Times",
            "Construction",
            "Superheroes",
            "Minecraft"};
    private static String[] videoIds = {
            "9HGfJhPqdBk",
            "DR8SJqdknO8",
            "sCywdHNummE",
            "BMbDhwB1RjI",
            "SXZ3qWAvbVs",
            "apDWFOpLZiU",};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_theater);
        Intent intent = getIntent();
        selected = intent.getIntExtra(QuizResultsActivity.SELECTED,-1); // -1 = none selected


        formButtons((ViewGroup) findViewById(R.id.vidsGroup));
    }
    private void formButtons(ViewGroup vg) {
        int count = videoIds.length;
        Button temp;
        Space space = null;
        RelativeLayout.LayoutParams lpB;
        RelativeLayout.LayoutParams lpS;
        Button previous = null;
        for(int i=0 ; i < count ; i++) {

            space = new Space(this);
            space.setId(View.generateViewId());
            temp = new Button(this);
            lpB = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
            lpS = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, 20);
            if(previous == null) {
                lpS.addRule(RelativeLayout.ALIGN_PARENT_TOP);
            } else {
                lpS.addRule(RelativeLayout.BELOW, previous.getId());
            }
            lpS.addRule(RelativeLayout.BELOW, space.getId());
            lpS.addRule(RelativeLayout.START_OF, space.getId());
            lpB.addRule(RelativeLayout.END_OF, space.getId());
            space.setLayoutParams(lpS);
            temp.setLayoutParams(lpB);
            temp.setText(videoCategories[i]);
            temp.setTextSize(35);
            temp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    launchVideo(((Button)v).getText().toString());
                }
            });
            temp.setId(View.generateViewId());
            vg.addView(space);
            vg.addView(temp);
            previous = temp;
        }

    }

    private void launchVideo(String catagory) {
        int id = Arrays.asList(videoCategories).indexOf(catagory);
        Intent vidIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:"+videoIds[id]));
        vidIntent.putExtra("force_fullscreen", true);
        vidIntent.putExtra("finish_on_ended", true);
        startActivity(vidIntent);
    }
}