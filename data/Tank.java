package data;

import java.awt.*;
import java.util.*;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import data.SuperShotBomb.Defer;

abstract class Tank
{
	boolean init=false; //第一次画时将图片载入内存
	static final Toolkit TLK=Toolkit.getDefaultToolkit(); //用来将图片读入Image数组
	
	int TANKSPEED;  //Tank移动速度(控制move()的延时)
	boolean allowFire=true;
		
	public static enum TANK_DIR {U,D,L,R,STOP};
	
	TANK_DIR tankDir;   //Tank主体的方向（过滤掉moveDir中的STOP属性）
	TANK_DIR moveDir;   //Tank的运动方向（同步于ut的keyList<>）
	
	boolean camp;   //Tank的阵营
	boolean isNPC;  //是否是有电脑控制的机器人Tank
	boolean isLive = true; //Tank是否存活
	boolean isWuDi = false; //Tank是否无敌
	boolean ismove = true; //Tank是否可以移动
	boolean isAppear = false; //在tank出现前画出星星
	boolean isCarryGift = false; //带礼物的tank
	boolean isRaiseHP = false;//tank正在回血时，无敌闪烁避让高亮度
	
	int step = 0; //控制在tank出现前画出星星
	int delayStep = 0; //无敌控制
	int lightStep = 0; //光环控制
	int lightDelayStep = 0;
	int x,y;                //Tank的坐标
	int oldx,oldy;  //存储Tank前一步的坐标
	
	//炮筒的坐标
	int turretX;
	int turretY;
	//天啓第二个炮筒的坐标
	int turretX2;
	int turretY2;
	
	int level;
	
	int RcX,RcY,RcWidth,RcHeight; //碰撞检测
	
	FightPanel fightPanel=null;  //主界面类的引用
	HealthPoint tankHealthPoint;  //Tank的生命值
	Bomb tankBomb=null;
	
	static Random rn=new Random();  //随机数发生器
	
	public static final Image[] TANKAPPEAR=
	{
		TLK.getImage(Tank.class.getClassLoader().getResource("images/item/appear/1.png")),
		TLK.getImage(Tank.class.getClassLoader().getResource("images/item/appear/2.png")),
		TLK.getImage(Tank.class.getClassLoader().getResource("images/item/appear/3.png")),
		TLK.getImage(Tank.class.getClassLoader().getResource("images/item/appear/4.png")),
		TLK.getImage(Tank.class.getClassLoader().getResource("images/item/appear/5.png")),
		TLK.getImage(Tank.class.getClassLoader().getResource("images/item/appear/6.png")),
		TLK.getImage(Tank.class.getClassLoader().getResource("images/item/appear/7.png")),
	};
	
