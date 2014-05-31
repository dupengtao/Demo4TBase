package com.dpt.demo4tbase.engine;

import java.util.List;

import android.content.Context;

import com.dpt.demo4tbase.engine.parser.DemoParser;
import com.dpt.demo4tbase.engine.to.EntryTo;
import com.dpt.demo4tbase.net.UriHelper;
import com.dpt.tbase.app.base.engine.IUiBaseResultCallBack;
import com.dpt.tbase.app.base.engine.TBaseEngine;
import com.dpt.tbase.app.base.utils.LogHelper;
import com.dpt.tbase.app.net.interfaces.AbStrResultCallBack;

/**
 * 主要处理网络访问和解析数据
 * 把解析好的数据返回给 Fragment 或 Activity
 * @author dupengtao
 *
 */
public class DemoEngine extends TBaseEngine{

    private static final String TAG=DemoEngine.class.getSimpleName();
    private Context mContext;
    public DemoEngine(Context context) {
        super();
        mContext=context;
    }

    public void loadRecentNews(int pageIndex,int pageSize, final IUiBaseResultCallBack<List<EntryTo>> uiCb) {
        AbStrResultCallBack strResultCallBack = new AbStrResultCallBack(uiCb) {
            @Override
            public void onSuccessCallBack(int statusCode, String content, String[] otherMsg) {
                LogHelper.e(TAG, content);
                try {
                    List<EntryTo> datas = DemoParser.parse(content);
                    uiCb.onSuccessResult(datas, statusCode, otherMsg);
                } catch (Exception e) {
                    e.printStackTrace();
                    uiCb.onFailureResult(e, content);
                }
            }
        };
        excuteStr(mContext, UriHelper.getRecentNews(pageIndex, pageSize), uiCb, strResultCallBack);
    }
    
}
