package com.yichan.pea.home.activity.selfshop;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yichan.pea.R;
import com.yichan.pea.home.activity.BrandActivity;
import com.yichan.pea.home.activity.IdcardActivity;
import com.yichan.pea.home.activity.InteriorActivity;
import com.yichan.pea.home.activity.LicenseActivity;
import com.yichan.pea.home.activity.enterprise.EnterpriseThirdActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SelfSecActivity extends AppCompatActivity {
    @BindView(R.id.top_left)
    FrameLayout back;
    @BindView(R.id.top_title)
    TextView title;
    @BindView(R.id.top_right)
    TextView right;
    @BindView(R.id.rl_idcard)
    RelativeLayout idcard;
    @BindView(R.id.rl_inner)
    RelativeLayout inner;
    @BindView(R.id.rl_zhizhao)
    RelativeLayout zhizhao;
    @BindView(R.id.rl_brand)
    RelativeLayout brand;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_self_sec);
        ButterKnife.bind(this);
        initView();
    }
    private void initView(){
        back.setVisibility(View.VISIBLE);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        right.setText("提交");
        title.setText("个体商户");
        title.setVisibility(View.VISIBLE);
        right.setVisibility(View.VISIBLE);
        right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ///startActivity(new Intent(SelfSecActivity.this,SelfSecActivity.class));
            }
        });
        right.setTextColor(getResources().getColor(R.color.tv_green));
        idcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(SelfSecActivity.this, IdcardActivity.class),1);
            }
        });
        inner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(SelfSecActivity.this, InteriorActivity.class),2);
            }
        });
        brand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(SelfSecActivity.this, BrandActivity.class),3);
            }
        });
        zhizhao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(SelfSecActivity.this, LicenseActivity.class),4);
            }
        });
    }
}
