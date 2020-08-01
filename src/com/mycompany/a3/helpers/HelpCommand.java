package com.mycompany.a3.helpers;

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.GameWorld;

public class HelpCommand extends Command {
	private GameWorld target;
	private String title = new String("Help");
	private String info = new String(
			"a - accelerate\n"
			+ "b - brake\n"
			+ "l - left"
			+ "r - right"
			+ "t - clock tick "
			+ "g - spider"
			+ "f - food"
			+ "only the button for flags"
			);
	public HelpCommand(GameWorld _t) {
		super("Help");
		target = _t;
	}
	
	@Override
	public void actionPerformed(ActionEvent ev) {
		Dialog.show(title, info, "ok", null);
		
	}
}