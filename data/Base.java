package data;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;

public class Base {
	
	static final Toolkit TLK=Toolkit.getDefaultToolkit(); //用来将图片读入Image数组
	public static final Image base1 = TLK.getImage(Tank.class.getClassLoader().getResource("images/item/base/base1.png"));
	public static final Image base2 = TLK.getImage(Tank.class.getClassLoader().getResource("images/item/base/base2.png"));
	
	public static final Image[] FIRE1=
	{
		TLK.getImage(Tank.class.getClassLoader().getResource("images/item/base/fire1/1.png")),
		TLK.getImage(Tank.class.getClassLoader().getResource("images/item/base/fire1/2.png")),
		TLK.getImage(Tank.class.getClassLoader().getResource("images/item/base/fire1/3.png")),
		TLK.getImage(Tank.class.getClassLoader().getResource("images/item/base/fire1/4.png")),
		TLK.getImage(Tank.class.getClassLoader().getResource("images/item/base/fire1/5.png")),
		TLK.getImage(Tank.class.getClassLoader().getResource("images/item/base/fire1/6.png")),
		TLK.getImage(Tank.class.getClassLoader().getResource("images/item/base/fire1/7.png")),
		TLK.getImage(Tank.class.getClassLoader().getResource("images/item/base/fire1/8.png")),
		TLK.getImage(Tank.class.getClassLoader().getResource("images/item/base/fire1/9.png")),
		TLK.getImage(Tank.class.getClassLoader().getResource("images/item/base/fire1/10.png")),
		TLK.getImage(Tank.class.getClassLoader().getResource("images/item/base/fire1/11.png")),
		TLK.getImage(Tank.class.getClassLoader().getResource("images/item/base/fire1/12.png")),
		TLK.getImage(Tank.class.getClassLoader().getResource("images/item/base/fire1/13.png")),
		TLK.getImage(Tank.class.getClassLoader().getResource("images/item/base/fire1/14.png")),
		TLK.getImage(Tank.class.getClassLoader().getResource("images/item/base/fire1/15.png")),
		TLK.getImage(Tank.class.getClassLoader().getResource("images/item/base/fire1/16.png")),
		TLK.getImage(Tank.class.getClassLoader().getResource("images/item/base/fire1/17.png")),
		TLK.getImage(Tank.class.getClassLoader().getResource("images/item/base/fire1/18.png")),
		TLK.getImage(Tank.class.getClassLoader().getResource("images/item/base/fire1/19.png")),
		TLK.getImage(Tank.class.getClassLoader().getResource("images/item/base/fire1/20.png")),
		TLK.getImage(Tank.class.getClassLoader().getResource("images/item/base/fire1/21.png")),
		TLK.getImage(Tank.class.getClassLoader().getResource("images/item/base/fire1/22.png")),
		TLK.getImage(Tank.class.getClassLoader().getResource("images/item/base/fire1/23.png")),
		TLK.getImage(Tank.class.getClassLoader().getResource("images/item/base/fire1/24.png")),
		TLK.getImage(Tank.class.getClassLoader().getResource("images/item/base/fire1/25.png")),
		TLK.getImage(Tank.class.getClassLoader().getResource("images/item/base/fire1/26.png")),
		TLK.getImage(Tank.class.getClassLoader().getResource("images/item/base/fire1/27.png")),
		TLK.getImage(Tank.class.getClassLoader().getResource("images/item/base/fire1/28.png")),
		TLK.getImage(Tank.class.getClassLoader().getResource("images/item/base/fire1/29.png")),
		TLK.getImage(Tank.class.getClassLoader().getResource("images/item/base/fire1/30.png"))
	};
	public static final Image[] FIRE2=
	{
		TLK.getImage(Tank.class.getClassLoader().getResource("images/item/base/fire2/1.png")),
		TLK.getImage(Tank.class.getClassLoader().getResource("images/item/base/fire2/2.png")),
		TLK.getImage(Tank.class.getClassLoader().getResource("images/item/base/fire2/3.png")),
		TLK.getImage(Tank.class.getClassLoader().getResource("images/item/base/fire2/4.png")),
		TLK.getImage(Tank.class.getClassLoader().getResource("images/item/base/fire2/5.png")),
		TLK.getImage(Tank.class.getClassLoader().getResource("images/item/base/fire2/6.png")),
		TLK.getImage(Tank.class.getClassLoader().getResource("images/item/base/fire2/7.png")),
		TLK.getImage(Tank.class.getClassLoader().getResource("images/item/base/fire2/8.png")),
		TLK.getImage(Tank.class.getClassLoader().getResource("images/item/base/fire2/9.png")),
		TLK.getImage(Tank.class.getClassLoader().getResource("images/item/base/fire2/10.png")),
		TLK.getImage(Tank.class.getClassLoader().getResource("images/item/base/fire2/11.png")),
		TLK.getImage(Tank.class.getClassLoader().getResource("images/item/base/fire2/12.png")),
		TLK.getImage(Tank.class.getClassLoader().getResource("images/item/base/fire2/13.png")),
		TLK.getImage(Tank.class.getClassLoader().getResource("images/item/base/fire2/14.png")),
		TLK.getImage(Tank.class.getClassLoader().getResource("images/item/base/fire2/15.png")),
		TLK.getImage(Tank.class.getClassLoader().getResource("images/item/base/fire2/16.png")),
		TLK.getImage(Tank.class.getClassLoader().getResource("images/item/base/fire2/17.png")),
		TLK.getImage(Tank.class.getClassLoader().getResource("images/item/base/fire2/18.png")),
		TLK.getImage(Tank.class.getClassLoader().getResource("images/item/base/fire2/19.png")),
		TLK.getImage(Tank.class.getClassLoader().getResource("images/item/base/fire2/20.png")),
		TLK.getImage(Tank.class.getClassLoader().getResource("images/item/base/fire2/21.png")),
		TLK.getImage(Tank.class.getClassLoader().getResource("images/item/base/fire2/22.png")),
		TLK.getImage(Tank.class.getClassLoader().getResource("images/item/base/fire2/23.png")),
		TLK.getImage(Tank.class.getClassLoader().getResource("images/item/base/fire2/24.png")),
		TLK.getImage(Tank.class.getClassLoader().getResource("images/item/base/fire2/25.png")),
		TLK.getImage(Tank.class.getClassLoader().getResource("images/item/base/fire2/26.png")),
		TLK.getImage(Tank.class.getClassLoader().getResource("images/item/base/fire2/27.png")),
		TLK.getImage(Tank.class.getClassLoader().getResource("images/item/base/fire2/28.png")),
		TLK.getImage(Tank.class.getClassLoader().getResource("images/item/base/fire2/29.png")),
		TLK.getImage(Tank.class.getClassLoader().getResource("images/item/base/fire2/30.png")),
		TLK.getImage(Tank.class.getClassLoader().getResource("images/item/base/fire2/31.png")),
		TLK.getImage(Tank.class.getClassLoader().getResource("images/item/base/fire2/32.png")),
		TLK.getImage(Tank.class.getClassLoader().getResource("images/item/base/fire2/33.png")),
		TLK.getImage(Tank.class.getClassLoader().getResource("images/item/base/fire2/34.png")),
		TLK.getImage(Tank.class.getClassLoader().getResource("images/item/base/fire2/35.png")),
		TLK.getImage(Tank.class.getClassLoader().getResource("images/item/base/fire2/36.png")),
		TLK.getImage(Tank.class.getClassLoader().getResource("images/item/base/fire2/37.png")),
		TLK.getImage(Tank.class.getClassLoader().getResource("images/item/base/fire2/38.png")),
		TLK.getImage(Tank.class.getClassLoader().getResource("images/item/base/fire2/39.png")),
		TLK.getImage(Tank.class.getClassLoader().getResource("images/item/base/fire2/40.png")),
		TLK.getImage(Tank.class.getClassLoader().getResource("images/item/base/fire2/41.png")),
		TLK.getImage(Tank.class.getClassLoader().getResource("images/item/base/fire2/42.png")),
		TLK.getImage(Tank.class.getClassLoader().getResource("images/item/base/fire2/43.png")),
		TLK.getImage(Tank.class.getClassLoader().getResource("images/item/base/fire2/44.png")),
		TLK.getImage(Tank.class.getClassLoader().getResource("images/item/base/fire2/45.png")),
		TLK.getImage(Tank.class.getClassLoader().getResource("images/item/base/fire2/46.png")),
		TLK.getImage(Tank.class.getClassLoader().getResource("images/item/base/fire2/47.png")),
		TLK.getImage(Tank.class.getClassLoader().getResource("images/item/base/fire2/48.png")),
		TLK.getImage(Tank.class.getClassLoader().getResource("images/item/base/fire2/49.png")),
		TLK.getImage(Tank.class.getClassLoader().getResource("images/item/base/fire2/50.png")),
		TLK.getImage(Tank.class.getClassLoader().getResource("images/item/base/fire2/51.png")),
		TLK.getImage(Tank.class.getClassLoader().getResource("images/item/base/fire2/52.png")),
		TLK.getImage(Tank.class.getClassLoader().getResource("images/item/base/fire2/53.png")),
		TLK.getImage(Tank.class.getClassLoader().getResource("images/item/base/fire2/54.png")),
		TLK.getImage(Tank.class.getClassLoader().getResource("images/item/base/fire2/55.png")),
		TLK.getImage(Tank.class.getClassLoader().getResource("images/item/base/fire2/56.png")),
		TLK.getImage(Tank.class.getClassLoader().getResource("images/item/base/fire2/57.png")),
		TLK.getImage(Tank.class.getClassLoader().getResource("images/item/base/fire2/58.png")),
		TLK.getImage(Tank.class.getClassLoader().getResource("images/item/base/fire2/59.png")),
		TLK.getImage(Tank.class.getClassLoader().getResource("images/item/base/fire2/60.png")),
		TLK.getImage(Tank.class.getClassLoader().getResource("images/item/base/fire2/61.png")),
		TLK.getImage(Tank.class.getClassLoader().getResource("images/item/base/fire2/62.png"))
	};
	
