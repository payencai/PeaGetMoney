package com.yichan.pea.basic.api;

import com.yichan.pea.basic.model.Account;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface UrlServiceInterface {
    @Headers({"Content-Type: application/json"})//需要添加头
    @POST(Api.MerchantUser.login)
    Call<ResponseBody> login2(
    );
}
