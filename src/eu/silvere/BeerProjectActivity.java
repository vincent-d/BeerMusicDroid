package eu.silvere;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class BeerProjectActivity extends Activity {
	
	private BeerSoundAnalyzer mBeerAnalyze;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
    	Log.d("Beer", "Beer" + "Activity started");
        TextView tv = new TextView(this);
        tv.setText("Hello, Beeeeeeeeeeer");
        setContentView(tv);

        mBeerAnalyze = new BeerSoundAnalyzer();
		mBeerAnalyze.launchRecording();
		float f = mBeerAnalyze.computeFFT();
		tv.setText("Hello, Beeeeeeeeeeer " + f);
    }
}