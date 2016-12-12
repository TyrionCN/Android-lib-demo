package me.tyrion.rxdemo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import me.tyrion.rxdemo.R;
import me.tyrion.rxdemo.model.News;

/**
 * Created by taomaogan on 2016/12/8.
 */

public class RetrofitAdapter extends RecyclerView.Adapter<RetrofitAdapter.BaseVH> {
    private List<News> mNewslist;
    private LayoutInflater mInflater;
    private Context mContext;

    public RetrofitAdapter(Context context) {
        mContext = context;
        mNewslist = new ArrayList<>();
        mInflater = LayoutInflater.from(context);
    }

    public void addItems(List<News> newsList) {
        mNewslist.addAll(newsList);
        notifyDataSetChanged();
    }

    public void addItem(News news) {
        mNewslist.add(news);
        notifyDataSetChanged();
    }

    @Override
    public BaseVH onCreateViewHolder(ViewGroup parent, int viewType) {
        return new BaseVH(mInflater.inflate(R.layout.recycler_retrofit_item, parent, false));
    }

    @Override
    public void onBindViewHolder(BaseVH holder, int position) {
        holder.update();
    }

    @Override
    public int getItemCount() {
        return mNewslist.size();
    }

    class BaseVH extends RecyclerView.ViewHolder {
        private TextView mRetrofitItemTv;

        public BaseVH(View itemView) {
            super(itemView);
            mRetrofitItemTv = (TextView) itemView.findViewById(R.id.retrofit_item_tv);
        }

        public void update() {
            News news = mNewslist.get(getAdapterPosition());
            mRetrofitItemTv.setText(news.getTitle());
        }
    }
}
