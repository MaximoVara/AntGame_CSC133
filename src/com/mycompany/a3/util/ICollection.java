package com.mycompany.a3.util;

public interface ICollection {
	public void add(GameObject newObject);
	
	public IIterator getIterator();
	
	public interface IIterator {
		public boolean hasNext();
		public GameObject getNext();
	}
}

