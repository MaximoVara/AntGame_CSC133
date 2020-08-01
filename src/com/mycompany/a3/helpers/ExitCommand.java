package com.mycompany.a3.helpers;

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.GameWorld;

public class ExitCommand extends Command {
	private GameWorld target;
	private TextField textField;
	private String enteredText;
	
	public ExitCommand(GameWorld _t) {
		super("Exit");
		target = _t;
	}
	@Override
	public void actionPerformed(ActionEvent ev) {
		textField = new TextField();
		Dialog.show("Are you sure you want to exit? ( y | n )", textField, new Command("enter"));
		enteredText = textField.getText();
		if((enteredText.toLowerCase()).charAt(0)== 'y'){
			target.exit();
		} 
	}
}
