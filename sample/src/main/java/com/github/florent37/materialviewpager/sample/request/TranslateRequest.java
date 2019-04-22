package com.github.florent37.materialviewpager.sample.request;

import com.github.florent37.materialviewpager.sample.bean.TranslateResult;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Ainsworth on 2018/6/6.
 */
public interface TranslateRequest {

    @GET("ajax.php?a=fy&f=zh&t=en")
    Call<TranslateResult> getCall(@Query("w") String content);
}
