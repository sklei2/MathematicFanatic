package swen_anigans.mathematicfanatic;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.droidbyme.toastlib.ToastLib;

public class StudentSettings extends AppCompatActivity {

    private student current;
    private TextView toolbarText;
    private EditText nameText;
    private EditText lowRangeText;
    private EditText highRangeText;
    private Spinner concepts;
    private Spinner interest1;
    private Spinner interest2;
    private Spinner interest3;

    private Class<?> parent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.GuardianTheme);
        setContentView(R.layout.activity_student_settings);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        myToolbar.setBackgroundResource(R.color.guardianPrimary);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        try
        {
            parent = Class.forName(getIntent().getStringExtra("parent"));
        }
        catch(Exception e)
        {

        }

        toolbarText = (TextView) findViewById(R.id.toolbar_text);
        nameText = (EditText) findViewById(R.id.student_name_text);
        lowRangeText = (EditText) findViewById(R.id.low_range);
        highRangeText = (EditText) findViewById(R.id.high_range);
        concepts = (Spinner) findViewById(R.id.learning_concepts_dropdown);
        interest1 = (Spinner) findViewById(R.id.interests_1_dropdown);
        interest2 = (Spinner) findViewById(R.id.interests_2_dropdown);
        interest3 = (Spinner) findViewById(R.id.interests_3_dropdown);

        setDropdowns();
        setStudentSettings();
    }

    private void setDropdowns()
    {
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

    private void setStudentSettings()
    {
        current = DataManager.getInstance().curStudent;
        if(current != null)
        {
            // set the text of the toolbar
            toolbarText.setText(current.name);

            // Now it is time to initialize all the settings with the current.
            nameText.setText(current.name);
            lowRangeText.setText(String.valueOf(current.rangeMin));
            highRangeText.setText(String.valueOf(current.rangeMax));
            concepts.setSelection(current.learningType.ordinal());
            interest1.setSelection(current.interests[0].ordinal());
            interest2.setSelection(current.interests[1].ordinal());
            interest3.setSelection(current.interests[2].ordinal());
        }
        else
        {
            // set the text of the toolbar to be "New Student"
            toolbarText.setText("New Student");
        }
    }

    public void Save(View view)
    {
        if(current == null)
        {
            createNewStudent();
        }
        else
        {
            saveStudent();
        }
    }

    private void createNewStudent()
    {
        try
        {
            // Get all the values from the form.
            String name = nameText.getText().toString();
            int lowRange = Integer.valueOf(lowRangeText.getText().toString());
            int highRange = Integer.valueOf(highRangeText.getText().toString());

            // Convert each of the strings into something that we'll see in the enum
            String interest1String = interest1.getSelectedItem().toString().toUpperCase();
            interest1String = interest1String.replaceAll("\\s", "_");
            String interest2String = interest2.getSelectedItem().toString().toUpperCase();
            interest2String = interest2String.replaceAll("\\s", "_");
            String interest3String = interest3.getSelectedItem().toString().toUpperCase();
            interest3String = interest3String.replaceAll("\\s", "_");
            String conceptString = concepts.getSelectedItem().toString().toUpperCase();
            conceptString = conceptString.replaceAll("\\s", "_");

            // get the enums
            Interest interest_1 = Interest.valueOf(interest1String);
            Interest interest_2 = Interest.valueOf(interest2String);
            Interest interest_3 = Interest.valueOf(interest3String);
            LearningType concept = LearningType.valueOf(conceptString);

            // create student and add to manager
            student newStudent = new student(name, lowRange, highRange, interest_1, interest_2, interest_3, concept);
            DataManager.getInstance().addStudent(newStudent);
            onBackPressed();
            ToastLib.success(this, "Student Created", Typeface.create("Helvetica", 0));
        }
        catch(Exception e)
        {
            ToastLib.error(this, "Invalid Values in the form", Typeface.create("Helvetica", 0));
        }
    }

    private void saveStudent()
    {
        try
        {
            // Get all the values from the form.
            String name = nameText.getText().toString();
            int lowRange = Integer.valueOf(lowRangeText.getText().toString());
            int highRange = Integer.valueOf(highRangeText.getText().toString());

            // Convert each of the strings into something that we'll see in the enum
            String interest1String = interest1.getSelectedItem().toString().toUpperCase();
            interest1String = interest1String.replaceAll("\\s", "_");
            String interest2String = interest2.getSelectedItem().toString().toUpperCase();
            interest2String = interest2String.replaceAll("\\s", "_");
            String interest3String = interest3.getSelectedItem().toString().toUpperCase();
            interest3String = interest3String.replaceAll("\\s", "_");
            String conceptString = concepts.getSelectedItem().toString().toUpperCase();
            conceptString = conceptString.replaceAll("\\s", "_");

            // get the enums
            Interest interest_1 = Interest.valueOf(interest1String);
            Interest interest_2 = Interest.valueOf(interest2String);
            Interest interest_3 = Interest.valueOf(interest3String);
            LearningType concept = LearningType.valueOf(conceptString);

            // update the current student
            Interest[] interests = {interest_1, interest_2, interest_3};

            current.name = name;
            current.rangeMin = lowRange;
            current.rangeMax = highRange;
            current.interests = interests;
            current.learningType = concept;

            onBackPressed();
            ToastLib.success(this, "Save Successful", Typeface.create("Helvetica", 0));
        }
        catch(Exception e)
        {
            ToastLib.error(this, "Invalid Values in the form", Typeface.create("Helvetica", 0));
        }
    }

    @Override
    public void onBackPressed()
    {
        Intent intent = new Intent(this, parent);
        startActivity(intent);
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
