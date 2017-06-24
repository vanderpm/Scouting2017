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

    public String heading, auton, message;

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



        addListenerOnButton();
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
                        "autonLowFuel\n";
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


            message = heading + auton;
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

    }

    public void addListenerOnButton() {
        final Context context = this;

        button = (Button) findViewById(R.id.button1);
        button.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
            Intent intent = new Intent(context, TeleopActivity.class);
            startActivity(intent);

            }

        });

    }


}
