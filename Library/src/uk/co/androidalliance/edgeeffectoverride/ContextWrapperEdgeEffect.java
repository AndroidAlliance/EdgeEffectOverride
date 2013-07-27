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

import android.graphics.PorterDuff;
import uk.co.androidalliance.edgeeffectoverride.R;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.util.DisplayMetrics;
import android.util.Log;

public class ContextWrapperEdgeEffect extends ContextWrapper {

  private static final int fDEFAULT_COLOR = 0xFF33B5E5;   //Holo blue color by default
	private static ResourcesEdgeEffect RES_EDGE_EFFECT;

  private int mColor;

	public ContextWrapperEdgeEffect(Context context) {
		this(context, fDEFAULT_COLOR);
	}

  public ContextWrapperEdgeEffect(Context context, int color) {
    super(context);
    mColor = color;
    Resources resources = context.getResources();
    if (RES_EDGE_EFFECT == null)
      RES_EDGE_EFFECT = new ResourcesEdgeEffect(resources.getAssets(), resources.getDisplayMetrics(), resources.getConfiguration());
  }

  public void setEdgeEffectColor(int color){
    mColor = color;
  }

  @Override
	public Resources getResources() {
		return RES_EDGE_EFFECT;
	}

	private class ResourcesEdgeEffect extends Resources {
		private int overscroll_edge = getPlatformDrawableId("overscroll_edge");
		private int overscroll_glow = getPlatformDrawableId("overscroll_glow");



		public ResourcesEdgeEffect(AssetManager assets, DisplayMetrics metrics, Configuration config) {
			//super(metrics, localConfiguration);
			super(assets, metrics, config);
		}

		private int getPlatformDrawableId(String name) {
			try {
				int i = ((Integer) Class.forName("com.android.internal.R$drawable").getField(name).get(null)).intValue();
				return i;
			} catch (ClassNotFoundException e) {
				Log.e("[ContextWrapperEdgeEffect].getPlatformDrawableId()", "Cannot find internal resource class");
				return 0;
			} catch (NoSuchFieldException e1) {
				Log.e("[ContextWrapperEdgeEffect].getPlatformDrawableId()", "Internal resource id does not exist: " + name);
				return 0;
			} catch (IllegalArgumentException e2) {
				Log.e("[ContextWrapperEdgeEffect].getPlatformDrawableId()", "Cannot access internal resource id: " + name);
				return 0;
			} catch (IllegalAccessException e3) {
				Log.e("[ContextWrapperEdgeEffect].getPlatformDrawableId()", "Cannot access internal resource id: " + name);
			}
			return 0;
		}

		public Drawable getDrawable(int resId) throws Resources.NotFoundException {
      Drawable ret = null;
			if (resId == this.overscroll_edge)
        ret = ContextWrapperEdgeEffect.this.getBaseContext().getResources().getDrawable(R.drawable.overscroll_edge);
			else if (resId == this.overscroll_glow)
        ret = ContextWrapperEdgeEffect.this.getBaseContext().getResources().getDrawable(R.drawable.overscroll_glow);
			else return super.getDrawable(resId);

      if(ret != null){
        ret.setColorFilter(mColor, PorterDuff.Mode.MULTIPLY);
      }

      return ret;
		}
	}
}
