package com.example.gamestudy;

import org.cocos2d.actions.base.CCRepeatForever;
import org.cocos2d.actions.interval.CCAnimate;
import org.cocos2d.actions.interval.CCBlink;
import org.cocos2d.layers.CCLayer;
import org.cocos2d.nodes.CCAnimation;
import org.cocos2d.nodes.CCDirector;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.nodes.CCSpriteFrameCache;
import org.cocos2d.types.CGPoint;
import org.cocos2d.types.CGSize;

public class PlaneLayer extends CCLayer{
	private static PlaneLayer planeLayer;
	public static final int AIRPLANE=1;
	private PlaneLayer(){
		CGSize winSize = CCDirector.sharedDirector().winSize();
		CCSpriteFrameCache.sharedSpriteFrameCache().addSpriteFrames("shoot.plist");
		CCSprite plane = CCSprite.sprite(CCSpriteFrameCache.sharedSpriteFrameCache().getSpriteFrame("hero1.png"));
		plane.setPosition(CGPoint.ccp(winSize.width/2, plane.getContentSize().height/2));
		this.addChild(plane,0,AIRPLANE);
		CCBlink blink = CCBlink.action(1,3);
		CCAnimation animation = CCAnimation.animation("heroAn1",0.1f);
		animation.addFrame(CCSpriteFrameCache.sharedSpriteFrameCache().getSpriteFrame("hero1.png"));
		animation.addFrame(CCSpriteFrameCache.sharedSpriteFrameCache().getSpriteFrame("hero2.png"));
		CCAnimate animate = CCAnimate.action(animation);
		plane.runAction(blink);
		plane.runAction(CCRepeatForever.action(animate));
		
		
	}
	public static PlaneLayer getInstance(){
		if(planeLayer==null){
			planeLayer=new PlaneLayer();
			
		}
		return planeLayer;
	}
}
