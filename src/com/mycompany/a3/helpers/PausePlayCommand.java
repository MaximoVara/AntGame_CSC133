package com.mycompany.a3.helpers;

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.Game;

public class PausePlayCommand extends Command {
	private Game target;
		
	public PausePlayCommand(Game _t) {
		super("Pause");
		target = _t;
	}
	
	@Override
	public void actionPerformed(ActionEvent ev) {
		//Dialog.show(myInfo, "This is a game about a bug who just wants to eat some food but spiders are jerks.\n", "ok", null);
		target.togglePause();
	}
	
}