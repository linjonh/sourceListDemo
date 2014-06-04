package com.tpv.xmic.sourcelist_2;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.view.View.OnKeyListener;
import android.view.View.OnLongClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

public class SecondActivity extends Activity implements OnFocusChangeListener, OnLongClickListener, OnKeyListener {

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
		registOnkeyListenr();
		setImageScale(iv_1, true);// set first view ,because it won't call
									// onFocusChange() first of all
	}

	private void getViewReference() {

		iv_1 = (ImageView) this.findViewById(R.id.img_1);
		iv_2 = (ImageView) this.findViewById(R.id.img_2);
		iv_3 = (ImageView) this.findViewById(R.id.img_3);
		iv_4 = (ImageView) this.findViewById(R.id.img_4);
		iv_5 = (ImageView) this.findViewById(R.id.img_5);
		iv_6 = (ImageView) this.findViewById(R.id.img_6);
		iv_7 = (ImageView) this.findViewById(R.id.img_7);
		// button全部初始化不可见
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 7; j++) {
				arrBtn[i][j] = (Button) this.findViewById(arrBtnId[i][j]);
				if (arrBtn[i][j] != null) {
					arrBtn[i][j].setVisibility(View.GONE);
					Log.i("arrBtn", "View.GONE" + arrBtn[i][j].toString());
				}
			}
		}
		// 信号源信息
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
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 7; j++) {
				if (arrBtn[i][j] != null) {
					arrBtn[i][j].setOnFocusChangeListener(this);
					Log.i("arrBtn[" + i + "][" + j + "]", "setOnFocusChangeListener");
				}
			}
		}
	}

	private void registClickListenr() {
		iv_1.setOnLongClickListener(this);
		iv_2.setOnLongClickListener(this);
		iv_3.setOnLongClickListener(this);
		iv_4.setOnLongClickListener(this);
		iv_5.setOnLongClickListener(this);
		iv_6.setOnLongClickListener(this);
		iv_7.setOnLongClickListener(this);
	}

	private void registOnkeyListenr() {
		iv_1.setOnKeyListener(this);
		iv_2.setOnKeyListener(this);
		iv_3.setOnKeyListener(this);
		iv_4.setOnKeyListener(this);
		iv_5.setOnKeyListener(this);
		iv_6.setOnKeyListener(this);
		iv_7.setOnKeyListener(this);
		// button全部设置setOnKeyListener
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 7; j++) {
				if (arrBtn[i][j] != null) {
					arrBtn[i][j].setOnKeyListener(this);
					Log.i("arrBtn[" + i + "][" + j + "]", "setOnKeyListener");
				}
			}
		}
	}

	// ImageView设置捕捉key事件，返回false以让父控件层能再捕捉到key。
	@Override
	public boolean onKey(View v, int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		// <-----用于测试用代码
		int p = -1;
		switch (v.getId()) {
		case R.id.img_1:
			p = 1;
			break;
		case R.id.img_2:
			p = 2;
			break;
		case R.id.img_3:
			p = 3;
			break;
		case R.id.img_4:
			p = 4;
			break;
		case R.id.img_5:
			p = 5;
			break;
		case R.id.img_6:
			p = 6;
			break;
		case R.id.img_7:
			p = 7;
			break;
		default:
			break;
		}
		// ----->
		if (keyCode == KeyEvent.KEYCODE_DPAD_RIGHT || keyCode == KeyEvent.KEYCODE_DPAD_LEFT) {
			mFocusLeave = true;
			// Toast.makeText(this, "left or right,position:"+p,
			// Toast.LENGTH_SHORT).show();
			Log.i("onKey of view ，mFocusLeave", "mFocusLeave:" + mFocusLeave + "position:" + p);
			return false;
		}
		if (keyCode == KeyEvent.KEYCODE_DPAD_DOWN || keyCode == KeyEvent.KEYCODE_DPAD_UP) {
			mFocusLeave = false;
			return false;
		}
		return false;
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
		if (v instanceof ImageView) {
			beginScale(v, hasFocus);
		} else {
			View view = getImgeViewByPosition(mSignalLongPressPosition);
			if (view == null) {
				return;
			}
			beginScale(view, hasFocus);
		}
	}

	private void beginScale(View v, boolean hasFocus) {
		mOrigin = (LayoutParams) v.getLayoutParams();
		if (hasFocus) {
			Log.i("setImageScale", "hasFocus");
			LayoutParams params = new LayoutParams(mOrigin.width + mScaleInt, mOrigin.height + mScaleInt);
			v.setLayoutParams(params);
		} else {
			Log.i("setImageScale", "Not hasFocus");
			LayoutParams params = new LayoutParams(mOrigin.width - mScaleInt, mOrigin.height - mScaleInt);
			v.setLayoutParams(params);
			if (mFocusLeave && mIsBtnVisibale) {
				setMenuBtnVisibility(mSignalLongPressPosition);
			}
		}
	}

	/**
	 * 当焦点是Image之间切换时返回true，在Image与button之间切换则不返回true。
	 * 
	 * @return
	 */
	private View getImgeViewByPosition(int p) {
		switch (p) {
		case 0:
			return iv_1;
		case 1:
			return iv_2;
		case 2:
			return iv_3;
		case 3:
			return iv_4;
		case 4:
			return iv_5;
		case 5:
			return iv_6;
		case 6:
			return iv_7;

		default:
			return null;
		}

	}

	@Override
	public boolean onLongClick(View v) {

		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.img_1:
			setMenuBtnVisibility(0);
			return true;
		case R.id.img_2:
			setMenuBtnVisibility(1);
			return true;
		case R.id.img_3:
			setMenuBtnVisibility(2);
			return true;
		case R.id.img_4:
			setMenuBtnVisibility(3);
			return true;
		case R.id.img_5:
			setMenuBtnVisibility(4);
			return true;
		case R.id.img_6:
			setMenuBtnVisibility(5);
			return true;
		case R.id.img_7:
			setMenuBtnVisibility(6);
			return true;
		default:
			return false;
		}

	}

	private void setMenuBtnVisibility(int signal_position) {
		if (signal_position == -1)
			return;
		mSignalLongPressPosition = signal_position;
		int renameBtn = 0;
		int setHome = 1;
		int rest = 2;
		if (!mIsBtnVisibale) {
			arrBtn[renameBtn][signal_position].setVisibility(View.VISIBLE);
			arrBtn[setHome][signal_position].setVisibility(View.VISIBLE);
			arrBtn[rest][signal_position].setVisibility(View.VISIBLE);
		} else if (mIsBtnVisibale) {
			arrBtn[renameBtn][signal_position].setVisibility(View.GONE);
			arrBtn[setHome][signal_position].setVisibility(View.GONE);
			arrBtn[rest][signal_position].setVisibility(View.GONE);
		}
		mIsBtnVisibale = !mIsBtnVisibale;
	}

	// 全局的onkey，在HorizontalScrollView之内捕捉不到left和right，只能在HorizontalScrollView之外
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if (keyCode == KeyEvent.KEYCODE_DPAD_RIGHT || keyCode == KeyEvent.KEYCODE_DPAD_LEFT) {
			mFocusLeave = true;
			// Toast.makeText(this, "left or right", Toast.LENGTH_SHORT).show();
			Log.i("onKeyDown_mFocusLeave", "mFocusLeave:" + mFocusLeave);
			return true;
		}
		// if (keyCode == KeyEvent.KEYCODE_BACK) {
		//
		// return true;
		// }

		return super.onKeyDown(keyCode, event);
	}

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
	private Button[][]		arrBtn			= { { mRename1, mRename2, mRename3, mRename4, mRename5, mRename6, mRename7 },
			{ mSetHome1, mSetHome2, mSetHome3, mSetHome4, mSetHome5, mSetHome6, mSetHome7 },
			{ mRest1, mRest2, mRest3, mRest4, mRest5, mRest6, mRest7 }, };
	private int[][]			arrBtnId		= {
			{ R.id.rename_btn_1, R.id.rename_btn_2, R.id.rename_btn_3, R.id.rename_btn_4, R.id.rename_btn_5, R.id.rename_btn_6, R.id.rename_btn_7 },
			{ R.id.set_home_page_1, R.id.set_home_page_2, R.id.set_home_page_3, R.id.set_home_page_4, R.id.set_home_page_5, R.id.set_home_page_6,
			R.id.set_home_page_7 }, { R.id.rest_1, R.id.rest_2, R.id.rest_3, R.id.rest_4, R.id.rest_5, R.id.rest_6, R.id.rest_7 }, };

	private int[]			mTv_id			= { R.id.signal_text_1, R.id.signal_text_2, R.id.signal_text_3, R.id.signal_text_4, R.id.signal_text_5,
			R.id.signal_text_6, R.id.signal_text_7, };
	private TextView		mTv_1;
	private TextView		mTv_2;
	private TextView		mTv_3;
	private TextView		mTv_4;
	private TextView		mTv_5;
	private TextView		mTv_6;
	private TextView		mTv_7;
	private TextView[]		arrTextVew		= { mTv_1, mTv_2, mTv_3, mTv_4, mTv_5, mTv_6, mTv_7, };
	private LayoutParams	mOrigin;
	private int				mScaleInt		= 40;
	// 左右焦点移动，离开标识
	private boolean			mFocusLeave		= false;
	// 点击显示ItemMenu的位置
	private int				mSignalLongPressPosition	= -1;
	// ItemMenu显示与否标识
	private boolean			mIsBtnVisibale	= false;

}
