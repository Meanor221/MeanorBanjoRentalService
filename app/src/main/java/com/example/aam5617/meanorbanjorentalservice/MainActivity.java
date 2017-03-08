package com.example.aam5617.meanorbanjorentalservice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Switch;

public class MainActivity extends AppCompatActivity {

    private BanjoOrder banjoOrder;

    private EditText numBanjos;
    private EditText numCases;
    private TextView baseCostView;
    private TextView salesTaxView;
    private TextView totalCostView;
    private TextView insuranceView;

    private Switch insuranceSwitch;

    private Spinner instrumentSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        banjoOrder = new BanjoOrder();

        numBanjos       = (EditText) findViewById(R.id.numBanjosEditText);
        numCases        = (EditText) findViewById(R.id.numCasesEditText);

        baseCostView    = (TextView) findViewById(R.id.baseRentalFeeValue);
        salesTaxView    = (TextView) findViewById(R.id.stateSalesTaxValue);
        insuranceView   = (TextView) findViewById(R.id.insuranceValue);
        totalCostView   = (TextView) findViewById(R.id.totalCostValue);

        insuranceSwitch = (Switch) findViewById(R.id.insuranceSwitch);


        insuranceSwitch.setChecked(false);
        insuranceSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(isChecked)
                    banjoOrder.setInsuranceCost(2.00);
                else
                    banjoOrder.setInsuranceCost(0.00);


                banjoOrder.calculateBaseCost(instrumentSpinner.getSelectedItem().toString());
                banjoOrder.calculateSalesTax();
                banjoOrder.calculateInsurance();
                banjoOrder.calculateTotalcost();
                displayOrder();
            }
        });

        instrumentSpinner = (Spinner)findViewById(R.id.instrumentSpinner);

        instrumentSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ((TextView) adapterView.getChildAt(0)).setTextSize(24);

                banjoOrder.calculateBaseCost(instrumentSpinner.getSelectedItem().toString());
                banjoOrder.calculateSalesTax();
                banjoOrder.calculateInsurance();
                banjoOrder.calculateTotalcost();
                displayOrder();
            }

            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        numBanjos.addTextChangedListener(banjosTextWatcher);
        numCases.addTextChangedListener(casesTextWatcher);
    }

    private TextWatcher banjosTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            try{
                banjoOrder.setNumBanjos((int) Integer.parseInt(s.toString()));
            } catch (NumberFormatException e) {
                banjoOrder.setNumBanjos(0);
            }
            banjoOrder.calculateBaseCost(instrumentSpinner.getSelectedItem().toString());
            banjoOrder.calculateSalesTax();
            banjoOrder.calculateInsurance();
            banjoOrder.calculateTotalcost();
            displayOrder();
        }

        @Override
        public void afterTextChanged(Editable s) {}
    };

    private TextWatcher casesTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            try{
                banjoOrder.setNumCases((int) Integer.parseInt(s.toString()));
            } catch (NumberFormatException e) {
                banjoOrder.setNumCases(0);
            }
            banjoOrder.calculateBaseCost(instrumentSpinner.getSelectedItem().toString());
            banjoOrder.calculateSalesTax();
            banjoOrder.calculateInsurance();
            banjoOrder.calculateTotalcost();
            displayOrder();
        }

        @Override
        public void afterTextChanged(Editable s) {}
    };

    private void displayOrder() {
        baseCostView.setText("$" + String.format("%.02f", banjoOrder.getBaseCost()));
        salesTaxView.setText("$" + String.format("%.02f", banjoOrder.getSalesTax()));
        insuranceView.setText("$" + String.format("%.02f", banjoOrder.getinsuranceTotal()));
        totalCostView.setText("$" + String.format("%.02f", banjoOrder.getTotalCost()));
    }


}
