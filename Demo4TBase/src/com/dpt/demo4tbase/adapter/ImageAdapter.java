package com.dpt.demo4tbase.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.dpt.demo4tbase.R;
import com.dpt.demo4tbase.engine.to.EntryTo;
import com.dpt.tbase.app.adapter.AbCustomTBaseAdapter;
import com.dpt.tbase.app.net.TBaseNetClent2;


public class ImageAdapter extends AbCustomTBaseAdapter<EntryTo> {

	private Context mContext;

    public ImageAdapter(Context context) {
        super();
        mContext = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = View.inflate(mContext, R.layout.item_images, null);
            holder.iv = (ImageView) convertView.findViewById(R.id.ivItem);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        EntryTo entry = getList().get(position);
        TBaseNetClent2.executeImageRequest(entry.topicIcon, holder.iv, R.drawable.ic_launcher, android.R.drawable.ic_delete);
        return convertView;
    }

    class ViewHolder {
    	ImageView iv;
    }
}
