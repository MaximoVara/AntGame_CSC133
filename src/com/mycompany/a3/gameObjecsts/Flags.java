package com.mycompany.a3.gameObjecsts;

import com.codename1.charts.models.Point;
import com.codename1.ui.Graphics;
import com.mycompany.a3.util.Fixed;
import com.mycompany.a3.util.GameObject;

public class Flags extends Fixed{
	private int sequenceNumber;
	private static int count = 0;
	
	public Flags() {
		super();
		count++;
		sequenceNumber = count;
	}
	public Flags(int _s, int _c, float _x, float _y) {
		super(_s, _c, _x, _y);
		count++;
		sequenceNumber = count;
	}
	
	public void setColor(int _c) {
		super.setColor(_c);
	}
	public int getSequenceNumber() {
		return sequenceNumber;
	}
	public String toString() {
		String data = super.toString();
		data = "Flag: " + data;
		data += "  SeqNum: " + sequenceNumber;
		return data;
	}
	
	@Override 
	public void draw(Graphics g, Point _p){
		g.setColor(this.getColor());
		if(isSelected()) {

			int[] xpoints = {
					(int)(this.getX() + _p.getX()),
					(int)(this.getX() - this.getSize()/2 + _p.getX()),
					(int)(this.getX() + this.getSize()/2 + _p.getX())
			};
			int[] ypoints = {(int)(this.getY() + this.getSize()/2 + _p.getY()),
					(int)(this.getY() - this.getSize()/2 + _p.getY()),
					(int)(this.getY() - this.getSize()/2 + _p.getY())
			};
			g.drawPolygon(xpoints, ypoints, 3);
		} else {
			g.fillTriangle(
						(int)(this.getX() + _p.getX()),
						(int)(this.getY() + this.getSize()/2 + _p.getY()),
						(int)(this.getX() - this.getSize()/2 + _p.getX()),
						(int)(this.getY() - this.getSize()/2 + _p.getY()),
						(int)(this.getX() + this.getSize()/2 + _p.getX()),
						(int)(this.getY() - this.getSize()/2 + _p.getY())
					);
		}
		g.setColor(0x000000);
		g.drawString(("" + this.sequenceNumber), (int)(this.getX() + _p.getX() - this.getSize()/4), (int)(this.getY() + _p.getY()- this.getSize()/2) );
	}
	
	@Override
	public void handleCollision(GameObject otherObject) {
		// Don't care about any collisions other than ant.
		// Since Ant is the only one that needs to handle the collision on their end.
		
		
	}
}
