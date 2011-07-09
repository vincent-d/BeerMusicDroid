package eu.silvere;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class BeerProjectActivity extends Activity {

	private BeerSoundAnalyzer mBeerAnalyze;
	private TextView tv;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// No Title bar
		requestWindowFeature(Window.FEATURE_NO_TITLE);

		// full screen
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
		getWindow().clearFlags(
				WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);

		setContentView(R.layout.main);

		Log.d("Beer", "Beer" + "Activity started");

		mBeerAnalyze = new BeerSoundAnalyzer();
		tv = (TextView) findViewById(R.id.text);
		Button button = (Button) findViewById(R.id.button);

		button.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				mBeerAnalyze.launchRecording();
				float f = mBeerAnalyze.computeFFT();
				tv.setText("Hello, Beeeeeeeeeeer " + f);
			}
		});

	}
}