package com.yichan.pea.basic.view.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.bigkoo.pickerview.adapter.ArrayWheelAdapter;
import com.contrarywind.listener.OnItemSelectedListener;
import com.contrarywind.view.WheelView;
import com.yichan.pea.R;
import com.yichan.pea.basic.view.TakePhotoPopWin;

import java.util.ArrayList;
import java.util.List;

public class SelectDialog extends BaseDialog {
    private Window window;
    private String payType="全部类型";
    private TextView cancel;
    private TextView confirm;
    private WheelView wheelView;

    public  interface  OnPayItemSelect{
        void onSelect(String type);
    }
    private TakePhotoPopWin.OnPayItemSelect mOnPayItemSelect;
    public void setOnPayItemSelect(TakePhotoPopWin.OnPayItemSelect onPayItemSelect){
        this.mOnPayItemSelect=onPayItemSelect;
    }
    public SelectDialog(Context context) {
        super(context);
    }

    @Override
    protected int getDialogStyleId() {
        return 0;
    }

    @Override
    protected View getView() {
        window = ((Activity) context).getWindow();
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_paytype, null);
        cancel=view.findViewById(R.id.tv_cancel);
        confirm=view.findViewById(R.id.tv_confirm);
        wheelView=view.findViewById(R.id.wheelview);
        wheelView.setCyclic(false);
        wheelView.setDividerColor(context.getResources().getColor(R.color.white));
        final List<String> mOptionsItems = new ArrayList<>();
        mOptionsItems.add("全部类型");
        mOptionsItems.add("支付宝");
        mOptionsItems.add("翼支付");
        mOptionsItems.add("微信");
        wheelView.setAdapter(new ArrayWheelAdapter(mOptionsItems));
        wheelView.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(int index) {
                payType=mOptionsItems.get(index);
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mOnPayItemSelect.onSelect("全部类型");
                dismiss();
            }
        });
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnPayItemSelect.onSelect(payType);
                dismiss();
            }
        });
        return view;
    }
}
