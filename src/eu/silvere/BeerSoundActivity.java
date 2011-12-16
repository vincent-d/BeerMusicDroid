package eu.silvere;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class BeerSoundActivity extends Activity {

	private TextView mTextTitle;
	private TextView mTextResult;
	private ProgressBar mAnalyzeProgresBar;
	private Button mLaunchButton;

	private BeerSoundAnalyzer mBeerSoundAnalyzer;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// No Title bar
		// requestWindowFeature(Window.FEATURE_NO_TITLE);

		// full screen
		// getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
		// getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);

		setContentView(R.layout.main);

		Log.d("Beer", "Beer" + "Activity started");

		mBeerSoundAnalyzer = new BeerSoundAnalyzer();

		Bundle bundle = getIntent().getBundleExtra("b");
		BeerBottle beerBottle = (BeerBottle) bundle.getParcelable("bottle");
		mBeerSoundAnalyzer.setBeerBottle(beerBottle);

		mTextTitle = (TextView) findViewById(R.id.textTitle);
		mTextTitle.setText(mBeerSoundAnalyzer.getBeerBottle().getName() + " "
				+ mBeerSoundAnalyzer.getBeerBottle().getDescription());

		mTextResult = (TextView) findViewById(R.id.textResult);
		mTextResult.setText("");

		mAnalyzeProgresBar = (ProgressBar) findViewById(R.id.analyzingProgressBar);
		mAnalyzeProgresBar.setMax(3);

		mLaunchButton = (Button) findViewById(R.id.button);

		mLaunchButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				new AnalyzerAsyncTask().execute();
			}
		});

	}

	/**
	 * @author Vincent Dupont <vincent.touffi@gmail.com>
	 * 
	 */
	public class AnalyzerAsyncTask extends AsyncTask<float[], Integer, Float> {

		float mFreq;
		float mVol;
		BeerNote mNote;

		@Override
		protected void onPreExecute() {
			mAnalyzeProgresBar.setProgress(0);
			mAnalyzeProgresBar.setVisibility(View.VISIBLE);
			mLaunchButton.setEnabled(false);
		}

		@Override
		protected Float doInBackground(float[]... params) {
			mBeerSoundAnalyzer.launchRecording();
			publishProgress(1);
			float f = mBeerSoundAnalyzer.computeFFT();
			publishProgress(2);
			mFreq = f;
			mVol = mBeerSoundAnalyzer.computeVolume(f);
			mNote = mBeerSoundAnalyzer.computeNote(f);
			return f;
		}

		@Override
		protected void onProgressUpdate(Integer... values) {
			mAnalyzeProgresBar.setProgress(values[0]);
		}

		@Override
		protected void onPostExecute(Float result) {
			mAnalyzeProgresBar.setVisibility(View.INVISIBLE);
			mTextResult.setText("Hello, " + mBeerSoundAnalyzer.getBeerBottle().getName() + " "
					+ result + " Hz " + mVol + " mL " + mNote);
			mLaunchButton.setEnabled(true);
		}

		@Override
		protected void onCancelled() {
			// TODO Auto-generated method stub
			super.onCancelled();
		}

	}

}