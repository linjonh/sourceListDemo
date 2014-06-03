package com.tpv.xmic.sourcelist_2;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.widget.ImageView;
import android.widget.RelativeLayout.LayoutParams;

public class SecondActivity extends Activity implements OnFocusChangeListener {
	private ImageView		iv_1;
	private ImageView		iv_2;
	private ImageView		iv_3;
	private ImageView		iv_4;
	private ImageView		iv_5;
	private ImageView		iv_6;
	private ImageView		iv_7;
	// private ImageView Img_test_1;
	// private ImageView Img_test_2;
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
		registListener();
		setImageScale(iv_1, true);// set first view ,because it won't call
									// onFocusChange() first of all
	}

	private void getViewReference() {
		// Img_test_1 = (ImageView) this.findViewById(R.id.img_test_1);
		// Img_test_2 = (ImageView) this.findViewById(R.id.img_test_2);

		iv_1 = (ImageView) this.findViewById(R.id.img_one);
		iv_2 = (ImageView) this.findViewById(R.id.img_two);
		iv_3 = (ImageView) this.findViewById(R.id.img_3);
		iv_4 = (ImageView) this.findViewById(R.id.img_4);
		iv_5 = (ImageView) this.findViewById(R.id.img_5);
		iv_6 = (ImageView) this.findViewById(R.id.img_6);
		iv_7 = (ImageView) this.findViewById(R.id.img_7);

	}

	private void registListener() {
		// Img_test_1.setOnFocusChangeListener(this);
		// Img_test_2.setOnFocusChangeListener(this);
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
		setImageScale(v, hasFocus);

	}

	/**
	 * ������һ��RelativeLayout��Ϊһ��Item���������ImageView��һ��TextView��
	 * ����ֻ��ȡImageView��ʵ�ַŴ�ͽ���仯��
	 * ���ֲ��ֵ��ص������Item�ؼ�����RelativeLayout���Ŀ�߲����ǹ̶�ֵ��match_parent��ֵ��
	 * ��Ҫwrap_content������ֵ������ImageView�Ŵ�󸸿ؼ�Ҳ����ŷŴ�
	 * 
	 * @author john.lin
	 * @param v
	 *            ����仯��ͼ
	 * @param hasFocus
	 *            �Ƿ��ý���
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
		}
	}

}
