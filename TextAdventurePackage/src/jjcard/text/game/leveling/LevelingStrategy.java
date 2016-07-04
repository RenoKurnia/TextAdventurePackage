package jjcard.text.game.leveling;

/**
 * A strategy to level
 *
 * @param <A>
 */
public interface LevelingStrategy<A extends HasLeveling> {

	
	/**
	 * Returns the user of the LevelingStrategy
	 * @return
	 */
	public A getUser();
	
	/**
	 * updates the user and returns the result
	 * @return
	 */
	public A update();
	

}
