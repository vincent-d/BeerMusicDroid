/**
 * 
 */
package eu.silvere;

import android.media.AudioFormat;
import android.media.AudioRecord;
import android.media.MediaRecorder.AudioSource;
import android.util.Log;
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
	
	public BeerSoundAnalyzer() {
		
		int bufSize = AudioRecord.getMinBufferSize(SAMPLING_FREQ, AudioFormat.CHANNEL_IN_MONO, AudioFormat.ENCODING_PCM_16BIT);
		mRecord = new AudioRecord(AudioSource.MIC, SAMPLING_FREQ,AudioFormat.CHANNEL_IN_MONO, 
				AudioFormat.ENCODING_PCM_16BIT, bufSize);
		
		mRawDataMic = new byte[bufSize];
		
		mFFT = new FloatFFT_1D(RECORD_DURATION * SAMPLING_FREQ);
		
	}
	
	
	public void launchRecording() {
		
		String s = new String(mRawDataMic, 0, 10);
		Log.d("Beer", s);
		
		mRecord.read(mRawDataMic, 0, mRawDataMic.length);
		
		s = new String(mRawDataMic, 0, 10);
		Log.d("Beer", s);
	}

	
}
