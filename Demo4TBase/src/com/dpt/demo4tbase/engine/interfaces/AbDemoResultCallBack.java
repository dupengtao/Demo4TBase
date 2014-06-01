package com.dpt.demo4tbase.engine.interfaces;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Handler;
import android.widget.Toast;

import com.dpt.tbase.app.base.engine.IUiBaseResultCallBack;

public abstract class AbDemoResultCallBack<T> implements IUiBaseResultCallBack<T>{
	
	private ProgressDialog pDialog;
	private Context mContent;

	public AbDemoResultCallBack(Context content) {
		super();
		mContent=content;
		pDialog = new ProgressDialog(content);
		pDialog.setMessage("Loading...");

	}

	@Override
	public void onFailureResult(Throwable e, String content) {
	}

	@Override
	public void onStart() {
		pDialog.show();  
	}

	@Override
	public void onFinish() {
		pDialog.dismiss();
	}

	@Override
	public void noNetworkEnvironment() {
		Toast.makeText(mContent, "no network !", Toast.LENGTH_SHORT).show();
	}

}
