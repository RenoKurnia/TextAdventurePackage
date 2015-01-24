package jjcard.textGames.game.parser;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import jjcard.textGames.game.parser.TextToken;
import jjcard.textGames.game.parser.impl.BasicTextTokenType;
import jjcard.textGames.game.parser.impl.SpaceTextTokenType;

import org.junit.Before;
import org.junit.Test;

public class TextTokenTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		String word = "glass-mail";
		String standardWord = "glass mail";
		TextToken<BasicTextTokenType> j = new TextToken<BasicTextTokenType>(word, standardWord, BasicTextTokenType.ARMOR);
		
		BasicTextTokenType t = j.getType();
		
		assertEquals(BasicTextTokenType.ARMOR, t);
		assertEquals(word, j.getToken());
		assertEquals(standardWord, j.getStandardToken());
		assertTrue(t.isObject());
		assertFalse(t.isVerb());
	}
	@Test
	public void otherEnumTest(){
		String word = "firefly";
		String standardWord = "Serenity";
		TextToken<SpaceTextTokenType> j = new TextToken<SpaceTextTokenType>(word, standardWord, SpaceTextTokenType.SHIP);
		
		SpaceTextTokenType t = j.getType();
		
		assertEquals(SpaceTextTokenType.SHIP, t);
		assertEquals(word, j.getToken());
		assertEquals(standardWord, j.getStandardToken());
		assertTrue(t.isObject());
		assertFalse(t.isVerb());
	}

}