	public static final Image[] TANKBODY0=
	{
		//我的坦克
		TLK.getImage(Tank.class.getClassLoader().getResource("images/tank/me/huiXiong00.png")),
		TLK.getImage(Tank.class.getClassLoader().getResource("images/tank/me/huiXiong01.png")),
		TLK.getImage(Tank.class.getClassLoader().getResource("images/tank/me/huiXiong02.png")),
		TLK.getImage(Tank.class.getClassLoader().getResource("images/tank/me/huiXiong03.png")),
		TLK.getImage(Tank.class.getClassLoader().getResource("images/tank/me/shaShou00.png")),
		TLK.getImage(Tank.class.getClassLoader().getResource("images/tank/me/shaShou01.png")),
		TLK.getImage(Tank.class.getClassLoader().getResource("images/tank/me/shaShou02.png")),
		TLK.getImage(Tank.class.getClassLoader().getResource("images/tank/me/shaShou03.png")),
		TLK.getImage(Tank.class.getClassLoader().getResource("images/tank/me/guangLing00.png")),
		TLK.getImage(Tank.class.getClassLoader().getResource("images/tank/me/guangLing01.png")),
		TLK.getImage(Tank.class.getClassLoader().getResource("images/tank/me/guangLing02.png")),
		TLK.getImage(Tank.class.getClassLoader().getResource("images/tank/me/guangLing03.png")),
		TLK.getImage(Tank.class.getClassLoader().getResource("images/tank/me/tianQi00.png")),
		TLK.getImage(Tank.class.getClassLoader().getResource("images/tank/me/tianQi01.png")),
		TLK.getImage(Tank.class.getClassLoader().getResource("images/tank/me/tianQi02.png")),
		TLK.getImage(Tank.class.getClassLoader().getResource("images/tank/me/tianQi03.png"))
	};
	public static final Image[] TANKBODY1=
	{
		//我的坦克
		TLK.getImage(Tank.class.getClassLoader().getResource("images/tank/enemy/xiNiu10.png")),
		TLK.getImage(Tank.class.getClassLoader().getResource("images/tank/enemy/xiNiu11.png")),
		TLK.getImage(Tank.class.getClassLoader().getResource("images/tank/enemy/xiNiu12.png")),
		TLK.getImage(Tank.class.getClassLoader().getResource("images/tank/enemy/xiNiu13.png")),
		TLK.getImage(Tank.class.getClassLoader().getResource("images/tank/enemy/shaShou10.png")),
		TLK.getImage(Tank.class.getClassLoader().getResource("images/tank/enemy/shaShou11.png")),
		TLK.getImage(Tank.class.getClassLoader().getResource("images/tank/enemy/shaShou12.png")),
		TLK.getImage(Tank.class.getClassLoader().getResource("images/tank/enemy/shaShou13.png")),
		TLK.getImage(Tank.class.getClassLoader().getResource("images/tank/enemy/guangLing10.png")),
		TLK.getImage(Tank.class.getClassLoader().getResource("images/tank/enemy/guangLing11.png")),
		TLK.getImage(Tank.class.getClassLoader().getResource("images/tank/enemy/guangLing12.png")),
		TLK.getImage(Tank.class.getClassLoader().getResource("images/tank/enemy/guangLing13.png")),
		TLK.getImage(Tank.class.getClassLoader().getResource("images/tank/enemy/tianQi10.png")),
		TLK.getImage(Tank.class.getClassLoader().getResource("images/tank/enemy/tianQi11.png")),
		TLK.getImage(Tank.class.getClassLoader().getResource("images/tank/enemy/tianQi12.png")),
		TLK.getImage(Tank.class.getClassLoader().getResource("images/tank/enemy/tianQi13.png"))
	};
	public static final Image[] WUDI=
	{
		//无敌时tank的样式
		TLK.getImage(Tank.class.getClassLoader().getResource("images/tank/me/wudi/1.png")),
		TLK.getImage(Tank.class.getClassLoader().getResource("images/tank/me/wudi/2.png")),
		TLK.getImage(Tank.class.getClassLoader().getResource("images/tank/me/wudi/3.png")),
		TLK.getImage(Tank.class.getClassLoader().getResource("images/tank/me/wudi/4.png")),
		TLK.getImage(Tank.class.getClassLoader().getResource("images/tank/me/wudi/5.png")),
		TLK.getImage(Tank.class.getClassLoader().getResource("images/tank/me/wudi/6.png")),
		TLK.getImage(Tank.class.getClassLoader().getResource("images/tank/me/wudi/7.png")),
		TLK.getImage(Tank.class.getClassLoader().getResource("images/tank/me/wudi/8.png")),
		TLK.getImage(Tank.class.getClassLoader().getResource("images/tank/me/wudi/9.png")),
		TLK.getImage(Tank.class.getClassLoader().getResource("images/tank/me/wudi/10.png")),
		TLK.getImage(Tank.class.getClassLoader().getResource("images/tank/me/wudi/11.png")),
		TLK.getImage(Tank.class.getClassLoader().getResource("images/tank/me/wudi/12.png")),
		TLK.getImage(Tank.class.getClassLoader().getResource("images/tank/me/wudi/13.png")),
		TLK.getImage(Tank.class.getClassLoader().getResource("images/tank/me/wudi/14.png")),
		TLK.getImage(Tank.class.getClassLoader().getResource("images/tank/me/wudi/15.png")),
		TLK.getImage(Tank.class.getClassLoader().getResource("images/tank/me/wudi/16.png")),
		TLK.getImage(Tank.class.getClassLoader().getResource("images/tank/me/wudi/17.png")),
		TLK.getImage(Tank.class.getClassLoader().getResource("images/tank/me/wudi/18.png")),
		TLK.getImage(Tank.class.getClassLoader().getResource("images/tank/me/wudi/19.png")),
		TLK.getImage(Tank.class.getClassLoader().getResource("images/tank/me/wudi/20.png")),
		TLK.getImage(Tank.class.getClassLoader().getResource("images/tank/me/wudi/21.png")),
		TLK.getImage(Tank.class.getClassLoader().getResource("images/tank/me/wudi/22.png")),
		TLK.getImage(Tank.class.getClassLoader().getResource("images/tank/me/wudi/23.png")),
		TLK.getImage(Tank.class.getClassLoader().getResource("images/tank/me/wudi/24.png")),
		TLK.getImage(Tank.class.getClassLoader().getResource("images/tank/me/wudi/25.png")),
		TLK.getImage(Tank.class.getClassLoader().getResource("images/tank/me/wudi/26.png")),
		TLK.getImage(Tank.class.getClassLoader().getResource("images/tank/me/wudi/27.png")),
		TLK.getImage(Tank.class.getClassLoader().getResource("images/tank/me/wudi/28.png")),
		TLK.getImage(Tank.class.getClassLoader().getResource("images/tank/me/wudi/29.png")),
		TLK.getImage(Tank.class.getClassLoader().getResource("images/tank/me/wudi/30.png")),
		TLK.getImage(Tank.class.getClassLoader().getResource("images/tank/me/wudi/31.png")),
		TLK.getImage(Tank.class.getClassLoader().getResource("images/tank/me/wudi/32.png")),
		TLK.getImage(Tank.class.getClassLoader().getResource("images/tank/me/wudi/33.png")),
		TLK.getImage(Tank.class.getClassLoader().getResource("images/tank/me/wudi/34.png")),
		TLK.getImage(Tank.class.getClassLoader().getResource("images/tank/me/wudi/35.png")),
		TLK.getImage(Tank.class.getClassLoader().getResource("images/tank/me/wudi/36.png")),
		TLK.getImage(Tank.class.getClassLoader().getResource("images/tank/me/wudi/37.png")),
		TLK.getImage(Tank.class.getClassLoader().getResource("images/tank/me/wudi/38.png")),
		TLK.getImage(Tank.class.getClassLoader().getResource("images/tank/me/wudi/39.png")),
		TLK.getImage(Tank.class.getClassLoader().getResource("images/tank/me/wudi/40.png")),
		TLK.getImage(Tank.class.getClassLoader().getResource("images/tank/me/wudi/41.png")),
		TLK.getImage(Tank.class.getClassLoader().getResource("images/tank/me/wudi/42.png")),
		TLK.getImage(Tank.class.getClassLoader().getResource("images/tank/me/wudi/43.png")),
		TLK.getImage(Tank.class.getClassLoader().getResource("images/tank/me/wudi/44.png")),
		TLK.getImage(Tank.class.getClassLoader().getResource("images/tank/me/wudi/45.png")),
		TLK.getImage(Tank.class.getClassLoader().getResource("images/tank/me/wudi/46.png")),
		TLK.getImage(Tank.class.getClassLoader().getResource("images/tank/me/wudi/47.png")),
		TLK.getImage(Tank.class.getClassLoader().getResource("images/tank/me/wudi/48.png")),
		TLK.getImage(Tank.class.getClassLoader().getResource("images/tank/me/wudi/49.png")),
		TLK.getImage(Tank.class.getClassLoader().getResource("images/tank/me/wudi/50.png")),
		TLK.getImage(Tank.class.getClassLoader().getResource("images/tank/me/wudi/51.png")),
		TLK.getImage(Tank.class.getClassLoader().getResource("images/tank/me/wudi/52.png")),
		TLK.getImage(Tank.class.getClassLoader().getResource("images/tank/me/wudi/53.png")),
		TLK.getImage(Tank.class.getClassLoader().getResource("images/tank/me/wudi/54.png")),
		TLK.getImage(Tank.class.getClassLoader().getResource("images/tank/me/wudi/55.png")),
		TLK.getImage(Tank.class.getClassLoader().getResource("images/tank/me/wudi/56.png")),
		TLK.getImage(Tank.class.getClassLoader().getResource("images/tank/me/wudi/57.png")),
		TLK.getImage(Tank.class.getClassLoader().getResource("images/tank/me/wudi/58.png")),
		TLK.getImage(Tank.class.getClassLoader().getResource("images/tank/me/wudi/59.png")),
		TLK.getImage(Tank.class.getClassLoader().getResource("images/tank/me/wudi/60.png")),
		TLK.getImage(Tank.class.getClassLoader().getResource("images/tank/me/wudi/61.png")),
		TLK.getImage(Tank.class.getClassLoader().getResource("images/tank/me/wudi/62.png")),
		TLK.getImage(Tank.class.getClassLoader().getResource("images/tank/me/wudi/63.png")),
		TLK.getImage(Tank.class.getClassLoader().getResource("images/tank/me/wudi/64.png")),
		TLK.getImage(Tank.class.getClassLoader().getResource("images/tank/me/wudi/65.png")),
		TLK.getImage(Tank.class.getClassLoader().getResource("images/tank/me/wudi/66.png")),
		TLK.getImage(Tank.class.getClassLoader().getResource("images/tank/me/wudi/67.png")),
		TLK.getImage(Tank.class.getClassLoader().getResource("images/tank/me/wudi/68.png")),
		TLK.getImage(Tank.class.getClassLoader().getResource("images/tank/me/wudi/69.png")),
		TLK.getImage(Tank.class.getClassLoader().getResource("images/tank/me/wudi/70.png")),
		TLK.getImage(Tank.class.getClassLoader().getResource("images/tank/me/wudi/71.png")),
		TLK.getImage(Tank.class.getClassLoader().getResource("images/tank/me/wudi/72.png")),
		TLK.getImage(Tank.class.getClassLoader().getResource("images/tank/me/wudi/73.png")),
		TLK.getImage(Tank.class.getClassLoader().getResource("images/tank/me/wudi/74.png")),
		TLK.getImage(Tank.class.getClassLoader().getResource("images/tank/me/wudi/75.png")),
		TLK.getImage(Tank.class.getClassLoader().getResource("images/tank/me/wudi/76.png")),
		TLK.getImage(Tank.class.getClassLoader().getResource("images/tank/me/wudi/77.png")),
		TLK.getImage(Tank.class.getClassLoader().getResource("images/tank/me/wudi/78.png")),
		TLK.getImage(Tank.class.getClassLoader().getResource("images/tank/me/wudi/79.png")),
		TLK.getImage(Tank.class.getClassLoader().getResource("images/tank/me/wudi/80.png")),
		TLK.getImage(Tank.class.getClassLoader().getResource("images/tank/me/wudi/81.png")),
		TLK.getImage(Tank.class.getClassLoader().getResource("images/tank/me/wudi/82.png")),
		TLK.getImage(Tank.class.getClassLoader().getResource("images/tank/me/wudi/83.png")),
		TLK.getImage(Tank.class.getClassLoader().getResource("images/tank/me/wudi/84.png")),
		TLK.getImage(Tank.class.getClassLoader().getResource("images/tank/me/wudi/85.png")),
		TLK.getImage(Tank.class.getClassLoader().getResource("images/tank/me/wudi/86.png")),
		TLK.getImage(Tank.class.getClassLoader().getResource("images/tank/me/wudi/87.png")),
		TLK.getImage(Tank.class.getClassLoader().getResource("images/tank/me/wudi/88.png")),
		TLK.getImage(Tank.class.getClassLoader().getResource("images/tank/me/wudi/89.png")),
		TLK.getImage(Tank.class.getClassLoader().getResource("images/tank/me/wudi/90.png")),
		TLK.getImage(Tank.class.getClassLoader().getResource("images/tank/me/wudi/91.png")),
		TLK.getImage(Tank.class.getClassLoader().getResource("images/tank/me/wudi/92.png")),
		TLK.getImage(Tank.class.getClassLoader().getResource("images/tank/me/wudi/93.png")),
		TLK.getImage(Tank.class.getClassLoader().getResource("images/tank/me/wudi/94.png")),
		TLK.getImage(Tank.class.getClassLoader().getResource("images/tank/me/wudi/95.png")),
		TLK.getImage(Tank.class.getClassLoader().getResource("images/tank/me/wudi/96.png")),
		TLK.getImage(Tank.class.getClassLoader().getResource("images/tank/me/wudi/97.png")),
		TLK.getImage(Tank.class.getClassLoader().getResource("images/tank/me/wudi/98.png")),
		TLK.getImage(Tank.class.getClassLoader().getResource("images/tank/me/wudi/99.png")),
		TLK.getImage(Tank.class.getClassLoader().getResource("images/tank/me/wudi/100.png")),
		TLK.getImage(Tank.class.getClassLoader().getResource("images/tank/me/wudi/101.png")),
		TLK.getImage(Tank.class.getClassLoader().getResource("images/tank/me/wudi/102.png")),
		TLK.getImage(Tank.class.getClassLoader().getResource("images/tank/me/wudi/103.png")),
		TLK.getImage(Tank.class.getClassLoader().getResource("images/tank/me/wudi/104.png")),
		TLK.getImage(Tank.class.getClassLoader().getResource("images/tank/me/wudi/105.png")),
		TLK.getImage(Tank.class.getClassLoader().getResource("images/tank/me/wudi/106.png")),
		TLK.getImage(Tank.class.getClassLoader().getResource("images/tank/me/wudi/107.png")),
		TLK.getImage(Tank.class.getClassLoader().getResource("images/tank/me/wudi/108.png")),
		TLK.getImage(Tank.class.getClassLoader().getResource("images/tank/me/wudi/109.png")),
		TLK.getImage(Tank.class.getClassLoader().getResource("images/tank/me/wudi/110.png")),
		TLK.getImage(Tank.class.getClassLoader().getResource("images/tank/me/wudi/111.png")),
		TLK.getImage(Tank.class.getClassLoader().getResource("images/tank/me/wudi/112.png"))
	};
	public static final Image[] GUANGHUAN=
	{
		//带礼物tank的光环
		TLK.getImage(Tank.class.getClassLoader().getResource("images/item/light/1.png")),
		TLK.getImage(Tank.class.getClassLoader().getResource("images/item/light/2.png")),
		TLK.getImage(Tank.class.getClassLoader().getResource("images/item/light/3.png")),
		TLK.getImage(Tank.class.getClassLoader().getResource("images/item/light/4.png")),
		TLK.getImage(Tank.class.getClassLoader().getResource("images/item/light/5.png")),
		TLK.getImage(Tank.class.getClassLoader().getResource("images/item/light/6.png")),
		TLK.getImage(Tank.class.getClassLoader().getResource("images/item/light/7.png")),
		TLK.getImage(Tank.class.getClassLoader().getResource("images/item/light/8.png")),
		TLK.getImage(Tank.class.getClassLoader().getResource("images/item/light/9.png")),
		TLK.getImage(Tank.class.getClassLoader().getResource("images/item/light/10.png")),
		TLK.getImage(Tank.class.getClassLoader().getResource("images/item/light/11.png")),
		TLK.getImage(Tank.class.getClassLoader().getResource("images/item/light/12.png"))
	};
	public static final Image levl = TLK.getImage(Tank.class.getClassLoader().getResource("images/item/level.png"));
	
	static Map<TANK_DIR,Image> imgMap0=new HashMap<TANK_DIR,Image>(); //存放我的tank
	static Map<TANK_DIR,Image> imgMap1=new HashMap<TANK_DIR,Image>(); //存放敌方tank-犀牛
	static Map<TANK_DIR,Image> imgMap2=new HashMap<TANK_DIR,Image>(); //存放敌方tank-杀手
	static Map<TANK_DIR,Image> imgMap3=new HashMap<TANK_DIR,Image>(); //存放敌方tank-光稜
	static Map<TANK_DIR,Image> imgMap4=new HashMap<TANK_DIR,Image>(); //存放敌方tank-天啓
	
