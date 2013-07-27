/*
 * Copyright (c) 2013 Android Alliance, LTD
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
import android.util.AttributeSet;

public class WebView extends android.webkit.WebView {

	public WebView(Context context) {
		this(context, null);
	}

	public WebView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

  public WebView(Context context, AttributeSet attrs, int defStyle) {
    super(new ContextWrapperEdgeEffect(context), attrs, defStyle);
  }

  @Deprecated
  @TargetApi(11)
  public WebView(Context context, AttributeSet attrs, int defStyle, boolean privateBrowsing) {
    super(new ContextWrapperEdgeEffect(context), attrs, defStyle, privateBrowsing);
  }

  public void setEdgeEffectColor(int edgeEffectColor){
    ((ContextWrapperEdgeEffect)  getContext()).setEdgeEffectColor(edgeEffectColor);
  }
}
