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
	private int mLevel;

	public BeerNote(float freq, BeerNoteName name, int level) {
		setValues(freq, name, level);
	}

	public BeerNote() {
		// TODO Auto-generated constructor stub
	}

	public void setValues(float freq, BeerNoteName name, int level) {
		mFreq = freq;
		mName = name;
		mLevel = level;
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
		mLevel = base.mLevel + 1;
	}

	public float getFreq() {
		return mFreq;
	}

	public BeerNoteName getName() {
		return mName;
	}

	public int getLevel() {
		return mLevel;
	}

	public String toString() {
		return mName.getName() + " " + mLevel;
	}
}