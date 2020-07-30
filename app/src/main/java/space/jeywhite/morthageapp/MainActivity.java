package space.jeywhite.morthageapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ToggleButton;


public class MainActivity extends AppCompatActivity {

    EditText amount, interest, period;
    Button calculate, loses;
    ToggleButton periodType;
    double matCap, valuation;
    boolean dataGet,taxOut, periodTypePos, insurance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        calculate = findViewById(R.id.calculate);
        loses =findViewById(R.id.loses_go_to);
        periodType= findViewById(R.id.PeriodType);
        calculate.setOnClickListener(CalculateListener);
        loses.setOnClickListener(losesListener);
        periodType.setOnCheckedChangeListener(PeriodTypeListener);
    }


    ToggleButton.OnCheckedChangeListener PeriodTypeListener = new ToggleButton.OnCheckedChangeListener () {

        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            periodTypePos = isChecked;
        }
    };

    View.OnClickListener CalculateListener = new View.OnClickListener () {
        @Override
        public void onClick(View v) {
            Intent intent_main= new Intent (MainActivity.this, Info_1.class);


            amount = findViewById(R.id.amountInput);
            interest = findViewById(R.id.interestInput);
            period = findViewById(R.id.periodInput);

            if(!TextUtils.isEmpty(amount.getText().toString().trim())
                    && !TextUtils.isEmpty(interest.getText().toString().trim())
                    && !TextUtils.isEmpty(period.getText().toString().trim()))
            {
                intent_main.putExtra("amount",  Double.parseDouble(amount.getText().toString()));
                intent_main.putExtra("interest", Double.parseDouble(interest.getText().toString()));
                if(periodTypePos) {
                    intent_main.putExtra("period",12 * Double.parseDouble(period.getText().toString()));
                }
                else {
                    intent_main.putExtra("period", Double.parseDouble(period.getText().toString()));
                }

                if(dataGet) {
                    intent_main.putExtra("MatCap", matCap);
                    intent_main.putExtra("taxOut", taxOut);
                    if(insurance)

                    {
                        double insuranceAmount;
                        if(periodTypePos) {
                            insuranceAmount = Double.parseDouble(amount.getText().toString())*0.005*Double.parseDouble(period.getText().toString());
                        }
                        else {
                            insuranceAmount = Double.parseDouble(amount.getText().toString())*0.005*(Double.parseDouble(period.getText().toString())/12);
                        }
                        intent_main.putExtra("insurance", insuranceAmount);

                    }
                    intent_main.putExtra("valuation", valuation);
                }

                startActivity(intent_main);
            }
            else {
                Toast T = Toast.makeText(getApplicationContext(), getText(R.string.inputError), Toast.LENGTH_SHORT);
                T.setGravity(Gravity.CENTER, 0, 0);
                T.show();
            }
        }
    };

    //complete
    View.OnClickListener losesListener = new View.OnClickListener () {
        @Override
        public void onClick(View v) {
            Intent intent_mainToLoss= new Intent (MainActivity.this, Loss.class);
            startActivityForResult(intent_mainToLoss, 1);
        }
    };



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(data==null){return;}
        dataGet = true ;
        matCap = data.getDoubleExtra("MatCap_toMain", 0);
        taxOut = data.getBooleanExtra("taxOut_toMain", false);
        insurance  = data.getBooleanExtra("insurance_toMain", false);
        valuation = data.getDoubleExtra("valuation_toMain", 0);


        //for debug
        //Toast Q = Toast.makeText(getApplicationContext(), "digit " + (int) matСap+" tax"+ taxOut+" strahovka "+ insurance +" ocenka "+valuation, Toast.LENGTH_SHORT);
        //Q.show();
    }
}
