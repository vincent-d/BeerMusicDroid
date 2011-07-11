/**
 * 
 */
package eu.silvere;

import android.util.Log;

/**
 * @author Vincent Dupont <vincent.touffi@gmail.com>
 * 
 */
public class BeerParameters {

	private float[] mA;
	private float[] mB;
	private float[] mFreq;
	private float[] mVol;

	public BeerParameters() {

	}

	public BeerParameters(float[][] param) {

		mFreq = new float[param[0].length];
		mVol = new float[param[0].length];

		for (int i = 0; i < param[0].length; i++) {
			mVol[i] = param[0][i];
			mFreq[i] = param[1][i];
		}

		computeCoef();

	}

	public float computeVolume(float freq) {

		float v = 0f;

		for (int i = 0; i < mA.length; i++) {
			if (freq >= mFreq[i] && freq <= mFreq[i + 1])
				v = mA[i] * freq + mB[i];
		}

		Log.d("Beer", "Beer freq " + freq + " Hz, vol " + v);

		return v;
	}

	public void computeCoef() {

		mA = new float[mFreq.length - 1];
		mB = new float[mFreq.length - 1];
		boolean sorted = false;

		while (!sorted) {
			sorted = true;
			for (int i = 0; i < mVol.length - 1; i++) {
				if (mVol[i] > mVol[i + 1]) {
					float tmpVol = mVol[i];
					float tmpFreq = mFreq[i];
					mVol[i] = mVol[i + 1];
					mVol[i + 1] = tmpVol;
					mFreq[i] = mFreq[i + 1];
					mFreq[i + 1] = tmpFreq;
					sorted = false;
				}
			}
		}

		for (int i = 0; i < mA.length; i++) {
			mA[i] = (mVol[i + 1] - mVol[i]) / (mFreq[i + 1] - mFreq[i]);
			mB[i] = mVol[i] - mA[i] * mFreq[i];
		}

	}

}
