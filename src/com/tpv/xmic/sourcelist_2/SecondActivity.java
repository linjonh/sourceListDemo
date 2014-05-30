package com.tpv.xmic.sourcelist_2;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;

public class SecondActivity extends Activity implements OnFocusChangeListener {
	private ImageView		iv_1;
	private ImageView		iv_2;
	private ImageView		iv_3;
	private ImageView		iv_4;
	private ImageView		iv_5;
	private ImageView		iv_6;
	private ImageView		iv_7;
	private int				mScaleInt	= 20;
	private LayoutParams	origin;

	public SecondActivity() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_main);
		getViewReference();
		registListener();
		
	}

	private void getViewReference() {
		iv_1 = (ImageView) this.findViewById(R.id.img_one);	
		iv_2 = (ImageView) this.findViewById(R.id.img_two);	
		iv_3 = (ImageView) this.findViewById(R.id.img_3);	
		iv_4 = (ImageView) this.findViewById(R.id.img_4);
		iv_5 = (ImageView) this.findViewById(R.id.img_5);
		iv_6 = (ImageView) this.findViewById(R.id.img_6);	
		iv_7 = (ImageView) this.findViewById(R.id.img_7);		
		
	}

	private void registListener(){
		iv_1.setOnFocusChangeListener(this);
		iv_2.setOnFocusChangeListener(this);
		iv_3.setOnFocusChangeListener(this);
		iv_4.setOnFocusChangeListener(this);
		iv_5.setOnFocusChangeListener(this);
		iv_6.setOnFocusChangeListener(this);
		iv_7.setOnFocusChangeListener(this);
	}
	@Override
	public void onFocusChange(View v, boolean hasFocus) {
		origin = v.getLayoutParams();
		setImageScale(v, hasFocus);

	}

	private void setImageScale(View v, boolean hasFocus) {
		if (hasFocus) {
			Log.i("setImageScale", "hasFocus");
			LayoutParams params = new LayoutParams(origin.width + mScaleInt, origin.height + mScaleInt);
			v.setLayoutParams(params);
		} else {
			Log.i("setImageScale", "Not hasFocus");
			LayoutParams params = new LayoutParams(origin.width, origin.height);
			v.setLayoutParams(params);
		}
	}

}
