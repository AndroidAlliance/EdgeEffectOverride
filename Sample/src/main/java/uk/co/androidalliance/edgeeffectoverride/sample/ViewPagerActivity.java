package uk.co.androidalliance.edgeeffectoverride.sample;

import android.os.Bundle;
import android.app.Activity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ViewPagerActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewpager_layout);

      ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);

      viewPager.setAdapter(new PagerAdapter() {

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
          TextView textView = new TextView(ViewPagerActivity.this);
          textView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
          textView.setGravity(Gravity.CENTER);
          textView.setText("Item "+position);
          container.addView(textView);
          return textView;
        }

        @Override
        public CharSequence getPageTitle(int position) {
          return "Item "+position;
        }

        @Override
        public int getCount() {
          return 2;
        }

        @Override
        public boolean isViewFromObject(View view, Object o) {
          return o instanceof View && view == o;
        }
      });
    }

}
