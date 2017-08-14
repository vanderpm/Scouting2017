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

import butterknife.BindView;
import butterknife.ButterKnife;


public class AutonActivity extends AppCompatActivity {

    public String heading, auton, message;

    @BindView(R.id.teamNumber_input_layout)
    public TextInputLayout teamNumberInputLayout;

    @BindView(R.id.matchNumber_input_layout)
    public TextInputLayout matchNumberInputLayout;

    @BindView(R.id.autonHighFuelScored_input_layout)
    public TextInputLayout autonHighFuelScoredInputLayout;

    @BindView(R.id.autonHighFuelMissed_input_layout)
    public TextInputLayout autonHighFuelMissedInputLayout;

    @BindView(R.id.autonLowFuel_input_layout)
    public TextInputLayout autonLowFuelInputLayout;

    @BindView(R.id.teamNumber_input)
    public EditText teamNumberInput;

    @BindView(R.id.matchNumber_input)
    public EditText matchNumberInput;

    @BindView(R.id.autonHighFuelScored_input)
    public EditText autonHighFuelScoredInput;

    @BindView(R.id.autonHighFuelMissed_input)
    public EditText autonHighFuelMissedInput;

    @BindView(R.id.autonLowFuel_input)
    public EditText autonLowFuelInput;

    @BindView(R.id.startingLocation_RadiobtnGrp)
    public RadioGroup startingLocationRadiobtnGrp;

    @BindView(R.id.baseLine_RadiobtnGrp)
    public RadioGroup baseLineRadiobtnGrp;

    @BindView(R.id.autonGear_RadiobtnGrp)
    public RadioGroup autonGearRadiobtnGrp;

    @BindView(R.id.autonGearSuccess_chkbx)
    public CheckBox autonGearSuccessChkbx;

    @BindView(R.id.activatedHopper_chkbx)
    public CheckBox activatedHopperChkbx;


    RadioButton startingLocation_Radiobtn, baseline_Radiobtn, autonGear_Radiobtn;

    @BindView(R.id.next_button)
    public Button nextButton;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_auton);


        ButterKnife.bind(this);
        //Save Data button

    }



    public void moveToTeleop(View view) {
        final Context context = this;

        //Auton Get current standing of radio buttons
        int selectedStartingLocation, selectedBaseline, selectedAutonGear;
        String autonGearSuccessString;
        String activatedHopperString;

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


        selectedStartingLocation = startingLocationRadiobtnGrp.getCheckedRadioButtonId();
        selectedBaseline = baseLineRadiobtnGrp.getCheckedRadioButtonId();
        selectedAutonGear = autonGearRadiobtnGrp.getCheckedRadioButtonId();

        if(autonGearSuccessChkbx.isChecked()){
            autonGearSuccessString = "true";
        }else
            autonGearSuccessString = "false";


        if(activatedHopperChkbx.isChecked()){
            activatedHopperString = "true";
        }else
            activatedHopperString = "false";



        startingLocation_Radiobtn = (RadioButton) findViewById(selectedStartingLocation);
        baseline_Radiobtn = (RadioButton) findViewById(selectedBaseline);
        autonGear_Radiobtn = (RadioButton) findViewById(selectedAutonGear);
        //compile string of all data
        auton = teamNumberInput.getText().toString() + "," +
                matchNumberInput.getText().toString()+","+
                startingLocation_Radiobtn.getText()+","+
                baseline_Radiobtn.getText()+","+
                autonGear_Radiobtn.getText()+","+
                autonGearSuccessString+","+
                autonHighFuelScoredInput.getText().toString()+","+
                autonHighFuelMissedInput.getText().toString()+","+
                autonLowFuelInput.getText().toString()+","+
                activatedHopperString;




            Intent intent = new Intent(context, TeleopActivity.class);
                intent.putExtra("auton", auton);
            startActivity(intent);



    }

    private boolean checkTeamNumber() {
        if (teamNumberInput.getText().toString().trim().isEmpty()) {
            teamNumberInput.setError("Enter a Team Number");
            requestFocus(teamNumberInput);
            return false;
        }else {
            teamNumberInputLayout.setErrorEnabled(false);
            return true;
        }
    }

    private boolean checkMatchNumber(){
        if(matchNumberInput.getText().toString().trim().isEmpty()) {
            matchNumberInput.setError("Enter Match Number");
            requestFocus(matchNumberInput);
            return false;
        }else {
            matchNumberInputLayout.setErrorEnabled(false);
            return true;
        }
    }

    private boolean checkHighFuelScored(){
        if(autonHighFuelScoredInput.getText().toString().trim().isEmpty()){
            autonHighFuelScoredInput.setError("Enter Fuel Count");
            requestFocus(autonHighFuelScoredInput);
            return false;
        }else{
            autonHighFuelScoredInputLayout.setErrorEnabled(false);
            return true;
        }
    }

    private boolean checkHighFuelMissed(){
        if(autonHighFuelMissedInput.getText().toString().trim().isEmpty()){
            autonHighFuelMissedInput.setError("Enter Fuel Count");
            requestFocus(autonHighFuelMissedInput);
            return false;
        }else{
            autonHighFuelMissedInputLayout.setErrorEnabled(false);
            return true;
        }
    }

    private boolean autonLowFuel(){
        if(autonLowFuelInput.getText().toString().trim().isEmpty()){
            autonLowFuelInput.setError("Enter Fuel Count");
            requestFocus(autonLowFuelInput);
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
