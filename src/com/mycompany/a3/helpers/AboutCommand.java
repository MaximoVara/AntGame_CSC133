package com.mycompany.a3.helpers;

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.GameWorld;

public class AboutCommand extends Command {
	private GameWorld target;
	private String myInfo = new String("Maixmo Vara, CSC133, Version 0.2");
	public AboutCommand(GameWorld _t) {
		super("About");
		target = _t;
	}
	@Override
	public void actionPerformed(ActionEvent ev) {
		Dialog.show(myInfo, "This is a game about a bug who just wants to eat some food but spiders are jerks.\n", "ok", null);
	}
}