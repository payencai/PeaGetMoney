package com.yichan.pea.home.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.yichan.pea.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LicenseActivity extends AppCompatActivity {
    @BindView(R.id.top_left)
    FrameLayout back;
    @BindView(R.id.top_title)
    TextView title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pea_activity_license);
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
        title.setText("营业执照");
        title.setVisibility(View.VISIBLE);
    }
}
