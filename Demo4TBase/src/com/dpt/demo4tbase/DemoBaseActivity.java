package com.dpt.demo4tbase;

import com.dpt.tbase.app.net.TBaseNetClent2;

import android.app.Activity;

public class DemoBaseActivity extends Activity {

	
	@Override
	protected void onStop() {
		super.onStop();
		TBaseNetClent2.cancelAllRequests();
	}
}
