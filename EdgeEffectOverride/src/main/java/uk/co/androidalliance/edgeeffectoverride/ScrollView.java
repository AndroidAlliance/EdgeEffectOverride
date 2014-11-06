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

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;

public class ScrollView extends android.widget.ScrollView {

    public static final int SCROLL_UP = 1;
    public static final int SCROLL_DOWN = 2;
    private OnScrollChangedListener mOnScrollChangedListener;


    public ScrollView(Context context) {
        this(context, null);
    }

    public ScrollView(Context context, AttributeSet attrs) {
        this(context, attrs, android.R.attr.scrollViewStyle);
    }

    public ScrollView(Context context, AttributeSet attrs, int defStyle) {
        super(new ContextWrapperEdgeEffect(context), attrs, defStyle);
        init(context, attrs, defStyle);
    }

    private void init(Context context, AttributeSet attrs, int defStyle) {
        int color = context.getResources().getColor(R.color.default_edgeeffect_color);

        if (attrs != null) {
            TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.EdgeEffectView, defStyle, 0);
            color = a.getColor(R.styleable.EdgeEffectView_edgeeffect_color, color);
            a.recycle();
        }
        setEdgeEffectColor(color);
    }

    public void setEdgeEffectColor(int edgeEffectColor) {
        ((ContextWrapperEdgeEffect) getContext()).setEdgeEffectColor(edgeEffectColor);
    }

    @Override
    protected void onScrollChanged(int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
        super.onScrollChanged(scrollX, scrollY, oldScrollX, oldScrollY);
        if (mOnScrollChangedListener != null) {
            int scrollDirection;
            if (scrollY > oldScrollY) {
                scrollDirection = SCROLL_UP;
            } else {
                scrollDirection = SCROLL_DOWN;
            }
            mOnScrollChangedListener.onScrollChanged(this, scrollDirection, scrollX, scrollY, oldScrollX, oldScrollY);
        }
    }

    public void setOnScrollChangedListener(OnScrollChangedListener listener) {
        mOnScrollChangedListener = listener;
    }

    public interface OnScrollChangedListener {
        void onScrollChanged(ScrollView scrollView, int scrollDirection, int scrollX, int scrollY, int oldScrollX, int oldScrollY);
    }
}
