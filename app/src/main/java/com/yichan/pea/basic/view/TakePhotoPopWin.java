package com.yichan.pea.basic.view;

import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.adapter.ArrayWheelAdapter;
import com.contrarywind.listener.OnItemSelectedListener;
import com.contrarywind.view.WheelView;
import com.yichan.pea.R;
import com.yichan.pea.home.activity.MainActivity;

import java.util.ArrayList;
import java.util.List;

public class TakePhotoPopWin extends PopupWindow {
    private Context mContext;
    private Window window;
    private View view;
    private String payType="全部类型";
    private TextView cancel;
    private TextView confirm;
    private WheelView wheelView;
    private float mAlpha=1f;
    private int flag=0;
    public  interface  OnPayItemSelect{
         void onSelect(String type);
    }
    private OnPayItemSelect mOnPayItemSelect;
    public void setOnPayItemSelect(OnPayItemSelect onPayItemSelect){
        this.mOnPayItemSelect=onPayItemSelect;
    }

    /**
     * 控制窗口背景的不透明度
     */
    public void setWindowBackgroundAlpha(float alpha) {
        if (mContext == null) return;
        if (mContext instanceof Activity) {
            WindowManager.LayoutParams layoutParams = window.getAttributes();
            layoutParams.alpha = alpha;
            Log.e("flag",layoutParams.flags+"--");
            //layoutParams.format = PixelFormat.TRANSLUCENT;
            flag=layoutParams.flags;
            layoutParams.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
            window.setAttributes(layoutParams);
        }
        if(alpha==1f){
            WindowManager.LayoutParams layoutParams = window.getAttributes();
            layoutParams.flags =  WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN;
            window.setAttributes(layoutParams);
        }
    }
    /**
     * 窗口显示，窗口背景透明度渐变动画
     */
    private void showBackgroundAnimator() {
        if (mAlpha >= 1f) return;
        ValueAnimator animator = ValueAnimator.ofFloat(1.0f, mAlpha);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float alpha = (float) animation.getAnimatedValue();
                setWindowBackgroundAlpha(alpha);
            }
        });
        animator.setDuration(360);
        animator.start();
    }

    public TakePhotoPopWin(Context mContext) {
        this.mContext=mContext;
        this.view = LayoutInflater.from(mContext).inflate(R.layout.dialog_paytype, null);
        cancel=view.findViewById(R.id.tv_cancel);
        confirm=view.findViewById(R.id.tv_confirm);
        wheelView=view.findViewById(R.id.wheelview);
        window = ((Activity) mContext).getWindow();
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
        wheelView.setCyclic(false);
        wheelView.setDividerColor(mContext.getResources().getColor(R.color.white));
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

//        // 设置外部可点击
//
//        // mMenuView添加OnTouchListener监听判断获取触屏位置如果在选择框外面则销毁弹出框
//        this.view.setOnTouchListener(new View.OnTouchListener() {
//
//            public boolean onTouch(View v, MotionEvent event) {
//
//                int height = view.findViewById(R.id.pop_layout).getTop();
//
//                int y = (int) event.getY();
//                if (event.getAction() == MotionEvent.ACTION_UP) {
//                    if (y < height) {
//
//                    }
//                }
//                return true;
//            }
//        });

        this.setOutsideTouchable(false);
        /* 设置弹出窗口特征 */
        // 设置视图
        this.setContentView(this.view);
        // 设置弹出窗体的宽和高

        this.setHeight(RelativeLayout.LayoutParams.WRAP_CONTENT);
        this.setWidth(RelativeLayout.LayoutParams.MATCH_PARENT);
        // 设置弹出窗体显示时的动画，从底部向上弹出
        this.setAnimationStyle(R.style.BottomRise);



    }


}
