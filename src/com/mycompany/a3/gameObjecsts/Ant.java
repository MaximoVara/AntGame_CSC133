package com.mycompany.a3.gameObjecsts;

import com.codename1.charts.models.Point;
import com.codename1.ui.Graphics;
import com.mycompany.a3.util.GameObject;
import com.mycompany.a3.util.ISteerable;
import com.mycompany.a3.util.Moveable;

/**
 * @author maximo
 * The player type
 */
public class Ant extends Moveable implements ISteerable{
	private static Ant theAnt;
	@SuppressWarnings("unused")
	private int size = 1;
	private int maximumSpeed = 25;
	private int acceleration = 4;
	private int currentMax = 25;
	@SuppressWarnings("unused")
	private int reducedSpeed = 0;
	
	private double maxFoodLevel = 75;
	private double	foodLevel = 75;
	private double foodHungerRate = 0.03125;
	
	private int healthRate = 5;
	private int healthLevel = 25;
	@SuppressWarnings("unused")
	private int maxHealthLevel = 25;
	
	private int lastFlagReached = 0;
	private int turnAngle = 15;
	private boolean isDead = false; 
	
	private Ant() {
		super();
	}
	private Ant(int _s, int _c, float _x, float _y) {
		super(_s, _c, _x, _y);
	}
	/**
	 * @return singleton. returns the single one
	 */
	public static Ant getAnt() {
		if(theAnt == null) {
			theAnt = new Ant();
		}
		return theAnt;
	}
	public static Ant getAnt(int _s, int _c, float _x, float _y, int maxWidth, int maxHeight) {
		if(theAnt == null) {
			theAnt = new Ant(_s, _c, _x, _y);
		}
		return theAnt;
	}
	/**
	 * for when someone dies
	 */
	public void reset() {
		foodLevel = maxFoodLevel;
		healthLevel = maxHealthLevel;
		lastFlagReached = 0;
		setSpeed(5);
		isDead = false;
	}
	@Override
	public void setHeading(int compassAngle) {
		super.setHeading(compassAngle); 
		// TODO Auto-generated method stub
		return;
	}

	@Override
	public void turnRight() {
		System.out.print("Right\n");
		super.setHeading((super.getHeading() + turnAngle+360)%360);
	}

	@Override
	public void turnLeft() {
		System.out.print("Left\n");
		super.setHeading((super.getHeading() - turnAngle+360)%360);
	}
	
	/**
	 * @return for gameworld to decide what to do when bug dies
	 */
	public boolean isDead() {
		return isDead;
	}
	
	/**
	 * @return
	 */
	public int getFlagReached() {
		return lastFlagReached;
	}
	/**
	 * @return
	 */
	public double getFoodLevel() {
		return foodLevel;
	}
	/**
	 * @return the health level of the ant
	 */
	public int getHealthLevel() {
		return healthLevel;
	}
	
	/**
	 * speeds up ant
	 */
	public void accelerate() {
		System.out.print("Accelerating\n");
		int currentSpeed = getSpeed();
		if(currentSpeed < currentMax) {
			setSpeed(currentSpeed + acceleration);
		}
	}
	
	/**
	 * slows ant
	 */
	public void brake() {
		System.out.print("Braking\n");
		int currentSpeed = getSpeed();
		if(currentSpeed > 0) {
			setSpeed(currentSpeed - 1);
		}
	}
	/**
	 * @return damages ant then decides if it died.
	 */
	public boolean damaged() {
		healthLevel -= healthRate;
		if(healthLevel <=0) {
			isDead = true;
		}
		return isDead;
	}
	/**
	 * checks if speed should be what it is. 
	 */
	public void updateSpeed() {
		if(foodLevel == 0) {
			currentMax = 0;
		} else if(healthLevel == 0) {
			currentMax = 0;
			isDead = true;
		} else {
			currentMax = healthLevel + 1;
		}
		if(getSpeed() > currentMax) {
			setSpeed(currentMax); 
		}
	}
	/**
	 * @param elapsedTime 
	 * @return so that this can do everything needed per tick
	 */
	public boolean update(int elapsedTime) {
		updateSpeed();
		move(elapsedTime);
		foodLevel -= foodHungerRate;
		if(foodLevel <=0) {
			isDead = true;
		}
		return isDead;
	}
	/**
	 * @param sequenceNumber
	 * for when the ant reaches a flag.
	 */
	public void flagReached(int sequenceNumber) {
		if(sequenceNumber == (lastFlagReached + 1)) {
			lastFlagReached = sequenceNumber;
		}
	}
	/**
	 * @param _food
	 * how much food does ant get?
	 */
	public void foodReached(int _food) {
		foodLevel += _food;
		if(foodLevel > maxFoodLevel) {
			foodLevel = maxFoodLevel;
		}
	}
	public String toString() {
		String data = super.toString();
		data = "Ant: " + data;
		data += "  MaxSpeed: " + maximumSpeed + "  FoodConsumptionRate: " + foodHungerRate;
		return data;
	}
	@Override
	public void draw(Graphics g, Point pCmpRelPrnt) {
		g.setColor(this.getColor());
		g.fillArc(
				(int)(this.getX() + pCmpRelPrnt.getX()),
				(int)(this.getY() + pCmpRelPrnt.getY()),
				this.getSize(),
				this.getSize(),
				0,
				360
				);
	}
	@Override
	public void handleCollision(GameObject otherObject) {
		// TODO Auto-generated method stub
		if(otherObject instanceof Flags) {
			this.flagReached(((Flags)otherObject).getSequenceNumber());
			
		} else if(otherObject instanceof Station_Food) {
			this.foodReached(((Station_Food)otherObject).getFood());
		} else if(otherObject instanceof Spider) {
			this.damaged();
		}
	}
}
