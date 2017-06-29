package com.shnu.bigdatasdk.utils;

import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.shnu.bigdatasdk.R;

/**
 * Created by Dainel on 2017/6/29.
 */

public class ToolbarHelper {
    private View line;
    private ConstraintLayout bgImg;
    private View view;
    private ImageView rightIcon;
    private ImageView leftIcon;
    private ImageView secondRightIcon;
    private TextView title;

    public ToolbarHelper(View view) {
        this.view = view;
        rightIcon = (ImageView) view.findViewById(R.id.right_icon);
        leftIcon = (ImageView) view.findViewById(R.id.left_icon);
        secondRightIcon =(ImageView) view.findViewById(R.id.second_right_icon);
        title = (TextView) view.findViewById(R.id.title);
        bgImg = ((ConstraintLayout) view.findViewById(R.id.toolbar));
        line = view.findViewById(R.id.line);
    }

    public void setRightIcon(int resId) {
        rightIcon.setImageResource(resId);
    }

    public void setLeftIcon(int resId) {
        leftIcon.setImageResource(resId);
    }

    public void setTitleColor(int color){
        title.setTextColor(color);
    }
    public void setTitle(String str) {
        title.setText(str);
    }

    public void setRightIconVisible(int visible) {
        rightIcon.setVisibility(visible);
    }

    public void setLeftIconVisible(int visible) {
        leftIcon.setVisibility(visible);
    }
    public void setSecondRightVisible(int visible){secondRightIcon.setVisibility(visible);}

    public void setBgImgColor(int color) {
        bgImg.setBackgroundColor(color);
    }

    public void setLineColor(int color) {
        line.setBackgroundColor(color);
    }
    public void setSecondRightIcon(int resId){
        secondRightIcon.setImageResource(resId);
    }

    public View getToolBarView(){return bgImg;}

    public void setLeftOnClick(View.OnClickListener onClick){
        leftIcon.setOnClickListener(onClick);
    }
    public void setRightOnClick(View.OnClickListener onClick){
        rightIcon.setOnClickListener(onClick);
    }

    public void setSecondRightaOnclick(View.OnClickListener onclick){
        secondRightIcon.setOnClickListener(onclick);
    }

    public void setToolbarVisible(int visible){
        bgImg.setVisibility(visible);
    }

    public ImageView getRightIcon() {
        return rightIcon;
    }

    public ImageView getLeftIcon() {
        return leftIcon;
    }

    public ImageView getSecondRightIcon() {
        return secondRightIcon;
    }

    public TextView getTitileView(){return title;}
}
