package com.mycompany.a3.helpers;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.GameWorld;

public class CollidedSpiderCommand extends Command {
	private GameWorld target;
	
	public CollidedSpiderCommand(GameWorld _t) {
		super("Spider");
		target = _t;
	}
	@Override
	public void actionPerformed(ActionEvent ev) {
		target.collided_spider();
	}
}
