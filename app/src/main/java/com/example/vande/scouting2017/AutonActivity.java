package com.example.vande.scouting2017;

import android.content.Intent;
import android.os.Bundle;
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

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import utils.FormatStringUtils;
import utils.StringUtils;
import utils.ViewUtils;

public class AutonActivity extends AppCompatActivity implements View.OnKeyListener {

    public static String AUTON_STRING_EXTRA = "auton_extra";

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

    @BindView(R.id.next_button)
    public Button nextButton;

    private ArrayList<CharSequence> autonDataStringList;
    public static final int REQUEST_CODE = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_auton);
        ButterKnife.bind(this);
        autonDataStringList = new ArrayList<>();
    }

    @Override
    protected void onResume() {
        super.onResume();

        autonDataStringList.clear();

        teamNumberInput.setOnKeyListener(this);
        matchNumberInput.setOnKeyListener(this);
        autonHighFuelScoredInput.setOnKeyListener(this);
        autonHighFuelMissedInput.setOnKeyListener(this);
        autonLowFuelInput.setOnKeyListener(this);
    }

    @Override
    protected void onPause() {
        super.onPause();

        teamNumberInput.setOnKeyListener(null);
        matchNumberInput.setOnKeyListener(null);
        autonHighFuelScoredInput.setOnKeyListener(null);
        autonHighFuelMissedInput.setOnKeyListener(null);
        autonLowFuelInput.setOnKeyListener(null);
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
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        if (keyCode != KeyEvent.KEYCODE_SPACE && keyCode != KeyEvent.KEYCODE_TAB) {
            TextInputEditText inputEditText = (TextInputEditText) v;

            if (inputEditText != null) {

                switch (inputEditText.getId()) {

                    case R.id.teamNumber_input:
                        teamNumberInputLayout.setError(null);
                        break;

                    case R.id.matchNumber_input:
                        matchNumberInputLayout.setError(null);
                        break;

                    case R.id.autonHighFuelScored_input:
                        autonHighFuelScoredInputLayout.setError(null);
                        break;

                    case R.id.autonHighFuelMissed_input:
                        autonHighFuelMissedInputLayout.setError(null);
                        break;

                    case R.id.autonLowFuel_input:
                        autonHighFuelScoredInputLayout.setError(null);
                        break;
                }
            }
        }
        return false;
    }

    public void onShowTeleop(View view) {
        boolean allInputsPassed = false;

        if (StringUtils.isEmptyOrNull(getTextInputLayoutString(teamNumberInputLayout))) {
            teamNumberInputLayout.setError(getText(R.string.teamNumberError));
            ViewUtils.requestFocus(teamNumberInputLayout, this);
        } else if (StringUtils.isEmptyOrNull(getTextInputLayoutString(matchNumberInputLayout))) {
            matchNumberInputLayout.setError(getText(R.string.matchNumberError));
            ViewUtils.requestFocus(matchNumberInputLayout, this);
        } else if (StringUtils.isEmptyOrNull(getTextInputLayoutString(autonHighFuelScoredInputLayout))) {
            autonHighFuelScoredInputLayout.setError(getText(R.string.autonHighFuelScoredError));
            ViewUtils.requestFocus(autonHighFuelScoredInputLayout, this);
        } else if (StringUtils.isEmptyOrNull(getTextInputLayoutString(autonHighFuelMissedInputLayout))) {
            autonHighFuelMissedInputLayout.setError(getText(R.string.autonHighFuelMissedError));
            ViewUtils.requestFocus(autonHighFuelMissedInputLayout, this);
        } else if (StringUtils.isEmptyOrNull(getTextInputLayoutString(autonLowFuelInputLayout))) {
            autonLowFuelInputLayout.setError(getText(R.string.autonLowFuelError));
            ViewUtils.requestFocus(autonLowFuelInputLayout, this);
        } else {
            allInputsPassed = true;
        }

        if (!allInputsPassed) {
            return;
        }

        String autonGearSuccessString = String.valueOf(autonGearSuccessChkbx.isChecked());
        String activatedHopperString = String.valueOf(activatedHopperChkbx.isChecked());

        final RadioButton startingLocation_Radiobtn = (RadioButton) findViewById(startingLocationRadiobtnGrp.getCheckedRadioButtonId());
        final RadioButton baseline_Radiobtn = (RadioButton) findViewById(baseLineRadiobtnGrp.getCheckedRadioButtonId());
        final RadioButton autonGear_Radiobtn = (RadioButton) findViewById(autonGearRadiobtnGrp.getCheckedRadioButtonId());

        autonDataStringList.add(getTextInputLayoutString(teamNumberInputLayout));
        autonDataStringList.add(getTextInputLayoutString(matchNumberInputLayout));
        autonDataStringList.add(startingLocation_Radiobtn.getText());
        autonDataStringList.add(baseline_Radiobtn.getText());
        autonDataStringList.add(autonGear_Radiobtn.getText());
        autonDataStringList.add(getTextInputLayoutString(autonHighFuelScoredInputLayout));
        autonDataStringList.add(getTextInputLayoutString(autonHighFuelMissedInputLayout));
        autonDataStringList.add(getTextInputLayoutString(autonLowFuelInputLayout));
        autonDataStringList.add(autonGearSuccessString);
        autonDataStringList.add(activatedHopperString);

        final Intent intent = new Intent(this, TeleopActivity.class);
        intent.putExtra(AUTON_STRING_EXTRA, FormatStringUtils.addDelimiter(autonDataStringList, ","));

        startActivityForResult(intent, REQUEST_CODE);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        try {
            super.onActivityResult(requestCode, resultCode, data);

            if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
                String requiredValue = data.getStringExtra("Key");
                clearData();
            }
        } catch (Exception ex) {
            Toast.makeText(this, ex.toString(), Toast.LENGTH_SHORT).show();
        }
    }

    public void clearData() {
        teamNumberInput.setText("");
        matchNumberInput.setText("");
        startingLocationRadiobtnGrp.check(R.id.failBaseline_Radiobtn);
        autonGearRadiobtnGrp.check(R.id.noAutonGear_Radiobtn);
        autonGearSuccessChkbx.setChecked(false);
        autonHighFuelScoredInput.setText("");
        autonHighFuelMissedInput.setText("");
        autonLowFuelInput.setText("");
        activatedHopperChkbx.setChecked(false);
        teamNumberInput.requestFocus();
    }

    private String getTextInputLayoutString(@NonNull TextInputLayout textInputLayout) {
        final EditText editText = textInputLayout.getEditText();
        return editText != null && editText.getText() != null ? editText.getText().toString() : "";
    }

}
