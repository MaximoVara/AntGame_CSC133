package com.mycompany.a3.gameObjecsts;

import com.codename1.charts.models.Point;
import com.codename1.ui.Graphics;
import com.mycompany.a3.util.Fixed;
import com.mycompany.a3.util.GameObject;

public class Station_Food extends Fixed {
	private int capacityRate = 25;
	private int capacity = 0;
	
	private String collisionSoundFile = "eating.wav";
	
	public Station_Food() {
		super();
		capacity = getSize() * capacityRate/5;
	}
	public Station_Food(int _s, int _c, float _x, float _y) {
		super(_s, _c, _x, _y);
		capacity = getSize() * capacityRate/5;
	}
	
	public int getCapacity() {
		return capacity;
	}
	public int getFood() {
		if(capacity <= 0) {
			return 0;
		} else {
			capacity -= capacityRate;
			return capacityRate;
		}
	}
	public String toString() {
		String data = super.toString();
		data = "Food Station: ";
		data += "  Capacity: " + capacity;
		return data;
	}
	
	@Override
	public void draw(Graphics g, Point _p) {
		g.setColor(this.getColor());
		int _s = this.getSize();
		if(isSelected()) {
			g.drawRect(
					(int)(this.getX() + _p.getX() - _s/2.0),
					(int)(this.getY() + _p.getY() - _s/2.0), 
					_s,
					_s
			);
		} else {
			g.fillRect(
					(int)(this.getX() + _p.getX() - _s/2.0),
					(int)(this.getY() + _p.getY() - _s/2.0), 
					_s,
					_s
			);
		}
		g.setColor(0x000000);
		g.drawString(("" + this.capacity), (int)(this.getX() + _p.getX() - 25), (int)(this.getY() + _p.getY() - 25) );
	}
	@Override
	public void handleCollision(GameObject otherObject) {
		// Don't care about any collisions other than ant.
		// Since Ant is the only one that needs to handle the collision on their end.
	}

}
