package com.mycompany.a3.helpers;

import com.codename1.ui.CheckBox;
import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.GameWorld;

public class SoundCommand extends Command {
	private GameWorld target;
	
	public SoundCommand(GameWorld _t) {
		super("Help");
		target = _t;
	}
	@Override
	public void actionPerformed(ActionEvent ev) {
		target.setSound(!(target.getSound()));
	}
}
