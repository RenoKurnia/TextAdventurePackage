package jjcard.textGames.game;

import java.util.Collection;
import java.util.HashMap;

public class GameElementMap<A extends GameElement> {
	private HashMap<String, String> altNamesMap;
	private HashMap<String, A> elementMap;
	
	
	
   public GameElementMap(){
	   super();
	   
	   altNamesMap = new HashMap<String, String>();
	   elementMap = new HashMap<String, A>();
   }
   
   public GameElementMap(int elementMapCapacity, int altNameMapCapacity){
	   super();
	   altNamesMap = new HashMap<String, String>(altNameMapCapacity);
	   elementMap = new HashMap<String, A>(elementMapCapacity);
   }
   private A put(String standerdName, String[] altNames, A gameElement){
	   String standardNameUpper = standerdName.toUpperCase();
	   if (altNames != null){
		   for (String altName: altNames){
			   altNamesMap.put(altName.toUpperCase(), standardNameUpper);
		   }		   
	   }
	   altNamesMap.put(standardNameUpper, standardNameUpper);
	   return elementMap.put(standardNameUpper, gameElement);

   }
    public void putStandardNameOnly(A gameElement){
    	String TrueName = gameElement.getElementName().getStandardName().toUpperCase();
    	altNamesMap.put(TrueName, TrueName);
    	elementMap.put(TrueName, gameElement);
    }
	public A put(A gameElement){
		return put(gameElement.getElementName().getStandardName(), 
				gameElement.getElementName().getAltNames(), gameElement);
			

	}
	
	public A get(String name){
		String realKey = altNamesMap.get(name.toUpperCase());
		
		if (realKey != null){
			return elementMap.get(realKey);
		} else {
			return null;
		}
	}
	
	public A getFromStandardName(String standerdName){
		return elementMap.get(standerdName.toUpperCase());
	}
	public boolean containsName(String name){
		return altNamesMap.containsKey(name.toUpperCase());
	}
	public boolean containsStandardName(String standerdName){
		return elementMap.containsKey(standerdName.toUpperCase());
	}
	/**
	 * returns the number of standard and alternate names.
	 * @return
	 */
	public int getNameCount(){
		return altNamesMap.size();
	}
	/**
	 * returns the number of GameElements
	 * @return
	 */
	public int getElementCount(){
		return elementMap.size();
	}
	
	public A remove(String name){
		String realKey = altNamesMap.get(name.toUpperCase());
		A element = elementMap.remove(realKey);
		
		
		//now remove from the altname table
		if (element.getElementName().getAltNames() != null){
			for (String altName: element.getElementName().getAltNames()){
				altNamesMap.remove(altName.toUpperCase());
			}
		}
		altNamesMap.remove(element.getStandardName().toUpperCase());
		return element;
	}
	
	/**
	 * gets the all the standard names from the map. All are uppercase.
	 * to get list with original case, call {@link getAllStandardNamesSaveCase()}
	 * @return
	 */
	public String[] getAllStandardNames(){
		return elementMap.keySet().toArray(new String[getElementCount()]);
	}
	
	public String getAllStandardNamesString(){
		return elementMap.keySet().toString();
	}
	public String[] getAllStandardNamesSaveCase(){
		String[] names = new String[getElementCount()];
		
		int i = 0;
		for (A element: elementMap.values()){
			names[i++] = element.getStandardName();
		}
		
		return names;
	}
	/**
	 * returns all the elements in the map.
	 * do not modify elements this way.
	 * @return
	 */
	public Collection<A> getElements(){
		return elementMap.values();
	}
	
	public boolean isEmpty(){
		return elementMap.isEmpty();
	}

	
	
	

}