package com.mycompany.a3.helpers;


import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.GameWorld;

public class CollidedFlagCommand extends Command {
	private GameWorld target;
	private TextField textField;
	private String enteredText;
	
	public CollidedFlagCommand(GameWorld _t) {
		super("CollideFlag");
		target = _t;
	}
	@Override
	public void actionPerformed(ActionEvent ev) {
		textField = new TextField();
		Dialog.show("Flag Collision", textField, new Command("enter"));
		enteredText = textField.getText();
		if(Character.isDigit(enteredText.charAt(0))){
			int flagNum = enteredText.charAt(0) - '0';
			target.collided_flag(flagNum);
		}
	}
}
