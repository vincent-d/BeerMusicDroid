/**
 * 
 */
package eu.silvere;

/**
 * @author Vincent Dupont <vincent.touffi@gmail.com>
 * 
 *         This class allows computing with notes
 * 
 */
public class BeerNoteComputing {

	private static final int NB_OCT = 8;
	private BeerNote mNotes[];

	public BeerNoteComputing() {

		mNotes = new BeerNote[NB_OCT * 12];

		mNotes[0] = new BeerNote(32.70f, BeerNoteName.C, 0);
		mNotes[1] = new BeerNote(34.65f, BeerNoteName.Cs, 0);
		mNotes[2] = new BeerNote(36.71f, BeerNoteName.D, 0);
		mNotes[3] = new BeerNote(38.89f, BeerNoteName.Eb, 0);
		mNotes[4] = new BeerNote(41.20f, BeerNoteName.E, 0);
		mNotes[5] = new BeerNote(43.65f, BeerNoteName.F, 0);
		mNotes[6] = new BeerNote(46.25f, BeerNoteName.Fs, 0);
		mNotes[7] = new BeerNote(49.00f, BeerNoteName.G, 0);
		mNotes[8] = new BeerNote(51.91f, BeerNoteName.Ab, 0);
		mNotes[9] = new BeerNote(55.00f, BeerNoteName.A, 0);
		mNotes[10] = new BeerNote(58.27f, BeerNoteName.Bb, 0);
		mNotes[11] = new BeerNote(67.74f, BeerNoteName.B, 0);

		for (int i = 12; i < mNotes.length; i++) {
			mNotes[i] = new BeerNote();
			mNotes[i].oct(mNotes[i - 12]);
		}

	}

	public BeerNote getApproxNoteFromFreq(float freq) {

		if (freq < (mNotes[1].getFreq() + mNotes[0].getFreq()) / 2)
			return mNotes[0];
		else if (freq >= (mNotes[NB_OCT * 12 - 1].getFreq() + mNotes[NB_OCT * 12 - 2].getFreq()) / 2)
			return mNotes[NB_OCT * 12 - 1];

		BeerNote n = mNotes[0];
		float prev = 0;
		float next = 1;
		double r = Math.pow(2.0, 1.0 / 24.0);
		for (int i = 1; i < mNotes.length - 1; i++) {
			prev = (float) (mNotes[i - 1].getFreq() * r);
			next = (float) (mNotes[i].getFreq() * r);
			if ((freq >= prev) && (freq < next)) {
				n = mNotes[i];
				break;
			}
		}

		BeerNote nret = new BeerNote(n);
		short t = (short) (2 * (freq - n.getFreq()) / (next - prev) * 255);
		nret.setTrust(t);
		return nret;

	}
}
