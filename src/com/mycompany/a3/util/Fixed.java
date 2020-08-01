package com.mycompany.a3.util;

import com.codename1.charts.models.Point;
import com.codename1.ui.Graphics;

public abstract class Fixed extends GameObject implements ISelectable{
	private boolean isSelected;
	public Fixed() {
		super();
		isSelected = false;
	}
	
	public Fixed(int _s, int _c, float _x, float _y) {
		super(_s, _c, _x, _y);
		isSelected = false;
	}
	
	@Override
	public void setY(float _y) {
		if(super.getY()  == -1) {
			super.setY(_y);
		}
	}
	
	@Override
	public void setX(float _x) {
		if(super.getX() == -1) {
			super.setX(_x);
		}
	}
	
	public void overrideLocation(Point _p, Point _o) {
		super.setX(_p.getX() - _o.getX());
		super.setY(_p.getY() - _o.getY()); 
	}
	public String toString() {
		return super.toString();
	}
	@Override
	public void draw(Graphics g, Point pCmpRelPrnt) {
		// TODO Auto-generated method stub
		g.drawRect((int)(this.getX() + pCmpRelPrnt.getX()), (int)(this.getY() + pCmpRelPrnt.getY()), this.getSize(), this.getSize());
	}
	@Override
	public void setSelected(Boolean yesNo) {
		// TODO Auto-generated method stub
		isSelected = yesNo;
	}
	@Override
	public boolean isSelected() {
		// TODO Auto-generated method stub
		return isSelected;
	}
	
	@Override
	public boolean contains(Point pPterRelPrnt, Point pCmpRelPrnt ) {
		// TODO Auto-generated method stub
		int px = (int) pPterRelPrnt.getX();
		int py = (int) pPterRelPrnt.getY();
		int xLoc = (int) (pCmpRelPrnt.getX() + getX());
		int yLoc = (int) (pCmpRelPrnt.getY() + getY());
		
		if( (px >= (xLoc - getSize()/2)) 
				&& (px <= (xLoc + getSize()/2)) 
				&& (py >= yLoc - getSize()/2)
				&& (py <= yLoc + getSize()/2)
				) {
			return true;
		}
		return false;
	}
}
