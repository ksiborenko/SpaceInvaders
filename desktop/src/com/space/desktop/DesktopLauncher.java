package com.space.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.space.App;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.foregroundFPS = App.FPS;
		config.backgroundFPS = App.FPS;
		config.width = App.WIDTH;
		config.height = App.HEIGHT;
		new LwjglApplication(new App(), config);
	}
}
