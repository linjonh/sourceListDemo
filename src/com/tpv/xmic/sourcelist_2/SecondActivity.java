package com.tpv.xmic.sourcelist_2;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

public class SecondActivity extends Activity implements OnFocusChangeListener, OnClickListener {
	private ImageView		iv_1;
	private ImageView		iv_2;
	private ImageView		iv_3;
	private ImageView		iv_4;
	private ImageView		iv_5;
	private ImageView		iv_6;
	private ImageView		iv_7;
	private Button			mRename1;
	private Button			mRest1;
	private Button			mSetHome1;

	private Button			mRename2;
	private Button			mRest2;
	private Button			mSetHome2;

	private Button			mRename3;
	private Button			mRest3;
	private Button			mSetHome3;

	private Button			mRename4;
	private Button			mRest4;
	private Button			mSetHome4;

	private Button			mRename5;
	private Button			mRest5;
	private Button			mSetHome5;

	private Button			mRename6;
	private Button			mRest6;
	private Button			mSetHome6;

	private Button			mRename7;
	private Button			mRest7;
	private Button			mSetHome7;
	private Button[][]		arrBtn		= { { mRename1, mRename2, mRename3, mRename4, mRename5, mRename6, mRename7 },
			{ mRest1, mRest2, mRest3, mRest4, mRest5, mRest6, mRest7 },
			{ mSetHome1, mSetHome2, mSetHome3, mSetHome4, mSetHome5, mSetHome6, mSetHome7 } };
	private int[][]			arrBtnId	= {
			{ R.id.rename_btn_1, R.id.rename_btn_2, R.id.rename_btn_3, R.id.rename_btn_4, R.id.rename_btn_5, R.id.rename_btn_6, R.id.rename_btn_7 },
			{ R.id.rest_1, R.id.rest_2, R.id.rest_3, R.id.rest_4, R.id.rest_5, R.id.rest_6, R.id.rest_7 },
			{ R.id.set_home_page_1, R.id.set_home_page_2, R.id.set_home_page_3, R.id.set_home_page_4, R.id.set_home_page_5, R.id.set_home_page_6,
			R.id.set_home_page_7 }		};

	private int[]			mTv_id		= { R.id.signal_text_1, R.id.signal_text_2, R.id.signal_text_3, R.id.signal_text_4, R.id.signal_text_5,
			R.id.signal_text_6, R.id.signal_text_7, };
	private TextView		mTv_1;
	private TextView		mTv_2;
	private TextView		mTv_3;
	private TextView		mTv_4;
	private TextView		mTv_5;
	private TextView		mTv_6;
	private TextView		mTv_7;
	private TextView[]		arrTextVew	= { mTv_1, mTv_2, mTv_3, mTv_4, mTv_5, mTv_6, mTv_7, };
	private boolean			mFocusLeave;

	private int				mScaleInt	= 40;
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
		registFocusListener();
		registClickListenr();
		setImageScale(iv_1, true);// set first view ,because it won't call
									// onFocusChange() first of all
	}

	private void getViewReference() {

		iv_1 = (ImageView) this.findViewById(R.id.img_one);
		iv_2 = (ImageView) this.findViewById(R.id.img_two);
		iv_3 = (ImageView) this.findViewById(R.id.img_3);
		iv_4 = (ImageView) this.findViewById(R.id.img_4);
		iv_5 = (ImageView) this.findViewById(R.id.img_5);
		iv_6 = (ImageView) this.findViewById(R.id.img_6);
		iv_7 = (ImageView) this.findViewById(R.id.img_7);
		// 全部初始化不可见
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 7; j++) {
				arrBtn[i][j] = (Button) this.findViewById(arrBtnId[i][j]);
				if (arrBtn[i][j] != null) {
					arrBtn[i][j].setVisibility(View.GONE);
					Log.i("arrBtn", "View.GONE" + arrBtn[i][j].toString());
				}
			}
		}

		for (int i = 0; i < arrTextVew.length; i++) {
			arrTextVew[i] = (TextView) this.findViewById(mTv_id[i]);
			arrTextVew[i].setText("signal " + (i + 1));
		}

	}

	private void registFocusListener() {

		iv_1.setOnFocusChangeListener(this);
		iv_2.setOnFocusChangeListener(this);
		iv_3.setOnFocusChangeListener(this);
		iv_4.setOnFocusChangeListener(this);
		iv_5.setOnFocusChangeListener(this);
		iv_6.setOnFocusChangeListener(this);
		iv_7.setOnFocusChangeListener(this);
	}

	private void registClickListenr() {
		iv_1.setOnClickListener(this);
		iv_2.setOnClickListener(this);
	}

	@Override
	public void onFocusChange(View v, boolean hasFocus) {
		setImageScale(v, hasFocus);

	}

	/**
	 * 布局是一个RelativeLayout作为一个Item，里面包含ImageView和一个TextView，
	 * 这样只获取ImageView来实现放大和焦点变化。
	 * 这种布局的特点必须是Item控件（即RelativeLayout）的宽高不能是固定值或match_parent的值
	 * （如果这样的设置会出现不放大或反而变得很小）， 需要wrap_content的属性值，这样ImageView放大后父控件也会跟着放大。
	 * 此处的放大思想是款和高都加上一个缩放值。
	 * 
	 * @author john.lin
	 * @param v
	 *            焦点变化视图
	 * @param hasFocus
	 *            是否获得焦点
	 */
	private void setImageScale(View v, boolean hasFocus) {
		origin = (LayoutParams) v.getLayoutParams();
		if (hasFocus) {
			Log.i("setImageScale", "hasFocus");
			LayoutParams params = new LayoutParams(origin.width + mScaleInt, origin.height + mScaleInt);
			v.setLayoutParams(params);
		} else {
			Log.i("setImageScale", "Not hasFocus");
			LayoutParams params = new LayoutParams(origin.width - mScaleInt, origin.height - mScaleInt);
			v.setLayoutParams(params);
			if (mFocusLeave) {

				if (mRename1 != null)
					this.mRename1.setVisibility(View.GONE);
				if (mRest1 != null)
					this.mRest1.setVisibility(View.GONE);
				if (mSetHome1 != null)
					this.mSetHome1.setVisibility(View.GONE);
			}
		}
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

		switch (v.getId()) {
		case R.id.img_one:
			
			break;
		case R.id.img_two:
			
			break;

		default:
			break;
		}
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if (keyCode == KeyEvent.KEYCODE_DPAD_RIGHT || keyCode == KeyEvent.KEYCODE_DPAD_LEFT) {
			mFocusLeave = true;
		} else {
			mFocusLeave = false;
		}
		// if (keyCode == KeyEvent.KEYCODE_BACK) {
		// if (mPopupWindow != null)
		// mPopupWindow.dismiss();
		// return true;
		// }

		return super.onKeyDown(keyCode, event);
	}
}
