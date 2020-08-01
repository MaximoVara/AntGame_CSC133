package com.mycompany.a3.helpers;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.GameWorld;

public class LeftCommand extends Command {
	private GameWorld target;
	
	public LeftCommand(GameWorld _t) {
		super("Left");
		target = _t;
	}
	@Override
	public void actionPerformed(ActionEvent ev) {
		target.left();
	}
}