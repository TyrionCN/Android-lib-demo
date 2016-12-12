package me.tyrion.rxdemo.net;

import me.tyrion.rxdemo.model.Recent;
import retrofit2.Call;
import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by taomaogan on 2016/12/7.
 */

public interface ApiDelegateService {

    @GET("news/hot")
    Call<Recent> news();

    @GET("news/hot")
    Observable<Recent> rxNews();
}
