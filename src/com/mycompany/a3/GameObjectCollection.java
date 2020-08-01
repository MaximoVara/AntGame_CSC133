package com.mycompany.a3;

import java.util.Vector;

import com.mycompany.a3.util.GameObject;
import com.mycompany.a3.util.ICollection;

/**
 * @author maximo
 *
 */
public class GameObjectCollection implements ICollection{
	private Vector<GameObject> theCollection;
	
	/**
	 * Creates the game object collection.
	 */
	public GameObjectCollection() {
		theCollection = new Vector<GameObject>();
	}
	@Override
	public void add(GameObject newObject) {
		theCollection.addElement(newObject);
	}

	@Override
	public IIterator getIterator() {
		// TODO Auto-generated method stub
		return new GameItterator();
	}
	
	private class GameItterator implements IIterator{
		private int currElementIndex;
		
		public GameItterator() {
			currElementIndex = -1;
		}
		@Override
		public boolean hasNext() {
			if(theCollection.size() <= 0) return false;
			if(currElementIndex == theCollection.size() -1) return false;
			return true;
		}

		@Override
		public GameObject getNext() {
			currElementIndex++;
			return (theCollection.elementAt(currElementIndex));
		}
		
	}
}

