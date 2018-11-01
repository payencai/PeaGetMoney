package com.yichan.pea.account.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.yichan.pea.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FindpwdActivity extends AppCompatActivity {
    @BindView(R.id.top_left)
    FrameLayout back;
    @BindView(R.id.top_right)
    TextView right;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pea_activity_findpwd);
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
        right.setVisibility(View.VISIBLE);
        right.setText("确认");
        right.setTextColor(getResources().getColor(R.color.tv_green));
        right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}
