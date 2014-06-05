package com.tpv.xmic.sourcelist_2;

import android.app.Activity;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.widget.TextView;

public class TestSparseBooleanArray extends Activity {
	SparseBooleanArray	list	= new SparseBooleanArray();

	public TestSparseBooleanArray() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sparse);
		for (int i = 0; i < 7; i++) {
			list.append(i, true);
		}
		TextView tv = ((TextView) this.findViewById(R.id.textView1));
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < 7; i++) {
			builder.append("\n" + i + list.valueAt(i));
		}
		list.append(2, false);
		builder.append("\nlist:2" + list.valueAt(2));
		list.put(0, false);
		builder.append("\nlist:0" + list.valueAt(0));			
		for (int i = 0; i < 7; i++) {			
			builder.append("\n" + i + list.valueAt(i));
		}
		tv.setText(builder.toString());
	}
}
