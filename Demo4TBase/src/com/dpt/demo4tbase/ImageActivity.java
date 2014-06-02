package com.dpt.demo4tbase;

import com.android.volley.toolbox.NetworkImageView;
import com.dpt.demo4tbase.net.UriHelper;
import com.dpt.tbase.app.application.TBaseApplication;
import com.dpt.tbase.app.net.TBaseNetClent2;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class ImageActivity extends Activity{
	
	private NetworkImageView mNiv;
	private ImageView mIv;
	private Button mBtnCache;
	private Context mContext;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mContext=this;
		setContentView(R.layout.activity_image);
		initView();
		setEvents();
	}

	private void setEvents() {
		mBtnCache.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//图片缓存有2级，一级利用lru算法存储在 内存中，一级存储在data/data/cache 中，这里清理的是中的缓存
				long cacheSize = TBaseNetClent2.getCacheSize(mContext);
				Toast.makeText(mContext, "缓存大小"+cacheSize, Toast.LENGTH_SHORT).show();
				TBaseNetClent2.cacheClear();
			}
		});
	}

	private void initView() {
		mBtnCache=(Button)findViewById(R.id.btnCache);
		mNiv=(NetworkImageView)findViewById(R.id.niv);
		mIv=(ImageView)findViewById(R.id.iv);
	}

	@Override
	protected void onResume() {
		super.onResume();
		loadNiv();
		loadIv();
	}

	private void loadIv() {
		TBaseNetClent2.executeImageRequest(UriHelper.getImageUrl(), mIv, R.drawable.ic_launcher, R.drawable.net_error);
	}

	private void loadNiv() {
		mNiv.setImageUrl(UriHelper.getImageUrl2(), TBaseApplication.getInstance().getImageLoader());
	}
}
