package com.mycompany.a3;

import java.util.Observable;
import java.util.Random;

import com.codename1.charts.models.Point;
import com.codename1.ui.Graphics;
import com.mycompany.a3.gameObjecsts.Ant;
import com.mycompany.a3.gameObjecsts.Flags;
import com.mycompany.a3.gameObjecsts.Spider;
import com.mycompany.a3.gameObjecsts.Station_Food;
import com.mycompany.a3.util.Fixed;
import com.mycompany.a3.util.GameObject;
import com.mycompany.a3.util.ICollection.IIterator;
import com.mycompany.a3.util.ICollider;

/**
 * @author maximo
 *
 */
public class GameWorld extends Observable {
	@SuppressWarnings("unused")
	private static double MAXWIDTH = 1024;
	@SuppressWarnings("unused")
	private static double MAXHEIGHT = 768;
	@SuppressWarnings("unused")
	private static final double YORIGIN = 0;
	@SuppressWarnings("unused")
	private static final double XORIGIN = 0;
	private static int CLOCK = 0, LIVES = 3;
	private static int trueTime = 0;
	@SuppressWarnings("unused")
	private static int lastFlagReached = 0, antsFoodLevel = 0;
	private static boolean sound = true;
	GameObjectCollection ObjCollection = new GameObjectCollection();
	private Random randomInt = new Random();
	private Sound backgroundMusic;
	private boolean isPaused;
	
	private String stationSoundFile = "eating.wav"; 
	private String spiderSoundFile = "sqeak.wav";
	private String flagSoundFile = "celebrate.wav";
	private Sound stationSound, spiderSound, flagSound;
	/**
	 * 
	 */
	public void init() {
		//This function loads the object collection with all relevant objects. 
		//Since Ant is added first and it's the only ant, this will save time later when ant needs to change.
		int randomX = randomInt.nextInt((int) (MAXWIDTH-50)) + 25;
		int randomY = randomInt.nextInt((int) (MAXHEIGHT-50)) + 25;
		int randomSize;
		int randomHeading;
		ObjCollection.add(Ant.getAnt(25, 0xaa0000, randomX, randomY, (int)MAXWIDTH, (int)MAXHEIGHT));
		int i;
		for( i= 0; i < 9; i++) {
			ObjCollection.add(new Flags(45, 0xbbbbff, randomX, randomY));
			randomX = randomInt.nextInt((int) (MAXWIDTH-50)) + 25;
			randomY = randomInt.nextInt((int) (MAXHEIGHT-50)) + 25;
		}
		for( i = 0; i < 9; i++ ) {
			randomSize = randomInt.nextInt(36) + 15;
			randomX = randomInt.nextInt((int) (MAXWIDTH-50)) + 25;
			randomY = randomInt.nextInt((int) (MAXHEIGHT-50)) + 25;
			randomHeading = randomInt.nextInt(361) + 90;
			ObjCollection.add(new Spider(randomSize, 0x000000, randomX, randomY, randomHeading, 15, (int)MAXWIDTH, (int)MAXHEIGHT));
		}
		for( i= 0; i< 5; i++) {
			randomSize = randomInt.nextInt(36) + 25;
			randomX = randomInt.nextInt((int) (MAXWIDTH-50)) + 25;
			randomY = randomInt.nextInt((int) (MAXHEIGHT-50)) + 25;
			ObjCollection.add(new Station_Food(randomSize, 0x55ff55, randomX, randomY));
		}
		
		backgroundMusic = new Sound("BGMusic.wav");
		stationSound = new Sound(stationSoundFile); 
		spiderSound = new Sound(spiderSoundFile);
		flagSound = new Sound(flagSoundFile);
		backgroundMusic.play();
		
		setChanged();
		notifyObservers();
	}
	
	private void setLocations() {
		IIterator tmp = this.getIterator();
		GameObject curObject;
		float i = 1;
		float j = 1;
		while(tmp.hasNext()) {
			curObject = tmp.getNext();
			curObject.setX((float)(MAXWIDTH*i/25.0));
			curObject.setY((float)(MAXHEIGHT*j/25.0));
			i++;
			j++;
		}	
	}
	/**
	 * Accelerates the ant by finding it in iterator
	 */
	public void accelerate() {
		//This function increases the speed of the Ant. 
		IIterator searchIt = this.getIterator();
		GameObject player = searchIt.getNext();
		
		((Ant)player).accelerate();
		setChanged();
		notifyObservers();
	}
	
