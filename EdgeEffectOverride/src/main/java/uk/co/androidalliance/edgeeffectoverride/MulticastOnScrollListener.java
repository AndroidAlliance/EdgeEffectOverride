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

import android.widget.AbsListView;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class MulticastOnScrollListener implements AbsListView.OnScrollListener {

    private Set<AbsListView.OnScrollListener> mListeners = new HashSet<AbsListView.OnScrollListener>();

    public MulticastOnScrollListener add(AbsListView.OnScrollListener scrollListener) {
        mListeners.add(scrollListener);
        return this;
    }

    public MulticastOnScrollListener clear() {
        mListeners.clear();
        return this;
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        Iterator iterator = this.mListeners.iterator();
        while (iterator.hasNext()) {
            ((AbsListView.OnScrollListener) iterator.next()).onScroll(view, firstVisibleItem, visibleItemCount, totalItemCount);
        }
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        Iterator iterator = mListeners.iterator();
        while (iterator.hasNext()) {
            ((AbsListView.OnScrollListener) iterator.next()).onScrollStateChanged(view, scrollState);
        }
    }

    public MulticastOnScrollListener remove(AbsListView.OnScrollListener scrollListener) {
        mListeners.remove(scrollListener);
        return this;
    }
}
