package uk.co.androidalliance.edgeeffectoverride.sample;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import uk.co.androidalliance.edgeeffectoverride.ListView;

public class ListViewActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview_layout);

        ((ListView) findViewById(R.id.listview))
                .setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.stringarray)));
    }

}
