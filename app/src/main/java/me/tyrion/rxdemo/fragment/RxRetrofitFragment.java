package me.tyrion.rxdemo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import me.tyrion.rxdemo.R;
import me.tyrion.rxdemo.adapter.RetrofitAdapter;
import me.tyrion.rxdemo.model.News;
import me.tyrion.rxdemo.model.Recent;
import me.tyrion.rxdemo.net.ApiDelegateService;
import me.tyrion.rxdemo.net.ApiService;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by taomaogan on 2016/12/12.
 */

public class RxRetrofitFragment extends Fragment {
    private ApiDelegateService mApiDelegateService;
    private RecyclerView mRxRetrofitRv;
    private RetrofitAdapter mRetrofitAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_rxretrofit, container, false);
        mApiDelegateService = new ApiService().getService();
        initView(view);
        request2Net();
        return view;
    }

    private void initView(View view) {
        mRxRetrofitRv = (RecyclerView) view.findViewById(R.id.rxretrofit_rv);
        mRxRetrofitRv.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRetrofitAdapter = new RetrofitAdapter(getActivity());
        mRxRetrofitRv.setAdapter(mRetrofitAdapter);
    }

    private void request2Net() {
        mApiDelegateService.rxNews()
                .flatMap(new Func1<Recent, Observable<News>>() {
                    @Override
                    public Observable<News> call(Recent recent) {
                        return Observable.from(recent.getRecent());
                    }
                }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<News>() {
                    @Override
                    public void call(News news) {
                        mRetrofitAdapter.addItem(news);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {

                    }
                });
    }
}
