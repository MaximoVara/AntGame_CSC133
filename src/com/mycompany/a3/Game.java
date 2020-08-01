package com.mycompany.a3;

import com.codename1.ui.CheckBox;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.UITimer;
import com.mycompany.a3.GameWorld;
import com.mycompany.a3.MapView;
import com.mycompany.a3.ScoreView;
import com.mycompany.a3.helpers.AboutCommand;
import com.mycompany.a3.helpers.AccelerateCommand;
import com.mycompany.a3.helpers.BrakeCommand;
import com.mycompany.a3.helpers.ClockTickCommand;
import com.mycompany.a3.helpers.CollidedFlagCommand;
import com.mycompany.a3.helpers.CollidedFoodCommand;
import com.mycompany.a3.helpers.CollidedSpiderCommand;
import com.mycompany.a3.helpers.ExitCommand;
import com.mycompany.a3.helpers.HelpCommand;
import com.mycompany.a3.helpers.LeftCommand;
import com.mycompany.a3.helpers.PausePlayCommand;
import com.mycompany.a3.helpers.PositionCommand;
import com.mycompany.a3.helpers.RightCommand;
import com.mycompany.a3.helpers.SoundCommand;
import com.mycompany.a3.util.myGuiButton;

/**
 * @author maximo
 *
 */
public class Game extends Form implements Runnable {
	private GameWorld gw;
	private MapView mv;
	private ScoreView sv;
	boolean exit = false;
	private int eventRate = 20;
	private UITimer timer;
	private boolean isPaused;
	private myGuiButton accelerateButton;
	private myGuiButton brakeButton;
	private myGuiButton leftButton;
	private myGuiButton rightButton;
	private myGuiButton pausePlayButton;
	private myGuiButton positionButton;
	
	AccelerateCommand accelerateCommand;
	BrakeCommand brakeCommand;
	LeftCommand leftCommand;
	RightCommand rightCommand;
	AboutCommand aboutCommand;
	HelpCommand helpCommand;
	SoundCommand soundCommand;
	ExitCommand exitCommand;
	PausePlayCommand pauseCommand;
	PositionCommand positionCommand;
	/**
	 * The game class. Controls everything
	 */
	public Game() {
		//creating model and views.
		gw = new GameWorld();
		mv = new MapView(gw);
		mv.setGame(this);
		sv = new ScoreView();
		sv.setTarget(gw);
		gw.addObserver(mv);
		gw.addObserver(sv);
		
		this.setLayout(new BorderLayout());
		this.add(BorderLayout.NORTH, sv);
		this.add(BorderLayout.CENTER, mv);
		
		//Code for Command Objects 
		accelerateCommand = new AccelerateCommand(gw);
		brakeCommand = new BrakeCommand(gw);
		leftCommand = new LeftCommand(gw);
		rightCommand = new RightCommand(gw);
		aboutCommand = new AboutCommand(gw);
		helpCommand = new HelpCommand(gw);
		soundCommand = new SoundCommand(gw);
		exitCommand = new ExitCommand(gw);
		pauseCommand = new PausePlayCommand(this);
		positionCommand = new PositionCommand(gw);
		
		//Adding Key Binding to each Command (assuming unpaused)
		actionListeners(false);
		addKeyListener('p', pauseCommand);
		
		//Adding buttons to hold commands
		accelerateButton = new myGuiButton();
		brakeButton = new myGuiButton();
		leftButton = new myGuiButton();
		rightButton = new myGuiButton();
		pausePlayButton = new myGuiButton();
		positionButton = new myGuiButton();
		
		//Setting commands for each button. 
		accelerateButton.setCommand(accelerateCommand);
		brakeButton.setCommand(brakeCommand);
		leftButton.setCommand(leftCommand);
		rightButton.setCommand(rightCommand);
		pausePlayButton.setCommand(pauseCommand);
		positionButton.setCommand(positionCommand);
		
		//Creating Containers for each gui and adding components to each.
		OtherGui southGui = new OtherGui();
		southGui.setLayout(new FlowLayout(Component.CENTER));
		southGui.addComponent(pausePlayButton);
		southGui.addComponent(positionButton);
		
		OtherGui eastGui = new OtherGui();
		eastGui.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
		eastGui.addComponent(brakeButton);
		eastGui.addComponent(rightButton);
		
		OtherGui westGui = new OtherGui();
		westGui.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
		westGui.addComponent(accelerateButton);
		westGui.addComponent(leftButton);
		
		//Placing each Gui in it's respective place.
		this.add(BorderLayout.SOUTH, southGui);
		this.add(BorderLayout.EAST, eastGui );
		this.add(BorderLayout.WEST, westGui);
		
		CheckBox soundBox = new CheckBox("Sound");
		soundBox.setSelected(gw.getSound());
		soundBox.addChangeListener(soundCommand);
		
		//Setting up the toolbar.
		Toolbar toolBar = this.getToolbar();
		toolBar.addCommandToLeftSideMenu(accelerateCommand);
		toolBar.addComponentToLeftSideMenu(soundBox);
		toolBar.addCommandToLeftSideMenu(aboutCommand);
		toolBar.addCommandToLeftSideMenu(exitCommand);
		toolBar.addCommandToRightBar(helpCommand);
		
		
		isPaused = false;
		
		//End of setup. 
		this.show();
		gw.setWidth(mv.getWidth());
		gw.setHeight(mv.getHeight());
		gw.init();	
		
		//Timer Setup
		timer = new UITimer(this);
		timer.schedule(eventRate, true, this);
	}
	
