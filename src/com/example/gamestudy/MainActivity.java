package com.example.gamestudy;

import org.cocos2d.layers.CCScene;
import org.cocos2d.nodes.CCDirector;
import org.cocos2d.opengl.CCGLSurfaceView;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class MainActivity extends Activity {
	private CCGLSurfaceView mGLSurfaceView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.activity_main);
		
		CCDirector director = CCDirector.sharedDirector();
		mGLSurfaceView = new CCGLSurfaceView(this);
		director.attachInView(mGLSurfaceView);
		
		// show FPS
		CCDirector.sharedDirector().setDisplayFPS(true);
		
		// frames per second
		CCDirector.sharedDirector().setAnimationInterval(1.0f / 30);
		
		CCScene scene = CCScene.node();
		scene.addChild(new GameLayer());
		CCDirector.sharedDirector().runWithScene(scene);
		setContentView(mGLSurfaceView);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
