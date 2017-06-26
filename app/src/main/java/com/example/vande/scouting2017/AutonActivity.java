package com.example.vande.scouting2017;

import android.os.Environment;

import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.view.View.OnClickListener;




public class AutonActivity extends AppCompatActivity {

    public String heading, auton, message;

    public TextInputLayout
    teamNumberInputLayout,
    matchNumberInputLayout,
    autonHighFuelScoredInputLayout,
    autonHighFuelMissedInputLayout,
    autonLowFuelInputLayout;

    public EditText teamNumber_input,
            matchNumber_input,
            autonHighFuelScored_input,
            autonHighFuelMissed_input,
            autonLowFuel_input;

    public RadioGroup startingLocation_RadiobtnGrp,
            baseLine_RadiobtnGrp,
            autonGear_RadiobtnGrp;

    RadioButton startingLocation_Radiobtn, baseline_Radiobtn, autonGear_Radiobtn;

    public Button nextButton;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auton);
        //Numeric data field
        teamNumberInputLayout = (TextInputLayout) findViewById(R.id.TeamNumber_input_layout);
        matchNumberInputLayout = (TextInputLayout) findViewById(R.id.MatchNumber_input_layout);
        autonHighFuelScoredInputLayout = (TextInputLayout) findViewById(R.id.AutonHighFuelScored_input_layout);
        autonHighFuelMissedInputLayout = (TextInputLayout) findViewById(R.id.AutonHighFuelMissed_input_layout);
        autonLowFuelInputLayout = (TextInputLayout) findViewById(R.id.AutonLowFuel_input_layout);



        teamNumber_input = (EditText) findViewById(R.id.TeamNumber_input);
        matchNumber_input = (EditText) findViewById(R.id.MatchNumber_input);
        autonHighFuelScored_input = (EditText) findViewById(R.id.AutonHighFuelScored_input);
        autonHighFuelMissed_input = (EditText) findViewById(R.id.AutonHighFuelMissed_input);
        autonLowFuel_input = (EditText) findViewById(R.id.AutonLowFuel_input);



        //Radio button Groups
        startingLocation_RadiobtnGrp = (RadioGroup)findViewById(R.id.StartingLocation_RadiobtnGrp);
        baseLine_RadiobtnGrp = (RadioGroup)findViewById(R.id.BaseLine_RadiobtnGrp);
        autonGear_RadiobtnGrp = (RadioGroup)findViewById(R.id.AutonGear_RadiobtnGrp);

    }



    public void moveToTeleop(View view) {
        final Context context = this;

        //Auton Get current standing of radio buttons
        int selectedStartingLocation, selectedBaseline, selectedAutonGear;

        if(!checkTeamNumber()) {return;}
        if(!checkMatchNumber()) {return;}
        if(!checkHighFuelScored()) {return;}
        if(!checkHighFuelMissed()) {return;}
        if(!autonLowFuel()) {return;}


        teamNumberInputLayout.setErrorEnabled(false);
        matchNumberInputLayout.setErrorEnabled(false);
        autonHighFuelScoredInputLayout.setErrorEnabled(false);
        autonHighFuelMissedInputLayout.setErrorEnabled(false);
        autonLowFuelInputLayout.setErrorEnabled(false);


        selectedStartingLocation = startingLocation_RadiobtnGrp.getCheckedRadioButtonId();
        selectedBaseline = baseLine_RadiobtnGrp.getCheckedRadioButtonId();
        selectedAutonGear = autonGear_RadiobtnGrp.getCheckedRadioButtonId();


        startingLocation_Radiobtn = (RadioButton) findViewById(selectedStartingLocation);
        baseline_Radiobtn = (RadioButton) findViewById(selectedBaseline);
        autonGear_Radiobtn = (RadioButton) findViewById(selectedAutonGear);
        //compile string of all data
        auton = teamNumber_input.getText().toString() + "," +
                matchNumber_input.getText().toString()+","+
                startingLocation_Radiobtn.getText()+","+
                baseline_Radiobtn.getText()+","+
                autonGear_Radiobtn.getText()+","+
                autonHighFuelScored_input.getText().toString()+","+
                autonHighFuelMissed_input.getText().toString()+","+
                autonLowFuel_input.getText().toString();




            Intent intent = new Intent(context, TeleopActivity.class);
                intent.putExtra("auton", auton);
            startActivity(intent);



    }

    private boolean checkTeamNumber() {
        if (teamNumber_input.getText().toString().trim().isEmpty()) {
            teamNumber_input.setError("Enter a Team Number");
            requestFocus(teamNumber_input);
            return false;
        }else {
            teamNumberInputLayout.setErrorEnabled(false);
            return true;
        }
    }

    private boolean checkMatchNumber(){
        if(matchNumber_input.getText().toString().trim().isEmpty()) {
            matchNumberInputLayout.setError("Enter Match Number");
            requestFocus(matchNumber_input);
            return false;
        }else {
            matchNumberInputLayout.setErrorEnabled(false);
            return true;
        }
    }

    private boolean checkHighFuelScored(){
        if(autonHighFuelScored_input.getText().toString().trim().isEmpty()){
            autonHighFuelScoredInputLayout.setError("Enter Fuel Count");
            requestFocus(autonHighFuelScored_input);
            return false;
        }else{
            autonHighFuelScoredInputLayout.setErrorEnabled(false);
            return true;
        }
    }

    private boolean checkHighFuelMissed(){
        if(autonHighFuelMissed_input.getText().toString().trim().isEmpty()){
            autonHighFuelMissedInputLayout.setError("Enter Fuel Count");
            requestFocus(autonHighFuelMissed_input);
            return false;
        }else{
            autonHighFuelMissedInputLayout.setErrorEnabled(false);
            return true;
        }
    }

    private boolean autonLowFuel(){
        if(autonLowFuel_input.getText().toString().trim().isEmpty()){
            autonLowFuelInputLayout.setError("Enter Fuel Count");
            requestFocus(autonLowFuel_input);
            return false;
        }else{
            autonLowFuelInputLayout.setErrorEnabled(false);
            return true;
        }
    }


    private void requestFocus(View view){
        if(view.requestFocus()){
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

}
