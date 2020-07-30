package space.jeywhite.morthageapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class PayAdapter extends RecyclerView.Adapter<PayAdapter.PayViewHolder> {

	private final List<Payment> payments;

	public PayAdapter(List<Payment> payments) {
		this.payments = payments;
		setHasStableIds(true);
	}


	@NonNull
	@Override
	public PayViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
		View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.payment, viewGroup, false);
		return new PayViewHolder(view);
	}

	@Override
	public void onBindViewHolder(@NonNull PayViewHolder holder, int position) {
		Payment payment= payments.get(position);
		PayViewHolder.bind(payment);

	}

	@Override
	public int getItemCount() {
		System.out.println("pos is"+payments.size());
		return payments.size();
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public int getItemViewType(int position) {
		return position;
	}


	public static class	PayViewHolder extends RecyclerView.ViewHolder {

		private static TextView paymentNumView;
		private static TextView paymentAmountView;
		private static TextView paymentInterestView;
		private static TextView paymentLoanView;
		private static TextView paymentBalanceView;

		public PayViewHolder(@NonNull View itemView) {
			super(itemView);
			paymentNumView = itemView.findViewById(R.id.paymentNum) ;
			paymentAmountView = itemView.findViewById(R.id.paymentAmount);
			paymentInterestView = itemView.findViewById(R.id.paymentInterest);
			paymentLoanView = itemView.findViewById(R.id.paymentLoan);
			paymentBalanceView = itemView.findViewById(R.id.paymentBalanse);

		}
		private static void bind(@NonNull Payment cur){
			paymentNumView.setText(String.valueOf(cur.getNum()));
			paymentAmountView.setText("Сумма платежа "+String.valueOf(new BigDecimal(cur.getPay()).setScale(2, RoundingMode.HALF_EVEN)));
			paymentInterestView.setText("выплата процентов "+String.valueOf(new BigDecimal(cur.getInterestAmount()).setScale(2, RoundingMode.HALF_EVEN)));
			paymentLoanView.setText(String.valueOf("выплата тела займа "+new BigDecimal(cur.getLoanAmount()).setScale(2, RoundingMode.HALF_EVEN)));
			paymentBalanceView.setText(String.valueOf("остаток займа "+new BigDecimal(cur.getBalance()).setScale(2, RoundingMode.HALF_EVEN)));
		}
	}

}
