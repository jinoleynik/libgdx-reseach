package com.shz.main;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;

public class GameDesc {
	  public static void main (String[] args) {
          new LwjglApplication(new GameMain(), "Game", 480, 320, false);
  }
}
