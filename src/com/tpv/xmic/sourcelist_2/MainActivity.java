package com.tpv.xmic.sourcelist_2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterViewFlipper;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ViewFlipper;

public class MainActivity extends ActionBarActivity {
	private AdapterViewFlipper	adapterViewFlipper;
	int							imgId[]	= { R.drawable.desert, R.drawable.lighthouse, R.drawable.penguins, R.drawable.koala };
	private ViewFlipper			viewFlipper;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		adapterViewFlipper = (AdapterViewFlipper) this.findViewById(R.id.adapterViewFlipper1);

		adapterViewFlipper.setAdapter(new MyAdapter(this));
		adapterViewFlipper.setAutoStart(true);
		adapterViewFlipper.setFlipInterval(1000);
		viewFlipper = (ViewFlipper) this.findViewById(R.id.viewFlipper1);
		Button btn = (Button) this.findViewById(R.id.button1);
		Button btn2 = (Button) this.findViewById(R.id.button2);
		for (int i = 0; i < 4; i++) {
			ImageView imageView = new ImageView(this);
			LayoutParams params = new LayoutParams(100 * i + 100, 100 * i + 100);
			imageView.setLayoutParams(params);
			imageView.setImageDrawable(this.getResources().getDrawable(imgId[i]));
			viewFlipper.addView(imageView);

		}
		viewFlipper.setAutoStart(true);
		btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				viewFlipper.showNext();
			}
		});
		btn2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent("SA"));
			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	class MyAdapter extends BaseAdapter {
		private Context	mC;

		public MyAdapter(Context c) {
			// TODO Auto-generated constructor stub
			mC = c;
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return 4;
		}

		@Override
		public ImageView getItem(int p) {
			// TODO Auto-generated method stub
			ImageView imageView = new ImageView(mC);
			LayoutParams params = new LayoutParams(100 * p + 100, 100 * p + 100);
			imageView.setLayoutParams(params);
			imageView.setImageDrawable(mC.getResources().getDrawable(imgId[p]));
			return imageView;
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub

			return getItem(position);
		}

	}

}
