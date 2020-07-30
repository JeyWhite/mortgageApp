package space.jeywhite.morthageapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;
import android.os.Bundle;

public class activity_payments extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_payments);
		final List<Payment> pays= getPayList();
		RecyclerView recyclerView = findViewById(R.id.payments_view);
		PayAdapter payAdapter = new PayAdapter(pays);
		recyclerView.setAdapter(payAdapter);
		LinearLayoutManager pLayoutManager = new LinearLayoutManager(this);
		recyclerView.setLayoutManager(pLayoutManager);


	}

	private List<Payment> getPayList(){
		//импорт нужных данных
		double amount = getIntent().getDoubleExtra("creditAmount", 0.0f);
		double interest = getIntent().getDoubleExtra("creditInterest", 0.0f);
		double periods = getIntent().getDoubleExtra("creditPeriod", 0.0f);
		double multiplier = getIntent().getDoubleExtra("multiplier", 0.0f);

		List<Payment> pays= new ArrayList<>();
		int i=1;
		pays.add(new Payment( i, multiplier, amount, interest, amount));
		for (; i<periods; i++){
			pays.add(new Payment( i+1, multiplier, amount, interest, pays.get(i-1).getBalance()));
			//for debug
			//System.out.println("платеж№ "+i);
			//pays.get(i).show();
		}
		return pays;
	}
}
