package eu.silvere;


/**
 * 
 * @author Vincent Dupont <vincent.touffi@gmail.com>
 * 
 *         This class represents a note associated with its frequency
 * 
 */
class BeerNote {

	private float mFreq;
	private BeerNoteName mName;
	private int mOctave;
	private short mTrust;

	public BeerNote(float freq, BeerNoteName name, int octave) {
		setValues(freq, name, octave, (short) 0);
	}

	public BeerNote(BeerNote note) {
		setValues(note.getFreq(), note.getName(), note.getOctave(), note.getTrust());
	}

	public BeerNote() {
		// TODO Auto-generated constructor stub
	}

	public void setValues(float freq, BeerNoteName name, int octave, short trust) {
		mFreq = freq;
		mName = name;
		mOctave = octave;
		mTrust = trust;
	}

	/**
	 * Initializes the attributes an octave higher than @p base
	 * 
	 * @param base
	 *            The base note
	 */
	public void oct(BeerNote base) {
		mFreq = base.mFreq * 2;
		mName = base.mName;
		mOctave = base.mOctave + 1;
	}

	public float getFreq() {
		return mFreq;
	}

	public BeerNoteName getName() {
		return mName;
	}

	public int getOctave() {
		return mOctave;
	}

	public short getTrust() {
		return mTrust;
	}

	public void setTrust(short trust) {
		mTrust = trust;
	}

	public String toString() {
		return mName.getName() + " " + mOctave + " trust " + mTrust;
	}

}
