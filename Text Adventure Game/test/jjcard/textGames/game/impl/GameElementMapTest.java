package jjcard.textGames.game.impl;

import static org.junit.Assert.*;

import jjcard.textGames.game.impl.GameElementMap;
import jjcard.textGames.game.impl.Item;
import jjcard.textGames.game.impl.Mob;

import org.junit.Test;

public class GameElementMapTest {

	@Test
	public void AddTestName() {
		
		Item e1 = new Item.ItemBuilder().standardName("item1").build();
		assertEquals("item1", e1.getStandardName());
		GameElementMap<Item> map = new GameElementMap<Item>();
		
		map.put(e1);
		
		assertEquals(1, map.getElementCount());
		Item r1 = map.get(e1.getStandardName());
		assertNotNull(r1);
		assertEquals(e1, r1);
		
		Item r2 = map.get("item1");
		assertNotNull(r2);
		assertEquals(r2, r1);
		
		Item r3 = map.getFromStandardName("item1");
		assertNotNull(r3);
		assertEquals(r3, r1);
		
		
	}
	@Test
	public void AddTestAltName(){
		Mob m = new Mob.MobBuilder().attack(9).standardName("Bob").altNames(new String[] {"Bubba", "Ray"}).build();
		
		GameElementMap<Mob> MobMap = new GameElementMap<Mob>();
		
		MobMap.put(m);
		assertEquals(1, MobMap.getElementCount());
		
		assertEquals(3, MobMap.getNameCount());
		
		Mob r1 = MobMap.get("Bob");
		assertNotNull(r1);
		assertEquals(m, r1);
		
		Mob r2 = MobMap.get("Bubba");
		assertNotNull(r2);
		assertEquals(m, r2);
		
		Mob r3 = MobMap.get("Ray");
		assertNotNull(r3);
		assertEquals(m, r3);
		
		//using alternate name here should return null
		Mob r4 = MobMap.getFromStandardName("Ray");
		assertNull(r4);
		
		Mob r5 = MobMap.get("not a real key");
		assertNull(r5);
	}
	
	@Test
	public void removeTest(){
		Mob m = new Mob.MobBuilder().standardName("Bob").altNames(new String[] {"Bubba", "Ray"}).build();
		
		GameElementMap<Mob> MobMap = new GameElementMap<Mob>();
		
		MobMap.put(m);
		assertEquals(1, MobMap.getElementCount());
		
		assertEquals(3, MobMap.getNameCount());
		
		
		Mob r1 = MobMap.remove("Bubba");
		assertNotNull(r1);
		assertEquals(r1, m);
		
		assertEquals(0, MobMap.getElementCount());
		
		assertEquals(0, MobMap.getNameCount());
		
		
		MobMap.put(m);
		assertEquals(1, MobMap.getElementCount());
		
		assertEquals(3, MobMap.getNameCount());
		
		
		Mob r2 = MobMap.remove("Bob");
		assertNotNull(r2);
		assertEquals(r2, m);
		
		assertEquals(0, MobMap.getElementCount());
		
		assertEquals(0, MobMap.getNameCount());
		
		
		MobMap.put(m);
		assertEquals(1, MobMap.getElementCount());
		
		assertEquals(3, MobMap.getNameCount());
		
		
		Mob r3 = MobMap.remove("Ray");
		assertNotNull(r3);
		assertEquals(r3, m);
		
		assertEquals(0, MobMap.getElementCount());
		
		assertEquals(0, MobMap.getNameCount());
		
		//added multiple, removing one shouldn't effect others
		MobMap.put(m);
		Mob m2 = new Mob.MobBuilder().standardName("Store keep").health(7).altNames(new String[] {"George"}).build();
		MobMap.put(m2);
		
		assertEquals(2, MobMap.getElementCount());
		assertEquals(5, MobMap.getNameCount());
		
		Mob r4 = MobMap.remove("store keep");
		assertNotNull(r4);
		assertEquals(r4, m2);
		
		assertEquals(1, MobMap.getElementCount());
		assertEquals(3, MobMap.getNameCount());
		
	}
	@Test
	public void containsTest(){
		GameElementMap<Mob> MobMap = new GameElementMap<Mob>();
		Mob m = new Mob.MobBuilder().standardName("Bob").altNames(new String[] {"Bubba", "Ray"}).build();
		MobMap.put(m);
		Mob m2 = new Mob.MobBuilder().standardName("Store keep").health(7).altNames(new String[] {"George"}).build();
		MobMap.put(m2);
		
		
		assertTrue(MobMap.containsName("store keep"));
		assertTrue(MobMap.containsName("George"));
		assertTrue(MobMap.containsName("Bob"));
		assertTrue(MobMap.containsName("Bubba"));
		assertTrue(MobMap.containsName("Ray"));
		
		
		assertTrue(MobMap.containsStandardName("Bob"));
		assertTrue(MobMap.containsStandardName("Store keep"));
		
		assertFalse(MobMap.containsStandardName("Bubba"));
		assertFalse(MobMap.containsStandardName("Ray"));

		
	}
	@Test
	public void testGetAllStandardNames(){
		Item e1 = new Item.ItemBuilder().standardName("item1").build();
		assertEquals("item1", e1.getStandardName());
		
		Item e2 = new Item.ItemBuilder().standardName("item2").altNames(new String[] {"health potion", "health flask"}).build();
		GameElementMap<Item> map = new GameElementMap<Item>();
		
		map.put(e1);
		map.put(e2);
		
		String[] names = map.getAllStandardNames();
		
		
		assertArrayEquals(new String[] {"ITEM2", "ITEM1"}, names);
	}
	
	@Test
	public void testGetAllStandardNamesKeepingCase(){
		Item e1 = new Item.ItemBuilder().standardName("iteM1").build();
		assertEquals("iteM1", e1.getStandardName());
		
		Item e2 = new Item.ItemBuilder().standardName("itEm2").altNames(new String[] {"health potion", "health flask"}).build();
		GameElementMap<Item> map = new GameElementMap<Item>();
		
		map.put(e1);
		map.put(e2);
		
		String[] names = map.getAllStandardNamesSaveCase();
		
		assertArrayEquals(new String[] {"itEm2", "iteM1"}, names);
	}

}