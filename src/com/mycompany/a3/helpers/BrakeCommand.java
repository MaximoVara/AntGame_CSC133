package com.mycompany.a3.helpers;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.GameWorld;

public class BrakeCommand extends Command {
	private GameWorld target;
	
	public BrakeCommand(GameWorld _t) {
		super("Brake");
		target = _t;
	}
	@Override
	public void actionPerformed(ActionEvent ev) {
		target.brake();
	}
}

