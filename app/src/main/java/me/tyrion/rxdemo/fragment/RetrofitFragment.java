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
import me.tyrion.rxdemo.model.Recent;
import me.tyrion.rxdemo.net.ApiDelegateService;
import me.tyrion.rxdemo.net.ApiService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by taomaogan on 2016/12/12.
 */

public class RetrofitFragment extends Fragment {
    private ApiDelegateService mApiDelegateService;
    private RecyclerView mRetrofitRv;
    private RetrofitAdapter mRetrofitAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_retrofit, container, false);
        mApiDelegateService = new ApiService().getService();
        initView(view);
        request2Net();
        return view;
    }

    private void initView(View view) {
        mRetrofitRv = (RecyclerView) view.findViewById(R.id.retrofit_rv);
        mRetrofitRv.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRetrofitAdapter = new RetrofitAdapter(getActivity());
        mRetrofitRv.setAdapter(mRetrofitAdapter);
    }

    private void request2Net() {
        Call<Recent> newsRequest = mApiDelegateService.news();
        newsRequest.enqueue(new Callback<Recent>() {
            @Override
            public void onResponse(Call<Recent> call, final Response<Recent> response) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (response.body() != null && response.body().getRecent() != null) {
                            mRetrofitAdapter.addItems(response.body().getRecent());
                        }
                    }
                });
            }

            @Override
            public void onFailure(Call<Recent> call, Throwable t) {
            }
        });
    }
}
