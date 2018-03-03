package com.guoyaohua.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageButton;

public class MyImageButton extends ImageButton {

	private int index = -1;

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public MyImageButton(Context context) {
		super(context);
		// TODO: do something here if you want
	}

	public MyImageButton(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO: do something here if you want
	}

	public MyImageButton(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO: do something here if you want
	}

}
