package cs.purdue.lab13;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class StockWatcherActivity extends Activity {

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		((TextView) findViewById(R.id.txt)).setTextColor(Color.BLUE);

		final StockListAdapter adapter = new StockListAdapter(this);
		ListView lv = (ListView) findViewById(R.id.list);
		lv.setAdapter(adapter);

		((Button) findViewById(R.id.txtbxb))
				.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View arg0) {
						synchronized (Stock.stocks_array) {
							EditText e = (EditText) StockWatcherActivity.this
									.findViewById(R.id.txtbxe);
							Stock[] array = new Stock[Stock.stocks_array.length + 1];
							for (int i = Stock.stocks_array.length - 1; i >= 0; i--) {
								array[i] = Stock.stocks_array[i];
							}
							array[Stock.stocks_array.length] = new Stock();
							array[Stock.stocks_array.length].name = e.getText()
									.toString();
							array[Stock.stocks_array.length].price = (int) (Math
									.random() * 1000);
							array[Stock.stocks_array.length].price_change_percent = 0;
							Stock.stocks_array = array;
							adapter.notifyDataSetChanged();
						}
					}

				});

		new Thread(new Runnable() {

			@Override
			public void run() {
				StockUpdater.main(null);
			}

		}).start();
		
		Stock.handler = new Handler();
//		final Handler handler = new Handler();
//		Runnable updateList = new Runnable(){
//
//			@Override
//			public void run() {
//				adapter.notifyDataSetChanged();
//				handler.postDelayed(this, 5000);
//			}
//		};
//		handler.postDelayed(updateList, 5000);
		
	}

	class StockListAdapter extends BaseAdapter {
		Activity ctx;

		public StockListAdapter(Activity ctx) {
			this.ctx = ctx;
			Stock.stocks_array = new Stock[0];
			Stock.list=this;
//			Stock.stocks_array[0] = new Stock();
//			Stock.stocks_array[0].name = "GOOG";
//			Stock.stocks_array[0].price = 500.89;
//			Stock.stocks_array[0].price_change_percent = .06;
		}

		@Override
		public int getCount() {

			return Stock.stocks_array.length;
		}

		@Override
		public Object getItem(int position) {
			return null;
		}

		@Override
		public long getItemId(int position) {
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {

			LayoutInflater inflater = (LayoutInflater) ctx.getLayoutInflater();
			View layout = inflater.inflate(R.layout.list_item, null);

			((TextView) layout.findViewById(R.id.name))
					.setText(Stock.stocks_array[position].name);
			((TextView) layout.findViewById(R.id.prc))
					.setText(" "
							+  (int)(Stock.stocks_array[position].price_change_percent*100)
							+ "%");
			TextView price = (TextView) layout.findViewById(R.id.prc);
			if (Stock.stocks_array[position].price_change_percent > 0)
				price.setTextColor(Color.GREEN);
			else
				price.setTextColor(Color.RED);

			((TextView) layout.findViewById(R.id.pr)).setText(" "
					+ Stock.stocks_array[position].price);

			return layout;
		}

	}

}