	static
	{
		imgMap0.put(TANK_DIR.U,TANKBODY0[0]);
		imgMap0.put(TANK_DIR.D,TANKBODY0[1]);
		imgMap0.put(TANK_DIR.L,TANKBODY0[2]);
		imgMap0.put(TANK_DIR.R,TANKBODY0[3]);
	}
	static
	{
		imgMap1.put(TANK_DIR.U,TANKBODY1[0]);
		imgMap1.put(TANK_DIR.D,TANKBODY1[1]);
		imgMap1.put(TANK_DIR.L,TANKBODY1[2]);
		imgMap1.put(TANK_DIR.R,TANKBODY1[3]);
	}
	static
	{
		imgMap2.put(TANK_DIR.U,TANKBODY1[4]);
		imgMap2.put(TANK_DIR.D,TANKBODY1[5]);
		imgMap2.put(TANK_DIR.L,TANKBODY1[6]);
		imgMap2.put(TANK_DIR.R,TANKBODY1[7]);
	}
	static
	{
		imgMap3.put(TANK_DIR.U,TANKBODY1[8]);
		imgMap3.put(TANK_DIR.D,TANKBODY1[9]);
		imgMap3.put(TANK_DIR.L,TANKBODY1[10]);
		imgMap3.put(TANK_DIR.R,TANKBODY1[11]);
	}
	static
	{
		imgMap4.put(TANK_DIR.U,TANKBODY1[12]);
		imgMap4.put(TANK_DIR.D,TANKBODY1[13]);
		imgMap4.put(TANK_DIR.L,TANKBODY1[14]);
		imgMap4.put(TANK_DIR.R,TANKBODY1[15]);
	}
	
//	public Tank(int x,int y,boolean camp,FightPanel fightPanel)  //Tank初始方向随机
//	{
//		this.x=x;
//		this.y=y;
//		this.oldx=x;
//		this.oldy=y;
//		
//		this.fightPanel=fightPanel;
//		this.camp=camp;
//		
//		int i=rn.nextInt(4);
//		if(0==i) { this.tankDir=TANK_DIR.U; }
//		else if(1==i) { this.tankDir=TANK_DIR.D; }
//		else if(2==i) { this.tankDir=TANK_DIR.L; }
//		else if(3==i) { this.tankDir=TANK_DIR.R; }
//		this.moveDir=TANK_DIR.STOP;
//	}

	public Tank(int x,int y,int level,TANK_DIR dir,boolean camp,FightPanel fightPanel) //指明Tank初始方向
	{
		this.x=x;
		this.y=y;
		this.oldx=x;
		this.oldy=y;
		this.level=level;
		this.fightPanel=fightPanel;
		this.camp=camp;
		this.tankDir=dir;
		if(camp)
		{
			this.moveDir=TANK_DIR.STOP;
		}else{
			this.moveDir=dir;
		}
	}
	
	public void draw(Graphics g)
	{
		if(!isAppear && !camp) // 创建tank时先出现星星
		{
			drawAppear(g);
		}else{
			if(isLive)
			{
				if(camp)
				{
					for (int i=0;i<WUDI.length;i++)
					{
						g.drawImage(WUDI[i],1000,1000,null);
					}
					switch(level)
					{
					case 0:
					{
						switch(tankDir)
						{
						case U:{ g.drawImage(imgMap0.get(tankDir),x, y, 29, 38,null); }break;
						case D:{ g.drawImage(imgMap0.get(tankDir), x, y+14, 29, 25,null); }break;
						case L:{ g.drawImage(imgMap0.get(tankDir), x-23, y+13, 56, 23,null); }break;
						case R:{ g.drawImage(imgMap0.get(tankDir),x-7, y+12, 55, 24,null); }break;
						default:{}break;
						}
					}
					break;
					case 1:
					{
						switch(tankDir)
						{
						case U:{ g.drawImage(imgMap0.get(tankDir),x, y, 29, 38,null);  g.drawImage(levl,x+20, y+27, 8, 13,null); }break;
						case D:{ g.drawImage(imgMap0.get(tankDir), x, y+14, 29, 25,null);  g.drawImage(levl,x+20, y+27, 8, 13,null);}break;
						case L:{ g.drawImage(imgMap0.get(tankDir), x-23, y+13, 56, 23,null);  g.drawImage(levl,x+20, y+27, 8, 13,null);}break;
						case R:{ g.drawImage(imgMap0.get(tankDir),x-7, y+12, 55, 24,null);  g.drawImage(levl,x+20, y+27, 8, 13,null);}break;
						default:{}break;
						}
					}
					break;
					case 2:
					{
						switch(tankDir)
						{
						case U:{ g.drawImage(imgMap0.get(tankDir),x-1, y-4, 31, 42,null);}break;
						case D:{ g.drawImage(imgMap0.get(tankDir),x, y, 31, 38,null);}break;
						case L:{ g.drawImage(imgMap0.get(tankDir),x-19, y+9, 57, 30,null);}break;
						case R:{ g.drawImage(imgMap0.get(tankDir),x-8, y+10, 57, 30,null);}break;
						default:{}break;
						}
					}
					break;
					case 3:
					{
						switch(tankDir)
						{
						case U:{ g.drawImage(imgMap0.get(tankDir),x-1, y-4, 31, 42,null);  g.drawImage(levl,x+20, y+27, 8, 13,null); }break;
						case D:{ g.drawImage(imgMap0.get(tankDir),x, y, 31, 38,null);  g.drawImage(levl,x+20, y+27, 8, 13,null);}break;
						case L:{ g.drawImage(imgMap0.get(tankDir),x-19, y+9, 57, 30,null);  g.drawImage(levl,x+20, y+27, 8, 13,null);}break;
						case R:{ g.drawImage(imgMap0.get(tankDir),x-8, y+10, 57, 30,null);  g.drawImage(levl,x+20, y+27, 8, 13,null);}break;
						default:{}break;
						}
					}
					break;
					case 4:
					{
						switch(tankDir)
						{
						case U:{ g.drawImage(imgMap0.get(tankDir),x-2, y-6, 33, 44,null);}break;
						case D:{ g.drawImage(imgMap0.get(tankDir),x-2, y, 33, 38,null);}break;
						case L:{ g.drawImage(imgMap0.get(tankDir),x-6, y-5, 49, 42,null);}break;
						case R:{ g.drawImage(imgMap0.get(tankDir),x-12, y-4, 49, 41,null);}break;
						default:{}break;
						}
					}
					break;
					case 5:
					{
						switch(tankDir)
						{
						case U:{ g.drawImage(imgMap0.get(tankDir),x-2, y-6, 33, 44,null);  g.drawImage(levl,x+20, y+27, 8, 13,null); }break;
						case D:{ g.drawImage(imgMap0.get(tankDir),x-2, y, 33, 38,null);  g.drawImage(levl,x+20, y+27, 8, 13,null);}break;
						case L:{ g.drawImage(imgMap0.get(tankDir),x-6, y-5, 49, 42,null);  g.drawImage(levl,x+20, y+27, 8, 13,null);}break;
						case R:{ g.drawImage(imgMap0.get(tankDir),x-12, y-4, 49, 41,null);  g.drawImage(levl,x+20, y+27, 8, 13,null);}break;
						default:{}break;
						}
					}
					break;
					case 6:
					{
						switch(tankDir)
						{
						case U:{ g.drawImage(imgMap0.get(tankDir),x-1, y-7, 31, 45,null);}break;
						case D:{ g.drawImage(imgMap0.get(tankDir),x-1, y-2, 31, 43,null);}break;
						case L:{ g.drawImage(imgMap0.get(tankDir),x-16, y+3, 56, 33,null);}break;
						case R:{ g.drawImage(imgMap0.get(tankDir),x-10, y+5, 56, 31,null);}break;
						default:{}break;
						}
					}
					break;
					case 7:
					{
						switch(tankDir)
						{
						case U:{ g.drawImage(imgMap0.get(tankDir),x-1, y-7, 31, 45,null);  g.drawImage(levl,x+20, y+27, 8, 13,null); }break;
						case D:{ g.drawImage(imgMap0.get(tankDir),x-1, y-2, 31, 43,null);  g.drawImage(levl,x+20, y+27, 8, 13,null);}break;
						case L:{ g.drawImage(imgMap0.get(tankDir),x-16, y+3, 56, 33,null);  g.drawImage(levl,x+20, y+27, 8, 13,null);}break;
						case R:{ g.drawImage(imgMap0.get(tankDir),x-10, y+5, 56, 31,null);  g.drawImage(levl,x+20, y+27, 8, 13,null);}break;
						default:{}break;
						}
					}
					break;
					default:{}break;
					}
				}else{
					switch(level)
					{
					case 0:
					{
						switch(tankDir)
						{
						case U:{ g.drawImage(imgMap1.get(tankDir),x-2, y, 33, 39,null); }break;
						case D:{ g.drawImage(imgMap1.get(tankDir),x-2, y+6, 33, 31,null); }break;
						case L:{ g.drawImage(imgMap1.get(tankDir),x-10, y+13, 47, 25,null); }break;
						case R:{ g.drawImage(imgMap1.get(tankDir),x-8, y+12, 46, 27,null); }break;
						default:{}break;
						}
					}
					break;
					case 1:
					{
						switch(tankDir)
						{
						
						case U:{ g.drawImage(imgMap1.get(tankDir),x-2, y, 33, 39,null); g.drawImage(levl,x+20, y+27, 8, 13,null); }break;
						case D:{ g.drawImage(imgMap1.get(tankDir),x-2, y+6, 33, 31,null);g.drawImage(levl,x+20, y+27, 8, 13,null); }break;
						case L:{ g.drawImage(imgMap1.get(tankDir),x-10, y+13, 47, 25,null);g.drawImage(levl,x+20, y+27, 8, 13,null); }break;
						case R:{ g.drawImage(imgMap1.get(tankDir),x-8, y+12, 46, 27,null);g.drawImage(levl,x+20, y+27, 8, 13,null); }break;
						default:{}break;
						}
					}
					break;
					case 2:
					{
						switch(tankDir)
						{
						case U:{ g.drawImage(imgMap2.get(tankDir),x-1, y-4, 31, 42,null);}break;
						case D:{ g.drawImage(imgMap2.get(tankDir),x, y, 31, 38,null);}break;
						case L:{ g.drawImage(imgMap2.get(tankDir),x-19, y+9, 57, 30,null);}break;
						case R:{ g.drawImage(imgMap2.get(tankDir),x-8, y+10, 57, 30,null);}break;
						default:{}break;
						}
					}
					break;
					case 3:
					{
						switch(tankDir)
						{
						case U:{ g.drawImage(imgMap2.get(tankDir),x-1, y-4, 31, 42,null);  g.drawImage(levl,x+20, y+27, 8, 13,null); }break;
						case D:{ g.drawImage(imgMap2.get(tankDir),x, y, 31, 38,null);  g.drawImage(levl,x+20, y+27, 8, 13,null);}break;
						case L:{ g.drawImage(imgMap2.get(tankDir),x-19, y+9, 57, 30,null);  g.drawImage(levl,x+20, y+27, 8, 13,null);}break;
						case R:{ g.drawImage(imgMap2.get(tankDir),x-8, y+10, 57, 30,null);  g.drawImage(levl,x+20, y+27, 8, 13,null);}break;
						default:{}break;
						}
					}
					break;
					case 4:
					{
						switch(tankDir)
						{
						case U:{ g.drawImage(imgMap3.get(tankDir),x-2, y-6, 33, 44,null);}break;
						case D:{ g.drawImage(imgMap3.get(tankDir),x-2, y, 33, 38,null);}break;
						case L:{ g.drawImage(imgMap3.get(tankDir),x-6, y-5, 49, 42,null);}break;
						case R:{ g.drawImage(imgMap3.get(tankDir),x-12, y-4, 49, 41,null);}break;
						default:{}break;
						}
					}
					break;
					case 5:
					{
						switch(tankDir)
						{
						case U:{ g.drawImage(imgMap3.get(tankDir),x-2, y-6, 33, 44,null);  g.drawImage(levl,x+20, y+27, 8, 13,null); }break;
						case D:{ g.drawImage(imgMap3.get(tankDir),x-2, y, 33, 38,null);  g.drawImage(levl,x+20, y+27, 8, 13,null);}break;
						case L:{ g.drawImage(imgMap3.get(tankDir),x-6, y-5, 49, 42,null);  g.drawImage(levl,x+20, y+27, 8, 13,null);}break;
						case R:{ g.drawImage(imgMap3.get(tankDir),x-12, y-4, 49, 41,null);  g.drawImage(levl,x+20, y+27, 8, 13,null);}break;
						default:{}break;
						}
					}
					break;
					case 6:
					{
						switch(tankDir)
						{
						case U:{ g.drawImage(imgMap4.get(tankDir),x-1, y-7, 31, 45,null);}break;
						case D:{ g.drawImage(imgMap4.get(tankDir),x-1, y-2, 31, 43,null);}break;
						case L:{ g.drawImage(imgMap4.get(tankDir),x-16, y+3, 56, 33,null);}break;
						case R:{ g.drawImage(imgMap4.get(tankDir),x-10, y+5, 56, 31,null);}break;
						default:{}break;
						}
					}
					break;
					case 7:
					{
						switch(tankDir)
						{
						case U:{ g.drawImage(imgMap4.get(tankDir),x-1, y-7, 31, 45,null);  g.drawImage(levl,x+20, y+27, 8, 13,null); }break;
						case D:{ g.drawImage(imgMap4.get(tankDir),x-1, y-2, 31, 43,null);  g.drawImage(levl,x+20, y+27, 8, 13,null);}break;
						case L:{ g.drawImage(imgMap4.get(tankDir),x-16, y+3, 56, 33,null);  g.drawImage(levl,x+20, y+27, 8, 13,null);}break;
						case R:{ g.drawImage(imgMap4.get(tankDir),x-10, y+5, 56, 31,null);  g.drawImage(levl,x+20, y+27, 8, 13,null);}break;
						default:{}break;
						}
					}
					break;
					default:{}break;
					}
					
					if(isCarryGift)
					{
						this.drawGuangHuan(g); // 画出光环
					}
				}
				this.tankHealthPoint.draw(g);  //绘制出Tank的血量

			}else
			{
				if(tankBomb!=null)
				{
					tankBomb.draw(g);
				}
			}
		}
		
	}
	
