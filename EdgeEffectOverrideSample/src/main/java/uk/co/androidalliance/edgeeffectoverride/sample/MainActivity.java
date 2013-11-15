package uk.co.androidalliance.edgeeffectoverride.sample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import uk.co.androidalliance.edgeeffectoverride.EdgeEffectListView;

public class MainActivity extends Activity implements AdapterView.OnItemClickListener {

    private static final String[] STRINGS = {
            "ListView",
            "ExpandableListView",
            "GridView",
            "ScrollView",
            "ViewPager",
            "WebView",
    };
    private static final Class[] ACTIVITIES = {
            ListViewActivity.class,
            ExpandableListViewActivity.class,
            GridViewActivity.class,
            ScrollViewActivity.class,
            ViewPagerActivity.class,
            WebViewActivity.class
    };
    private EdgeEffectListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mListView = (EdgeEffectListView) findViewById(R.id.listview);
        mListView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, STRINGS));
        mListView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(this, ACTIVITIES[position]);
        startActivity(intent);
    }
}
