package me.tyrion.rxdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by taomaogan on 2016/12/7.
 */

public class RxActivity extends AppCompatActivity {
    private ApiService mApiService;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mApiService = new ApiService();
    }
}
