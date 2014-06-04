package com.tpv.xmic.sourcelist_2;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

public class TestHorizontalListView extends Activity {
	private int[]	imgSrc	= { R.drawable.hydrangeas, R.drawable.tulips, R.drawable.koala, R.drawable.lighthouse, R.drawable.desert,
			R.drawable.jellyfish, R.drawable.penguins };

	public TestHorizontalListView() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.horizontal_listview);
		ListView listView = (ListView) this.findViewById(R.id.listView1);

		listView.setAdapter(new MyAdapter(this));
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
			return 7;
		}

		@Override
		public View getItem(int p) {
			// TODO Auto-generated method stub
			View view = View.inflate(mC, R.layout.item_image, null);
			ImageView imageView = (ImageView) view.findViewById(R.id.item_img);
			imageView.setImageResource(imgSrc[p]);
			imageView.setOnClickListener(clickListener);
			view.findViewById(R.id.rename_btn).setOnClickListener(clickListener);
			view.findViewById(R.id.rest).setOnClickListener(clickListener);
			view.findViewById(R.id.apply_home).setOnClickListener(clickListener);
			return view;
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
//		private Button btn1;
//		private Button btn2;
//		private Button btn3;
		OnClickListener	clickListener	= new OnClickListener() {

											@Override
											public void onClick(View v) {
												// TODO Auto-generated method
												// stub
//												if (v.getId() == R.id.item_img) {
//													
//												}
												Toast.makeText(mC, "点击了button事件。。。", Toast.LENGTH_SHORT).show();
											}
										};
	}
}
