package eu.silvere;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

public class BeerMainActivity extends Activity {

	private BeerBottleLoader mBeerBottleLoader;
	private ProgressBar mProgressCircle;
	private Button mLaunchButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		mBeerBottleLoader = new BeerBottleLoader(getResources());

		setContentView(R.layout.beer_main_activity_view);
		mProgressCircle = (ProgressBar) findViewById(R.id.progressBar1);
		mLaunchButton = (Button) findViewById(R.id.launch);

		mLaunchButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Context ctx = getApplicationContext();
				Intent intent = new Intent(ctx, BeerListActivity.class);
				intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

				Bundle bundle = new Bundle();

				bundle.putParcelableArrayList("bottle_list", mBeerBottleLoader.getBottles());

				intent.putExtras(bundle);

				ctx.startActivity(intent);
			}
		});

		Log.d("Beer", "Beer" + "Loading bottles");
		new ProgressAsyncTask().execute();

	}

	public class ProgressAsyncTask extends AsyncTask<Void, Void, Void> {

		protected void onPreExecute() {
			mProgressCircle.setVisibility(View.VISIBLE);
			mLaunchButton.setClickable(false);
			Log.d("Beer", "Beer Start loading task");
		}

		protected Void doInBackground(Void... params) {

			mBeerBottleLoader.start();

			while (mBeerBottleLoader.isRunning())
				;
			Log.d("Beer", "Beer  --- Stop loading task");
			return null;
		}

		protected void onPostExecute(Void result) {
			mProgressCircle.setVisibility(View.GONE);
			mLaunchButton.setClickable(true);
			Log.d("Beer", "Beer Stop loading task");
		}

		protected void onCancelled() {
			super.onCancelled();
			mBeerBottleLoader.kill();
		}

	}

}
