package com.mycompany.a3;

import java.io.IOException;
import java.io.InputStream;

import com.codename1.media.Media;
import com.codename1.media.MediaManager;
import com.codename1.ui.Display;

public class Sound implements Runnable {
	private Media m;
	
	public Sound(String fileName) {
		try {
			InputStream inputStream = Display.getInstance().getResourceAsStream(getClass(), "/" + fileName);
			if(fileName == "BGMusic.wav")
                m = MediaManager.createMedia(inputStream, "audio/wav", this);
            else
                m = MediaManager.createMedia(inputStream, "audio/wav");
			
		} catch (IOException e) {
			System.out.println("Error with sound file: " + fileName);
		}
	}
	public void pause() {
		m.pause();
	}
	public void play() {
		m.setTime(0);
		m.play();
	}
	
	@Override
	public void run() {
		m.setTime(0);
		m.play();
	}
}
