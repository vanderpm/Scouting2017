package com.example.vande.scouting2017;

import android.os.Environment;

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
import android.support.design.widget.TextInputLayout;


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



public class TeleopActivity extends AppCompatActivity {

    public String heading, auton, teleop, message;

    public TextInputLayout  teleopGearPlacedInputLayout,
            teleopGearDroppedInputLayout,
            teleopHighFuelScoredInputLayout,
            teleopHighFuelMissedInputLayout,
            teleopLowFuelInputLayout,
            climbTimeInputLayout;



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

    RadioButton gearPlacement_Radiobtn,
            fuelRetreival_Radiobtn,
            gearRetreival_Radiobtn,
            climbing_Radiobtn,
            defense_Radiobtn;

    Button save_btn;




    public CheckBox fouls_chbx;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teleop);
        //Numeric data field

        teleopGearPlacedInputLayout = (TextInputLayout) findViewById(R.id.TeleopGearPlaced_input_layout);
        teleopGearDroppedInputLayout = (TextInputLayout) findViewById(R.id.TeleopGearDropped_input_layout);
        teleopHighFuelScoredInputLayout = (TextInputLayout) findViewById(R.id.TeleopHighFuelScored_input_layout);
        teleopHighFuelMissedInputLayout = (TextInputLayout) findViewById(R.id.TeleopHighFuelMissed_input_layout);
        teleopLowFuelInputLayout = (TextInputLayout) findViewById(R.id.TeleopLowFuel_input_layout);
        climbTimeInputLayout = (TextInputLayout) findViewById(R.id.ClimbTime_input_layout);



        teleopGearPlaced_input = (EditText) findViewById(R.id.TeleopGearPlaced_input);
        teleopGearDropped_input = (EditText) findViewById(R.id.TeleopGearDropped_input);
        teleopHighFuelScored_input = (EditText) findViewById(R.id.TeleopHighFuelScored_input);
        teleopHighFuelMissed_input = (EditText) findViewById(R.id.TeleopHighFuelMissed_input);
        teleopLowFuel_input = (EditText) findViewById(R.id.TeleopLowFuel_input);
        climbTime_input = (EditText) findViewById(R.id.ClimbTime_input);


        //Radio button Groups
        gearPlacement_RadiobtnGrp = (RadioGroup)findViewById(R.id.GearPlacement_RadiobtnGrp);
        fuelRetrieval_RadiobtnGrp = (RadioGroup) findViewById(R.id.FuelRetrieval_RadiobtnGrp);
        gearRetrieval_RadiobtnGrp = (RadioGroup) findViewById(R.id.GearRetrieval_RadiobtnGrp);
        climbing_RadiobtnGrp = (RadioGroup) findViewById(R.id.Climbing_RadiobtnGrp);
        defense_RadiobtnGrp = (RadioGroup) findViewById(R.id.Defense_RadiobtnGrp);
        //Save Data button
        save_btn = (Button) findViewById(R.id.Save_btn);

        fouls_chbx = (CheckBox) findViewById(R.id.Fouls_chkbx);

        Bundle bundle = getIntent().getExtras();
        auton = bundle.getString("auton");

