package com.yichan.pea.account.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yichan.pea.R;
import com.yichan.pea.basic.view.dialog.ConfirmDialog;
import com.zhy.autolayout.utils.L;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UpdatepwdActivity extends AppCompatActivity {
    @BindView(R.id.top_left)
    FrameLayout back;
    @BindView(R.id.top_right)
    TextView right;
    @BindView(R.id.oldPwd)
    EditText oldPwd;
    @BindView(R.id.newPwd)
    EditText newPwd;
    @BindView(R.id.confirmPwd)
    EditText confirmPwd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pea_activity_updatepwd);
        ButterKnife.bind(this);
        initView();
    }
    private void initView(){
        back.setVisibility(View.VISIBLE);
        right.setVisibility(View.VISIBLE);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }
        });
        right.setText("确认");
        right.setTextColor(getResources().getColor(R.color.tv_green));
    }
    private void showDialog()
    {
        View view = LayoutInflater.from(UpdatepwdActivity.this).inflate(R.layout.dialog,null);
        TextView confirm=view.findViewById(R.id.tv_confirm);
        ConfirmDialog confirmDialog=new ConfirmDialog(UpdatepwdActivity.this,view);
        confirmDialog.show();
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmDialog.dismiss();
                finish();
            }
        });
        Window window=confirmDialog.getWindow();
        WindowManager windowManager=getWindowManager();
        Display display=windowManager.getDefaultDisplay();
        WindowManager.LayoutParams lp=window.getAttributes();
        lp.width=(int) (display.getWidth()*0.7);
        window.setAttributes(lp);

    }
}
