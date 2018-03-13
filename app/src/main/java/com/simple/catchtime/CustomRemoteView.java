package com.simple.catchtime;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.RemoteViews;
import android.widget.TextView;

/**
 * Created by simple on 2018/3/12.
 */

@SuppressLint("AppCompatCustomView")
@RemoteViews.RemoteView
public class CustomRemoteView extends TextView {
    public CustomRemoteView(Context context) {
        super(context);
    }

    public CustomRemoteView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }
}
