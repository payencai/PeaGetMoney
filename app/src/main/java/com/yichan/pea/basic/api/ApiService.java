package com.yichan.pea.basic.api;

import com.yichan.pea.basic.model.Account;

import java.security.Key;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService {
    @Headers({"Content-Type: application/json", "Accept: application/json"})//需要添加头
    @POST(Api.MerchantUser.login)
    Observable<ResponseBody> login(
            @Body RequestBody requestBody
    );

    @POST(Api.MerchantUser.sendCode)
    Observable<ResponseBody> sendCode(
            @Query("userName") String userName
    );

    @POST(Api.MerchantUser.register)
    Observable<ResponseBody> register(
            @Query("userName") String userName,
            @Query("code") String code,
            @Query("passWord") String passWord
    );
    @POST(Api.MerchantUser.modifyPwd)
    Observable<ResponseBody> modifyPwd(
            @Query("newPassWord") String newPassWord,
            @Header("token")  String token
    );

    @POST(Api.MerchantUser.forgetPwd)
    Observable<ResponseBody> forgetPwd(
            @Query("userName") String userName,
            @Query("code") String code
    );
}