	/**
	 * slows down the ant
	 */
	public void brake() {
		//This function decreases the speed of the Ant.
		IIterator searchIt = this.getIterator();
		GameObject player = searchIt.getNext();
		
		((Ant)player).brake();
		setChanged();
		notifyObservers();
	}
	/**
	 * Turn ant counter clockwise
	 */
	public void left() {
		//This function turns the Ant Left by finding it in the first position of the iterator. 
		IIterator searchIt = this.getIterator();
		GameObject player = searchIt.getNext();
		
		((Ant)player).turnLeft();
		setChanged();
		notifyObservers();
		//players.get(0).turnLeft();
	}
	/**
	 * Turn clockwise
	 */
	public void right() {
		//This function turns the Ant right by finding it in the first position of the iterator. 
		IIterator searchIt = this.getIterator();
		GameObject player = searchIt.getNext();
		
		((Ant)player).turnRight();
		setChanged();
		notifyObservers();
	}
	/**
	 * Collided with food
	 */
	public void collided_food() {
		//This function handles collision with food stations.
		IIterator searchIt = this.getIterator();
		GameObject player = searchIt.getNext();
		
		((Ant)player).foodReached(5);
		setChanged();
		notifyObservers();
	}
	/**
	 * @param _number int of which flag has been collided with
	 */
	public void collided_flag(int _number) {
		//This function handles collisions with flags.
		IIterator searchIt = this.getIterator();
		GameObject player = searchIt.getNext();
		
		((Ant)player).flagReached(_number);
		setChanged();
		notifyObservers();
	}
	/**
	 * Randome ant collision
	 */
	public void collided_spider() {
		//This function handles collisions with Spiders.
		IIterator searchIt = this.getIterator();
		GameObject player = searchIt.getNext();
		
		boolean _isDead = ((Ant)player).damaged();
		if(_isDead) {
			LIVES--;
			((Ant)player).reset();
			//gameOver();
		}
		setChanged();
		notifyObservers();
	}
	/**
	 * Display the information. Depricated basically since scoreview does this now. 
	 * Left over from A1
	 */
	public void display() {
		//This function displays information on the game. 
		IIterator searchIt = this.getIterator();
		GameObject player = searchIt.getNext();
		System.out.println("Lives: " + LIVES);
		System.out.println("Time: " + CLOCK);
		System.out.println("Last Flag Reached: " + ((Ant)player).getFlagReached());
		System.out.println("Current food Level: " + ((Ant)player).getFoodLevel());
		System.out.println("Health Level: " + ((Ant)player).getHealthLevel());
		setChanged();
		notifyObservers();
	}
	
	/**
	 * Prints out every string.
	 */
	public void map() {
		//This function goes through the game object collection and displays info on all objects. 
		IIterator gameObjects = this.getIterator();
		GameObject curObject;
		while(gameObjects.hasNext()) {
			curObject = gameObjects.getNext();
			System.out.println(curObject.toString());
		}		
		//setChanged();
		//notifyObservers();
	}
	/**
	 * Exits the whole thing
	 */
	public void exit() {
		System.exit(0);
	}
	/**
	 * @return the time in game
	 */
	public int getClock() {
		return CLOCK;
	}
	
	/**
	 * @return returns the number of lives left in game.
	 */
	public int getLives() {
		return LIVES;
	}
	
	/**
	 * @return returns last flag reached so far
	 */
	public int getLastFlagReached() {
		IIterator searchIt = this.getIterator();
		GameObject player = searchIt.getNext();
			int _lfr = ((Ant)player).getFlagReached();
			return _lfr;
	}
	/**
	 * @return if sound is on
	 */
	public boolean getSound() {
		return sound;
	}
	/**
	 * @param _s what should sound be? on or off? 1 for on.
	 * @return
	 */
	
