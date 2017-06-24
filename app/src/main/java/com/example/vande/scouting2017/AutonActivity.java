package com.example.vande.scouting2017;

import android.os.Environment;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
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

import java.io.File;

import java.io.FileOutputStream;

import java.io.IOException;



public class AutonActivity extends AppCompatActivity {

    public String heading, auton, teleop, message;

    public EditText teamNumber_input,
            matchNumber_input,
            autonHighFuelScored_input,
            autonHighFuelMissed_input,
            autonLowFuel_input;

    public RadioGroup startingLocation_RadiobtnGrp,
            baseLine_RadiobtnGrp,
            autonGear_RadiobtnGrp;
    public Button save_btn,
                    button;

    //Varibles from the Teleop activity
    public EditText teleopGearPlaced_input,
            teleopGearDropped_input,
            teleopHighFuelScored_input,
            teleopHighFuelMissed_input,
            teleopLowFuel_input,
            climbTime_input;

    public RadioGroup gearPlacement_RadiobtnGrp,
            fuelRetrieval_RadiobtnGrp,
            gearRetrieval_RadiobtnGrp,
            climbing_RadiobtnGrp,
            defense_RadiobtnGrp;
    public CheckBox fouls_chbx;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auton);
        //Numeric data field
        teamNumber_input = (EditText) findViewById(R.id.TeamNumber_input);
        matchNumber_input = (EditText) findViewById(R.id.MatchNumber_input);
        autonHighFuelScored_input = (EditText) findViewById(R.id.AutonHighFuelScored_input);
        autonHighFuelMissed_input = (EditText) findViewById(R.id.AutonHighFuelMissed_input);
        autonLowFuel_input = (EditText) findViewById(R.id.AutonLowFuel_input);
        //Radio button Groups
        startingLocation_RadiobtnGrp = (RadioGroup)findViewById(R.id.StartingLocation_RadiobtnGrp);
        baseLine_RadiobtnGrp = (RadioGroup)findViewById(R.id.BaseLine_RadiobtnGrp);
        autonGear_RadiobtnGrp = (RadioGroup)findViewById(R.id.AutonGear_RadiobtnGrp);
        //Save Data button
        save_btn = (Button) findViewById(R.id.Save_btn);

//        Teleop Data
        //Teleop Text/Number input
        teleopGearPlaced_input = (EditText) findViewById(R.id.TeleopGearPlaced_input);
        teleopGearDropped_input = (EditText) findViewById(R.id.TeleopGearDropped_input);
        teleopHighFuelScored_input = (EditText) findViewById(R.id.TeleopHighFuelScored_input);
        teleopHighFuelMissed_input = (EditText) findViewById(R.id.TeleopHighFuelMissed_input);
        teleopLowFuel_input = (EditText) findViewById(R.id.TeleopLowFuel_input);
        climbTime_input = (EditText) findViewById(R.id.ClimbTime_input);

        //Teleop Radio button Group
        gearPlacement_RadiobtnGrp = (RadioGroup)findViewById(R.id.GearPlacement_RadiobtnGrp);
        fuelRetrieval_RadiobtnGrp = (RadioGroup) findViewById(R.id.FuelRetrieval_RadiobtnGrp);
        gearRetrieval_RadiobtnGrp = (RadioGroup) findViewById(R.id.GearRetrieval_RadiobtnGrp);
        climbing_RadiobtnGrp = (RadioGroup) findViewById(R.id.Climbing_RadiobtnGrp);
        defense_RadiobtnGrp = (RadioGroup) findViewById(R.id.Defense_RadiobtnGrp);

        //Teleop Buttons
        fouls_chbx = (CheckBox) findViewById(R.id.Fouls_chkbx);


