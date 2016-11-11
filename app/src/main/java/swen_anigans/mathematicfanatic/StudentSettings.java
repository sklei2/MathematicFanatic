package swen_anigans.mathematicfanatic;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

public class StudentSettings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.GuardianTheme);
        setContentView(R.layout.activity_student_settings);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        myToolbar.setBackgroundResource(R.color.guardianPrimary);
        setSupportActionBar(myToolbar);

        // This is where we would set the text on the toolbar
        // to match the student's name or new student if it is a new one.
        TextView toolbarText = (TextView) findViewById(R.id.toolbar_text);
        toolbarText.setText("New Student or Name");

        // Make it so that the image button next to the spinner also
        // drops the menu.
        final Spinner learningConcepts = (Spinner) findViewById(R.id.learning_concepts_dropdown);
        final Spinner interests1_dropdown = (Spinner) findViewById(R.id.interests_1_dropdown);
        final Spinner interests2_dropdown = (Spinner) findViewById(R.id.interests_2_dropdown);
        final Spinner interests3_dropdown = (Spinner) findViewById(R.id.interests_3_dropdown);

        ImageButton learningConceptsButton = (ImageButton) findViewById(R.id.learning_concepts_arrow);
        ImageButton interests1_button = (ImageButton) findViewById(R.id.interests_1_arrow);
        ImageButton interests2_button = (ImageButton) findViewById(R.id.interests_2_arrow);
        ImageButton interests3_button = (ImageButton) findViewById(R.id.interests_3_arrow);

        // The actual listeners here.

        learningConceptsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                learningConcepts.performClick();
            }
        });

        interests1_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                interests1_dropdown.performClick();
            }
        });

        interests2_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                interests2_dropdown.performClick();
            }
        });

        interests3_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                interests3_dropdown.performClick();
            }
        });



    }
}
