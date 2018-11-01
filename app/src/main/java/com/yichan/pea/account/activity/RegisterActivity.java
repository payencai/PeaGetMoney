package com.yichan.pea.account.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.payencai.library.http.retrofitAndrxjava.ApiException;
import com.payencai.library.http.retrofitAndrxjava.CustomException;
import com.payencai.library.http.retrofitAndrxjava.NetWorkManager;
import com.payencai.library.http.retrofitAndrxjava.schedulers.SchedulerProvider;
import com.payencai.library.util.TimerCount;
import com.payencai.library.util.ToastUtil;
import com.yichan.pea.R;
import com.yichan.pea.basic.api.ApiService;
import com.yichan.pea.home.activity.MainActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

public class RegisterActivity extends AppCompatActivity {
    @BindView(R.id.top_left)
    FrameLayout back;
    @BindView(R.id.top_title)
    TextView title;
    @BindView(R.id.getCode)
    TextView sendCode;
    @BindView(R.id.tv_reg)
    TextView reg;
    @BindView(R.id.in_phone)
    EditText in_phone;
    @BindView(R.id.in_code)
    EditText in_code;
    @BindView(R.id.in_pass)
    EditText in_pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pea_activity_register);
        ButterKnife.bind(this);
        initView();
    }
    private void initView(){
        back.setVisibility(View.VISIBLE);
        title.setText("注册");
        title.setVisibility(View.VISIBLE);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        sendCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimerCount timer = new TimerCount(60000, 1000, sendCode);
                timer.start();
                String phone=in_phone.getEditableText().toString();
                if(!TextUtils.isEmpty(phone))
                     getCode(phone);
                else{
                    ToastUtil.showToast(RegisterActivity.this,"请先输入手机号码");
                }
            }
        });
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phone=in_phone.getEditableText().toString();
                String code=in_code.getEditableText().toString();
                String pass=in_pass.getEditableText().toString();
                if(TextUtils.isEmpty(phone)){
                    ToastUtil.showToast(RegisterActivity.this, "手机号不能为空");
                    return;
                }
                if(TextUtils.isEmpty(code)){
                    ToastUtil.showToast(RegisterActivity.this, "验证码不能为空");
                    return;
                }
                if(TextUtils.isEmpty(pass)){
                    ToastUtil.showToast(RegisterActivity.this, "密码不能为空");
                    return;
                }
                submit(phone,code,pass);
            }
        });
    }
    private void getCode(String phone){

        Disposable disposable = NetWorkManager.getRequest(ApiService.class).sendCode(phone)
                //.compose(ResponseTransformer.handleResult())
                .compose(SchedulerProvider.getInstance().applySchedulers())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody responseBody) throws Exception {
                        String result=responseBody.string();
                        Log.e("reg",result);
                        JSONObject jsonObject=new JSONObject(result);
                        int code=jsonObject.getInt("resultCode");
                        if(code==0){
                            ToastUtil.showToast(RegisterActivity.this, "发送成功");
                            //finish();
                        }

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        ApiException apiException = CustomException.handleException(throwable);
                        Log.e("error", apiException.getDisplayMessage());
                        ToastUtil.showToast(RegisterActivity.this, apiException.getDisplayMessage());
                    }
                });

        new CompositeDisposable().add(disposable);
    }

    private String retrunJsonData(Map<String,Object> params) {
        JSONObject requestData = new JSONObject(params);
        return requestData.toString();
    }


    private void submit(String phone,String code,String pass){
        Disposable disposable = NetWorkManager.getRequest(ApiService.class).register(phone,code,pass)
                //.compose(ResponseTransformer.handleResult())
                .compose(SchedulerProvider.getInstance().applySchedulers())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody responseBody) throws Exception {
                        String result=responseBody.string();
                        Log.e("result",result);
                        JSONObject jsonObject=new JSONObject(result);
                        String msg=jsonObject.getString("message");
                        int code=jsonObject.getInt("resultCode");
                        if(code==0){
                            ToastUtil.showToast(RegisterActivity.this, "注册成功");
                            finish();
                        }else{
                            ToastUtil.showToast(RegisterActivity.this, msg+"");
                        }

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        ApiException apiException = CustomException.handleException(throwable);
                        Log.e("error", apiException.getDisplayMessage());
                        ToastUtil.showToast(RegisterActivity.this, apiException.getDisplayMessage());
                    }
                });

        new CompositeDisposable().add(disposable);
    }
}
