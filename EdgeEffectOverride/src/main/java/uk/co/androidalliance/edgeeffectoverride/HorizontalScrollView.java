/*
 * Copyright (c) 2014 Android Alliance, LTD
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package uk.co.androidalliance.edgeeffectoverride;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.util.AttributeSet;

public class HorizontalScrollView extends android.widget.HorizontalScrollView {

    public static final int SCROLL_RIGHT = 1;
    public static final int SCROLL_LEFT = 2;
    private OnScrollChangedListener mOnScrollChangedListener;

    public HorizontalScrollView(Context context) {
        this(context, null);
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public HorizontalScrollView(Context context, AttributeSet attrs) {
        super(new ContextWrapperEdgeEffect(context), attrs);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            init(context, attrs, android.R.attr.horizontalScrollViewStyle);
        } else {
            init(context, attrs, 0);
        }
    }

    public HorizontalScrollView(Context context, AttributeSet attrs, int defStyle) {
        super(new ContextWrapperEdgeEffect(context), attrs, defStyle);
        init(context, attrs, defStyle);
    }

    private void init(Context context, AttributeSet attrs, int defStyle) {
        int color = context.getResources().getColor(R.color.default_edgeeffect_color);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.EdgeEffectView, defStyle, 0);

        setEdgeEffectColor(color = a.getColor(R.styleable.EdgeEffectView_edgeeffect_color, color));

        a.recycle();
    }

    public void setEdgeEffectColor(int edgeEffectColor) {
        ((ContextWrapperEdgeEffect) getContext()).setEdgeEffectColor(edgeEffectColor);
    }

    @Override
    protected void onScrollChanged(int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
        super.onScrollChanged(scrollX, scrollY, oldScrollX, oldScrollY);
        if (mOnScrollChangedListener != null) {
            int scrollDirection;
            if (scrollX > oldScrollX) {
                scrollDirection = SCROLL_RIGHT;
            } else {
                scrollDirection = SCROLL_LEFT;
            }
            mOnScrollChangedListener.onScrollChanged(this, scrollDirection, scrollX, scrollY, oldScrollX, oldScrollY);
        }
    }

    public void setOnScrollChangedListener(OnScrollChangedListener listener) {
        mOnScrollChangedListener = listener;
    }

    public interface OnScrollChangedListener {
        void onScrollChanged(HorizontalScrollView scrollView, int scrollDirection, int scrollX, int scrollY, int oldScrollX, int oldScrollY);
    }
}
