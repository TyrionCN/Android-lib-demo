package me.tyrion.rxdemo;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by taomaogan on 2016/12/7.
 */

public class ApiService {

    public ApiDelegateService getRetrofit() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://news-at.zhihu.com/api/3/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(ApiDelegateService.class);
    }
}
