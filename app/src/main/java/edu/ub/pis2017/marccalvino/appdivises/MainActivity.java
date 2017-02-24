package edu.ub.pis2017.marccalvino.appdivises;

import android.renderscript.Double2;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    final double perCanvEst = 0.15;
    double perCanv = perCanvEst;
    double canvied = 1.05;
    double canvide = 0.95;
    double canvi = canvied;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText entrada = (EditText)findViewById(R.id.numDE);



        //controla si la entrada es modificada i recalcula
        entrada.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                EditText sortid = (EditText) findViewById(R.id.numDS);
                sortid.setText(calculDivisa());

            }
        });

        EditText percentatge = (EditText)findViewById(R.id.numEP);

        //controla si la entrada del percentatge s'ha modificat
        percentatge.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                CheckBox per = (CheckBox)findViewById(R.id.introPer);
                if (per.isChecked()) {
                    try {
                        EditText entradaPercentatge = (EditText) findViewById(R.id.numEP);
                        perCanv = Double.parseDouble(entradaPercentatge.getText().toString());
                        EditText sortid = (EditText)findViewById(R.id.numDS);
                        sortid.setText(calculDivisa());
                    }catch (NumberFormatException e) {
                        per.setActivated(false);
                        perCanv = perCanvEst;
                        EditText sortid = (EditText)findViewById(R.id.numDS);
                        sortid.setText(calculDivisa());
                    }
                }
            }
        });


        Button bSw = (Button)findViewById(R.id.bSwipe);

        // canvia l'ordre de les divises
        bSw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView d1 = (TextView)findViewById(R.id.tTipusDE);
                TextView d2 = (TextView)findViewById(R.id.tTipusDS);
                d1.setText(R.string.d2);
                d2.setText(R.string.d1);
                if (canvi==canvied) canvi=canvide;
                else canvi=canvied;
                EditText sortid = (EditText)findViewById(R.id.numDS);
                sortid.setText(calculDivisa());
            }
        });

        CheckBox per = (CheckBox)findViewById(R.id.introPer);


        // comprova si canvia el check del percentatge
        per.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (buttonView.isChecked()) {
                    EditText entradaPercentatge = (EditText)findViewById(R.id.numEP);
                    try {
                        perCanv = Double.parseDouble(entradaPercentatge.getText().toString());
                        EditText sortid = (EditText)findViewById(R.id.numDS);
                        sortid.setText(calculDivisa());
                    } catch (NumberFormatException e) {
                        perCanv = perCanvEst;
                        EditText sortid = (EditText)findViewById(R.id.numDS);
                        sortid.setText(calculDivisa());
                        buttonView.setActivated(false);
                    }

                } else {
                    perCanv = perCanvEst;
                    EditText sortid = (EditText)findViewById(R.id.numDS);
                    sortid.setText(calculDivisa());
                }

            }
        });

        CheckBox can = (CheckBox)findViewById(R.id.introPer);


        // comprova si canvia el check del percentatge
        can.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (buttonView.isChecked()) {
                    EditText entradaCan = (EditText)findViewById(R.id.entradaCanvi);
                    try {
                        canvi = Double.parseDouble(entradaCan.getText().toString());
                        EditText sortid = (EditText)findViewById(R.id.numDS);
                        sortid.setText(calculDivisa());
                    } catch (NumberFormatException e) {
                        TextView div1 = (TextView)findViewById(R.id.tTipusDE);
                        if (div1.getText().toString() == getString(R.string.d1) ) {
                            canvi=canvied;
                        } else canvi = canvide;
                        EditText sortid = (EditText)findViewById(R.id.numDS);
                        sortid.setText(calculDivisa());
                        buttonView.setActivated(false);
                    }

                } else {
                    TextView div1 = (TextView)findViewById(R.id.tTipusDE);
                    if (div1.getText().toString() == getString(R.string.d1) ) {
                        canvi=canvied;
                    } else canvi = canvide;
                    EditText sortid = (EditText)findViewById(R.id.numDS);
                    sortid.setText(calculDivisa());
                    buttonView.setActivated(false);
                }

            }
        });


    }

    public String calculDivisa() {
        double sor = 0;
        EditText entrada = (EditText)findViewById(R.id.numDE);
        try {
            sor = canvi * Double.parseDouble(entrada.getText().toString());
            sor = sor - ((perCanv / 100) * sor);
        } catch (NumberFormatException e) {
            return "0";
        }
        return Double.toString(sor);
    }
}


