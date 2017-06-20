package com.example.vande.scouting2017;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class AutonActivity extends AppCompatActivity {

//    public EditText editTextName, editTextEmail, editTextFeedbackBody;
    public EditText teamNumber_input, matchNumber_input, autonHighFuel_input, autonLowFuel_input;
    public Button feederStartingLocation_Radiobtn, middleStartingLocation_Radiobtn, boilerStartingLocation_Radiobtn, successBaseline_Radiobtn, failBaseline_Radiobtn;
    public Button feederAutonGear_Radiobtn, middleAutonGear_Radiobtn, boilerAutonGear_Radiobtn, noAutonGear_Radiobtn;
    public Button save_btn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auton);

        teamNumber_input = (EditText) findViewById(R.id.TeamNumber_input);
        matchNumber_input = (EditText) findViewById(R.id.MatchNumber_input);
        autonHighFuel_input = (EditText) findViewById(R.id.AutonHighFuel_input);
        autonLowFuel_input = (EditText) findViewById(R.id.AutonLowFuel_input);

        feederStartingLocation_Radiobtn = (Button) findViewById(R.id.FeederStartingLocation_Radiobtn);
        middleStartingLocation_Radiobtn = (Button) findViewById(R.id.MiddleStartingLocation_Radiobtn);
        boilerStartingLocation_Radiobtn = (Button) findViewById(R.id.BoilerStartingLocation_Radiobtn);
        successBaseline_Radiobtn        = (Button) findViewById(R.id.SuccessBaseline_Radiobtn);
        failBaseline_Radiobtn           = (Button) findViewById(R.id.FailBaseline_Radiobtn);
        feederAutonGear_Radiobtn        = (Button) findViewById(R.id.FeederAutonGear_Radiobtn);
        middleAutonGear_Radiobtn        = (Button) findViewById(R.id.MiddleAutonGear_Radiobtn);
        boilerAutonGear_Radiobtn        = (Button) findViewById(R.id.BoilerAutonGear_Radiobtn);
        noAutonGear_Radiobtn            = (Button) findViewById(R.id.NoAutonGear_Radiobtn);
        save_btn                   = (Button) findViewById(R.id.Save_btn);

    }

    public void sendFeedback(View view) throws IOException {

        String state;
        state = Environment.getExternalStorageState();

        if(Environment.MEDIA_MOUNTED.equals(state)){
            File Root = Environment.getExternalStorageDirectory();
            File Dir = new File(Root.getAbsoluteFile()+"/MyAppFile");
            if(!Dir.exists()) {
                Dir.mkdir();
            }
            File file = new File(Dir,"MyMessage.csv");
            String Message = teamNumber_input.getText().toString() + "," +
                    matchNumber_input.getText().toString()+","+
                    autonHighFuel_input.getText().toString()+","+
                    autonLowFuel_input.getText().toString()+"\n";
            try{
                FileOutputStream fileOutputStream= new FileOutputStream(file,true);
                fileOutputStream.write(Message.getBytes());
                fileOutputStream.close();
            }catch(FileNotFoundException e){
                e.printStackTrace();
            }catch (IOException e){
                e.printStackTrace();
            }
        }else{
            Toast.makeText(getApplicationContext(),"SD card not found", Toast.LENGTH_LONG).show();
        }

        Toast.makeText(getApplicationContext(),"message Saved", Toast.LENGTH_LONG).show();

    }


//Starting Location Radio Button
    public void onStartingLocationRadioButtonClick(View view){
        //Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        //Check which radio button was clicked
        switch (view.getId()){
            case R.id.FeederStartingLocation_Radiobtn:
                if (checked)
                    //Feeder Starting Location
                    break;
            case R.id.MiddleStartingLocation_Radiobtn:
                if (checked)
                    //Middle Starting Location
                    break;
            case R.id.BoilerStartingLocation_Radiobtn:
                if (checked)
                    //Boiler Starting location
                    break;
        }
    }



//Crossing baseline Radio Button
    public void onBaselineRadioButtonClicked(View view){
        //Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        //Check which radio button was clicked
        switch (view.getId()){
            case R.id.SuccessBaseline_Radiobtn:
                if (checked)
                    //Successful Baseline Crossing
                break;
            case R.id.FailBaseline_Radiobtn:
                if (checked)
                    //failed baseline crossing
                break;
        }
    }


//    Auton Gear Placement Radio Buttons
    public void onAutonGearRadioButtonClicked(View view){
        //Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        //Check which radio button was clicked
        switch (view.getId()){
            case R.id.FeederAutonGear_Radiobtn:
                if (checked)
                    //Feeder Auton Gear
                    break;
            case R.id.MiddleAutonGear_Radiobtn:
                if (checked)
                    //Middle Auton Gear
                    break;
            case R.id.BoilerAutonGear_Radiobtn:
                if (checked)
                    //Boiler Auton Gear
                    break;
            case R.id.NoAutonGear_Radiobtn:
                if (checked)
                    //No Auton Gear
                    break;
        }
    }


}
