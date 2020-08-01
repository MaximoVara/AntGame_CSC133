package com.mycompany.a3.util;

import com.codename1.charts.models.Point;
import com.codename1.ui.Graphics;

public abstract class Moveable extends GameObject{
	private int heading;
	private int speed;
	
	public Moveable() {
		super();
		setHeading(90);
		speed = 5;
	}
	public Moveable(int _s, int _c, float _x, float _y) {
		super(_s, _c, _x, _y);
		setHeading(90);
		speed = 1;
	}
	public Moveable(int _s, int _c, float _x, float _y, int _h, int _sp) {
		super(_s, _c, _x, _y);
		setHeading(_h);
		speed = _sp;
	}
	public int getHeading() {
		return heading;
	}
	protected void setHeading(int _h) {
		this.heading = _h;
	}
	public int getSpeed() {
		return speed;
	}
	public void setSpeed(int _sp) {
		speed = _sp;
	}
	
	public void move(int elapsedTime) {
		int tempHeading = 90 - getHeading();
		double deltaX = Math.cos(Math.toRadians(tempHeading)) * getSpeed();
		double deltaY = Math.sin(Math.toRadians(tempHeading)) * getSpeed();
		move(deltaX/50 * elapsedTime, deltaY/50 * elapsedTime);
	}
	public void move(double _deltaX, double _deltaY) {
		double newX = super.getX() + _deltaX;
		double newY = super.getY() + _deltaY;
		super.setX((float) newX);
		super.setY((float) newY);
	}
	public String toString() {
		String data = super.toString();
		data += "  Heading: " + heading + "  Speed: " + speed;
		return data;
	}
	@Override
	public void draw(Graphics g, Point pCmpRelPrnt) {
		// TODO Auto-generated method stub
		g.drawRect((int)(this.getX() + pCmpRelPrnt.getX()), (int)(this.getY() + pCmpRelPrnt.getY()), this.getSize(), this.getSize());
	}
}
