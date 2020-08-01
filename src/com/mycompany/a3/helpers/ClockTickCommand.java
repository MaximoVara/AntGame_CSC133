package com.mycompany.a3.helpers;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.GameWorld;

public class ClockTickCommand extends Command {
	private GameWorld target;
	
	public ClockTickCommand(GameWorld _t) {
		super("Clock");
		target = _t;
	}
	@Override
	public void actionPerformed(ActionEvent ev) {
		//target.clock_tick();
	}
}
