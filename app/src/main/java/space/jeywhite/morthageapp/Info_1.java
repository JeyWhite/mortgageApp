package space.jeywhite.morthageapp;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Pie;
import com.anychart.core.ui.Legend;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;


public class Info_1 extends AppCompatActivity {
	Mortgage credit;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_info_1);
		double amount = getIntent().getDoubleExtra("amount", 0.0f);
		double interest = getIntent().getDoubleExtra("interest", 0.0f);
		double period = getIntent().getDoubleExtra("period", 0.0f);
		double matСap = getIntent().getDoubleExtra("MatCap", 0.0f);
		double insurance = getIntent().getDoubleExtra("insurance", 0.0);
		double valuation = getIntent().getDoubleExtra("valuation", 0.0);
		boolean isTaxOut = getIntent().getBooleanExtra("taxOut", false);
		double finAmount = amount-matСap;
		if(isTaxOut)
		{
			double taxOut;
			if (finAmount >=3000000) {
				taxOut = 360000;
			}
			else {
				taxOut= finAmount * 0.13;
			}
			finAmount = finAmount -taxOut;
		}


		this.credit = new Mortgage(finAmount, interest, period);

		Pie diag = AnyChart.pie();

		List<DataEntry> information = new ArrayList<>();
		information.add(new ValueDataEntry(getString(R.string.chart_amount)+" "+ Math.round(credit.getAmount()),new BigDecimal(credit.getAmount()).setScale(2, RoundingMode.HALF_EVEN)));
		double overPay = credit.getMultiplier()*credit.getAmount()*credit.getPeriods()-credit.getAmount();
		information.add(new ValueDataEntry(getString(R.string.credit_owerpay) +" "+ Math.round(overPay), new BigDecimal(overPay).setScale(2, RoundingMode.HALF_EVEN)));
		if(insurance>0.0)
		{
			information.add(new ValueDataEntry(getString(R.string.insurance)+" "+ Math.round(insurance) ,new BigDecimal(insurance).setScale(2, RoundingMode.HALF_EVEN)));
		}
		if(valuation>0.0){
			information.add(new ValueDataEntry(getString(R.string.valuation)+" "+ Math.round(valuation),new BigDecimal(valuation).setScale(2, RoundingMode.HALF_EVEN)));
		}


		diag.data(information);
		//Toast T= Toast.makeText(getApplicationContext(), information.get(1).getValue("x").toString(), Toast.LENGTH_LONG);
		//T.show();
		Legend l= diag.legend();
		l.position("center-top");
		l.align("left");
		l.itemsLayout("vertical-expandable");
		AnyChartView anyChartView = (AnyChartView) findViewById(R.id.any_chart_view);
		anyChartView.setChart(diag);

	}

	public void PaymentRender(View p){

		Intent intent_info= new Intent(this, activity_payments.class);
		intent_info.putExtra("multiplier", credit.getMultiplier());
		intent_info.putExtra("creditAmount", credit.getAmount());
		intent_info.putExtra("creditInterest", credit.getInterest());
		intent_info.putExtra("creditPeriod", credit.getPeriods());
		startActivity(intent_info);
	}





}
