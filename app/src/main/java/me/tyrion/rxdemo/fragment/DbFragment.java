package me.tyrion.rxdemo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import io.requery.Persistable;
import io.requery.query.Result;
import io.requery.rx.SingleEntityStore;
import me.tyrion.rxdemo.R;
import me.tyrion.rxdemo.adapter.DbAdapter;
import me.tyrion.rxdemo.requery.RequeryApplication;
import me.tyrion.rxdemo.requery.model.PersionEntity;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Created by taomaogan on 2016/12/12.
 */

public class DbFragment extends Fragment {
    private PersionEntity mPersionEntity;
    private SingleEntityStore<Persistable> mData;
    private RecyclerView mDbRv;
    private DbAdapter mDbAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_db, container, false);
        mData = ((RequeryApplication) getActivity().getApplication()).getData();
        initView(view);
//        addData();
        queryData();
        return view;
    }

    private void initView(View view) {
        mDbRv = (RecyclerView) view.findViewById(R.id.db_rv);
        mDbAdapter = new DbAdapter(getActivity());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mDbRv.setLayoutManager(linearLayoutManager);
        mDbRv.setAdapter(mDbAdapter);
    }

    private void addData() {
        List<PersionEntity> persionEntities = new ArrayList<>();
        final long startTime = System.currentTimeMillis();
        for (int i = 0; i < 300; i++) {
            mPersionEntity = new PersionEntity();
            mPersionEntity.setName("马小鱼" + i);
            mPersionEntity.setAddress("深圳浅水湾" + i + "号");
            mPersionEntity.setAge((18 + i) + "");

            persionEntities.add(mPersionEntity);
        }

        mData.insert(persionEntities).subscribe(new Action1<Iterable<PersionEntity>>() {
            @Override
            public void call(Iterable<PersionEntity> persionEntities) {
                Log.e("RequeryAA", (System.currentTimeMillis() - startTime) + "");
            }
        });
    }

    private void queryData() {
        mData.select(PersionEntity.class)
                .get()
                .toSelfObservable()
                .map(new Func1<Result<PersionEntity>, List<PersionEntity>>() {
                    @Override
                    public List<PersionEntity> call(Result<PersionEntity> persionEntities) {
                        return persionEntities.toList();
                    }
                })
                .subscribe(new Action1<List<PersionEntity>>() {
                    @Override
                    public void call(List<PersionEntity> persionEntities) {
                        mDbAdapter.addItems(persionEntities);
                    }
                });
    }

    private void deleteData() {
        mData.delete(PersionEntity.class).get().toSingle()
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        Log.e("dkakdsk", integer + "");
                    }
                });
    }

    private void updateData() {
        mData.findByKey(PersionEntity.class, 79)
                .subscribe(new Action1<PersionEntity>() {
                    @Override
                    public void call(PersionEntity persionEntity) {
                        update(persionEntity);
                    }
                });
    }

    private void update(PersionEntity persionEntity) {
        if (persionEntity != null) {
            persionEntity.setName("马小鱼" + 10);
            persionEntity.setAddress("香港九龙湾" + 10 + "号");
            persionEntity.setAge("2323");
            mData.update(persionEntity)
                    .subscribe(new Action1<PersionEntity>() {
                        @Override
                        public void call(PersionEntity persionEntity) {
                            Log.e("dkakdsk", "dsadd" + "");
                        }
                    }, new Action1<Throwable>() {
                        @Override
                        public void call(Throwable throwable) {
                            Log.e("dkakdsk", "dsadd" + "");
                        }
                    });
        }
    }
}
