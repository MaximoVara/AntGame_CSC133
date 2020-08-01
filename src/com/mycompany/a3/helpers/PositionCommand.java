package com.mycompany.a3.helpers;

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.GameWorld;

public class PositionCommand extends Command{
	private GameWorld target;
	private boolean active;
	
	public PositionCommand(GameWorld _t) {
		super("Position");
		target = _t;
		active = false;
	}
	
	@Override
	public void actionPerformed(ActionEvent ev) {
		if(!active) {
			active = true;
		}
	}
	
	public boolean isActive() {
		boolean _a = false;
		if(active) {
			_a = active;
			active = false;
		}
		return _a;
	}
	public void deactivate() {
		active = false;
	}

}
