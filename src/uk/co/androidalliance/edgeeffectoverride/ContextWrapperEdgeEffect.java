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

	private static ResourcesEdgeEffect RES_EDGE_EFFECT;

	public ContextWrapperEdgeEffect(Context context) {
		super(context);
		Resources resources = context.getResources();
		if (RES_EDGE_EFFECT == null)
			RES_EDGE_EFFECT = new ResourcesEdgeEffect(resources.getAssets(), resources.getDisplayMetrics(), resources.getConfiguration());
	}

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
			if (resId == this.overscroll_edge)
				return ContextWrapperEdgeEffect.this.getBaseContext().getResources().getDrawable(R.drawable.overscroll_edge);
			if (resId == this.overscroll_glow)
				return ContextWrapperEdgeEffect.this.getBaseContext().getResources().getDrawable(R.drawable.overscroll_glow);
			return super.getDrawable(resId);
		}
	}
}
