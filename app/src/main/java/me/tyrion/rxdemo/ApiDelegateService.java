package me.tyrion.rxdemo;

import java.util.List;

import me.tyrion.rxdemo.model.News;
import me.tyrion.rxdemo.model.Recent;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by taomaogan on 2016/12/7.
 */

public interface ApiDelegateService {

    @GET("news/hot")
    Call<Recent> news();

}
