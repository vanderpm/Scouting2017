package com.example.vande.scouting2017;

import android.os.Environment;

import android.support.annotation.NonNull;
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
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import utils.FormatStringUtils;
import utils.StringUtils;
import utils.ViewUtils;


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


    @BindView(R.id.save_btn)
    public Button saveBtn;

@BindView(R.id.fouls_chkbx)
    public CheckBox foulsChbx;

    private ArrayList<CharSequence> headingDataStringList;
    private ArrayList<CharSequence> teleopDataStringList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teleop);


        ButterKnife.bind(this);
        //Save Data button

        Bundle bundle = getIntent().getExtras();
        auton = bundle.getString("auton_extra");


    }

    public void saveData(View view) throws IOException {
        String state;
        state = Environment.getExternalStorageState();
        boolean allInputsPassed = false;


        if (StringUtils.isEmptyOrNull(getTextInputLayoutString(teleopGearPlacedInputLayout))) {
            teleopGearPlacedInputLayout.setError("Enter Gears Placed");
            ViewUtils.requestFocus(teleopGearPlacedInputLayout, this);
        }else if (StringUtils.isEmptyOrNull(getTextInputLayoutString(teleopGearDroppedInputLayout))){
            teleopGearDroppedInputLayout.setError("Enter Gear Dropped");
            ViewUtils.requestFocus(teleopGearDroppedInputLayout, this);
        }else if (StringUtils.isEmptyOrNull(getTextInputLayoutString(teleopHighFuelScoredInputLayout))){
            teleopHighFuelScoredInputLayout.setError("Enter High Fuel Scored");
            ViewUtils.requestFocus(teleopHighFuelScoredInputLayout, this);
        }else if (StringUtils.isEmptyOrNull(getTextInputLayoutString(teleopHighFuelMissedInputLayout))){
            teleopHighFuelMissedInputLayout.setError("Enter High Fuel Missed");
            ViewUtils.requestFocus(teleopHighFuelMissedInputLayout, this);
        }else if (StringUtils.isEmptyOrNull(getTextInputLayoutString(teleopLowFuelInputLayout))) {
            teleopLowFuelInputLayout.setError("Enter Low Fuel Scored");
            ViewUtils.requestFocus(teleopLowFuelInputLayout, this);
        }else if (StringUtils.isEmptyOrNull(getTextInputLayoutString(climbTimeInputLayout))) {
            climbTimeInputLayout.setError("Enter Climb Time");
            ViewUtils.requestFocus(climbTimeInputLayout, this);
        }else {
            allInputsPassed = true;
        }



        final RadioButton gearPlacement_Radiobtn = (RadioButton) findViewById(gearPlacementRadiobtnGrp.getCheckedRadioButtonId());
        final RadioButton fuelRetreival_Radiobtn = (RadioButton) findViewById(fuelRetrievalRadiobtnGrp.getCheckedRadioButtonId());
        final RadioButton gearRetreival_Radiobtn = (RadioButton) findViewById(gearRetrievalRadiobtnGrp.getCheckedRadioButtonId());
        final RadioButton climbing_Radiobtn = (RadioButton) findViewById(climbingRadiobtnGrp.getCheckedRadioButtonId());
        final RadioButton defense_Radiobtn = (RadioButton) findViewById(defenseRadiobtnGrp.getCheckedRadioButtonId());


        if(Environment.MEDIA_MOUNTED.equals(state)){
            File Root = Environment.getExternalStorageDirectory();
            File Dir = new File(Root.getAbsoluteFile()+"/Documents");
            //create csv file
            File file = new File(Dir,"MyMessage.csv");

//            if first time file is opened create row header
            if(!file.exists()){
                headingDataStringList.add("teamNumber");
                headingDataStringList.add("matchNumber");
                headingDataStringList.add("startingLocation," );
                headingDataStringList.add("baseline," );
                headingDataStringList.add("autonGear," );
                headingDataStringList.add("autonGearSuccess,");
                headingDataStringList.add("autonHighFuelScored," );
                headingDataStringList.add("autonHighFuelMissed," );
                headingDataStringList.add("autonLowFuel,");
                headingDataStringList.add("activatedHopper,");
                headingDataStringList.add("teleopGearPlaced,");
                headingDataStringList.add("gearPlacement,");
                headingDataStringList.add("teleopGearDropped,");
                headingDataStringList.add("teleopHighFuelScored,");
                headingDataStringList.add("teleopHighFuelMissed,");
                headingDataStringList.add("teleopLowFuel,");
                headingDataStringList.add("fuelRetrieval,");
                headingDataStringList.add("gearRetrieval,");
                headingDataStringList.add("climbing,");
                headingDataStringList.add("climbTime,");
                headingDataStringList.add("defense\n");
            }
            else{
                //empty header as the are already created
                headingDataStringList.add("");
            }


            teleopDataStringList.add(getTextInputLayoutString(teleopGearPlacedInputLayout));
            teleopDataStringList.add(gearPlacement_Radiobtn.getText());
            teleopDataStringList.add(getTextInputLayoutString(teleopGearDroppedInputLayout));
            teleopDataStringList.add(getTextInputLayoutString(teleopHighFuelScoredInputLayout));
            teleopDataStringList.add(getTextInputLayoutString(teleopHighFuelMissedInputLayout));
            teleopDataStringList.add(getTextInputLayoutString(teleopLowFuelInputLayout));
            teleopDataStringList.add(fuelRetreival_Radiobtn.getText());
            teleopDataStringList.add(gearRetreival_Radiobtn.getText());
            teleopDataStringList.add(climbing_Radiobtn.getText());
            teleopDataStringList.add(getTextInputLayoutString(climbTimeInputLayout));
            teleopDataStringList.add(defense_Radiobtn.getText());

            String message = FormatStringUtils.addDelimiter(headingDataStringList, ",") +
                    "\n" + auton + FormatStringUtils.addDelimiter(teleopDataStringList, "'");


//                message = heading + auton +","+ teleop + "\n";
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


                Intent intent = new Intent(this, AutonActivity.class);
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


    private String getTextInputLayoutString(@NonNull TextInputLayout textInputLayout) {
        final EditText editText = textInputLayout.getEditText();
        return editText != null && editText.getText() != null ? editText.getText().toString() : "";
    }

}
