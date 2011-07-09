/**
 * 
 */
package eu.silvere;

import android.media.AudioFormat;
import android.media.AudioRecord;
import android.media.MediaRecorder.AudioSource;
import android.util.FloatMath;
import android.util.Log;
import android.widget.SlidingDrawer;
import edu.emory.mathcs.jtransforms.fft.FloatFFT_1D;


/**
 * @author Vincent Dupont <vincent.touffi@gmail.com>
 *
 */
public class BeerSoundAnalyzer {
	
	/** 
	 * The sampling frequency of the record
	 */
	private static final int SAMPLING_FREQ = 44100;
	/** 
	 * The duration of record
	 */
	private static final int RECORD_DURATION = 3;
	
	
	private FloatFFT_1D mFFT;
	private AudioRecord mRecord;
	private byte[] mRawDataMic;
	private float[] mSoundData;
	
	public BeerSoundAnalyzer() {
		
		int bufSize = AudioRecord.getMinBufferSize(SAMPLING_FREQ, AudioFormat.CHANNEL_IN_MONO, AudioFormat.ENCODING_PCM_16BIT);
		bufSize = RECORD_DURATION * SAMPLING_FREQ < bufSize ? bufSize : RECORD_DURATION * SAMPLING_FREQ;
		Log.d("Beer", "Beer " + "bufSize " + bufSize);
		mRecord = new AudioRecord(AudioSource.MIC, SAMPLING_FREQ,AudioFormat.CHANNEL_IN_MONO, 
				AudioFormat.ENCODING_PCM_16BIT, bufSize);
		
		mRawDataMic = new byte[bufSize];
		mSoundData = new float[bufSize] ;
		
		mFFT = new FloatFFT_1D(bufSize);
		
	}
	
	
	public void launchRecording() {
		
		int ret ;
		float maxNorm = 0;
		float norm;
		int iMax = 0;
		
		String s = new String();
		for (int i = 0 ; i < 10 ; i++)
			s += " " + mRawDataMic[i] ;
		Log.d("Beer", "Beer " + s);
		Log.d("Beer", "Beer " + mRawDataMic.length);
		
		mRecord.startRecording();
		ret = mRecord.read(mRawDataMic, 0, mRawDataMic.length);
		mRecord.stop();
		
		for (int i = 0 ; i < ret ; i++)
			mSoundData[i] = mRawDataMic[i];
		
		mFFT.realForward(mSoundData);
		iMax = 0;
		maxNorm = norm = mSoundData[0];
		for (int i = 1 ; i < mSoundData.length - 1 ; i++) {
			norm = FloatMath.sqrt(mSoundData[i] * mSoundData[i] + mSoundData[i+1] * mSoundData[i+1]);
			if (maxNorm < norm) {
				maxNorm = norm ;
				iMax = i;
			}
		}
		Log.d("Beer", "Beer max norm " + maxNorm + " iMax " + iMax);
	}

	
}
