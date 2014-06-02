package com.dpt.demo4tbase.engine.interfaces;

import android.app.ProgressDialog;
import android.content.Context;
import android.widget.Toast;

import com.dpt.tbase.app.base.engine.IUiBaseResultCallBack;

public abstract class AbDemoResultCallBack<T> implements
		IUiBaseResultCallBack<T> {

	private ProgressDialog pDialog;
	private Context mContent;
	private boolean isFirst = true;

	public AbDemoResultCallBack(Context content) {
		super();
		mContent = content;
		if (isFirst) {
			pDialog = new ProgressDialog(content);
			pDialog.setMessage("Loading...");
		}

	}

	@Override
	public void onFailureResult(Throwable e, String content) {
	}

	@Override
	public void onStart() {
		if (isFirst) {
			pDialog.show();
		}
	}

	@Override
	public void onFinish() {
		if (isFirst) {
			pDialog.dismiss();
			isFirst = false;
		}
	}

	@Override
	public void noNetworkEnvironment() {
		Toast.makeText(mContent, "no network !", Toast.LENGTH_SHORT).show();
	}

}
