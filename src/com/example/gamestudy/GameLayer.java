package com.example.gamestudy;

import org.cocos2d.layers.CCLayer;
import org.cocos2d.nodes.CCDirector;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.types.CGPoint;
import org.cocos2d.types.CGRect;

import android.view.MotionEvent;

public class GameLayer extends CCLayer{
	private CCSprite background1;
	private CCSprite background2;

	public GameLayer() {
		
		 background1 = CCSprite.sprite("shoot_background.png");
		background1.setAnchorPoint(CGPoint.ccp(0,0));
		background1.setPosition(CGPoint.ccp(0, 0));
		this.addChild(background1);
		 background2 = CCSprite.sprite("shoot_background.png");
		background2.setAnchorPoint(CGPoint.ccp(0,0));
		background2.setPosition(CGPoint.ccp(0,background2.getContentSize().height-2));
		this.addChild(background2);
		this.schedule("backgroundMove", 0.01f);
		this.addChild(PlaneLayer.getInstance());
		BulletLayer bulletLayer = new BulletLayer();
		this.addChild(bulletLayer);
		this.setIsTouchEnabled(true);
		bulletLayer.startShoot(0.1f);
	}
	
	public void backgroundMove(float dt){
		CGPoint bgc1=background1.getPosition();
		background1.setPosition(0,bgc1.y-2);
		background2.setPosition(0,bgc1.y+background1.getContentSize().getHeight()-2);
		if(background2.getPosition().y==0){
			background1.setPosition(0,0);
		}
	}
	
	@Override
	public boolean ccTouchesBegan(MotionEvent event) {
		System.out.println("ccTouchesBegan");
		return true;
	}
	
	private CGPoint lastTouchPoint ;
	@Override
	public boolean ccTouchesMoved(MotionEvent event) {
		System.out.println("ccTouchesMoved");
		CGPoint c = CGPoint.ccp(event.getX(), event.getY());
		CGPoint beginPoint = CCDirector.sharedDirector().convertToGL(c);
		CGRect planeRect = PlaneLayer.getInstance().getChildByTag(PlaneLayer.AIRPLANE).getBoundingBox();
		planeRect.origin.set(planeRect.origin.x-15,planeRect.origin.y-15);
		planeRect.size.set(planeRect.size.width+30, planeRect.size.height+30);
		if(CGRect.containsPoint(planeRect, beginPoint)){
			System.out.println("contais plane");
		}
		return super.ccTouchesMoved(event);
	}
	
	@Override
	public boolean ccTouchesEnded(MotionEvent event) {
		// TODO Auto-generated method stub
		return super.ccTouchesEnded(event);
	}
}