	public void drawAppear(Graphics g)
	{
		if(!init)
		{
			for (int i=0;i<TANKAPPEAR.length;i++)
			{
				g.drawImage(TANKAPPEAR[i],1000,1000,null);
			}
			for (int i=0;i<TANKBODY0.length;i++)
			{
				g.drawImage(TANKBODY0[i],1000,1000,null);
			}
			for (int i=0;i<TANKBODY1.length;i++)
			{
				g.drawImage(TANKBODY1[i],1000,1000,null);
			}
			init = true;
		}
		
		if(step==TANKAPPEAR.length)
		{
			isAppear = true;
			return;
		}
		
		g.drawImage(TANKAPPEAR[step],x-5,y+5,null);
		
		delayStep++;
		
		if(delayStep==5)
		{
			step++;
			delayStep = 0;
		}
	}
	
	public void drawGuangHuan(Graphics g)
	{
		boolean lightInit = false;
		if(!lightInit)
		{
			for (int i=0;i<GUANGHUAN.length;i++)
			{
				g.drawImage(GUANGHUAN[i],1000,1000,null);
			}
			lightInit = true;
		}
		
		g.drawImage(GUANGHUAN[lightStep], x-15, y-13, null);
		
		lightDelayStep++;
		
		if(lightDelayStep==2)
		{
			lightStep++;
			lightDelayStep = 0;
		}
		
		if(lightStep>=12)
		{
			lightStep = 0;
		}
	}

	//boolean APWisOver = true;;
	
	public void move()  //Tank主体移动(炮筒坐标变换)
	{
		if(isLive && moveDir!=TANK_DIR.STOP && ismove && !isOutOfWindow())
		{
//			if(APWisOver)
//			{
//				APWisOver = false;
//				new Thread(new TankMoveSound()).start();
//			}
			
//			System.out.println(APWisOver);
			
			tankDir=moveDir;
			switch(level)
			{
				case 0:
				case 1:
				{ 
					switch(tankDir)
					{
						case U:
						{
							oldx=x;
							oldy=y;
							y--;
							this.setTurretXY(x+13,y-5);
							if(isHitWall()) {y=oldy;}
						}break;
						case D:
						{
							oldx=x;
							oldy=y;
							y++;
							if(camp){
								this.setTurretXY(x+12,y+35);
							}else{
								this.setTurretXY(x+12,y+26);
							}
							if(isHitWall()) {y=oldy;}
						}break;
						case L:
						{
							oldx=x;
							oldy=y;
							x--;
							if(camp){
								this.setTurretXY(x-27,y+16);
							}else{
								this.setTurretXY(x-15,y+14);
							}
							if(isHitWall()) {x=oldx;}
						}break;
						case R:
						{
							oldx=x;
							oldy=y;
							x++;
							if(camp){
								this.setTurretXY(x+49,y+15);
							}else{
								this.setTurretXY(x+38,y+13);
							}
							if(isHitWall()) {x=oldx;}
						}break;
						default:break;
					}
				}break;
				case 2:
				case 3:
				{ 
					switch(tankDir)
					{
						case U:
						{
							oldx=x;
							oldy=y;
							y--;
							this.setTurretXY(x+12,y-8);
							if(isHitWall()) {y=oldy;}
						}break;
						case D:
						{
							oldx=x;
							oldy=y;
							y++;
							this.setTurretXY(x+13,y+32);
							if(isHitWall()) {y=oldy;}
						}break;
						case L:
						{
							oldx=x;
							oldy=y;
							x--;
							this.setTurretXY(x-23,y+14);
							if(isHitWall()) {x=oldx;}
						}break;
						case R:
						{
							oldx=x;
							oldy=y;
							x++;
							this.setTurretXY(x+49,y+14);
							if(isHitWall()) {x=oldx;}
						}break;
						default:break;
					}
				}break;
				case 4:
				case 5:
				{
					switch(tankDir)
					{
						case U:
						{
							oldx=x;
							oldy=y;
							y--;
							if(isHitWall()) {y=oldy;}
							this.setTurretXY(x+14,y-8);
						}break;
						case D:
						{
							oldx=x;
							oldy=y;
							y++;
							if(isHitWall()) {y=oldy;}
							this.setTurretXY(x+13,y+12);
						}break;
						case L:
						{
							oldx=x;
							oldy=y;
							x--;
							if(isHitWall()) {x=oldx;}
							this.setTurretXY(x+6,y);
						}break;
						case R:
						{
							oldx=x;
							oldy=y;
							x++;
							if(isHitWall()) {x=oldx;}
							this.setTurretXY(x+23,y+2);
						}break;
						default:break;
					}
				}break;
				case 6:
				case 7:
				{ 
					switch(tankDir)
					{
						case U:
						{
							oldx=x;
							oldy=y;
							y--;
							if(isHitWall()) {y=oldy;}
							this.setTurretXY(x+9,y-10);
							this.setTurretXY2(x+16,y-10);
						}break;
						case D:
						{
							oldx=x;
							oldy=y;
							y++;
							if(isHitWall()) {y=oldy;}
							this.setTurretXY(x+16,y+32);
							this.setTurretXY2(x+9,y+32);
						}break;
						case L:
						{
							oldx=x;
							oldy=y;
							x--;
							if(isHitWall()) {x=oldx;}
							this.setTurretXY(x-20,y+13);
							this.setTurretXY2(x-20,y+9);
						}break;
						case R:
						{
							oldx=x;
							oldy=y;
							x++;
							if(isHitWall()) {x=oldx;}
							this.setTurretXY(x+45,y+9);
							this.setTurretXY2(x+45,y+13);
						}break;
						default:break;
					}
				}break;
				default:break;
			}
			
			Item itemTemp=null;
			for(int i=0;i<fightPanel.itemList.size();i++)  //碰到道具的处理
			{
				itemTemp=fightPanel.itemList.get(i);
				if(this.getRect().intersects(itemTemp.getRect()) && !itemTemp.touch && this.camp)
				{
					if(this.isWuDi)
					{
						itemTemp.touch(Tank.rn.nextInt(2),this); //当tank处于无敌状态时碰触的礼物不会再是无敌
					}else{
						itemTemp.touch(Tank.rn.nextInt(3),this);
					}
				}
			}
			
		}
		this.hitDispose();  //处理和其它的碰撞
	}
	
