package com.example.vande.scouting2017;

import android.os.Environment;

import android.support.design.widget.TextInputEditText;
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

import butterknife.BindView;
import butterknife.ButterKnife;


public class TeleopActivity extends AppCompatActivity {

    public String heading, auton, teleop, message;

    @BindView(R.id.teleopGearPlaced_input_layout)
    public TextInputLayout  teleopGearPlacedInputLayout;

    @BindView(R.id.teleopGearDropped_input_layout)
    public TextInputLayout teleopGearDroppedInputLayout;

    @BindView(R.id.teleopHighFuelScored_input_layout)
    public TextInputLayout teleopHighFuelScoredInputLayout;

    @BindView(R.id.teleopHighFuelMissed_input_layout)
    public TextInputLayout teleopHighFuelMissedInputLayout;

    @BindView(R.id.teleopLowFuel_input_layout)
    public TextInputLayout teleopLowFuelInputLayout;

    @BindView(R.id.climbTime_input_layout)
    public TextInputLayout climbTimeInputLayout;

    @BindView(R.id.teleopGearPlaced_input)
    public TextInputEditText teleopGearPlacedInput;

    @BindView(R.id.teleopGearDropped_input)
    public TextInputEditText teleopGearDroppedInput;

    @BindView(R.id.teleopHighFuelScored_input)
    public TextInputEditText teleopHighFuelScoredInput;

    @BindView(R.id.teleopHighFuelMissed_input)
    public TextInputEditText teleopHighFuelMissedInput;

    @BindView(R.id.teleopLowFuel_input)
    public TextInputEditText teleopLowFuelInput;

    @BindView(R.id.climbTime_input)
    public TextInputEditText climbTimeInput;

    @BindView(R.id.gearPlacement_RadiobtnGrp)
    public RadioGroup gearPlacementRadiobtnGrp;

    @BindView(R.id.fuelRetrieval_RadiobtnGrp)
    public RadioGroup fuelRetrievalRadiobtnGrp;

    @BindView(R.id.gearRetrieval_RadiobtnGrp)
    public RadioGroup gearRetrievalRadiobtnGrp;

    @BindView(R.id.climbing_RadiobtnGrp)
    public RadioGroup climbingRadiobtnGrp;

    @BindView(R.id.defense_RadiobtnGrp)
    public RadioGroup defenseRadiobtnGrp;

    RadioButton gearPlacement_Radiobtn,
            fuelRetreival_Radiobtn,
            gearRetreival_Radiobtn,
            climbing_Radiobtn,
            defense_Radiobtn;

    @BindView(R.id.save_btn)
    public Button saveBtn;

@BindView(R.id.fouls_chkbx)
    public CheckBox foulsChbx;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teleop);


        ButterKnife.bind(this);
        //Save Data button

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


        selectedGearPlacement = gearPlacementRadiobtnGrp.getCheckedRadioButtonId();
        selectedFuelRetrieval = fuelRetrievalRadiobtnGrp.getCheckedRadioButtonId();
        selectedGearRetrieval = gearRetrievalRadiobtnGrp.getCheckedRadioButtonId();
        selectedClimbing= climbingRadiobtnGrp.getCheckedRadioButtonId();
        selectedDefense = defenseRadiobtnGrp.getCheckedRadioButtonId();


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
                        "fuelRetrieval,"+
                        "gearRetrieval,"+
                        "climbing,"+
                        "climbTime,"+
                        "defense\n";
            }
            else{
                //empty header as the are already created
                heading = "";
            }

                teleop = teleopGearPlacedInput.getText().toString()+","+
                        gearPlacement_Radiobtn.getText()+","+
                        teleopGearDroppedInput.getText().toString()+","+
                        teleopHighFuelScoredInput.getText().toString()+","+
                        teleopHighFuelMissedInput.getText().toString()+","+
                        teleopLowFuelInput.getText().toString()+","+
                        fuelRetreival_Radiobtn.getText()+","+
                        gearRetreival_Radiobtn.getText()+","+
                        climbing_Radiobtn.getText()+","+
                        climbTimeInput.getText().toString()+","+
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

        teleopGearPlacedInput.setText("");
        gearPlacementRadiobtnGrp.check(R.id.passiveGearPlacement_btn);
        teleopGearDroppedInput.setText("");
        teleopHighFuelScoredInput.setText("");
        teleopHighFuelMissedInput.setText("");
        teleopLowFuelInput.setText("");
        fuelRetrievalRadiobtnGrp.check(R.id.noFuelRetrieval_btn);
        gearRetrievalRadiobtnGrp.check(R.id.noGearRetrieval_btn);
        climbingRadiobtnGrp.check(R.id.failClimb_btn);
        climbTimeInput.setText("");
        defenseRadiobtnGrp.check(R.id.noDefense_btn);
    }



    private boolean teleopGearPlaced(){
        if(teleopGearPlacedInput.getText().toString().trim().isEmpty()){
            teleopGearPlacedInputLayout.setError("Enter Gear Count");
            requestFocus(teleopGearPlacedInput);
            return false;
        }else{
            teleopGearPlacedInputLayout.setErrorEnabled(false);
            return true;
        }
    }

    private boolean teleopGearDropped(){
        if(teleopGearDroppedInput.getText().toString().trim().isEmpty()){
            teleopGearDroppedInputLayout.setError("Enter Gear Count");
            requestFocus(teleopGearDroppedInput);
            return false;
        }else{
            teleopGearDroppedInputLayout.setErrorEnabled(false);
            return true;
        }
    }

    private boolean teleopHighFuelScored(){
        if(teleopHighFuelScoredInput.getText().toString().trim().isEmpty()){
            teleopHighFuelScoredInputLayout.setError("Enter Fuel Count");
            requestFocus(teleopHighFuelScoredInput);
            return false;
        }else{
            teleopHighFuelScoredInputLayout.setErrorEnabled(false);
            return true;
        }
    }

    private boolean teleopHighFuelMissed(){
        if(teleopHighFuelMissedInput.getText().toString().trim().isEmpty()){
            teleopHighFuelMissedInputLayout.setError("Enter Fuel Count");
            requestFocus(teleopHighFuelMissedInput);
            return false;
        }else{
            teleopHighFuelMissedInputLayout.setErrorEnabled(false);
            return true;
        }
    }

    private boolean teleopLowFuel(){
        if(teleopLowFuelInput.getText().toString().trim().isEmpty()){
            teleopLowFuelInputLayout.setError("Enter Fuel Count");
            requestFocus(teleopLowFuelInput);
            return false;
        }else{
            teleopLowFuelInputLayout.setErrorEnabled(false);
            return true;
        }
    }

    private boolean climbTime() {
        if (climbTimeInput.getText().toString().trim().isEmpty()) {
            climbTimeInputLayout.setError("Enter Climb Time");
            requestFocus(climbTimeInput);
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
