package com.mycompany.a3.util;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Button;

public class myGuiButton extends Button {
	public myGuiButton() {
		super("GuiButton");
		this.getAllStyles().setBgTransparency(0xFF);
		this.getAllStyles().setBgColor(ColorUtil.BLACK);
		
	}
}
