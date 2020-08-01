package com.mycompany.a3.helpers;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.GameWorld;

public class CollidedFoodCommand extends Command {
	private GameWorld target;
	
	public CollidedFoodCommand(GameWorld _t) {
		super("Food Station");
		target = _t;
	}
	@Override
	public void actionPerformed(ActionEvent ev) {
		target.collided_food();
	}
}