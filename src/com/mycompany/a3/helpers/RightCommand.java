package com.mycompany.a3.helpers;


import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.GameWorld;

public class RightCommand extends Command {
	private GameWorld target;
	
	public RightCommand(GameWorld _t) {
		super("Right");
		target = _t;
	}
	@Override
	public void actionPerformed(ActionEvent ev) {
		target.right();
	}
}
