package com.mycompany.a3.util;

import com.codename1.charts.models.Point;

public abstract class GameObject implements IDrawable, ICollider{
	private int size;
	private int color;
	private Point location;
	private boolean isColliding = false;
	
	public GameObject(){
		size = 25;
		color = 0;
		location = new Point(-1, -1);
	}
	public GameObject(int _s, int _c, float _x, float _y) {
		size = _s;
		color = _c;
		location = new Point(_x, _y);
	}
	
	public int getSize() {
		return size;
	}
	public float getX() {
		return location.getX();
	}
	public float getY() {
		return location.getY();
	}
	public void setX(float _x) {
		location.setX(_x);
	}
	public void setY(float _y) {
		location.setY(_y);
	}
	public int getColor() {
		return color;
	}
	public void setColor(int _c) {
		color = _c;
	}
	public String toString() {
		String data = "Size: " + size + "   Color: " + color + "  location: " + getX() + ' ' + getY();
		return data;
	}
	@Override
	public boolean collidesWith(GameObject otherObject) {
		// TODO Auto-generated method stub
		boolean result = false;
		float thisCenterX = (int) (this.getX());
		float thisCenterY = (int) (this.getY());
		float otherCenterX = otherObject.getX();
		float otherCenterY = otherObject.getY();
		float dx = thisCenterX - otherCenterX;
		float dy = thisCenterY - otherCenterY;
		float distSquared = (dx*dx + dy*dy);
		
		int thisRadius = this.getSize()/2;
		int otherRadius = otherObject.getSize();
		int radiiSqr = (thisRadius*thisRadius 
				+ 2*thisRadius*otherRadius 
				+ otherRadius*otherRadius);
		if(distSquared <= radiiSqr) {
			result = true;
		}
		
		return result;
	}

	public void setIsColliding(boolean _iC) {
		isColliding = _iC;
	}
	public boolean getIsColliding() {
		// TODO Auto-generated method stub
		return isColliding;
	}
	
}

