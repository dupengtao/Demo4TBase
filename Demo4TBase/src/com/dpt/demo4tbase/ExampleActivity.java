package com.dpt.demo4tbase;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class ExampleActivity extends DemoBaseActivity{
	private Context mContext;
	private Button mBtnList;
	private Button mBtnImage;
	private Button mBtnListImages;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mContext = this;
		setContentView(R.layout.activity_example);
		initView();
		setEvents();
	}

	private void setEvents() {
		mBtnList.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(mContext, ListActivity.class);
				startActivity(intent);
			}
		});
		mBtnImage.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(mContext, ImageActivity.class);
				startActivity(intent);
			}
		});
		mBtnListImages.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(mContext, ImagesActivity.class);
				startActivity(intent);
			}
		});
	}

	private void initView() {
		mBtnList=(Button)findViewById(R.id.btnList);
		mBtnImage=(Button)findViewById(R.id.btnImage);
		mBtnListImages=(Button)findViewById(R.id.btnListImages);
	}
}
