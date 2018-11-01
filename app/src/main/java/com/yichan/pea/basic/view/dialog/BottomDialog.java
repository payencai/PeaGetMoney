package com.yichan.pea.basic.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.bigkoo.pickerview.adapter.ArrayWheelAdapter;
import com.contrarywind.listener.OnItemSelectedListener;
import com.contrarywind.view.WheelView;
import com.yichan.pea.R;
import com.yichan.pea.basic.view.TakePhotoPopWin;

import java.util.ArrayList;
import java.util.List;

public class BottomDialog extends Dialog {
    private String year="2018";
    private String mon="1";
    private TextView cancel;
    private TextView confirm;
    private WheelView wheelView;
    private WheelView wheelView2;
    private boolean iscancelable=false;//控制点击dialog外部是否dismiss
    private boolean isBackCanCelable=false;//控制返回键是否dismiss
    private View view;
    private Context context;
    public BottomDialog(Context context) {
        super(context, R.style.common_dialog_style);
        this.context = context;
        view = LayoutInflater.from(context).inflate(R.layout.dialog_time,null);

    }
    public  interface  OnPayItemSelect{
        void onSelect(String type);
    }
    private TakePhotoPopWin.OnPayItemSelect mOnPayItemSelect;
    public void setOnPayItemSelect(TakePhotoPopWin.OnPayItemSelect onPayItemSelect){
        this.mOnPayItemSelect=onPayItemSelect;
    }
    private void initView(View view){
        cancel=view.findViewById(R.id.tv_cancel);
        confirm=view.findViewById(R.id.tv_confirm);
        wheelView=view.findViewById(R.id.wheelview);
        wheelView2=view.findViewById(R.id.wheelview2);
        wheelView2.setDividerColor(context.getResources().getColor(R.color.white));
        wheelView2.setCyclic(false);
        wheelView.setCyclic(false);
        wheelView.setDividerColor(context.getResources().getColor(R.color.white));
        final List<Integer> mOptionsItems2 = new ArrayList<>();
        for(int i=1;i<=12;i++){
            mOptionsItems2.add(i);
        }
        final List<String> mOptionsItems = new ArrayList<>();
        mOptionsItems.add("2018");
        mOptionsItems.add("2019");
        mOptionsItems.add("2020");
        mOptionsItems.add("2021");
        wheelView2.setAdapter(new ArrayWheelAdapter(mOptionsItems2));
        wheelView.setAdapter(new ArrayWheelAdapter(mOptionsItems));
        wheelView.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(int index) {
                year=mOptionsItems.get(index);
            }
        });
        wheelView2.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(int index) {
                mon=mOptionsItems2.get(index)+"";
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mOnPayItemSelect.onSelect(year+","+mon);
                dismiss();
            }
        });
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnPayItemSelect.onSelect(year+","+mon);
                dismiss();
            }
        });
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(view);//这行一定要写在前面
        initView(view);
        setCancelable(iscancelable);//点击外部不可dismiss
        setCanceledOnTouchOutside(isBackCanCelable);
        Window window = this.getWindow();
        window.setGravity(Gravity.BOTTOM);
        WindowManager.LayoutParams params = window.getAttributes();
        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setAttributes(params);
    }
}
