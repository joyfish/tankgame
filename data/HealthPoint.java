package data;

import java.awt.Color;
import java.awt.Graphics;

abstract class HealthPoint {

	int HEALTHPOINTSIZE; //血条的长度
	int topHealthPoint;  //总血量
	int healthPoint;  //当前血量
	Tank tk;
	Base base;
	
	public HealthPoint(int topHealthPoint)
	{
		this.topHealthPoint=topHealthPoint;
		this.healthPoint=topHealthPoint;
	}
	
	abstract void draw(Graphics g);
	abstract void cutsHitPoint(int cuts,Shot s);
	
	public void raisesHitPoint(int raises) //血量增加
	{
		if((healthPoint+raises)<topHealthPoint)
		{
			healthPoint += raises;
		}else{
			healthPoint = topHealthPoint;
		}
	}
		
	public boolean isEmpty()
	{
		if(healthPoint<=0) { return true; }
		else { return false; }
	}
	
	public int getHealthPoint() {
		return healthPoint;
	}

	public void setHealthPoint(int healthPoint) {
		this.healthPoint = healthPoint;
	}

	public int getTopHealthPoint() {
		return topHealthPoint;
	}

	public void setTopHealthPoint(int topHealthPoint) {
		this.topHealthPoint = topHealthPoint;
	}
		
}

class TankHealthPoint extends HealthPoint
{
	public TankHealthPoint(int topHealthPoint,Tank tk)
	{
		super(topHealthPoint);
		this.HEALTHPOINTSIZE = 36;
		this.tk = tk;
	}
	
	public void draw(Graphics g)
	{
		Color c=g.getColor();

		g.setColor(Color.white);
		g.drawRect(tk.x-4,tk.y-14,HEALTHPOINTSIZE,5);
		
		if(healthPoint <= 400)
		{
			g.setColor(Color.red);
		}else if(healthPoint <= 900)
		{
			g.setColor(Color.orange);
		}else
		{
			g.setColor(Color.green);
		}
		
		g.fillRect(tk.x-3,tk.y-13,healthPoint*HEALTHPOINTSIZE/topHealthPoint-1,4);		
		
		g.setColor(c);
	}
	
	public void cutsHitPoint(int cuts,Shot s)  //被子弹击中时的减血
	{
		if((healthPoint-cuts)>0)
		{
			healthPoint -= cuts;
		}else{
			healthPoint = 0;
		}
//			if(tk.isLive&&tk instanceof RobotTank) { ((RobotTank) tk).avoidThrust(s); }
		if(isEmpty()) { tk.dead(); } //若血空了,Tank死掉 
	}
}

class BaseHealthPoint extends HealthPoint
{
	public BaseHealthPoint(int topHealthPoint,Base base)
	{
		super(topHealthPoint);
		this.HEALTHPOINTSIZE = 80;
		this.base = base;
	}
	
	public void draw(Graphics g)
	{
		Color c=g.getColor();

		g.setColor(Color.white);
		g.drawRect(base.x,base.y-12,HEALTHPOINTSIZE,5);
		
		if(healthPoint <= topHealthPoint/3)
		{
			g.setColor(Color.red);
		}else if(healthPoint <= topHealthPoint*2/3)
		{
			g.setColor(Color.orange);
		}else
		{
			g.setColor(Color.green);
		}
		
		g.fillRect(base.x+1,base.y-11,healthPoint*HEALTHPOINTSIZE/topHealthPoint-1,4);		
		
		g.setColor(c);
	}
	
	public void cutsHitPoint(int cuts,Shot s)  //被子弹击中时的减血
	{
//			System.out.println(healthPoint);
//			System.out.println(cuts);
		
		if((healthPoint-cuts)>0)
		{
			healthPoint -= cuts;
		}else{
			healthPoint = 0;
		}
//			if(tk.isLive&&tk instanceof RobotTank) { ((RobotTank) tk).avoidThrust(s); }
		if(isEmpty()) { base.dead(); } //若血空了,Tank死掉 
	}
}
