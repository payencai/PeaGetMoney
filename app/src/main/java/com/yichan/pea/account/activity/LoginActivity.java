package com.yichan.pea.account.activity;

import android.accounts.Account;
import android.content.Intent;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.payencai.library.http.retrofitAndrxjava.ApiException;
import com.payencai.library.http.retrofitAndrxjava.CustomException;
import com.payencai.library.http.retrofitAndrxjava.NetWorkManager;
import com.payencai.library.http.retrofitAndrxjava.schedulers.SchedulerProvider;
import com.payencai.library.util.ToastUtil;
import com.yichan.pea.MyAPP;
import com.yichan.pea.R;
import com.yichan.pea.basic.api.ApiService;
import com.yichan.pea.basic.api.MaRetrofit;
import com.yichan.pea.basic.api.UrlServiceInterface;
import com.yichan.pea.home.activity.MainActivity;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import ng.max.slideview.SlideView;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    @BindView(R.id.slideView)
    SlideView mSlideView;
    @BindView(R.id.register)
    TextView register;
    @BindView(R.id.forget)
    TextView forget;
    @BindView(R.id.login_phone)
    EditText et_phone;
    @BindView(R.id.login_pwd)
    EditText et_pwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pea_activity_login);
        ButterKnife.bind(this);
        initView();

    }

    private void initView() {

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });
        forget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, ForgetpwdActivity.class));
            }
        });
        mSlideView.setOnSlideCompleteListener(new SlideView.OnSlideCompleteListener() {
            @Override
            public void onSlideComplete(SlideView slideView) {
                String phone = et_phone.getEditableText().toString();
                String pwd = et_pwd.getEditableText().toString();
                login(phone, pwd);

            }
        });
    }



    private void login(String phone, String pwd) {
        String value = retrunJsonData(phone, pwd);
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), value);
        Disposable disposable = NetWorkManager.getRequest(ApiService.class).login(body)
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
                            startActivity(new Intent(LoginActivity.this, MainActivity.class));
                            ToastUtil.showToast(LoginActivity.this, "登录成功");
                            finish();
                        }else {
                            ToastUtil.showToast(LoginActivity.this, msg);
                        }

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        ApiException apiException = CustomException.handleException(throwable);
                        Log.e("error", apiException.getDisplayMessage());
                        ToastUtil.showToast(LoginActivity.this, apiException.getDisplayMessage());
                    }
                });

        new CompositeDisposable().add(disposable);
    }

    private String retrunJsonData(String username, String password) {
        JSONObject requestData = new JSONObject();
        try {
            requestData.put("password", password);
            requestData.put("username", username);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return requestData.toString();
    }
}
