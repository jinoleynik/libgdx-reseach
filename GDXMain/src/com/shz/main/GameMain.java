package com.shz.main;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class GameMain implements ApplicationListener {

	private OrthographicCamera camera;
	private long mTime;
	private Star mStary;
	private float mX;
	private float mY;
	private long mTimeLimit;
	public static final int HEIGHT = 480;
	public static final int WIDTH = 800;

	@Override
	public void create() {
		mStary = new Star();
		camera = new OrthographicCamera();
		camera.setToOrtho(false, WIDTH, HEIGHT);

	}

	@Override
	public void resize(int width, int height) {

	}

	@Override
	public void render() {
		// System.out.println(System.currentTimeMillis() - mTime);

		mTimeLimit += System.currentTimeMillis() - mTime;
		mTime = System.currentTimeMillis();
		if (mTimeLimit > 0.2) {
			mTimeLimit = 0;
			Gdx.gl.glClearColor(0, 0, 0.2f, 1);
			Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

			camera.update();
			mStary.drawThis(camera.combined);
			if (Gdx.input.isKeyPressed(Keys.SPACE))
				mStary.reset();

			if (Gdx.input.isTouched()) {

				Vector3 touchPos = new Vector3();
				touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
				camera.unproject(touchPos);
				mX = touchPos.x;
				mY = touchPos.y;
				mStary.setPosititon(mX, mY);
			//	mStary.jump();
				// mStary.chaos();
			//	System.out.println(touchPos.x + "    " + touchPos.y);
			}
		}
	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void dispose() {
		mStary.dispose();
	}

}
