package com.tpv.xmic.sourcelist_2;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.view.View.OnKeyListener;
import android.view.View.OnLongClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

public class SecondActivity extends Activity implements OnFocusChangeListener, OnLongClickListener, OnKeyListener, OnCheckedChangeListener {
	public SecondActivity() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_main);
		initComponent();
	}

	private void initComponent() {
		getViewReference();
		registFocusListener();
		registClickListenr();
		registOnkeyListenr();
		// set first view ,because it won't call onFocusChange() first of all
		// setImageScale(iv_1, true);
		registCheckChangeListener();
	}

	private void getViewReference() {

		// ImageSignalItem
		for (int i = 0; i < arrIamgeId.length; i++) {
			arrImageItem[i] = (ImageView) this.findViewById(arrIamgeId[i]);
		}
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
		// 模拟信号源移除与增加
		for (int i = 0; i < arrCheckBox.length; i++) {
			arrCheckBox[i] = (CheckBox) this.findViewById(arrCheckBoxId[i]);
			arrCheckBox[i].setChecked(true);
			mBooleanArray.append(i, true);
		}
	}

	private void registFocusListener() {
		// ImageSignalItem
		for (int i = 0; i < arrIamgeId.length; i++) {
			arrImageItem[i].setOnFocusChangeListener(this);
		}
		// MenuButton
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
		// ImageSignalItem
		for (int i = 0; i < arrIamgeId.length; i++) {
			arrImageItem[i].setOnLongClickListener(this);
		}

	}

	private void registOnkeyListenr() {
		// ImageSignalItem
		for (int i = 0; i < arrIamgeId.length; i++) {
			arrImageItem[i].setOnKeyListener(this);
		}
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

	private int findSignalItemFocusPosition() {
		if (arrImageItem[0].isFocused()) {
			return 0;
		} else if (arrImageItem[1].isFocused()) {
			return 1;
		} else if (arrImageItem[2].isFocused()) {
			return 2;
		} else if (arrImageItem[3].isFocused()) {
			return 3;
		} else if (arrImageItem[4].isFocused()) {
			return 4;
		} else if (arrImageItem[5].isFocused()) {
			return 5;
		} else if (arrImageItem[6].isFocused()) {
			return 6;
		} else
			return -1;
	}

	private void registCheckChangeListener() {
		for (int i = 0; i < arrCheckBox.length; i++) {
			arrCheckBox[i].setOnCheckedChangeListener(this);
		}
	}

	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		// TODO checkBox
		int id = buttonView.getId();
		switch (id) {
		case R.id.s1:
			setItemVisibility(arrImageItem, arrTextVew, isChecked, 0);
			break;
		case R.id.s2:
			setItemVisibility(arrImageItem, arrTextVew, isChecked, 1);
			break;
		case R.id.s3:
			setItemVisibility(arrImageItem, arrTextVew, isChecked, 2);
			break;
		case R.id.s4:
			setItemVisibility(arrImageItem, arrTextVew, isChecked, 3);
			break;
		case R.id.s5:
			setItemVisibility(arrImageItem, arrTextVew, isChecked, 4);
			break;
		case R.id.s6:
			setItemVisibility(arrImageItem, arrTextVew, isChecked, 5);
			break;
		case R.id.s7:
			setItemVisibility(arrImageItem, arrTextVew, isChecked, 6);
			break;

		default:
			break;
		}

	}

	// private

	private void setItemVisibility(ImageView[] iv, TextView[] tv, boolean isChecked, int index) {
		int focusPos = findSignalItemFocusPosition();
		if (isChecked) {
			iv[index].setVisibility(View.VISIBLE);
			tv[index].setVisibility(View.VISIBLE);
			if (focusPos != -1)
				iv[focusPos].requestFocus();
		} else {
			iv[index].setVisibility(View.GONE);
			tv[index].setVisibility(View.GONE);
			// <---------------
			// 模拟情况先注释掉，没有checkBox模拟，真实自检信号源情况时反注释过来。
			// int p = findSignalItemFocusPosition();
			// if (p >= index){
			if (focusPos != -1 && index < 6) {
				iv[index + 1].requestFocus();
			}
			// } ---------->
		}
	}

	/*
	 * ImageView设置捕捉key事件，返回false以让父控件层能再捕捉到key。(non-Javadoc)
	 * 
	 * @see android.view.View.OnKeyListener#onKey(android.view.View, int,
	 * android.view.KeyEvent)
	 */
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
		mItemFocusPos = findSignalItemFocusPosition();
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
			View view = getImgeViewByPosition(mItemClickPos);
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
				setMenuBtnVisibility(mItemClickPos);
			}
		}
	}

	/**
	 * 
	 * 
	 * @return
	 */
	private View getImgeViewByPosition(int p) {
		switch (p) {
		case 0:
			return arrImageItem[0];
		case 1:
			return arrImageItem[1];
		case 2:
			return arrImageItem[2];
		case 3:
			return arrImageItem[3];
		case 4:
			return arrImageItem[4];
		case 5:
			return arrImageItem[5];
		case 6:
			return arrImageItem[6];

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
		mItemClickPos = signal_position;
		int renameBtn = 0;
		int setHome = 1;
		int rest = 2;
		if (!mIsBtnVisibale) {
			arrBtn[renameBtn][signal_position].setVisibility(View.VISIBLE);
			arrBtn[setHome][signal_position].setVisibility(View.VISIBLE);
			arrBtn[rest][signal_position].setVisibility(View.VISIBLE);
//			arrBtn[renameBtn][signal_position].setWidth(arrBtn[renameBtn][signal_position].getWidth() + mScaleInt);
//			arrBtn[setHome][signal_position].setWidth(arrBtn[setHome][signal_position].getWidth() + mScaleInt);
//			arrBtn[rest][signal_position].setWidth(arrBtn[rest][signal_position].getWidth() + mScaleInt);
		} else if (mIsBtnVisibale) {
			arrBtn[renameBtn][signal_position].setVisibility(View.GONE);
			arrBtn[setHome][signal_position].setVisibility(View.GONE);
			arrBtn[rest][signal_position].setVisibility(View.GONE);
//			arrBtn[renameBtn][signal_position].setWidth(arrBtn[renameBtn][signal_position].getWidth() - mScaleInt);
//			arrBtn[setHome][signal_position].setWidth(arrBtn[setHome][signal_position].getWidth() - mScaleInt);
//			arrBtn[rest][signal_position].setWidth(arrBtn[rest][signal_position].getWidth() - mScaleInt);
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
			// return true;
		}
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			if (mIsBtnVisibale) {
				setMenuBtnVisibility(mItemClickPos);
				return true;
			}
			
		}

		return super.onKeyDown(keyCode, event);
	}

	// 信号源预览图片
	private ImageView			iv_1, iv_2, iv_3, iv_4, iv_5, iv_6, iv_7;																	// 不能直接引用，要使用数组访问才不会出现NullException
	private ImageView[]			arrImageItem	= { iv_1, iv_2, iv_3, iv_4, iv_5, iv_6, iv_7 };
	private int[]				arrIamgeId		= { R.id.img_1, R.id.img_2, R.id.img_3, R.id.img_4, R.id.img_5, R.id.img_6, R.id.img_7, };
	// MenuButton
	private Button				mRename1, mRename2, mRename3, mRename4, mRename5, mRename6, mRename7;
	private Button				mSetHome1, mSetHome2, mSetHome3, mSetHome4, mSetHome5, mSetHome6, mSetHome7;
	private Button				mRest1, mRest2, mRest3, mRest4, mRest5, mRest6, mRest7;
	private Button[][]			arrBtn			= { { mRename1, mRename2, mRename3, mRename4, mRename5, mRename6, mRename7 },
			{ mSetHome1, mSetHome2, mSetHome3, mSetHome4, mSetHome5, mSetHome6, mSetHome7 },
			{ mRest1, mRest2, mRest3, mRest4, mRest5, mRest6, mRest7 }, };
	private int[][]				arrBtnId		= {
			{ R.id.rename_btn_1, R.id.rename_btn_2, R.id.rename_btn_3, R.id.rename_btn_4, R.id.rename_btn_5, R.id.rename_btn_6, R.id.rename_btn_7 },
			{ R.id.set_home_page_1, R.id.set_home_page_2, R.id.set_home_page_3, R.id.set_home_page_4, R.id.set_home_page_5, R.id.set_home_page_6,
			R.id.set_home_page_7 }, { R.id.rest_1, R.id.rest_2, R.id.rest_3, R.id.rest_4, R.id.rest_5, R.id.rest_6, R.id.rest_7 }, };
	// TextView
	private int[]				mTv_id			= { R.id.signal_text_1, R.id.signal_text_2, R.id.signal_text_3, R.id.signal_text_4,
			R.id.signal_text_5, R.id.signal_text_6, R.id.signal_text_7, };
	private TextView			mTv_1, mTv_2, mTv_3, mTv_4, mTv_5, mTv_6, mTv_7;
	private TextView[]			arrTextVew		= { mTv_1, mTv_2, mTv_3, mTv_4, mTv_5, mTv_6, mTv_7, };
	// CheckBox模拟信号源检测
	private CheckBox			mCheckBox1, mCheckBox2, mCheckBox3, mCheckBox4, mCheckBox5, mCheckBox6, mCheckBox7;
	private CheckBox[]			arrCheckBox		= { mCheckBox1, mCheckBox2, mCheckBox3, mCheckBox4, mCheckBox5, mCheckBox6, mCheckBox7, };
	private int[]				arrCheckBoxId	= { R.id.s1, R.id.s2, R.id.s3, R.id.s4, R.id.s5, R.id.s6, R.id.s7, };
	private LayoutParams		mOrigin;
	private int					mScaleInt		= 40;
	// 左右焦点移动，离开标识
	private boolean				mFocusLeave		= false;
	// 点击显示ItemMenu的位置
	private int					mItemClickPos	= -1;
	// 焦点位置
	private int					mItemFocusPos	= -1;
	private SparseBooleanArray	mBooleanArray	= new SparseBooleanArray();
	// ItemMenu显示与否标识
	private boolean				mIsBtnVisibale	= false;

}
