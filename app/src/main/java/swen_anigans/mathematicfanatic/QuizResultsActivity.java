package swen_anigans.mathematicfanatic;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class QuizResultsActivity extends AppCompatActivity {

    public final static String SELECTED = "selected";
    private static int videoId;
    private static boolean played = false;

    private static String picked = null;

    private static String[] rewardNames;
    private static TextView pickedTV;

    private static String[] videoCategories = {
            "Magic",
            "Animals",
            "Medieval Times",
            "Construction",
            "Superheroes",
            "Minecraft"};
    private static String[] videoIds = {
            "9HGfJhPqdBk",
            "T7HGSvczDA4",
            "sCywdHNummE",
            "87I82q3OpaU",
            "lFtYgj5Lt6Q",
            "apDWFOpLZiU",};

    private enum Star {
        FULL (2),
        HALF (1),
        EMPTY (0);
        private int value;
        Star(int val) {
            this.value = val;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_results);
    //get info we need (thanks Grant!)
        Intent intent = getIntent();
        int total = intent.getIntExtra("numberTotal",21);
        int correct = intent.getIntExtra("numberCorrect",13);

        ((TextView)findViewById(R.id.correct)).setText(java.lang.Integer.toString(correct));
        ((TextView)findViewById(R.id.total)).setText(java.lang.Integer.toString(total));

        //get ImageViews
        ViewGroup starGroup = (ViewGroup) findViewById(R.id.stars);
        int starCount = starGroup.getChildCount();
        ImageView[] starViews = getStarViews(starGroup,starCount);

        Star[] starValues = getStars(correct,total,starGroup.getChildCount());
        setStars(starViews,starValues);

        setRewardListeners();

        // Back Button things
        Toolbar toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        toolbar.setBackgroundResource(R.color.quizPrimary);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private LinearLayout formRewardSelectedLayout(LinearLayout ll, String r) {
        LinearLayout.LayoutParams lp;
        videoId = Arrays.asList(videoCategories).indexOf(r);
        pickedTV.setText(r);
        ll.addView(pickedTV);

        Button b1 = new Button(this);
        lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,0,2);
        lp.setMargins(100,100,100,10);
        b1.setLayoutParams(lp);
        b1.setText("View Now");
        b1.setTextSize(35);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent lockerIntent = new Intent(QuizResultsActivity.this, StudentLocker.class);
                Intent vidIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:"+videoIds[videoId]));
                vidIntent.putExtra("force_fullscreen", true);
                vidIntent.putExtra("finish_on_ended", true);
                Intent[] intents = {lockerIntent, vidIntent};
                startActivities(intents);
            }
        });
        b1.setId(View.generateViewId());
        ll.addView(b1);

        Button b2 = new Button(this);
        lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,0,3);
        lp.setMargins(100,100,100,200);
        b2.setLayoutParams(lp);
        b2.setText("Return To My Locker");
        b2.setTextSize(35);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(QuizResultsActivity.this, StudentLocker.class);
                startActivity(intent);
            }
        });
        b2.setId(View.generateViewId());
        ll.addView(b2);
        return ll;
    }

    private void setRewardListeners() {
        ViewGroup rewardGroup = (ViewGroup) findViewById(R.id.slideuppanel);

        // grab all the interests of the student.
        Set<Interest> interests = new HashSet<>(Arrays.asList(DataManager.getInstance().curStudent.interests));

        // create a button for each unique interest
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);


        for(Interest i: interests) {
            View view = inflater.inflate(R.layout.reward_button, null);
            Button temp = (Button)view.findViewById(R.id.reward_button);
            temp.setText(i.toString());
            temp.setOnClickListener(new Button.OnClickListener() {
                @Override
                public void onClick(View v) {
                    LinearLayout slideUp = (LinearLayout) ((Button) v).getParent();
                    slideUp.removeAllViews();
                    picked = ((Button)v).getText().toString();
                    formRewardSelectedLayout(slideUp,picked);
                }
            });
            rewardGroup.addView(view);
        }
    }


    private ImageView[] getStarViews(ViewGroup starGroup, int starCount) {
        ImageView[] starViews = new ImageView[starCount];
        for (int i=0 ; i<starCount ; i++) {
            starViews[i] = (ImageView) starGroup.getChildAt(i);
        }
        return starViews;
    }

    private Star[] getStars(int corrects, int totals, int starCount){

        Star[] toReturn = new Star[starCount];
        double starNum = ((double)corrects) / ((double)totals) * starCount;
        for (int i=0 ; i<starCount ; i++) {
            if(starNum >= 1) {
                toReturn[i] = Star.FULL;
            } else if(starNum >= 0.5){
                toReturn[i] = Star.HALF;
            } else {
                toReturn[i] = Star.EMPTY;
            }
            starNum -= 1;
        }
        return toReturn;
    }

    private void setStars(ImageView[] starViews, Star[] starValues){
        Drawable[] starIcons = new Drawable[3];
        starIcons[2] = ResourcesCompat.getDrawable(getResources(),R.drawable.ic_star,null);
        starIcons[1] = ResourcesCompat.getDrawable(getResources(),R.drawable.ic_star_half,null);
        starIcons[0] = ResourcesCompat.getDrawable(getResources(),R.drawable.ic_star_border,null);
        for (int i=0 ; i<starViews.length ; i++) {
            int iconVal = starValues[i].value;
            starViews[i].setImageDrawable(starIcons[iconVal]);
        }
    }

}
