package com.mycompany.a3.util;

public interface ICollider {
	public boolean collidesWith(GameObject otherObject);
	public void handleCollision(GameObject otherObject);
}
