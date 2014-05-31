package com.dpt.demo4tbase.adapter;

import android.content.Context;
import android.text.Html;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dpt.demo4tbase.R;
import com.dpt.demo4tbase.engine.to.EntryTo;
import com.dpt.tbase.app.adapter.AbCustomTBaseAdapter;


public class DemoAdapter extends AbCustomTBaseAdapter<EntryTo> {

	private Context mContext;

    public DemoAdapter(Context context) {
        super();
        mContext = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = View.inflate(mContext, R.layout.item_news2, null);
            holder.tvNewsItemTitle = (TextView) convertView.findViewById(R.id.tvNewsItemTitle);
            holder.tvNewsItemSummary = (TextView) convertView.findViewById(R.id.tvNewsItemSummary);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        EntryTo entry = getList().get(position);
        holder.tvNewsItemTitle.setText(entry.title);
        holder.tvNewsItemSummary.setText(Html.fromHtml(entry.summary));

        return convertView;
    }

    class ViewHolder {
        TextView tvNewsItemTitle;
        TextView tvNewsItemSummary;
    }
}
