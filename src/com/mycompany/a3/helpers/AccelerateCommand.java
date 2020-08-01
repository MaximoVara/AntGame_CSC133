package com.mycompany.a3.helpers;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.GameWorld;

public class AccelerateCommand extends Command {
	private GameWorld target;
	
	public AccelerateCommand(GameWorld _t) {
		super("Accelerate");
		target = _t;
	}
	@Override
	public void actionPerformed(ActionEvent ev) {
		if(isEnabled()) {
			target.accelerate();
		}
		//Menu button doesn't updte correctly so I had to only allow the action performed to happen IF the command is enabled. 
	}
}
