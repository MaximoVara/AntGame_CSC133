package com.mycompany.a3;

import java.util.Observable;
import java.util.Observer;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Container;
import com.codename1.ui.Graphics;
import com.codename1.ui.plaf.Border;
import com.mycompany.a3.util.GameObject;
import com.mycompany.a3.util.ICollection.IIterator;

public class MapView extends Container implements Observer{
	Point myOrigin;
	GameWorld target;
	Game game;

	private int iPx = 0;
	private int iPy = 0;
	
	public MapView(GameWorld _t) {
		myOrigin = new Point (this.getX(), this.getY());
		target=_t;
		
		this.getAllStyles().setBorder(Border.createLineBorder(5, ColorUtil.rgb(255,0,0)));
	}
	
	@Override
	public void update(Observable observable, Object data) {
		// TODO Auto-generated method stub
		repaint();
		target.map();
	}
	
	@Override
	public void paint(Graphics c) {
		super.paint(c);
		c.setColor(0x000000);
		
		myOrigin.setX(this.getX());
		myOrigin.setY(this.getY());
		target.drawAll(c, myOrigin);
		
	}
	public void drawAll(Graphics c, Point parentOrigin) {
		// TODO Auto-generated method stub
		IIterator gameObjects = target.getIterator(); //ObjCollection.getIterator();
		GameObject curObject;
		
		while(gameObjects.hasNext()) {
			curObject = gameObjects.getNext();
			curObject.draw(c, parentOrigin);
		}	
		
	}
	@Override
	public void pointerPressed(int _x, int _y) {
		//This is where all the clicks on map view begin. 
		//This gets the location of the click and the origin of map view.
		//It then calls all the function to handle the selections within gameworld. 
		//If the command for repositioning is active, then it will pass that arguement to gameworld which handles the relocation.
		iPx = _x - getParent().getAbsoluteX();
		iPy = _y - getParent().getAbsoluteY();
		Point pPtrRelPrnt = new Point(iPx, iPy);
		Point pCmpRelPrnt = new Point(getX(), getY());
		target.pointerPressed(pPtrRelPrnt, pCmpRelPrnt, game.reposistionActive());
		repaint();
	}
	public void setGame(Game _g) {
		game = _g;
	}

}