	abstract void hitDispose();  //处理和其它Tank的碰撞
	
	public void setMoveDir(TANK_DIR dir)  //设定Tank的运动方向
	{		
		this.moveDir=dir;
	}
	
	public void dead()
	{
		isLive = false;
		tankBomb = new TankBomb(x-51,y-44,this);
		
		Item item = null;
		int x = 0;
		int y = 0;
		
		Tank tempTk = null;
		RoadBlock block = null;
		WallNode wn = null;
		MountainNode mn = null;
		RiverNode Rn = null;
		
		boolean isTouchWalls = true;
		boolean isTouchMountains = true;
		boolean isTouchRiverNodes = true;
		boolean isTouchTanks = true;
		boolean isTouchBase = true;

		if(isCarryGift)
		{
			item = new Item(x,y,fightPanel);
			
			while(isTouchWalls || isTouchMountains || isTouchTanks || isTouchBase || isTouchRiverNodes)
			{
				isTouchWalls = false;
				isTouchMountains = false;
				isTouchRiverNodes = false;
				isTouchTanks = false;
				isTouchBase = false;
				
				item.x = rn.nextInt(630);
				item.y = rn.nextInt(530);
				
				for(int i=0;i<fightPanel.roadBlockList.size();i++)
				{
					block = fightPanel.roadBlockList.get(i);
					
					for(int j=0;j<block.wallNodesList.size();j++)
					{
						wn = block.wallNodesList.get(j);
							
						if(item.getRect().intersects(wn.getRect()))
						{
							isTouchWalls = true;
						}
					}
					
					for(int j=0;j<block.mountainNodesList.size();j++)
					{
						mn = block.mountainNodesList.get(j);
						
						if(item.getRect().intersects(mn.getRect()))
						{
							isTouchMountains = true;
						}
					}
					
					for(int j=0;j<block.riverNodesList.size();j++)
					{
						Rn = block.riverNodesList.get(j);
						
						if(item.getRect().intersects(Rn.getRect()))
						{
							isTouchRiverNodes = true;
						}
					}
				}
				
				for(int i=0;i<fightPanel.tanksList.size();i++)
				{
					tempTk=fightPanel.tanksList.get(i);
					if(tempTk.getMoveRect().intersects(item.getRect()))
					{
						isTouchTanks = true;
					}
				}
				
				if(fightPanel.base.getMoveRect().intersects(item.getRect()))
				{
					isTouchBase = true;
				}
			}
				
			fightPanel.itemList.add(item);//添加一个道具
		}
		
		//加分
		if(!camp)
		{
			switch(level)
			{
			case 0:
				Status.addScore(100);
				break;
			case 1:
				Status.addScore(300);
				break;
			case 2:
				Status.addScore(200);
				break;
			case 3:
				Status.addScore(400);
				break;
			case 4:
				Status.addScore(400);
				break;
			case 5:
				Status.addScore(600);
				break;
			case 6:
				Status.addScore(500);
				break;
			case 7:
				Status.addScore(800);
				break;
				default:break;
			}
		}
	}
	
	public boolean isOutOfWindow()
	{
		boolean isOut = false;
		switch(level)
		{
		case 0:
		case 1:
		{
			if(camp){
				switch(moveDir)
				{
				case U:	if(y<=15){isOut = true;}break;
				case D:	if(y>=526){isOut = true;}break;
				case L:if(x<=26){isOut = true;}break;
				case R:if(x>=628){isOut = true;}break;
				default:break;
				}
			}else{
				switch(moveDir)
				{
				case U:	if(y<=15){isOut = true;}break;
				case D:	if(y>=526){isOut = true;}break;
				case L:if(x<=18){isOut = true;}break;
				case R:if(x>=635){isOut = true;}break;
				default:break;
				}
			}
			
		}break;
		case 2:
		case 3:
		{ 
			switch(moveDir)
			{
			case U:if(y<=15){isOut = true;}break;
			case D:if(y>=526){isOut = true;}break;
			case L:if(x<=23){isOut = true;}break;
			case R:if(x>=628){isOut = true;}break;
			default:break;
			}
		}break;
		case 4:
		case 5:
		{
			switch(moveDir)
			{
			case U:if(y<=15){isOut = true;}break;
			case D:if(y>=526){isOut = true;}break;
			case L:if(x<=12){isOut = true;}break;
			case R:if(x>=638){isOut = true;}break;
			default:break;
			}
		}break;
		case 6:
		case 7:
		{
			switch(moveDir)
			{
			case U:if(y<=15){isOut = true;}break;
			case D:if(y>=525){isOut = true;}break;
			case L:if(x<=22){isOut = true;}break;
			case R:if(x>=630){isOut = true;}break;
			default:break;
			}
		}break;
		default:break;
		}
		
		if(!camp && isOut) // 电脑碰到墙后自动改变方向
		{
			switch(moveDir)
			{
			case U:
				switch(Tank.rn.nextInt(3))
				{
				case 0:	moveDir = TANK_DIR.D;break;
				case 1:moveDir = TANK_DIR.L;break;
				case 2:moveDir = TANK_DIR.R;break;
				default:break;
				}
				break;
			case D:
				switch(Tank.rn.nextInt(3))
				{
				case 0:moveDir = TANK_DIR.U;break;
				case 1:moveDir = TANK_DIR.L;break;
				case 2:moveDir = TANK_DIR.R;break;
				default:break;
				}
				break;
			case L:
				switch(Tank.rn.nextInt(3))
				{
				case 0:moveDir = TANK_DIR.U;break;
				case 1:moveDir = TANK_DIR.D;break;
				case 2:moveDir = TANK_DIR.R;break;
				default:break;
				}
				break;
			case R:
				switch(Tank.rn.nextInt(3))
				{
				case 0:moveDir = TANK_DIR.U;break;
				case 1:moveDir = TANK_DIR.D;break;
				case 2:moveDir = TANK_DIR.L;break;
				default:break;
				}
				break;
			default:break;
			}
			isOut = false;
		}
		return isOut;
	}
	
	public boolean inMidArea()  //Tank创建时不出现在中间区域内
	{
		Rectangle minArea = new Rectangle(160, 111, 352,353);
		return minArea.intersects(this.getRect());
	}
	
	public boolean isHitWall()  //Tank是否撞墙了
	{
		RoadBlock wall = null;
		
		for(int i=0;i<fightPanel.roadBlockList.size();i++)
		{
			wall = fightPanel.roadBlockList.get(i);
			
			if(wall.isHits(this.getMoveRect(),x,oldx,y,oldy))
			{
				if(!camp)
				{
					switch(moveDir)
					{
					case U:
						switch(Tank.rn.nextInt(3))
						{
						case 0:	moveDir = TANK_DIR.D;break;
						case 1:moveDir = TANK_DIR.L;break;
						case 2:moveDir = TANK_DIR.R;break;
						default:break;
						}
						break;
					case D:
						switch(Tank.rn.nextInt(3))
						{
						case 0:moveDir = TANK_DIR.U;break;
						case 1:moveDir = TANK_DIR.L;break;
						case 2:moveDir = TANK_DIR.R;break;
						default:break;
						}
						break;
					case L:
						switch(Tank.rn.nextInt(3))
						{
						case 0:moveDir = TANK_DIR.U;break;
						case 1:moveDir = TANK_DIR.D;break;
						case 2:moveDir = TANK_DIR.R;break;
						default:break;
						}
						break;
					case R:
						switch(Tank.rn.nextInt(3))
						{
						case 0:moveDir = TANK_DIR.U;break;
						case 1:moveDir = TANK_DIR.D;break;
						case 2:moveDir = TANK_DIR.L;break;
						default:break;
						}
						break;
					default:break;
					}
				}
				return true;
			}
		}
		return false;
	}
	
	public Rectangle getRect() //判断子弹、礼品碰撞
	{
		switch(level)
		{
		case 0:
		case 1:
			if(camp)
			{
				if(tankDir == TANK_DIR.U  || tankDir == TANK_DIR.D)
				{
					RcX = x;
					RcY = y+12;
					RcWidth = 28;
					RcHeight = 25;
				}else{
					RcX = x-8;
					RcY = y+12;
					RcWidth = 40;
					RcHeight = 23;
				}
			}else{
				if(tankDir == TANK_DIR.U)
				{
					RcX = x-2;
					RcY = y+12;
					RcWidth = 32;
					RcHeight = 26;
				}else if(tankDir == TANK_DIR.D)
				{
					RcX = x-2;
					RcY = y+6;
					RcWidth = 32;
					RcHeight = 30;
				}else{
					RcX = x-8;
					RcY = y+13;
					RcWidth = 44;
					RcHeight = 24;
				}
			}
			break;
		case 2:
		case 3:
			if(tankDir == TANK_DIR.U ){
				RcX = x-1;
				RcY = y+12;
				RcWidth = 30;
				RcHeight = 24;
			}else if(tankDir == TANK_DIR.D){
				RcX = x;
				RcY = y+7;
				RcWidth = 29;
				RcHeight = 31;
			}else if(tankDir == TANK_DIR.L){
				RcX = x-6;
				RcY = y+11;
				RcWidth = 43;
				RcHeight = 26;
			}else{
				RcX = x-8;
				RcY = y+11;
				RcWidth = 44;
				RcHeight = 27;
			}
			break;
		case 4:
		case 5:
			if(tankDir == TANK_DIR.U)
			{
				RcX = x-2;
				RcY = y-5;
				RcWidth = 32;
				RcHeight = 42;
			}else if(tankDir == TANK_DIR.D){
				RcX = x-2;
				RcY = y-1;
				RcWidth = 32;
				RcHeight = 38;
			}else if(tankDir == TANK_DIR.L){
				RcX = x-6;
				RcY = y-4;
				RcWidth = 48;
				RcHeight = 40;
			}else{
				RcX = x-12;
				RcY = y-4;
				RcWidth = 48;
				RcHeight = 40;
			}
			break;
		case 6:
		case 7:
			if(tankDir == TANK_DIR.U){
				RcX = x-2;
				RcY = y+8;
				RcWidth = 32;
				RcHeight = 29;
			}else if(tankDir == TANK_DIR.D)	{
				RcX = x-2;
				RcY = y+6;
				RcWidth = 32;
				RcHeight = 32;
			}else if(tankDir == TANK_DIR.L){
				RcX = x-16;
				RcY = y+9;
				RcWidth = 56;
				RcHeight = 27;
			}else{
				RcX = x-11;
				RcY = y+9;
				RcWidth = 55;
				RcHeight = 27;
			}
			break;
			default:break;
		}
			
		return new Rectangle(RcX,RcY,RcWidth,RcHeight);
	}
	
