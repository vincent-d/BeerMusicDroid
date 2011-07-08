package eu.silvere;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class BeerProjectActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TextView tv = new TextView(this);
        tv.setText("Hello, Beeeeeeeeeeer");
        setContentView(tv);

    }
}