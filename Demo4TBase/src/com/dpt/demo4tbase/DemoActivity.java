package com.dpt.demo4tbase;

import java.util.List;

import com.dpt.demo4tbase.adapter.DemoAdapter;
import com.dpt.demo4tbase.engine.DemoEngine;
import com.dpt.demo4tbase.engine.interfaces.AbDemoResultCallBack;
import com.dpt.demo4tbase.engine.to.EntryTo;
import com.dpt.tbase.app.base.engine.AbUiBaseResultCallBack;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;
import android.app.Activity;
import android.content.Context;

public class DemoActivity extends Activity {

	private ListView mLv;
	private DemoAdapter mAdapter;
	private Context mContext;
	private DemoEngine mDemoEngine;
	private AbDemoResultCallBack<List<EntryTo>> mListCb;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mContext = this;
		setContentView(R.layout.activity_demo);
		initView();
		setEvents();
	}

	private void initView() {
		mLv = (ListView) findViewById(R.id.lvDemo);
	}

	private void setEvents() {
		initAdapter();
		initCallBack();
	}

	private void initCallBack() {
		mDemoEngine = new DemoEngine(mContext);
		mListCb = new AbDemoResultCallBack<List<EntryTo>>(mContext) {
			@Override
			public void onSuccessResult(List<EntryTo> datas, int statusCode,
					String[] otherMsg) {
				if (datas == null) {
					Toast.makeText(mContext,
							"datas==null and statusCode=" + statusCode,
							Toast.LENGTH_SHORT).show();
				} else {
					datas2ui(datas);
				}

			}
		};
	}

	protected void datas2ui(List<EntryTo> datas) {
		if (datas.size() == 0) {
			Toast.makeText(mContext, "没有新闻了", Toast.LENGTH_SHORT).show();
		} else {
			mAdapter.getList().addAll(datas);
			if (mLv.getAdapter() == null) {
				mLv.setAdapter(mAdapter);
			} else {
				mAdapter.notifyDataSetChanged();
			}
		}
	}

	private void initAdapter() {
		mAdapter = new DemoAdapter(mContext);
	}

	@Override
	protected void onResume() {
		super.onResume();
		loadNews(1, 20);
	}

	private void loadNews(int pageIndex, int pageSize) {
		mDemoEngine.loadRecentNews(pageIndex, pageSize, mListCb);
	}
}
