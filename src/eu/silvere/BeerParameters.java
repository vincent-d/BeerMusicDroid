/**
 * 
 */
package eu.silvere;

import android.util.Log;

/**
 * @author Vincent Dupont <vincent.touffi@gmail.com>
 * 
 *         This class handle the computing of volume in the bottle
 * 
 */
public class BeerParameters {

	/**
	 * The array of slopes for each line
	 */
	private float[] mA;
	/**
	 * The array of y-intercept for each line
	 */
	private float[] mB;
	/**
	 * The array of reference frequencies
	 */
	private float[] mFreq;
	/**
	 * The array of reference volumes
	 */
	private float[] mVol;

	public BeerParameters() {

	}

	/**
	 * Constructor for BeerParameters
	 * 
	 * It computes slopes and y-intercept for each couple of (volume, freq)
	 * points
	 * 
	 * @param param
	 *            The array of parameters (0-index = volume, 1-index = freq)
	 */
	public BeerParameters(float[][] param) {

		mFreq = new float[param[0].length];
		mVol = new float[param[0].length];

		for (int i = 0; i < param[0].length; i++) {
			mVol[i] = param[0][i];
			mFreq[i] = param[1][i];
		}

		computeCoef();

	}

	/**
	 * Compute the volume of beer from frequency in accordance to the models
	 * 
	 * @param freq
	 *            The frequency
	 * @return The computed volume
	 */
	public float computeVolume(float freq) {

		float v = 0f;

		if (freq <= mFreq[0])
			v = mA[0] * freq + mB[0];
		else if (freq >= mFreq[mFreq.length - 1])
			v = mA[mA.length - 1] * freq + mB[mB.length - 1];
		else {
			for (int i = 0; i < mA.length; i++) {
				if (freq >= mFreq[i] && freq <= mFreq[i + 1])
					v = mA[i] * freq + mB[i];
			}
		}

		Log.d("Beer", "Beer freq " + freq + " Hz, vol " + v);

		return v;
	}

	/**
	 * Compute the coefficients (slopes and y-intercepts)
	 */
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
