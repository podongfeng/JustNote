package com.podongfeng.justnote.layout;

import android.app.Activity;
import android.content.Context;
import android.view.KeyEvent;
import android.widget.RelativeLayout;

public class DetailRelativeLayout extends RelativeLayout {

	public DetailRelativeLayout(Context context) {
		super(context);
	}
	
	@Override
	public boolean dispatchKeyEventPreIme(KeyEvent event) {
		Activity activity = (Activity)getContext();
		if(event.getKeyCode() == KeyEvent.KEYCODE_BACK    
                && event.getAction() == KeyEvent.ACTION_DOWN){
			activity.finish();
			return true;
		}
		return super.dispatchKeyEventPreIme(event);
	}

}
