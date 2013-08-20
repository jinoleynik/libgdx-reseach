package com.shz.main;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix4;

public class Star extends SpriteBatch {

	private Texture mStarImage;
	private float mX = 10;
	private float mY = 10;
	private float mFinalX;
	private float mFinalY;
	private float mSpeed;
	private boolean mMove;
	private float mTime;
	private float mWeight = 10;
	private boolean mChaos;
	private double mAngle;
	private float mX0 = 10;
	private float mY0 = 10;
	private static final float STARTSPEED = 100;
	private static final float G = 9.8f;

	public Star() {
		mStarImage = new Texture(Gdx.files.internal("img_star.png"));
		// mStar.width = 64;
		// mStar.height = 64;
	}

	public void drawThis(Matrix4 matrix) {
		updatePosition();
		update();
		setProjectionMatrix(matrix);
		begin();
		draw(mStarImage, mX, mY);
		end();
	}

	private void update() {
		if (mMove) {
			mTime += 0.2;

			if (mX > GameMain.WIDTH - mStarImage.getWidth()) {
				mAngle = wall();
				mX = GameMain.WIDTH - mStarImage.getWidth();
			}
			if (mX < 0) {
				mAngle = wall();
				mX = 0;
			}
			if (mY > GameMain.HEIGHT - mStarImage.getHeight()) {
				mAngle = wall();
				mY = GameMain.HEIGHT - mStarImage.getHeight();
			}
			if (mY < 0) {
				mAngle = wall();
				mY = 0;
			}
			mX0 = mX;
			mY0 = mY;
			mY += (float) (mSpeed * mTime * Math.sin(mAngle) - mSpeed * 0.1);
			mX += (float) (mSpeed * mTime * Math.cos(mAngle));
			if (mSpeed < 1) {
				reset();
			}

		}
	}

	private double wall() {
		mSpeed *= 0.8f;
		mFinalY = mY;
		mTime = 0;
		mFinalX = mX;
		mAngle = Math.PI + Math.atan((mY0 - mY) / (mX0 - mX));
		if (mX0 > mX && mY0<mY) {
		 mAngle+=Math.PI/2 ;
		} else if(mX0 < mX && mY0<mY){
			mAngle +=Math.PI/2;
		}else if(mX0 > mX && mY0>mY){
			mAngle +=2*Math.PI;
		}else if(mX0 < mX && mY0>mY){
			mAngle -=Math.PI/2;
		}
		return mAngle;
	}

	private void updatePosition() {

	}

	public void setPosititon(float x, float y) {
		if (!mMove) {
			mMove = true;
			mFinalX = x;
			mFinalY = y;
			mSpeed = STARTSPEED;
			mAngle = Math.PI + Math.atan((mY - y) / (mX - x));
			if (mX < x) {		 
				mAngle += Math.PI;
			}
			System.out.println(Math.toDegrees(mAngle)-90);
		}
	}

	public void reset() {
		mTime = 0;
		mX = GameMain.WIDTH / 2 - mStarImage.getWidth() / 2;
		mY = GameMain.HEIGHT / 2 - mStarImage.getWidth() / 2;
		mMove = false;
		mChaos = false;

	}
}