	public boolean setSound(boolean _s) {
		sound = _s;
		if(sound) {
			backgroundMusic.play();
		} else {
			backgroundMusic.pause();
		}
		setChanged();
		notifyObservers();
		
		return sound;
	}
	/**
	 * @return the food level of the ant
	 */
	public double getFoodLevel() {
		IIterator searchIt = this.getIterator();
		GameObject player = searchIt.getNext();
		if(player != null ) {
			double _flvl = ((Ant)player).getFoodLevel();
			return _flvl;
		} else {
			return 0;
		}
	}
	/**
	 * @return the current hunger level of the ant
	 */
	public int getHealthLevel() {
		IIterator searchIt = this.getIterator();
		GameObject player = searchIt.getNext();
		if(player != null) {
			int _hlvl = ((Ant)player).getHealthLevel();
			return _hlvl;
		} else {
			return 0;
		}
	}
	/**
	 * @param _h
	 */
	public void setHeight(int _h) {
		MAXHEIGHT = _h;
	}
	/**
	 * @param _w
	 */
	public void setWidth(int _w) {
		MAXWIDTH = _w;
	}
	public IIterator getIterator() {
		return ObjCollection.getIterator();
	}
	
/////////////////////////////////////////////////////////////////////////////////////////
////     This is for assignment 3 past this point.									/////
/////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * clock tick once forward.
	 */
	public void clock_tick(int elapsedTime) {
		//This function ticks the clock and everything should update that needs to update. 
		trueTime += elapsedTime;
		if(trueTime >= 1000) {
			CLOCK++;
			trueTime = trueTime % 1000;
		}
		IIterator searchIt = this.getIterator();
		GameObject curObject;
		while(searchIt.hasNext()) {
			curObject = searchIt.getNext();
			if(curObject instanceof Ant) {
				((Ant)curObject).update(elapsedTime);
				boolean _isDead = ((Ant)curObject).isDead();
				if(_isDead) {
					LIVES--;
					((Ant)curObject).reset();
					//gameOver();
					return;
				}
			} else if(curObject instanceof Spider) {
				((Spider)curObject).move(elapsedTime);
			}
		}
		checkCollisions();
		checkGameOver();
		setChanged();
		notifyObservers();
	}
	private void checkGameOver() {
	// TODO Auto-generated method stub
		if(LIVES <= 0) {
			System.out.println("\n\nYou ran out of lives.\n  --  Game Over  --  \n\n");
			exit();
		} else if(lastFlagReached >= 9) {
			System.out.println("\n\nYou did it!\nYou beat the game!\n  --  Congradulations  --\n");
			exit();
		}
	}

	public void checkCollisions() {
		boolean isColliding = false;
		IIterator gameObjects = this.getIterator();
		while(gameObjects.hasNext()) {
			ICollider curObject = (ICollider)(gameObjects.getNext());
			IIterator toCompare = this.getIterator();
			isColliding = false;
			while(toCompare.hasNext()) {
				ICollider otherObject = (ICollider)(toCompare.getNext());
				if(otherObject != curObject) {
					if(curObject.collidesWith(((GameObject)(otherObject)))) {
						isColliding = true;
						//System.out.println("Collision!\n");
						if( !(((GameObject)curObject).getIsColliding()) ) {
							curObject.handleCollision(((GameObject)(otherObject)));
							if((curObject instanceof Ant) && sound) {
								if(otherObject instanceof Station_Food) {
									stationSound.play();
								} else if (otherObject instanceof Spider) {
									spiderSound.play();
								} else if (otherObject instanceof Flags)
									flagSound.play();
									lastFlagReached = ((Ant)curObject).getFlagReached();
							}
						}
					}
				}
			}
			((GameObject)curObject).setIsColliding(isColliding);
		}
	}
	public void drawAll(Graphics c, Point parentOrigin) {
		// Draws every object relative to the parent's origin.
		IIterator gameObjects = this.getIterator();
		GameObject curObject;
		
		while(gameObjects.hasNext()) {
			curObject = gameObjects.getNext();
			curObject.draw(c, parentOrigin);
		}	
		
	}

	public void pointerPressed(Point pPtrRelPrnt, Point pCmpRelPrnt, boolean changePosition) {
		// This handels the click event.
		// This is called by Mapview when someone clicks on mapview. 
		// This uses the relevant information to determine if it should move any currently selected objects.
		// This also checks every object to see it they are being selected by the pointer. 
		
		//		First while loop handles relocation
		//		Second while loop handles selection.
		
		IIterator myIter = this.getIterator();
		IIterator toChangeIter = this.getIterator();
		GameObject curObject;
		
		while(toChangeIter.hasNext()) {
			curObject = toChangeIter.getNext();
			if(curObject instanceof Fixed) {
				if(changePosition) {
					if(((Fixed)curObject).isSelected()) {
						((Fixed)curObject).overrideLocation(pPtrRelPrnt, pCmpRelPrnt);
					}
				}
			}
		}
		while(myIter.hasNext()) {
			curObject = myIter.getNext();
			if(curObject instanceof Fixed) {
				if(isPaused) {
					if(((Fixed)curObject).contains(pPtrRelPrnt, pCmpRelPrnt)) {
						((Fixed)curObject).setSelected(true);
					} else {
						((Fixed)curObject).setSelected(false);
					}
				} else {
					((Fixed)curObject).setSelected(false);
				}
			}
		}
		
	}
	
	public void setPaused(boolean toSet) {
		isPaused = toSet;
		if(toSet || !sound) {
			backgroundMusic.play();
		} else {
			backgroundMusic.pause();
		}
	}
	public boolean getPaused() {
		return isPaused;
	}

	public void unSelectAll() {
		// Added so that everything could be unselected quickly with just one function if needed.
		IIterator myIter = this.getIterator();
		GameObject curObject;
		while(myIter.hasNext()) {
			curObject = myIter.getNext();
			if(curObject instanceof Fixed) {
				((Fixed)curObject).setSelected(false);
			}
		}
	}

}
