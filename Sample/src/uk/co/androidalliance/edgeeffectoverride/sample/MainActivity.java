package uk.co.androidalliance.edgeeffectoverride.sample;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import uk.co.androidalliance.edgeeffectoverride.EdgeEffectListView;

public class MainActivity extends Activity {

  private static final String[] fSTRINGS = {
      "Item 1",
      "Item 2",
      "Item 3",
      "Item 4",
      "Item 5",
      "Item 6",
      "Item 7",
      "Item 8",
      "Item 9",
      "Item 10",
      "Item 11",
      "Item 12",
      "Item 13",
      "Item 14",
      "Item 15",
      "Item 16",
      "Item 17",
      "Item 18",
      "Item 19",
      "Item 20",
      "Item 21",
      "Item 22",
      "Item 23",
      "Item 24",
      "Item 25"
  };

  private EdgeEffectListView mListView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    mListView = (EdgeEffectListView) findViewById(R.id.listview);

    mListView.setEdgeEffectColor(Color.RED);

    mListView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, fSTRINGS));
  }


}
