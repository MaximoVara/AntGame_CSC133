package com.mycompany.a3.gameObjecsts;

import java.util.Random;

import com.codename1.charts.models.Point;
import com.codename1.ui.Graphics;
import com.mycompany.a3.util.GameObject;
import com.mycompany.a3.util.Moveable;

public class Spider extends Moveable{
	private Random randomInt = new Random();
	private int MAXWIDTH, MAXHEIGHT;
	public Spider() {
		super();
		MAXWIDTH = MAXHEIGHT = 1000;
	}
	public Spider(int _s, int _c, float _x, float _y) {
		super(_s, _c, _x, _y);
		MAXWIDTH = MAXHEIGHT = 1000;
	}
	public Spider(int _s, int _c, float _x, float _y, int _hd, int _sp) {
		super(_s, _c, _x, _y, _hd, _sp);
		MAXWIDTH = MAXHEIGHT = 1000;
	}
	public Spider(int _s, int _c, float _x, float _y, int _hd, int _sp, int maxW, int maxH) {
		super(_s, _c, _x, _y, _hd, _sp);
		MAXWIDTH = maxW;
		MAXHEIGHT = maxH;
	}
	@Override
	public void move(int elapseTime) {
		//Randomly changes the main heading in either direction.
		//After that it does the math for change in position then lets super do the rest.
		int headingChange = randomInt.nextInt(181) - 90;
		int tempHeading = 90 - (getHeading() + headingChange);
		double deltaX = Math.cos(Math.toRadians(tempHeading)) * getSpeed();
		double deltaY = Math.sin(Math.toRadians(tempHeading)) * getSpeed();
		super.move(deltaX/50 * elapseTime, deltaY/50 * elapseTime);
		checkOutOfBounds();
	}
	
	public void checkOutOfBounds() {
		if(this.getX() <= 0 ) {
			this.setX(this.getX() + 25);
			this.setHeading(this.getHeading() + 120);
		} else if (this.getX() >= MAXWIDTH){
			this.setX(this.getX() - 25);
			this.setHeading(this.getHeading() + 120);
		}
		if(this.getY() <= 0 ) {
			this.setY(this.getY() + 25);
			this.setHeading(this.getHeading() + 120);
		} else if (this.getY() >= MAXHEIGHT){
			this.setY(this.getY() - 25);
			this.setHeading(this.getHeading() + 120);
		}	
	}
	
	public String toString(){
		String data = super.toString();
		data = "Spider: " + data;
		return data;
	}
	
	@Override 
	public void draw(Graphics g, Point _p){
		g.setColor(this.getColor());
		int sides = 3;
		int xCord[] = {
						(int)(this.getX() + _p.getX()),
						(int)(this.getX() - this.getSize() + _p.getX()), 
						(int)(this.getX() + this.getSize() + _p.getX())
					};
		int yCord[] = {
						(int)(this.getY() + this.getSize() + _p.getY()), 
						(int)(this.getY() - this.getSize()/2 + _p.getY()), 
						(int)(this.getY() - this.getSize()/2 + _p.getY())
					};
		g.drawPolygon(xCord, yCord, sides);
		
	}
	@Override
	public void handleCollision(GameObject otherObject) {
		// Don't care about any collisions other than ant.
		// Since Ant is the only one that needs to handle the collision on their end.
	}
}
