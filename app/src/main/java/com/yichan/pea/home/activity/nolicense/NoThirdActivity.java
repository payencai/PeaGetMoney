package com.yichan.pea.home.activity.nolicense;

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

import butterknife.BindView;
import butterknife.ButterKnife;

public class NoThirdActivity extends AppCompatActivity {
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
    @BindView(R.id.rl_brand)
    RelativeLayout brand;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_no_third);
        ButterKnife.bind(this);
        initView();
    }
    private void initView(){
        idcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(NoThirdActivity.this, IdcardActivity.class),1);
            }
        });
        inner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(NoThirdActivity.this, InteriorActivity.class),2);
            }
        });
        brand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(NoThirdActivity.this, BrandActivity.class),3);
            }
        });

        back.setVisibility(View.VISIBLE);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        title.setText("无证商户");
        right.setVisibility(View.VISIBLE);
        title.setVisibility(View.VISIBLE);
        right.setText("提交");
        right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        right.setTextColor(getResources().getColor(R.color.tv_green));
    }
}
