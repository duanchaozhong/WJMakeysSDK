package com.dabcdefg.dell.ddd.DCZ_okhttp;

import android.app.Dialog;
import android.content.Context;
import android.view.View;

import com.example.dell.myapplication2.R;


/**
 * Created by enjoytouch-ad02 on 2015/8/5.
 */
public class MiddleDialog<E> extends Dialog {
    private onButtonCLickListener2 listener2;
    private int position;
    private View view;
    /**
     *     确认与取消
     *
     * */
    public MiddleDialog(Context context, final onButtonCLickListener2 listener, int theme) {
        super(context, theme);
        view = View.inflate(context, R.layout.dialog_middle2, null);
        setContentView(view);
        setCancelable(false);        //设置点击对话框以外的区域时，是否结束对话框
        /*((TextView) view.findViewById(R.id.title)).setText(tv_ok);       //设置对话框的标题内容
        ((TextView) view.findViewById(R.id.content)).setText(content);
        if(tv_ok!=null){
            ((TextView) view.findViewById(R.id.tv_ok)).setText(tv_ok);
        }*/
        this.listener2 = listener;
        view.findViewById(com.example.authorlibrary.R.id.ok).setOnClickListener(new View.OnClickListener() {//取消
            @Override
            public void onClick(View v) {
                dismiss();
                listener2.onActivieButtonClick("1", position);
            }
        });
        view.findViewById(com.example.authorlibrary.R.id.cancel).setOnClickListener(new View.OnClickListener() {//取消
            @Override
            public void onClick(View v) {
                dismiss();
                listener2.onActivieButtonClick(null, position);
            }
        });
    }
    public interface onButtonCLickListener2<E>{
        public void onActivieButtonClick(E bean, int position);
    }
}
