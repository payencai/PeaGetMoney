package com.yichan.pea.account.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.payencai.library.http.retrofitAndrxjava.ApiException;
import com.payencai.library.http.retrofitAndrxjava.CustomException;
import com.payencai.library.http.retrofitAndrxjava.NetWorkManager;
import com.payencai.library.http.retrofitAndrxjava.schedulers.SchedulerProvider;
import com.payencai.library.util.ToastUtil;
import com.yichan.pea.R;
import com.yichan.pea.basic.api.ApiService;

import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import okhttp3.ResponseBody;

public class ForgetpwdActivity extends AppCompatActivity {
    @BindView(R.id.top_right)
    TextView tv_right;
    @BindView(R.id.top_left)
    FrameLayout fl_back;
    @BindView(R.id.send)
    TextView tv_send;
    @BindView(R.id.et_phone)
    EditText et_phone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pea_activity_forgetpwd);
        ButterKnife.bind(this);
        initView();
    }
    private void initView(){
        fl_back.setVisibility(View.VISIBLE);
        tv_right.setText("下一步");
        tv_right.setVisibility(View.VISIBLE);
        tv_right.setTextColor(getResources().getColor(R.color.tv_green));
        tv_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ForgetpwdActivity.this,FindpwdActivity.class));
            }
        });
        fl_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        tv_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phone=et_phone.getEditableText().toString();
                if(!TextUtils.isEmpty(phone))
                 getCode(phone);
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
                            ToastUtil.showToast(ForgetpwdActivity.this, "发送成功");
                            //finish();
                        }

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        ApiException apiException = CustomException.handleException(throwable);
                        Log.e("error", apiException.getDisplayMessage());
                        ToastUtil.showToast(ForgetpwdActivity.this, apiException.getDisplayMessage());
                    }
                });

        new CompositeDisposable().add(disposable);
    }
}
