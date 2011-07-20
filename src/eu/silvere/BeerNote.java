package eu.silvere;

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