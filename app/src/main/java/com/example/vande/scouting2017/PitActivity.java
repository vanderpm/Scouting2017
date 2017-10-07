package com.example.vande.scouting2017;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import utils.FormatStringUtils;
import utils.StringUtils;
import utils.ViewUtils;

/**
 * Created by Matt on 9/30/2017.
 */

public class PitActivity extends AppCompatActivity implements View.OnKeyListener {
    @BindView(R.id.pit_teamNumber_input_layout)
    public TextInputLayout pitTeamNumberInputLayout;

    @BindView(R.id.pit_fuelCapacity_input_layout)
    public TextInputLayout pitFuelCapacityInputLayout;

    @BindView(R.id.pit_numberOfGears_input_layout)
    public TextInputLayout pitNumberOfGearsInputLayout;

    @BindView(R.id.pit_autonPlan_input_layout)
    public TextInputLayout pitAutonPlanInputLayout;

    @BindView(R.id.pit_climbTime_input_layout)
    public TextInputLayout pitClimbTimeInputLayout;


    private ArrayList<CharSequence> pitDataStringList;
//    private ArrayList<CharSequence> headingDataStringList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_pit);
        pitDataStringList = new ArrayList<>();

        ButterKnife.bind(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.match_scouting:
                startActivity(new Intent(this, AutonActivity.class));
                return true;
            case R.id.pit_scouting:
                startActivity(new Intent(this, PitActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        pitTeamNumberInputLayout.setOnKeyListener(this);
        pitFuelCapacityInputLayout.setOnKeyListener(this);
        pitNumberOfGearsInputLayout.setOnKeyListener(this);
        pitAutonPlanInputLayout.setOnKeyListener(this);
        pitClimbTimeInputLayout.setOnKeyListener(this);

    }


    @Override
    protected void onPause() {
        super.onPause();

        pitTeamNumberInputLayout.setOnKeyListener(this);
        pitFuelCapacityInputLayout.setOnKeyListener(this);
        pitNumberOfGearsInputLayout.setOnKeyListener(this);
        pitAutonPlanInputLayout.setOnKeyListener(this);
        pitClimbTimeInputLayout.setOnKeyListener(this);

    }

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {

        if (keyCode != KeyEvent.KEYCODE_SPACE && keyCode != KeyEvent.KEYCODE_TAB) {
            TextInputEditText inputEditText = (TextInputEditText) v;

            if (inputEditText != null) {

                switch (inputEditText.getId()) {

                    case R.id.pit_teamNumber_input:
                        pitTeamNumberInputLayout.setError(null);
                        break;

                    case R.id.pit_fuelCapacity_input:
                        pitFuelCapacityInputLayout.setError(null);
                        break;

                    case R.id.pit_NumberOfGears_input:
                        pitNumberOfGearsInputLayout.setError(null);
                        break;

                    case R.id.pit_autonPlan_input:
                        pitAutonPlanInputLayout.setError(null);
                        break;

                    case R.id.pit_climbTime_input:
                        pitClimbTimeInputLayout.setError(null);
                        break;
                }
            }
        }
        return false;
    }

    public void saveData(View view) throws IOException {
        String state = Environment.getExternalStorageState();
        boolean allInputsPassed = false;


        if (StringUtils.isEmptyOrNull(getTextInputLayoutString(pitTeamNumberInputLayout))) {
            pitTeamNumberInputLayout.setError(getText(R.string.pitTeamNumberError));
            ViewUtils.requestFocus(pitTeamNumberInputLayout, this);
        } else if (StringUtils.isEmptyOrNull(getTextInputLayoutString(pitFuelCapacityInputLayout))) {
            pitFuelCapacityInputLayout.setError(getText(R.string.pitFuelCapacityError));
            ViewUtils.requestFocus(pitFuelCapacityInputLayout, this);
        } else if (StringUtils.isEmptyOrNull(getTextInputLayoutString(pitNumberOfGearsInputLayout))) {
            pitNumberOfGearsInputLayout.setError(getText(R.string.pitNumberOfGearsError));
            ViewUtils.requestFocus(pitNumberOfGearsInputLayout, this);
        } else if (StringUtils.isEmptyOrNull(getTextInputLayoutString(pitAutonPlanInputLayout))) {
            pitAutonPlanInputLayout.setError(getText(R.string.pitAutonPlanError));
            ViewUtils.requestFocus(pitAutonPlanInputLayout, this);
        } else if (StringUtils.isEmptyOrNull(getTextInputLayoutString(pitClimbTimeInputLayout))) {
            pitClimbTimeInputLayout.setError(getText(R.string.pitClimbTimeError));
            ViewUtils.requestFocus(pitClimbTimeInputLayout, this);

        } else {
            allInputsPassed = true;
        }
        if (!allInputsPassed) {
            return;
        }


//        final RadioButton gearPlacement_Radiobtn = (RadioButton) findViewById(gearPlacementRadiobtnGrp.getCheckedRadioButtonId());
//        final RadioButton fuelRetreival_Radiobtn = (RadioButton) findViewById(fuelRetrievalRadiobtnGrp.getCheckedRadioButtonId());
//        final RadioButton gearRetreival_Radiobtn = (RadioButton) findViewById(gearRetrievalRadiobtnGrp.getCheckedRadioButtonId());
//        final RadioButton climbing_Radiobtn = (RadioButton) findViewById(climbingRadiobtnGrp.getCheckedRadioButtonId());
//        final RadioButton defense_Radiobtn = (RadioButton) findViewById(defenseRadiobtnGrp.getCheckedRadioButtonId());


        if (Environment.MEDIA_MOUNTED.equals(state)) {
            File Root = Environment.getExternalStorageDirectory();
            File Dir = new File(Root.getAbsoluteFile() + "/Documents");
            //create csv file
            File file = new File(Dir, "pit.csv");


            pitDataStringList.add(getTextInputLayoutString(pitTeamNumberInputLayout));
            pitDataStringList.add(getTextInputLayoutString(pitFuelCapacityInputLayout));
            pitDataStringList.add(getTextInputLayoutString(pitNumberOfGearsInputLayout));
            pitDataStringList.add(getTextInputLayoutString(pitAutonPlanInputLayout));
            pitDataStringList.add(getTextInputLayoutString(pitClimbTimeInputLayout));


            String message = FormatStringUtils.addDelimiter(pitDataStringList, ",");
//            String message = FormatStringUtils.addDelimiter(teleopDataStringList, ",") + auton +","+ FormatStringUtils.addDelimiter(teleopDataStringList, ",");

//                message = heading + auton +","+ teleop + "\n";
            //Output data to file
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(file, true);
                fileOutputStream.write(message.getBytes());
                fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            Toast.makeText(getApplicationContext(), "SD card not found", Toast.LENGTH_LONG).show();
        }
        Toast.makeText(getApplicationContext(), "message Saved", Toast.LENGTH_LONG).show();

        finish();
    }


    private String getTextInputLayoutString(@NonNull TextInputLayout textInputLayout) {
        final EditText editText = textInputLayout.getEditText();
        return editText != null && editText.getText() != null ? editText.getText().toString() : "";
    }
}