	class OtherGui extends Container{
		// This is just here to help make things more readable above. 
		public OtherGui() {
			super();
		}
	}
	
	
	
	@Override
	public void run() {
		// This is for the interface Runnable. 
		// We want to make the game tick everytime this function is called by the UItimer scheduler.
		gw.clock_tick(eventRate);
		
	}
	public void togglePause() {
		// Toggles the game pause and unpause.
		// This is called by the PausePlay Command.
		if(isPaused) {
			//Is currently paused so you change to unpaused.
			isPaused = false;
			pausePlayButton.setText("Pause");
			actionListeners(isPaused);
			buttons(isPaused);
			gw.setPaused(isPaused);
			gw.unSelectAll();
			
			timer.schedule(eventRate, true, this);
		} else {
			//Is currently unpaused so you change to paused.
			timer.cancel();
			isPaused = true;
			pausePlayButton.setText("Play");
			buttons(isPaused);
			actionListeners(isPaused);
			gw.setPaused(isPaused);
		}
	}
	
	public boolean isPaused() {
		//	Helpful getting to see if the game should be paused right now. 
		return isPaused;
	}
	
	public void actionListeners(boolean _isPaused) {
		// This is for the pause and unpause command. 
		// This enables or disables the action listeners.
		if(_isPaused) {
			removeKeyListener('a', accelerateCommand);
			removeKeyListener('b', brakeCommand);
			removeKeyListener('l', leftCommand);
			removeKeyListener('r', rightCommand);
		} else {
			addKeyListener('a', accelerateCommand);
			addKeyListener('b', brakeCommand);
			addKeyListener('l', leftCommand);
			addKeyListener('r', rightCommand);
		}
	}
	public void buttons(boolean _isPaused) {
		// This function is for the pause and unpause command. 
		// This handles the logic for enabling and disabling all the buttons and commands.
		// If paused, disable everything except the "Position" command.
		// If unpaused, enable everything except the position command. 
		if(_isPaused) {
			accelerateCommand.setEnabled(false);
			accelerateButton.setEnabled(false);
			brakeButton.setEnabled(false);
			leftButton.setEnabled(false);
			rightButton.setEnabled(false);
			positionButton.setEnabled(true);
		} else {
			accelerateCommand.setEnabled(true);
			accelerateButton.setEnabled(true);
			brakeButton.setEnabled(true);
			leftButton.setEnabled(true);
			rightButton.setEnabled(true);	
			positionButton.setEnabled(false);
		}
	}
	public boolean reposistionActive() {
		return positionCommand.isActive();
	}
}