package com.yichan.pea;

import android.app.Application;

import com.payencai.library.http.retrofitAndrxjava.NetWorkManager;
import com.yichan.pea.basic.api.Api;
import com.yichan.pea.basic.api.MaRetrofit;


public class MyAPP extends Application {
    /**
     *
     * release sha1: 2F:39:DB:CA:7F:6C:15:9E:73:EC:EB:A2:20:2A:BA:8F:9C:3A:19:8D
     * debug sha1: 6B:38:39:B6:A5:4A:C7:1D:21:9C:EF:A6:BE:97:64:0C:E0:86:1D:96
     */

    private static MyAPP INSTANCE;
    public static boolean isLogin = false;
    public static String alias;//推送别名
    public static String token;
    public static boolean isDebug = false;
    public static final String APP_ID="wx57f1a1cb08d803fe";
    @Override
    public void onCreate() {
        super.onCreate();
        INSTANCE = this;
        NetWorkManager.getInstance().init(Api.baseUrl);

    }

    public static synchronized MyAPP getInstance() {
        return INSTANCE;
    }



}
