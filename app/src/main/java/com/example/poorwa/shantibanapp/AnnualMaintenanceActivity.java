package com.example.poorwa.shantibanapp;

import android.app.DatePickerDialog;
import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.Calendar;

public class AnnualMaintenanceActivity extends AppCompatActivity {

    Button dateButton, save;
    String date = "";
    RadioGroup paymentType;
    RadioButton advance, late, onTime;
    LinearLayout form, lateFeeLayout;
    AnnualMaintenance income = new AnnualMaintenance();
    AnnualMaintenanceDBInterface dbInterface;
    Context context = this;
    EditText memberName, plotNumber, amountPaid, lateFeeFine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_annual_maintenance);

        dateButton = (Button) findViewById(R.id.selectDate);
        paymentType = (RadioGroup) findViewById(R.id.paymentType);
        form = (LinearLayout) findViewById(R.id.annualMaintenance);
        advance = (RadioButton) findViewById(R.id.advance);
        onTime = (RadioButton) findViewById(R.id.onTime);
        late = (RadioButton) findViewById(R.id.late);
        lateFeeLayout = (LinearLayout) findViewById(R.id.lateFeeLayout);
        memberName = (EditText) findViewById(R.id.memberName);
        plotNumber = (EditText) findViewById(R.id.plotNumber);
        amountPaid = (EditText) findViewById(R.id.amountPaid);
        lateFeeFine = (EditText) findViewById(R.id.lateFee);



        save = (Button) findViewById(R.id.save);
        dbInterface = new AnnualMaintenanceDBInterface(context);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!checkAll()) {
                    Toast.makeText(getBaseContext(), "Complete all fields", Toast.LENGTH_SHORT).show();
                }
                else {

                    income.setMemberName(memberName.getText().toString());
                    income.setPlotNumber(plotNumber.getText().toString());
                    income.setPaymentDate(dateButton.getText().toString());
                    income.setAmountPaid(amountPaid.getText().toString());
                    income.setLateFeeFine(lateFeeFine.getText().toString());

                    dbInterface.insert(income);

                    if (dbInterface.z == -1) {
                        dbInterface.update(income);
                    }
                    dbInterface.getInfo(income.getPlotNumber());
                    Toast.makeText(getBaseContext(), "Insert Successful", Toast.LENGTH_SHORT).show();
                    finish();
                }

            }
        });
        //   form.setVisibility(View.GONE);

    }

    public boolean checkAll() {
        if(memberName.getText().toString().isEmpty())
            return false;
        if(plotNumber.getText().toString().isEmpty())
            return false;
        if(dateButton.getText().toString().equals("DD-MM-YYYY"))
            return false;
        if(amountPaid.getText().toString().isEmpty())
            return false;
        if(late.isChecked() && lateFeeFine.getText().toString().isEmpty())
            return false;
        return true;
    }

    public void selectPayment(View v) {
        boolean checked = ((RadioButton)v).isChecked();
        switch(v.getId()) {
            case R.id.late:
                if(checked) {
                    lateFeeFine.setText("");
                    lateFeeLayout.setVisibility(View.VISIBLE);
                }
                else
                    lateFeeLayout.setVisibility(View.GONE);
                break;
            case R.id.onTime:
                if(checked) {
                    lateFeeFine.setText("-1");
                    lateFeeLayout.setVisibility(View.GONE);
                }
                else {
                }
                break;
            case R.id.advance:
                if(checked) {
                    lateFeeFine.setText("-1");
                    lateFeeLayout.setVisibility(View.GONE);
                }
                else {
                }
                break;
            default:
                lateFeeLayout.setVisibility(View.GONE);
                break;
        }

    }

    public void selectDate(View v) {
        Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH);
        int mDay = c.get(Calendar.DAY_OF_MONTH);


        // Launch Date Picker Dialog
        DatePickerDialog dpd = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        // Display Selected date in textbox
                        dateButton.setText(dayOfMonth + "-"
                                + (monthOfYear + 1) + "-" + year);
                        dateButton.setTextColor(Color.parseColor("#000000"));

                    }
                }, mYear, mMonth, mDay);
        dpd.show();

        Log.println(Log.ASSERT, "Date", date);

    }


}