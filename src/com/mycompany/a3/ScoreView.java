package com.mycompany.a3;

import java.util.Observable;
import java.util.Observer;

import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.FlowLayout;

public class ScoreView extends Container implements Observer {
	private padLabel lives = new padLabel();
	private padLabel livesV = new padLabel();
	private padLabel time = new padLabel();
	private padLabel timeV = new padLabel();
	private padLabel lastFlag = new padLabel();
	private padLabel lastFlagV = new padLabel();
	private padLabel foodlvl = new padLabel();
	private padLabel foodlvlV = new padLabel();
	private padLabel damage = new padLabel();
	private padLabel damageV = new padLabel();
	private padLabel sound = new padLabel();
	private padLabel soundV = new padLabel();
	
	private GameWorld target;
	
	/**
	 * 
	 */
	public ScoreView(){
		super();
		this.setLayout(new FlowLayout(Component.CENTER));
		this.add(lives);
		this.add(livesV);
		this.add(time);
		this.add(timeV);
		this.add(lastFlag);
		this.add(lastFlagV);
		this.add(foodlvl);
		this.add(foodlvlV);
		this.add(damage);
		this.add(damageV);
		this.add(sound);
		this.add(soundV);
		
		lives.setText("Lives: ");
		livesV.setText("000.0");
		time.setText("Time: ");
		timeV.setText("000.0");
		lastFlag.setText("Last Flag: ");
		lastFlagV.setText("000.0");
		foodlvl.setText("Food Level: ");
		foodlvlV.setText("000.0");
		damage.setText("Health: ");
		damageV.setText("000.0");
		sound.setText("Sound: ");
		soundV.setText("000.0");
		
	}
	/**
	 * @param gw important that we can communicate with gameworld
	 */
	public void setTarget(GameWorld gw) {
		target = gw;
	}
	
	@Override
	public void update(Observable observable, Object data) {
		// TODO Auto-generated method stub
		String _l = "" + target.getLives();
		int _cInt = target.getClock();
		String _c;
		if(_cInt < 10) {
			_c = "0" + target.getClock();
		} else {
			_c = "" + target.getClock();
		}
			
		String _lfr = "" + target.getLastFlagReached();
		String _flvl = "" + target.getFoodLevel();
		String _dmg = "" + target.getHealthLevel();
		String _s;
		
		if(target.getSound()) {
			_s = "On";
		} else {
			_s = "Off";
		}
		
		livesV.setText(_l);
		timeV.setText(_c);
		lastFlagV.setText(_lfr);
		foodlvlV.setText(_flvl);
		damageV.setText(_dmg);
		soundV.setText(_s);
	}

	private class padLabel extends Label{
		public padLabel(){
			super();
			this.getAllStyles().setPadding(Component.RIGHT, 2);
			this.getAllStyles().setPadding(Component.LEFT, 2);
		}
	}
}
