package com.example.vande.scouting2017;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class TeleopActivity extends AppCompatActivity {

    public EditText teleopGearPlaced_input,
                    teleopGearDropped_input,
                    teleopHighFuelScored_input,
                    teleopHighFuelMissed_input,
                    teleopLowFuel_input,
                    climbTime_input;

    public RadioGroup gearPlacement_RadiobtnGrp,
                      fuelRetreival_RadiobtnGrp,
                      gearRetrieval_RadiobtnGrp,
                      climbing_RadiobtnGrp,
                      defense_RadiobtnGrp;
    public CheckBox fouls_chbx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teleop);

        //Teleop Text/Number input
        teleopGearPlaced_input = (EditText) findViewById(R.id.TeleopGearPlaced_input);
        teleopGearDropped_input = (EditText) findViewById(R.id.TeleopGearDropped_input);
        teleopHighFuelScored_input = (EditText) findViewById(R.id.TeleopHighFuelScored_input);
        teleopHighFuelMissed_input = (EditText) findViewById(R.id.TeleopHighFuelMissed_input);
        teleopLowFuel_input = (EditText) findViewById(R.id.TeleopLowFuel_input);
        climbTime_input = (EditText) findViewById(R.id.ClimbTime_input);

        //Teleop Radio button Group
        gearPlacement_RadiobtnGrp = (RadioGroup)findViewById(R.id.GearPlacement_RadiobtnGrp);
        fuelRetreival_RadiobtnGrp = (RadioGroup) findViewById(R.id.FuelRetrieval_RadiobtnGrp);
        gearRetrieval_RadiobtnGrp = (RadioGroup) findViewById(R.id.GearRetrieval_RadiobtnGrp);
        climbing_RadiobtnGrp = (RadioGroup) findViewById(R.id.Climbing_RadiobtnGrp);
        defense_RadiobtnGrp = (RadioGroup) findViewById(R.id.Defense_RadiobtnGrp);

        //Teleop Buttons
        fouls_chbx = (CheckBox) findViewById(R.id.Fouls_chkbx);


        int selectedGearPlacement = gearPlacement_RadiobtnGrp.getCheckedRadioButtonId();
        int selectedFuelRetreival = fuelRetreival_RadiobtnGrp.getCheckedRadioButtonId();
        int selectedGearRetrieval = gearRetrieval_RadiobtnGrp.getCheckedRadioButtonId();
        int selectedClimbing= climbing_RadiobtnGrp.getCheckedRadioButtonId();
        int selecteddefense = defense_RadiobtnGrp.getCheckedRadioButtonId();
        RadioButton gearPlacement_Radiobtn = (RadioButton) findViewById(selectedGearPlacement);
        RadioButton fuelRetreival_Radiobtn = (RadioButton) findViewById(selectedFuelRetreival);
        RadioButton gearRetreival_Radiobtn = (RadioButton) findViewById(selectedGearRetrieval);
        RadioButton climbing_Radiobtn = (RadioButton) findViewById(selectedClimbing);
        RadioButton defense_Radiobtn = (RadioButton) findViewById(selecteddefense);


    }
}