//        addListenerOnButton();
    }

    public void saveData(View view) throws IOException {

        String state;
        state = Environment.getExternalStorageState();

        if(Environment.MEDIA_MOUNTED.equals(state)){
            File Root = Environment.getExternalStorageDirectory();
            File Dir = new File(Root.getAbsoluteFile()+"/MyAppFile");

            //Auton Get current standing of radio buttons
            int selectedStartingLocation = startingLocation_RadiobtnGrp.getCheckedRadioButtonId();
            int selectedBaseline = baseLine_RadiobtnGrp.getCheckedRadioButtonId();
            int selectedAutonGear = autonGear_RadiobtnGrp.getCheckedRadioButtonId();
            RadioButton startingLocation_Radiobtn = (RadioButton) findViewById(selectedStartingLocation);
            RadioButton baseline_Radiobtn = (RadioButton) findViewById(selectedBaseline);
            RadioButton autonGear_Radiobtn = (RadioButton) findViewById(selectedAutonGear);


//            Teleop Current standing of radio buttons
            int selectedGearPlacement = gearPlacement_RadiobtnGrp.getCheckedRadioButtonId();
            int selectedFuelRetrieval = fuelRetrieval_RadiobtnGrp.getCheckedRadioButtonId();
            int selectedGearRetrieval = gearRetrieval_RadiobtnGrp.getCheckedRadioButtonId();
            int selectedClimbing= climbing_RadiobtnGrp.getCheckedRadioButtonId();
            int selectedDefense = defense_RadiobtnGrp.getCheckedRadioButtonId();
            RadioButton gearPlacement_Radiobtn = (RadioButton) findViewById(selectedGearPlacement);
            RadioButton fuelRetreival_Radiobtn = (RadioButton) findViewById(selectedFuelRetrieval);
            RadioButton gearRetreival_Radiobtn = (RadioButton) findViewById(selectedGearRetrieval);
            RadioButton climbing_Radiobtn = (RadioButton) findViewById(selectedClimbing);
            RadioButton defense_Radiobtn = (RadioButton) findViewById(selectedDefense);

            //create csv file
            File file = new File(Dir,"MyMessage.csv");

            //if first time file is opened create row header
            if(!file.exists()){
                heading = "teamNumber," +
                        "matchNumber," +
                        "startingLocation," +
                        "baseline," +
                        "autonGear," +
                        "autonHighFuelScored," +
                        "autonHighFuelMissed," +
                        "autonLowFuel," +
//                        Teleop Headings
                        "teleopGearPlaced"+
                        "gearPlacement"+
                        "teleopGearDropped"+
                        "teleopHighFuelScored"+
                        "teleopHighFuelMissed"+
                        "teleopLowFuel"+
                        "fuelRetrieval," +
                        "gearRetrieval," +
                        "climb," +
                        "ClimbTime," +
                        "defense," +
                        "fouls\n";
            }
            else{
                //empty header as the are already created
                heading = "";
            }
            //compile string of all data
             auton = teamNumber_input.getText().toString() + "," +
                     matchNumber_input.getText().toString()+","+
                     startingLocation_Radiobtn.getText()+","+
                     baseline_Radiobtn.getText()+","+
                     autonGear_Radiobtn.getText()+","+
                     autonHighFuelScored_input.getText().toString()+","+
                     autonHighFuelMissed_input.getText().toString()+","+
                     autonLowFuel_input.getText().toString()+"\n";

            teleop = teleopGearPlaced_input.getText().toString()+","+
                     gearPlacement_Radiobtn.getText()+","+
                     teleopGearDropped_input.getText().toString()+","+
                     teleopHighFuelScored_input.getText().toString()+","+
                     teleopHighFuelMissed_input.getText().toString()+","+
                     teleopLowFuel_input.getText().toString()+","+
                     fuelRetreival_Radiobtn.getText()+","+
                     gearRetreival_Radiobtn.getText()+","+
                     climbing_Radiobtn.getText()+","+
                     climbTime_input.getText().toString()+","+
                     defense_Radiobtn.getText();


            message = heading + auton + teleop;
            //Output data to file
            try{
                FileOutputStream fileOutputStream= new FileOutputStream(file,true);
                fileOutputStream.write(message.getBytes());
                fileOutputStream.close();
            } catch (IOException e){
                e.printStackTrace();
            }
        }else{
            Toast.makeText(getApplicationContext(),"SD card not found", Toast.LENGTH_LONG).show();
        }
        Toast.makeText(getApplicationContext(),"message Saved", Toast.LENGTH_LONG).show();

        //Clear data from form fields
        clearData(view);
    }


    public void clearData(View view){
        teamNumber_input.setText("");
        matchNumber_input.setText("");
        startingLocation_RadiobtnGrp.clearCheck();
        baseLine_RadiobtnGrp.clearCheck();
        autonGear_RadiobtnGrp.clearCheck();
        autonHighFuelScored_input.setText("");
        autonHighFuelMissed_input.setText("");
        autonLowFuel_input.setText("");


//Clear TeleopItems
        gearPlacement_RadiobtnGrp.clearCheck();
        teleopGearDropped_input.setText("");
        teleopHighFuelScored_input.setText("");
        teleopHighFuelMissed_input.setText("");
        teleopLowFuel_input.setText("");
        fuelRetrieval_RadiobtnGrp.clearCheck();
        gearRetrieval_RadiobtnGrp.clearCheck();
        climbing_RadiobtnGrp.clearCheck();
        climbTime_input.setText("");
        defense_RadiobtnGrp.clearCheck();
    }

//    public void addListenerOnButton() {
//        final Context context = this;
//
//        button = (Button) findViewById(R.id.button1);
//        button.setOnClickListener(new OnClickListener() {
//
//            @Override
//            public void onClick(View arg0) {
//            Intent intent = new Intent(context, TeleopActivity.class);
//            startActivity(intent);
//
//            }
//
//        });
//
//    }


}
