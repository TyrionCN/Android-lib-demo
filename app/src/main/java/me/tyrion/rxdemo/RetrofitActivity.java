package me.tyrion.rxdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.List;

import me.tyrion.rxdemo.model.News;
import me.tyrion.rxdemo.model.Recent;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by taomaogan on 2016/12/7.
 */

public class RetrofitActivity extends AppCompatActivity {
    private ApiDelegateService mApiDelegateService;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mApiDelegateService = new ApiService().getRetrofit();
        testData();
    }

    private void testData() {
        Call<Recent> newsRequest = mApiDelegateService.news();
        newsRequest.enqueue(new Callback<Recent>() {
            @Override
            public void onResponse(Call<Recent> call, Response<Recent> response) {
                Log.e("retrofit", "return:" + response.body());
            }

            @Override
            public void onFailure(Call<Recent> call, Throwable t) {
                Log.e("retrofit", "error:" + t.getMessage());
            }
        });
    }
}
