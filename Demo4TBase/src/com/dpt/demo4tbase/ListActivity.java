package com.dpt.demo4tbase;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.widget.ListView;
import android.widget.Toast;

import com.dpt.demo4tbase.adapter.DemoAdapter;
import com.dpt.demo4tbase.engine.DemoEngine;
import com.dpt.demo4tbase.engine.interfaces.AbDemoResultCallBack;
import com.dpt.demo4tbase.engine.to.EntryTo;
import com.dpt.tbase.app.net.TBaseNetClent2;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

public class ListActivity extends Activity {

	// private ListView mLv;
	private DemoAdapter mAdapter;
	private Context mContext;
	private DemoEngine mDemoEngine;
	private AbDemoResultCallBack<List<EntryTo>> mListCb;
	private PullToRefreshListView mRefreshListView;
	private int pageIndex=1, pageSize=20;
	protected boolean mIsPullDown=true;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mContext = this;
		setContentView(R.layout.activity_list);
		initView();
		setEvents();
	}

	private void initView() {
		mRefreshListView= (PullToRefreshListView) findViewById(R.id.pull_refresh_list);
	}

	private void setEvents() {
		initAdapter();
		initCallBack();
		mRefreshListView.setAdapter(mAdapter);
		mRefreshListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {

			@Override
			public void onPullDownToRefresh(
					PullToRefreshBase<ListView> refreshView) {
				mIsPullDown=true;
				updateLabel(refreshView);
				pullDown();
			}

			@Override
			public void onPullUpToRefresh(
					PullToRefreshBase<ListView> refreshView) {
				mIsPullDown=false;
				updateLabel(refreshView);
				pullUp();
			}
			
			private void updateLabel(PullToRefreshBase<ListView> refreshView) {
				String label = DateUtils.formatDateTime(mContext,
                      System.currentTimeMillis(), DateUtils.FORMAT_SHOW_TIME
                              | DateUtils.FORMAT_SHOW_DATE
                              | DateUtils.FORMAT_ABBREV_ALL);
              // Update the LastUpdatedLabel
              refreshView.getLoadingLayoutProxy().setLastUpdatedLabel(label);
			}

		});
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
					datas2ui(datas,mIsPullDown);
				}

			}
		};
	}

	protected void datas2ui(List<EntryTo> datas,boolean isPullDown) {
		if (datas.size() == 0) {
			Toast.makeText(mContext, "没有新闻了", Toast.LENGTH_SHORT).show();
		} else {
			if (isPullDown) {
				mAdapter.getList().clear();
			}
			mAdapter.getList().addAll(datas);
			mAdapter.notifyDataSetChanged();
		}
		loadFinished();
	}

	private void initAdapter() {
		mAdapter = new DemoAdapter(mContext);
	}

	@Override
	protected void onResume() {
		super.onResume();
		pullDown();
	}
	
	private void pullUp(){
		loadNews(++pageIndex, pageSize);
	}
	private void pullDown(){
		pageIndex=1;
		loadNews(pageIndex, pageSize);
	}
	
	private void loadNews(int pageIndex, int pageSize) {
		mDemoEngine.loadRecentNews(pageIndex, pageSize, mListCb);
	}
    
    private void loadFinished() {
    	mRefreshListView.onRefreshComplete();
    }
    
    @Override
    protected void onStop() {
    	super.onStop();
    	TBaseNetClent2.cancelAllRequests();
    }
}