//        addListenerOnButton();
    }

    public void saveData(View view) throws IOException {
        final Context context = this;
        String state;
        state = Environment.getExternalStorageState();
        int selectedGearPlacement,selectedFuelRetrieval,selectedGearRetrieval,selectedClimbing,selectedDefense;

        if(!teleopGearPlaced()) {return;}
        if(!teleopGearDropped()) {return;}
        if(!teleopHighFuelScored()) {return;}
        if(!teleopHighFuelMissed()) {return;}
        if(!teleopLowFuel()) {return;}
        if(!climbTime()){return;}


        teleopGearPlacedInputLayout.setErrorEnabled(false);
        teleopGearDroppedInputLayout.setErrorEnabled(false);
        teleopHighFuelScoredInputLayout.setErrorEnabled(false);
        teleopHighFuelMissedInputLayout.setErrorEnabled(false);
        teleopLowFuelInputLayout.setErrorEnabled(false);
        climbTimeInputLayout.setErrorEnabled(false);


        selectedGearPlacement = gearPlacement_RadiobtnGrp.getCheckedRadioButtonId();
        selectedFuelRetrieval = fuelRetrieval_RadiobtnGrp.getCheckedRadioButtonId();
        selectedGearRetrieval = gearRetrieval_RadiobtnGrp.getCheckedRadioButtonId();
        selectedClimbing= climbing_RadiobtnGrp.getCheckedRadioButtonId();
        selectedDefense = defense_RadiobtnGrp.getCheckedRadioButtonId();


        gearPlacement_Radiobtn = (RadioButton) findViewById(selectedGearPlacement);
        fuelRetreival_Radiobtn = (RadioButton) findViewById(selectedFuelRetrieval);
        gearRetreival_Radiobtn = (RadioButton) findViewById(selectedGearRetrieval);
        climbing_Radiobtn = (RadioButton) findViewById(selectedClimbing);
        defense_Radiobtn = (RadioButton) findViewById(selectedDefense);

//        if (!gearPlacement()){return;}
//        if (!fuelRetrieval()){return;}
//        if (!gearRetrieval()){return;}
//        if (!climbing()){return;}
//        if (!defense()){return;}
//        gearPlacement_Radiobtn.setError(null);
//        fuelRetreival_Radiobtn.setError(null);
//        gearRetreival_Radiobtn.setError(null);
//        climbing_Radiobtn.setError(null);
//        defense_Radiobtn.setError(null);

        if(Environment.MEDIA_MOUNTED.equals(state)){
            File Root = Environment.getExternalStorageDirectory();
            File Dir = new File(Root.getAbsoluteFile()+"/MyAppFile");
            //create csv file
            File file = new File(Dir,"MyMessage.csv");

//            if first time file is opened create row header
            if(!file.exists()){
                heading = "teamNumber," +
                        "matchNumber," +
                        "startingLocation," +
                        "baseline," +
                        "autonGear," +
                        "autonHighFuelScored," +
                        "autonHighFuelMissed," +
                        "autonLowFuel,"+
                        "teleopGearPlaced,"+
                        "gearPlacement,"+
                        "teleopGearDropped,"+
                        "teleopHighFuelScored,"+
                        "teleopHighFuelMissed,"+
                        "teleopLowFuel,"+
                        "fuelRetreival,"+
                        "gearRetreival,"+
                        "climbing,"+
                        "climbTime,"+
                        "defense\n";
            }
            else{
                //empty header as the are already created
                heading = "";
            }

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


                message = heading + auton +","+ teleop + "\n";
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



                Intent intent = new Intent(context, AutonActivity.class);
                startActivity(intent);


            //Clear data from form fields
            clearData(view);
        }



    public void clearData(View view){

        teleopGearPlaced_input.setText("");
        gearPlacement_RadiobtnGrp.check(R.id.PassiveGearPlacement_btn);
        teleopGearDropped_input.setText("");
        teleopHighFuelScored_input.setText("");
        teleopHighFuelMissed_input.setText("");
        teleopLowFuel_input.setText("");
        fuelRetrieval_RadiobtnGrp.check(R.id.NoFuelRetrieval_btn);
        gearRetrieval_RadiobtnGrp.check(R.id.NoGearRetrieval_btn);
        climbing_RadiobtnGrp.check(R.id.FailClimb_btn);
        climbTime_input.setText("");
        defense_RadiobtnGrp.check(R.id.NoDefense_btn);
    }



    private boolean teleopGearPlaced(){
        if(teleopGearPlaced_input.getText().toString().trim().isEmpty()){
            teleopGearPlacedInputLayout.setError("Enter Gear Count");
            requestFocus(teleopGearPlaced_input);
            return false;
        }else{
            teleopGearPlacedInputLayout.setErrorEnabled(false);
            return true;
        }
    }

    private boolean teleopGearDropped(){
        if(teleopGearDropped_input.getText().toString().trim().isEmpty()){
            teleopGearDroppedInputLayout.setError("Enter Gear Count");
            requestFocus(teleopGearDropped_input);
            return false;
        }else{
            teleopGearDroppedInputLayout.setErrorEnabled(false);
            return true;
        }
    }

    private boolean teleopHighFuelScored(){
        if(teleopHighFuelScored_input.getText().toString().trim().isEmpty()){
            teleopHighFuelScoredInputLayout.setError("Enter Fuel Count");
            requestFocus(teleopHighFuelScored_input);
            return false;
        }else{
            teleopHighFuelScoredInputLayout.setErrorEnabled(false);
            return true;
        }
    }

    private boolean teleopHighFuelMissed(){
        if(teleopHighFuelMissed_input.getText().toString().trim().isEmpty()){
            teleopHighFuelMissedInputLayout.setError("Enter Fuel Count");
            requestFocus(teleopHighFuelMissed_input);
            return false;
        }else{
            teleopHighFuelMissedInputLayout.setErrorEnabled(false);
            return true;
        }
    }

    private boolean teleopLowFuel(){
        if(teleopLowFuel_input.getText().toString().trim().isEmpty()){
            teleopLowFuelInputLayout.setError("Enter Fuel Count");
            requestFocus(teleopLowFuel_input);
            return false;
        }else{
            teleopLowFuelInputLayout.setErrorEnabled(false);
            return true;
        }
    }

    private boolean climbTime() {
        if (climbTime_input.getText().toString().trim().isEmpty()) {
            climbTimeInputLayout.setError("Enter Climb Time");
            requestFocus(climbTime_input);
            return false;
        } else {
            climbTimeInputLayout.setErrorEnabled(false);
            return true;
        }
    }

//    private boolean gearPlacement(){
//        if (gearPlacement_RadiobtnGrp.getCheckedRadioButtonId() == -1) {
//            gearPlacement_Radiobtn.setError("Select Gear Placement");
//            return false;
//        }return true;
//    }
//
//    private boolean fuelRetrieval(){
//        if (fuelRetrieval_RadiobtnGrp.getCheckedRadioButtonId() == -1) {
//            fuelRetreival_Radiobtn.setError("Select Fuel Retrieval");
//            return false;
//        }return true;
//    }
//
//    private boolean gearRetrieval(){
//        if (gearRetrieval_RadiobtnGrp.getCheckedRadioButtonId() == -1) {
//            gearRetreival_Radiobtn.setError("Select Gear Retrieval");
//            return false;
//        }return true;
//    }
//
//    private boolean climbing(){
//        if (climbing_RadiobtnGrp.getCheckedRadioButtonId() == -1) {
//            climbing_Radiobtn.setError("Select Climb");
//            return false;
//        }return true;
//    }
//
//    private boolean defense(){
//        if (defense_RadiobtnGrp.getCheckedRadioButtonId() == -1) {
//            defense_Radiobtn.setError("Select Defense");
//            return false;
//        }return true;
//    }

    private void requestFocus(View view){
        if(view.requestFocus()){
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

}
