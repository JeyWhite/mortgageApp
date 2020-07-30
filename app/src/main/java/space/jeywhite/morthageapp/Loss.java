package space.jeywhite.morthageapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

public class Loss extends AppCompatActivity {
	EditText valuation;
	Spinner matCapSpinner;
	Switch taxSwitch, insuranceSwitch;
	boolean isTaxOut, isInsurance;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_loss);
		// Получаем экземпляр элемента Spinner
		matCapSpinner = findViewById(R.id.mat_cap_spinner);
		// Настраиваем адаптер
		ArrayAdapter<?> adapter = ArrayAdapter.createFromResource(this, R.array.mat_cap, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// Вызываем адаптер
		matCapSpinner.setAdapter(adapter);


		taxSwitch = findViewById(R.id.tax_out);
		if (taxSwitch != null) {
			taxSwitch.setOnCheckedChangeListener(taxListener);
		}
		insuranceSwitch= findViewById(R.id.insurance);
		if(insuranceSwitch!=null) {
			insuranceSwitch.setOnCheckedChangeListener(insuranceListener);
		}
	}

	@Override
	public void onBackPressed() {
		double matCapAmount;
		switch (matCapSpinner.getSelectedItemPosition()) {
			case 1:
				matCapAmount = 466617f;
				break;
			case 2:
				matCapAmount = 616617f;
				break;
			default:
				matCapAmount = 0;
				break;
		}
		valuation=findViewById(R.id.valuation);

		Intent intent_loss = new Intent();
		intent_loss.putExtra("MatCap_toMain", matCapAmount);
		intent_loss.putExtra("taxOut_toMain", isTaxOut);
		intent_loss.putExtra("insurance_toMain", isInsurance);
		intent_loss.putExtra("valuation_toMain",  Double.parseDouble(valuation.getText().toString()));
		setResult(RESULT_OK, intent_loss);
		finish();
	}

	CompoundButton.OnCheckedChangeListener taxListener = new CompoundButton.OnCheckedChangeListener() {

		@Override
		public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
			isTaxOut=isChecked;
			Log.v("Switch taxlistener State=", ""+isChecked);
		}

	};

	CompoundButton.OnCheckedChangeListener insuranceListener = new CompoundButton.OnCheckedChangeListener() {

		@Override
		public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
			isInsurance=isChecked;
			Log.v("Switch insuranceListener State=", ""+isChecked);

		}

	};

}


