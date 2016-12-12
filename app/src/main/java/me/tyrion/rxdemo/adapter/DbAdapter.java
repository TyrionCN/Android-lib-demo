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
import me.tyrion.rxdemo.requery.model.PersionEntity;

/**
 * Created by taomaogan on 2016/12/9.
 */

public class DbAdapter extends RecyclerView.Adapter<DbAdapter.BaseVH> {
    private Context mContext;
    private LayoutInflater mInflater;
    private List<PersionEntity> mPersionEntities;

    public DbAdapter(Context context) {
        mContext = context;
        mInflater = LayoutInflater.from(context);
        mPersionEntities = new ArrayList<>();
    }

    public void addItem(PersionEntity persionEntity) {
        mPersionEntities.add(persionEntity);
        notifyDataSetChanged();
    }

    public void addItems(List<PersionEntity> persionEntities) {
        if (persionEntities != null && persionEntities.size() > 0) {
            mPersionEntities.addAll(persionEntities);
            notifyDataSetChanged();
        }
    }

    @Override
    public BaseVH onCreateViewHolder(ViewGroup parent, int viewType) {
        return new BaseVH(mInflater.inflate(R.layout.recycler_db, parent, false));
    }

    @Override
    public void onBindViewHolder(BaseVH holder, int position) {
        holder.update();
    }

    @Override
    public int getItemCount() {
        return mPersionEntities.size();
    }

    protected class BaseVH extends RecyclerView.ViewHolder {
        private TextView mDbNameTv;
        private TextView mDbAgeTv;
        private TextView mDbAddressTv;

        public BaseVH(View itemView) {
            super(itemView);
            mDbNameTv = (TextView) itemView.findViewById(R.id.db_name_tv);
            mDbAgeTv = (TextView) itemView.findViewById(R.id.db_age_tv);
            mDbAddressTv = (TextView) itemView.findViewById(R.id.db_address_tv);
        }

        public void update() {
            PersionEntity persionEntity = mPersionEntities.get(getAdapterPosition());
            mDbNameTv.setText(persionEntity.getName());
            mDbAgeTv.setText(persionEntity.getAge());
            mDbAddressTv.setText(persionEntity.getAddress());
        }
    }
}
