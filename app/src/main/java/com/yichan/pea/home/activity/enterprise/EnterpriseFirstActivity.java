package com.yichan.pea.home.activity.enterprise;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.yichan.pea.R;
import com.yichan.pea.home.activity.nolicense.NoFirstActivity;
import com.yichan.pea.home.activity.selfshop.SelfSecActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EnterpriseFirstActivity extends AppCompatActivity {
    @BindView(R.id.top_left)
    FrameLayout back;
    @BindView(R.id.top_title)
    TextView title;
    @BindView(R.id.top_right)
    TextView right;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enterprise_first);
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
        title.setText("企业商户");
        right.setVisibility(View.VISIBLE);
        title.setVisibility(View.VISIBLE);
        right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(EnterpriseFirstActivity.this,EnterpriseSecActivity.class));
            }
        });
        right.setTextColor(getResources().getColor(R.color.tv_green));
    }
}
