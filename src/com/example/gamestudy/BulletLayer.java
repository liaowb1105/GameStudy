package com.example.gamestudy;

import org.cocos2d.actions.instant.CCCallFuncN;
import org.cocos2d.actions.interval.CCMoveTo;
import org.cocos2d.actions.interval.CCSequence;
import org.cocos2d.layers.CCLayer;
import org.cocos2d.nodes.CCDirector;
import org.cocos2d.nodes.CCNode;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.types.CGPoint;

public class BulletLayer extends CCLayer{
	private CCNode plane;
	public BulletLayer(){
//		 CCTexture2D texture = CCTextureCache.sharedTextureCache().addImage("");
		plane =  PlaneLayer.getInstance().getChildByTag(PlaneLayer.AIRPLANE);
		
	}
	
	
	public void addBullet(float dt){
		CCSprite bullet = CCSprite.sprite("bullet.png", true);
		CGPoint planePosition =plane.getPosition();
		CGPoint bulletPosition = CGPoint.ccp(planePosition.x,planePosition.y+plane.getContentSize().height/2+5);
		bullet.setPosition(bulletPosition);
		this.addChild(bullet);
		float length=CCDirector.sharedDirector().winSize().height+bullet.getContentSize().height/2-bulletPosition.y;//飞行距离
		float velocity=420/1;//飞行速度：420pixel/sec  
		float realMoveDuration=length/velocity;//飞行时间
		CCMoveTo moveTo=CCMoveTo.action(realMoveDuration, 
				CGPoint.ccp(bullet.getPosition().x,CCDirector.sharedDirector().winSize().height+bullet.getContentSize().height/2));
		CCCallFuncN actionDone =CCCallFuncN.action(this, "bulletMoveFinished" );
		CCSequence seq= CCSequence.actions(moveTo, actionDone);
		bullet.runAction(seq);
	}
	
	public void bulletMoveFinished(Object o){
		//System.out.println("bulletMoveFinished");
	}
	
	public void startShoot(float delay){
		this.schedule("addBullet", 0.2f);
		System.out.println("startShoot");
	}
	
	public void stopShoot(){
		this.unschedule("addBullet");
		System.out.println("stopShoot");
	}
}