	public static int HEALTHPOINT=3000;
	HealthPoint baseHealthPoint = new BaseHealthPoint(HEALTHPOINT,this);  //基地的生命值
	
	boolean init1 = false; //第一次画时将图片载入内存
	boolean init2 = false;
	boolean isLive = true;
	int x,y;
	int step1 = 0;
	int step2 = 0;
	
	FightPanel fp = null;
	Bomb baseBomb = null;
	
	public Base(int x, int y, FightPanel fp)
	{
		this.x = x;
		this.y = y;
		this.fp = fp;
	}
	
	public void draw(Graphics g)
	{
		if(isLive)
		{
			baseHealthPoint.draw(g);
			
			if(baseHealthPoint.getHealthPoint() > baseHealthPoint.getTopHealthPoint()*2/3)
			{
				g.drawImage(base1, x, y,80,56,null);
			}else{
				g.drawImage(base2, x, y,80,56,null);
				drawFire(g);
			}
			g.setColor(Color.darkGray);
			g.drawRect(x-10,y-14,100,74);
		}else
		{
			if(baseBomb!=null)
			{
				baseBomb.draw(g);
			}
		}
	}
	
	public void drawFire(Graphics g)
	{
		//右侧火焰
		if(!init1)
		{
			for (int i=0;i<FIRE1.length;i++)
			{
				g.drawImage(FIRE1[i],x+58,y+20,null);
				init1=true;
			}
		}
		
		g.drawImage(FIRE1[step1],x+58,y+20,null);
		
		step1++;
		if(step1>=FIRE1.length)
		{
			step1 = 0;
		}
		//左侧火焰
		if(!init2)
		{
			for (int i=0;i<FIRE2.length;i++)
			{
				g.drawImage(FIRE2[i],x+22,y-1,null);
				init2=true;
			}
		}
		
		g.drawImage(FIRE2[step2],x+22,y-1,null);
		
		step2++;
		if(step2>=FIRE1.length)
		{
			step2 = 0;
		}
	}
	
	public void setXY(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	
	public void dead()
	{
		isLive = false;
		baseBomb = new BaseBomb(x-60,y-130,this);
	}
	
	public Rectangle getRect() //判断子弹、奖品碰撞
	{
		return new Rectangle(x,y,80,56);
	}

	public Rectangle getMoveRect() //判断移动碰撞
	{
		return new Rectangle(x-10,y-14,100,74);
	}
}
