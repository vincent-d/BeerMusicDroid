/**
 * 
 */
package eu.silvere;

/**
 * @author Vincent Dupont <vincent.touffi@gmail.com>
 * 
 */
public enum BeerNoteName {

	C("Do"), Cs("Do#"), D("Ré"), Eb("Mib"), E("Mi"), F("Fa"), Fs("Fa#"), G("Sol"), Ab("Lab"), A(
			"La"), Bb("Sib"), B("Si");

	private final String mName;

	private BeerNoteName(String name) {
		mName = name;
	}

	public String getName() {
		return mName;
	}

}
