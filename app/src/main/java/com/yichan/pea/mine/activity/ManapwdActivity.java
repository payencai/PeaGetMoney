package com.yichan.pea.mine.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.yichan.pea.R;
import com.yichan.pea.account.activity.FindpwdActivity;
import com.yichan.pea.account.activity.ForgetpwdActivity;
import com.yichan.pea.account.activity.UpdatepwdActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ManapwdActivity extends AppCompatActivity {
    @BindView(R.id.top_left)
    FrameLayout back;
    @BindView(R.id.rl_modPwd)
    RelativeLayout modPwd;
    @BindView(R.id.rl_forgetPwd)
    RelativeLayout forget;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pea_activity_manapwd);
        ButterKnife.bind(this);
        initView();
    }
    private void initView(){
        back.setVisibility(View.VISIBLE);
        modPwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ManapwdActivity.this, UpdatepwdActivity.class));
            }
        });
        forget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ManapwdActivity.this, ForgetpwdActivity.class));
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
