package com.zhangmin.textviewlink;

import android.app.Activity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    public static final String TAG = "MainActivity";

    private TextView textView;

    private String clickStr1 = "", clickStr2 = "";
    private String str1 = "", str2 = "", str3 = "";
    private int start1 = 0, start2 = 0, end1 = 0, end2 = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String s = "球队[呼赛测试球队3]邀请[球员2]加入球队";

        textView = (TextView) findViewById(R.id.main_textview);
        textView.setText(getClickableSpan());

        //此行必须有
        textView.setMovementMethod(LinkMovementMethod.getInstance());

    }


    private SpannableString getClickableSpan() {

        //监听器
        TextListener listener = new TextListener() {
            @Override
            public void onClick() {
                Log.d(TAG,"MainActivity");
                Toast.makeText(MainActivity.this, "Click Success", Toast.LENGTH_SHORT).show();
            }
        };

        SpannableString spanableInfo = new SpannableString("球队[呼赛测试球队3]邀请[球员2]加入球队");
        int start = 2;  //超链接起始位置
        int end = 11;   //超链接结束位置

        //可以为多部分设置超链接
        spanableInfo.setSpan(new Clickable(listener), start, end, Spanned.SPAN_MARK_MARK);
        spanableInfo.setSpan(new Clickable(listener), 14, 18, Spanned.SPAN_MARK_MARK);

        return spanableInfo;
    }
}

class Clickable extends ClickableSpan implements View.OnClickListener {
    private final TextListener mListener;

    public Clickable(TextListener listener) {
        mListener = listener;
    }

    @Override
    public void onClick(View view) {     //这个方法是靠触摸启动的
        Log.d("MainActivity","Clickable");
        mListener.onClick();          // 这方法是靠监听器强行调用方法启动的  ，因为mListener没有可监听的对象。  事实证明我的猜想是正确的，我把原来的VIew.OnClickListener随便换成了一个listener,结果依然是正确的。
    }

}