package com.yichan.pea.home.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.yichan.pea.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class IdcardActivity extends AppCompatActivity {
    @BindView(R.id.top_left)
    FrameLayout back;
    @BindView(R.id.top_title)
    TextView title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pea_activity_idcard);
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
        title.setText("法人身份证");
        title.setVisibility(View.VISIBLE);
    }
}