	public Rectangle getMoveRect() //判断移动碰撞
	{
		switch(level)
		{
		case 0:
		case 1:
			if(camp)
			{
				if(tankDir == TANK_DIR.U)
				{
					RcX = x-5;
					RcY = y-4;
					RcWidth = 38;
					RcHeight = 45;
				}else if(tankDir == TANK_DIR.D)
				{
					RcX = x-5;
					RcY = y+8;
					RcWidth = 38;
					RcHeight = 35;
				}else if(tankDir == TANK_DIR.L){
					RcX = x-27;
					RcY = y+7;
					RcWidth = 64;
					RcHeight = 33;
				}else{
					RcX = x-13;
					RcY = y+7;
					RcWidth = 64;
					RcHeight = 33;
				}
			}else{
				if(tankDir == TANK_DIR.U)
				{
					RcX = x-7;
					RcY = y-5;
					RcWidth = 42;
					RcHeight = 48;
				}else if(tankDir == TANK_DIR.D)
				{
					RcX = x-7;
					RcY = y+1;
					RcWidth = 42;
					RcHeight = 40;
				}else{
					RcX = x-13;
					RcY = y+8;
					RcWidth = 54;
					RcHeight = 34;
				}
			}
			break;
		case 2:
		case 3:
			if(tankDir == TANK_DIR.U ){
				RcX = x-6;
				RcY = y-8;
				RcWidth = 40;
				RcHeight = 50;
			}else if(tankDir == TANK_DIR.D){
				RcX = x-5;
				RcY = y-4;
				RcWidth = 39;
				RcHeight = 46;
			}else if(tankDir == TANK_DIR.L){
				RcX = x-23;
				RcY = y+6;
				RcWidth = 65;
				RcHeight = 36;
			}else{
				RcX = x-13;
				RcY = y+6;
				RcWidth = 65;
				RcHeight = 36;
			}
			break;
		case 4:
		case 5:
			if(tankDir == TANK_DIR.U)
			{
				RcX = x-7;
				RcY = y-10;
				RcWidth = 42;
				RcHeight = 50;
			}else if(tankDir == TANK_DIR.D)
			{
				RcX = x-7;
				RcY = y-5;
				RcWidth = 42;
				RcHeight = 45;
			}else if(tankDir == TANK_DIR.L){
				RcX = x-11;
				RcY = y-8;
				RcWidth = 58;
				RcHeight = 47;
			}else{
				RcX = x-17;
				RcY = y-8;
				RcWidth = 58;
				RcHeight = 47;
			}
			break;
		case 6:
		case 7:
			if(tankDir == TANK_DIR.U){
				RcX = x-7;
				RcY = y-10;
				RcWidth = 42;
				RcHeight = 50;
			}else if(tankDir == TANK_DIR.D)	{
				RcX = x-7;
				RcY = y-6;
				RcWidth = 42;
				RcHeight = 47;
			}else if(tankDir == TANK_DIR.L){
				RcX = x-21;
				RcY = y+1;
				RcWidth = 65;
				RcHeight = 38;
			}else{
				RcX = x-16;
				RcY = y+1;
				RcWidth = 65;
				RcHeight = 38;
			}
			break;
			default:break;
		}

		return new Rectangle(RcX,RcY,RcWidth,RcHeight);
	}
	
	public int getAltitudeRect() //开火时检测坦克海拔高度(目前只适用STAGE3)
	{
		if(x>0 && x<96 && y>0 && y<560)
		{
			return 2;
		}else if(x>=96 && x<528 && y>496 && y<560)
		{
			return 2;
		}else if(x>=96 && x<384 && y>0 && y<352)
		{
			return 2;
		}else if(x>=384 && x<560 && y>0 && y<304)
		{
			return 2;
		}
//		g.drawRect(0, 0, 96,35*16);
//		
//		g.drawRect(6*16,31*16, 27*16,4*16);
//		
//		g.drawRect(6*16,0, 18*16,22*16);
//		
//		g.drawRect(24*16,0, 11*16,19*16);
		return 1;
	}
	
	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}


	public int getTurretX() {
		return turretX;
	}
	
	public int getTurretY() {
		return turretY;
	}

	public void setTurretXY(int turretX, int turretY) {
		this.turretX = turretX;
		this.turretY = turretY;
	}
	public int getTurretX2() {
		return turretX2;
	}
	
	public int getTurretY2() {
		return turretY2;
	}

	public void setTurretXY2(int turretX2, int turretY2) {
		this.turretX2 = turretX2;
		this.turretY2 = turretY2;
	}
	
	public void setXY(int x,int y) {
		this.x = x;
		this.y = y;
	}
	
//	class TankMoveSound implements Runnable
//	{
//		public void run() {
//			while(true)
//			{
//				AePlayWave apw=new AePlayWave("sounds/1.wav");
//				apw.start();
//				try {
//						Thread.sleep(400);
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
//			}
//		}
//		
//	}
}

class WuDi implements Runnable
{
	UserTank ut;
	int i;
	int count;
	int last;
	int oldLevel;
	boolean iPP;
	boolean nearOver;
	static boolean isFinish;
	static boolean isPause; //暂停游戏
	static boolean exit; //退出战斗
	
	public static enum TANK_DIR {U,D,L,R,STOP};
	
	public WuDi(UserTank ut,boolean isBirth)
	{
		i = 0;
		count = 0;
		last = 12;
		oldLevel = ut.level;
		iPP = false;
		nearOver = false;
		isFinish = false;
		isPause = false;
		exit = false;
		this.ut = ut;
		
		switch(ut.level)
		{
		case 0:
		case 1:
			i = 0;
			break;
		case 2:
		case 3:
			i = 28;
			break;
		case 4:
		case 5:
			i = 56;
			break;
		case 6:
		case 7:
			i = 84;
			break;
		default:break;
		}
		
		if(isBirth){
			count = 5; //出生
		}
		else{
			count = 10; //无敌礼物
		}
	}
	
	public void run() {
		while(!isFinish)
		{
			if(exit){break;}
			try
			{
				if(!isPause)
				{
					if((ut.level-oldLevel)!=0)
					{
						if(oldLevel%2!=0)
						{
							i += 28;
						}
						oldLevel = ut.level;
					}
					
					if(!nearOver)
					{
						if(!ut.isRaiseHP)
						{
							Tank.imgMap0.clear();
							
							Tank.imgMap0.put(Tank.TANK_DIR.U,Tank.WUDI[i]);
							Tank.imgMap0.put(Tank.TANK_DIR.D,Tank.WUDI[i+7]);
							Tank.imgMap0.put(Tank.TANK_DIR.L,Tank.WUDI[i+14]);
							Tank.imgMap0.put(Tank.TANK_DIR.R,Tank.WUDI[i+21]);
						}
						
						if(i%7==0){
							iPP = true;
							count--;
						}else if(i%7==5){
							iPP = false;
						}
							
						if(iPP){
							i++;
						}else{
							i--;
						}
					}else{
						Tank.imgMap0.clear();
						
						Tank.imgMap0.put(Tank.TANK_DIR.U,Tank.WUDI[i-1]);
						Tank.imgMap0.put(Tank.TANK_DIR.D,Tank.WUDI[i+6]);
						Tank.imgMap0.put(Tank.TANK_DIR.L,Tank.WUDI[i+13]);
						Tank.imgMap0.put(Tank.TANK_DIR.R,Tank.WUDI[i+20]);
						
						last--;
					}
					
					if(count==0){
						nearOver = true;
					}
					
					if(last==0){
						isFinish = true;
					}
				}
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		Tank.imgMap0.clear();
		switch(ut.level)
		{
		case 0:
		case 1:
			Tank.imgMap0.put(Tank.TANK_DIR.U,Tank.TANKBODY0[0]);
			Tank.imgMap0.put(Tank.TANK_DIR.D,Tank.TANKBODY0[1]);
			Tank.imgMap0.put(Tank.TANK_DIR.L,Tank.TANKBODY0[2]);
			Tank.imgMap0.put(Tank.TANK_DIR.R,Tank.TANKBODY0[3]);
			break;
		case 2:
		case 3:
			Tank.imgMap0.put(Tank.TANK_DIR.U,Tank.TANKBODY0[4]);
			Tank.imgMap0.put(Tank.TANK_DIR.D,Tank.TANKBODY0[5]);
			Tank.imgMap0.put(Tank.TANK_DIR.L,Tank.TANKBODY0[6]);
			Tank.imgMap0.put(Tank.TANK_DIR.R,Tank.TANKBODY0[7]);
			break;
		case 4:
		case 5:
			Tank.imgMap0.put(Tank.TANK_DIR.U,Tank.TANKBODY0[8]);
			Tank.imgMap0.put(Tank.TANK_DIR.D,Tank.TANKBODY0[9]);
			Tank.imgMap0.put(Tank.TANK_DIR.L,Tank.TANKBODY0[10]);
			Tank.imgMap0.put(Tank.TANK_DIR.R,Tank.TANKBODY0[11]);
			break;
		case 6:
		case 7:
			Tank.imgMap0.put(Tank.TANK_DIR.U,Tank.TANKBODY0[12]);
			Tank.imgMap0.put(Tank.TANK_DIR.D,Tank.TANKBODY0[13]);
			Tank.imgMap0.put(Tank.TANK_DIR.L,Tank.TANKBODY0[14]);
			Tank.imgMap0.put(Tank.TANK_DIR.R,Tank.TANKBODY0[15]);
			break;
			default:break;
		}
		ut.isWuDi = false;
	}
}


class UserTank extends Tank  //由玩家自主控制的Tank
{
	public static int HEALTHPOINT=300;
	
	LinkedList<TANK_DIR> keyList=new LinkedList<TANK_DIR>(); //存储用户所按的方向键
	
	public UserTank(int x,int y,int level,TANK_DIR dir,boolean camp,FightPanel tc)
	{
		super(x,y,level,dir,camp,tc);
		this.isNPC=false;
		this.tankHealthPoint=new TankHealthPoint(HEALTHPOINT,this);
		keyList.add(TANK_DIR.STOP);
		this.TANKSPEED=6;
		this.turretX=x+13;
		this.turretY=y-5;
	}

	public void draw(Graphics g)
	{
		super.draw(g);
	}
	
	public void fire() //开火
	{
		allowFire = false;
		ismove = false;
		switch(level)
		{
		case 4:
		case 5:
			if(Status.getStage()==3)
			{
				fightPanel.shotsList.add(new LightShot(level,getAltitudeRect(),this,tankDir));
			}else{
				fightPanel.shotsList.add(new LightShot(level,1,this,tankDir));
			}
			break;
		default:
			if(Status.getStage()==3)
			{
				fightPanel.shotsList.add(new NormalShot(level,getAltitudeRect(),this,tankDir));
			}else{
				fightPanel.shotsList.add(new NormalShot(level,1,this,tankDir));
			}
			break;
		}
	}
	
	public void rebirth()  //复活
	{
		if(Status.cutPlay1_life()){
			this.level=0;
			this.x=200;
			this.y=500;
			this.setTurretXY(x+13,y-5);
			this.tankDir=TANK_DIR.U;
			this.setMoveDir(TANK_DIR.STOP);
			
			Tank.imgMap0.clear();
			Tank.imgMap0.put(TANK_DIR.U,Tank.TANKBODY0[0]);
			Tank.imgMap0.put(TANK_DIR.D,Tank.TANKBODY0[1]);
			Tank.imgMap0.put(TANK_DIR.L,Tank.TANKBODY0[2]);
			Tank.imgMap0.put(TANK_DIR.R,Tank.TANKBODY0[3]);
			
			FireBomb.init = false;
			SuperShotBomb.init = false;
			
			this.keyList.clear();
			this.tankHealthPoint.raisesHitPoint(HEALTHPOINT); //回满血
			this.tankHealthPoint.setTopHealthPoint(HEALTHPOINT); 
			this.isLive=true;
			this.isWuDi = true;
			new Thread(new WuDi(this,true)).start();
		}else{
			
			System.out.println("GAME OVER");
			
			fightPanel.roadBlockList.clear();
			fightPanel.itemList.clear();
			fightPanel.shotsList.clear();
			FightPanel.delvesList.clear();
			Tank tk;
			for(int i=0;i<fightPanel.tanksList.size();i++)
			{
				tk=fightPanel.tanksList.get(i);
				tk.isLive=false;
			}
			fightPanel.tanksList.clear();
			fightPanel.gameOver=true;
		}
	}
	
	public void hitDispose() //处理与基地、Tank的碰撞 
	{
		Tank tempTank;
		for(int i=0;i<fightPanel.tanksList.size();i++)  //Tank碰撞检测
		{
			tempTank=fightPanel.tanksList.get(i);
			if(tempTank==this||!tempTank.isLive)
			{ continue; } //Tank是自己或对方是死Tank则不操作

			if(this.getMoveRect().intersects(tempTank.getMoveRect())) 
			{
				double distance = Math.sqrt((x-tempTank.x)*(x-tempTank.x) + (y-tempTank.y)*(y-tempTank.y));
				double oldDistance = Math.sqrt((oldx-tempTank.x)*(oldx-tempTank.x) + (oldy-tempTank.y)*(oldy-tempTank.y));
				if(distance>=oldDistance)
				{
					continue;
				}
				switch(moveDir)
				{
					case U:
					case D:{y=oldy;}break;
					case L:
					case R:{x=oldx;}break;
					default:break;
				}
				this.setMoveDir(TANK_DIR.STOP);
			}
		}
		
		Base base = fightPanel.base;
		double distance = Math.sqrt((x-base.x)*(x-base.x) + (y-base.y)*(y-base.y));
		double oldDistance = Math.sqrt((oldx-base.x)*(oldx-base.x) + (oldy-base.y)*(oldy-base.y));
		
		if(this.getMoveRect().intersects(base.getMoveRect()) && distance<oldDistance) 
		{
			switch(moveDir)
			{
				case U:
				case D:{y=oldy;}break;
				case L:
				case R:{x=oldx;}break;
				default:break;
			}
			this.setMoveDir(TANK_DIR.STOP);
		}
	}
	
	public void keyDispose(int Key,boolean b)  //对用户按键的处理
	{
		if(b)  //有键按下
		{
			switch(Key)
			{
				case KeyEvent.VK_UP:
				case KeyEvent.VK_W:
				{
					keyList.remove(TANK_DIR.U);
					keyList.add(TANK_DIR.U);
				}break;
				case KeyEvent.VK_DOWN:
				case KeyEvent.VK_S:
				{
					keyList.remove(TANK_DIR.D);
					keyList.add(TANK_DIR.D);
				}break;
				case KeyEvent.VK_LEFT:
				case KeyEvent.VK_A:
				{
					keyList.remove(TANK_DIR.L);
					keyList.add(TANK_DIR.L);
				}break;
				case KeyEvent.VK_RIGHT:
				case KeyEvent.VK_D:
				{
					keyList.remove(TANK_DIR.R);
					keyList.add(TANK_DIR.R);
				}break;
				default:break;
			}
		}
		else  //有键抬起
		{
			switch(Key)
			{
				case KeyEvent.VK_UP:
				case KeyEvent.VK_W:{ keyList.remove(TANK_DIR.U); }break;
				case KeyEvent.VK_DOWN:
				case KeyEvent.VK_S:{ keyList.remove(TANK_DIR.D); }break;
				case KeyEvent.VK_LEFT:
				case KeyEvent.VK_A:{ keyList.remove(TANK_DIR.L); }break;
				case KeyEvent.VK_RIGHT:
				case KeyEvent.VK_D:{ keyList.remove(TANK_DIR.R); }break;
				default:break;
			}
		}
		if(keyList.size()==0) 
		{
			keyList.add(TANK_DIR.STOP); 
		}
		
		super.setMoveDir(keyList.get(keyList.size()-1));
	}
}

class RobotTank extends Tank  //电脑控制的Tank
{
	int hangfire = 0; // 延时开火

	public RobotTank(int x, int y, int level, TANK_DIR dir, boolean camp, FightPanel fp)
	{
		super(x,y,level,dir,camp,fp);
		
		this.iniShotLocation();
		
		this.isNPC=true;
		this.level = level;
		
		switch(level)
		{
		case 0:
			this.TANKSPEED=6;
			switch(tankDir)
			{
				case U:{this.setTurretXY(x+13,y-5);}break;
				case D:{this.setTurretXY(x+12,y+26);}break;
				case L:{this.setTurretXY(x-15,y+14);}break;
				case R:{this.setTurretXY(x+38,y+13);}break;
				default:break;
			}
			this.tankHealthPoint=new TankHealthPoint(300,this);
			break;
		case 1:
			this.TANKSPEED=4;
			switch(tankDir)
			{
				case U:{this.setTurretXY(x+13,y-5);}break;
				case D:{this.setTurretXY(x+12,y+26);}break;
				case L:{this.setTurretXY(x-15,y+14);}break;
				case R:{this.setTurretXY(x+38,y+13);}break;
				default:break;
			}
			this.tankHealthPoint=new TankHealthPoint(450,this);
			break;
		case 2:
			this.TANKSPEED=6;
			switch(tankDir)
			{
			case U:{this.setTurretXY(x+12,y-8);}break;
			case D:{this.setTurretXY(x+13,y+32);}break;
			case L:{this.setTurretXY(x-23,y+14);}break;
			case R:{this.setTurretXY(x+49,y+14);}break;
			default:break;
			}
			this.tankHealthPoint=new TankHealthPoint(600,this);
			break;
		case 3:
			this.TANKSPEED=4;
			switch(tankDir)
			{
				case U:{this.setTurretXY(x+12,y-8);}break;
				case D:{this.setTurretXY(x+13,y+32);}break;
				case L:{this.setTurretXY(x-23,y+14);}break;
				case R:{this.setTurretXY(x+49,y+14);}break;
				default:break;
			}
			this.tankHealthPoint=new TankHealthPoint(700,this);
			break;
		case 4:
			this.TANKSPEED=6;
			switch(tankDir)
			{
				case U:{this.setTurretXY(x+14,y-8);}break;
				case D:{this.setTurretXY(x+13,y+12);}break;
				case L:{this.setTurretXY(x+6,y);}break;
				case R:{this.setTurretXY(x+23,y+2);}break;
				default:break;
			}
			this.tankHealthPoint=new TankHealthPoint(800,this);
			break;
		case 5:
			this.TANKSPEED=5;
			switch(tankDir)
			{
				case U:{this.setTurretXY(x+14,y-8);}break;
				case D:{this.setTurretXY(x+13,y+12);}break;
				case L:{this.setTurretXY(x+6,y);}break;
				case R:{this.setTurretXY(x+23,y+2);}break;
				default:break;
			}
			this.tankHealthPoint=new TankHealthPoint(900,this);
			break;
		case 6:
			this.TANKSPEED=6;
			switch(tankDir)
			{
				case U:{this.setTurretXY(x+9,y-10);this.setTurretXY2(x+16,y-10);}break;
				case D:{this.setTurretXY(x+16,y+32);this.setTurretXY2(x+9,y+32);}break;
				case L:{this.setTurretXY(x-20,y+13);this.setTurretXY2(x-20,y+9);}break;
				case R:{this.setTurretXY(x+45,y+9);this.setTurretXY2(x+45,y+13);}break;
				default:break;
			}
			this.tankHealthPoint=new TankHealthPoint(1000,this);
			break;
		case 7:
			this.TANKSPEED=5;
			switch(tankDir)
			{
				case U:{this.setTurretXY(x+9,y-10);this.setTurretXY2(x+16,y-10);}break;
				case D:{this.setTurretXY(x+16,y+32);this.setTurretXY2(x+9,y+32);}break;
				case L:{this.setTurretXY(x-20,y+13);this.setTurretXY2(x-20,y+9);}break;
				case R:{this.setTurretXY(x+45,y+9);this.setTurretXY2(x+45,y+13);}break;
				default:break;
			}
			this.tankHealthPoint=new TankHealthPoint(1200,this);
			break;
			default:break;
		}
		
		switch(Tank.rn.nextInt(3)) //tank出现的位置随机
		{
		case 0:
			isCarryGift = true;
			break;
		default:break;
		}
	}
	
	public void iniShotLocation()
	{
		switch(level)
		{
			case 0:
			case 1:
			{ 
				switch(tankDir)
				{
					case U:{this.setTurretXY(x+13,y-5);}break;
					case D:{this.setTurretXY(x+12,y+26);}break;
					case L:{this.setTurretXY(x-15,y+14);}break;
					case R:{this.setTurretXY(x+38,y+13);}break;
					default:break;
				}
			}break;
			case 2:
			case 3:
			{ 
				switch(tankDir)
				{
					case U:{this.setTurretXY(x+12,y-8);}break;
					case D:{this.setTurretXY(x+13,y+32);}break;
					case L:{this.setTurretXY(x-23,y+14);}break;
					case R:{this.setTurretXY(x+49,y+14);}break;
					default:break;
				}
			}break;
			case 4:
			case 5:
			{
				switch(tankDir)
				{
					case U:{this.setTurretXY(x+14,y-8);}break;
					case D:{this.setTurretXY(x+13,y+12);}break;
					case L:{this.setTurretXY(x+6,y);}break;
					case R:{this.setTurretXY(x+23,y+2);}break;
					default:break;
				}
			}break;
			case 6:
			case 7:
			{ 
				switch(tankDir)
				{
					case U:{this.setTurretXY(x+9,y-10);this.setTurretXY2(x+16,y-10);}break;
					case D:{this.setTurretXY(x+16,y+32);this.setTurretXY2(x+9,y+32);}break;
					case L:{this.setTurretXY(x-20,y+13);this.setTurretXY2(x-20,y+9);}break;
					case R:{this.setTurretXY(x+45,y+9);this.setTurretXY2(x+45,y+13);}break;
					default:break;
				}
			}break;
			default:break;
		}
	}

	public void draw(Graphics g)
	{
		super.draw(g);
	}
	
	public void fire() //开火
	{
		allowFire = false;
		ismove = false;
		switch(level)
		{
		case 4:
		case 5:
			if(Status.getStage()==3)
			{
				fightPanel.shotsList.add(new LightShot(level,getAltitudeRect(),this,tankDir));
			}else{
				fightPanel.shotsList.add(new LightShot(level,1,this,tankDir));
			}
			break;
		default:
			if(Status.getStage()==3)
			{
				fightPanel.shotsList.add(new NormalShot(level,getAltitudeRect(),this,tankDir));
			}else{
				fightPanel.shotsList.add(new NormalShot(level,1,this,tankDir));
			}
			break;
		}
	}
	
	public void hitDispose() //处理与Tank，与基地的碰撞 
	{
		Tank tempTank;
		for(int i=0;i<fightPanel.tanksList.size();i++)
		{
			tempTank=fightPanel.tanksList.get(i);
			if(tempTank==this||!tempTank.isLive)
			{ continue; } //Tank是自己或敌方Tank已死则不操作

			if(this.getMoveRect().intersects(tempTank.getMoveRect())) 
			{
				double distance = Math.sqrt((x-tempTank.x)*(x-tempTank.x) + (y-tempTank.y)*(y-tempTank.y));
				double oldDistance = Math.sqrt((oldx-tempTank.x)*(oldx-tempTank.x) + (oldy-tempTank.y)*(oldy-tempTank.y));
				if(distance>=oldDistance)
				{
					continue;
				}
				switch(moveDir)
				{
					case U:
					case D:{y=oldy;}break;
					case L:
					case R:{x=oldx;}break;
					default:break;
				}
				
				switch(moveDir)
				{
				case U:
					switch(Tank.rn.nextInt(3))
					{
					case 0:	moveDir = TANK_DIR.D;break;
					case 1:moveDir = TANK_DIR.L;break;
					case 2:moveDir = TANK_DIR.R;break;
					default:break;
					}
					break;
				case D:
					switch(Tank.rn.nextInt(3))
					{
					case 0:moveDir = TANK_DIR.U;break;
					case 1:moveDir = TANK_DIR.L;break;
					case 2:moveDir = TANK_DIR.R;break;
					default:break;
					}
					break;
				case L:
					switch(Tank.rn.nextInt(3))
					{
					case 0:moveDir = TANK_DIR.U;break;
					case 1:moveDir = TANK_DIR.D;break;
					case 2:moveDir = TANK_DIR.R;break;
					default:break;
					}
					break;
				case R:
					switch(Tank.rn.nextInt(3))
					{
					case 0:moveDir = TANK_DIR.U;break;
					case 1:moveDir = TANK_DIR.D;break;
					case 2:moveDir = TANK_DIR.L;break;
					default:break;
					}
					break;
				default:break;
				}
			}
		}
		
		Base base = fightPanel.base;
		double distance = Math.sqrt((x-base.x)*(x-base.x) + (y-base.y)*(y-base.y));
		double oldDistance = Math.sqrt((oldx-base.x)*(oldx-base.x) + (oldy-base.y)*(oldy-base.y));
		
		if(this.getMoveRect().intersects(base.getMoveRect()) && distance<oldDistance) 
		{
			switch(moveDir)
			{
				case U:
				case D:{y=oldy;}break;
				case L:
				case R:{x=oldx;}break;
				default:break;
			}
			
			switch(moveDir)
			{
			case U:
				switch(Tank.rn.nextInt(3))
				{
				case 0:	moveDir = TANK_DIR.D;break;
				case 1:moveDir = TANK_DIR.L;break;
				case 2:moveDir = TANK_DIR.R;break;
				default:break;
				}
				break;
			case D:
				switch(Tank.rn.nextInt(3))
				{
				case 0:moveDir = TANK_DIR.U;break;
				case 1:moveDir = TANK_DIR.L;break;
				case 2:moveDir = TANK_DIR.R;break;
				default:break;
				}
				break;
			case L:
				switch(Tank.rn.nextInt(3))
				{
				case 0:moveDir = TANK_DIR.U;break;
				case 1:moveDir = TANK_DIR.D;break;
				case 2:moveDir = TANK_DIR.R;break;
				default:break;
				}
				break;
			case R:
				switch(Tank.rn.nextInt(3))
				{
				case 0:moveDir = TANK_DIR.U;break;
				case 1:moveDir = TANK_DIR.D;break;
				case 2:moveDir = TANK_DIR.L;break;
				default:break;
				}
				break;
			default:break;
			}
		}
	}
	
	public void autoAction()
	{
		switch(level)
		{
		case 4:
		case 5:
			if(hangfire==400)
			{
				fire();
				hangfire = 0;
			}
			hangfire++;
			break;
		default:
			if(allowFire)
			{
				fire();
			}
			break;	
		}
		super.move();
	}
	
//	public boolean isHitWall(Rectangle r)  //是否碰墙,躲避炮弹时用于辅助判断往哪个方向躲合适
//	{
//		Wall w=null;
//		for(int i=0;i<tc.wallList.size();i++)
//		{
//			w=tc.wallList.get(i);
//			if(w.isHits(r))
//			{
//				return true;
//			}
//		}
//		return false;
//	}
//	
//	public void avoidThrust(Shot s)  //中弹时躲避
//	{	
//		if(shotdir!=Math.abs(s.lengthY)/(Math.abs(s.lengthX)+0.001))//子弹角度改变时改变躲避的方向
//		{
//			if(Math.abs((double)s.lengthY)/(Math.abs(s.lengthX)+0.001)>3)
//			{
//				if(x+30>TankClient.WIN_WIDTH||isHitWall(new Rectangle(x+20,y-20,10,40)))
//				{ setMoveDir(Tank.TANK_DIR.L); }
//				else if(x-30<0||isHitWall(new Rectangle(x-30,y-20,10,40)))
//				{ setMoveDir(Tank.TANK_DIR.R); }
//				else
//				{
//					int change=Tank.rn.nextInt(2);
//					switch(change)
//					{
//						case 1:{ setMoveDir(Tank.TANK_DIR.L); }break;
//						case 0:{ setMoveDir(Tank.TANK_DIR.R); }break;
//						default: break;
//					}
//				}
//			}
//			else if(Math.abs((double)s.lengthY)/(Math.abs(s.lengthX)+0.001)<0.3)
//			{
//				if(y+30>TankClient.WIN_HEIGHT||isHitWall(new Rectangle(x-20,y+20,40,10)))
//				{ setMoveDir(Tank.TANK_DIR.U); }
//				else if(y-30<0||isHitWall(new Rectangle(x-20,y-30,40,10)))
//				{ setMoveDir(Tank.TANK_DIR.D); }
//				else
//				{
//					int change=Tank.rn.nextInt(2);
//					switch(change)
//					{
//						case 1:{ setMoveDir(Tank.TANK_DIR.U); }break;
//						case 0:{ setMoveDir(Tank.TANK_DIR.D); }break;
//						default: break;
//					}
//				}
//			}
//			else
//			{	
//				int change=Tank.rn.nextInt(4);
//				
//				switch(change)
//				{
//					case 3:{ setMoveDir(Tank.TANK_DIR.U); }break;
//					case 2:{ setMoveDir(Tank.TANK_DIR.D); }break;
//					case 1:{ setMoveDir(Tank.TANK_DIR.L); }break;
//					case 0:{ setMoveDir(Tank.TANK_DIR.R); }break;
//					default: break;
//				}
//			}
//			this.move();
//			
//			shotdir=Math.abs(s.lengthY)/(Math.abs(s.lengthX)+0.001);
//		}		
//	}

}