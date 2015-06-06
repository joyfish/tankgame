package data;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

abstract class Shot
{
	int shotSpeed;  //子弹移动速度
	int shotPower;  //子弹威力
	int ox, oy, sx, sy;  //Tank的坐标,s表示炮弹坐标(初始时为炮筒端点)
	int tankLevel;
	int altitude;//子弹的海拔高度
	boolean isLive;
	boolean isBomb;  //死时是否产生爆炸
	boolean isFire = true;
	boolean isBombWithoutDelve;//子弹碰到tank、基地、障碍物时，爆炸后不产生弹坑
	boolean isFirst = true;//天啓只为第一颗子弹爆炸完成2/3时创建第二颗子弹
		
	List<RangeEnemyTank> rangeEnemyTank = Collections.synchronizedList(new ArrayList<RangeEnemyTank>());//存储光稜子弹爆炸点X圆形范围内的敌方坦克
	List<Tank> rETank = Collections.synchronizedList(new ArrayList<Tank>());//存储光稜子弹爆炸点X圆形范围内的敌方坦克-灯光束结束处理tank减血
	
	FightPanel fightPanel=null;
	Tank tk=null;
	ShotBomb shotBomb=null;
	FireBomb fireBomb=null;
	
	public static enum TANK_DIR {U,D,L,R,STOP};
	TANK_DIR tankDir;   //Tank主体的方向
	
	public static final Toolkit TLK=Toolkit.getDefaultToolkit();
	
//	public Shot(int ox,int oy,int sx,int sy,Tank tk)
//	{
//		this.ox=ox;
//		this.oy=oy;
//		this.sx=sx;
//		this.sy=sy;
//		this.fightPanel=tk.fightPanel;
//		this.tk=tk;
//		this.isLive=true;
//	}
//	
	public Shot(int tankLevel,int altitude,Tank tk,Tank.TANK_DIR tankDir)
	{
		this.ox=tk.x;
		this.oy=tk.y;
		this.sx=tk.turretX;
		this.sy=tk.turretY;
		this.fightPanel=tk.fightPanel;
		this.tk=tk;
		this.isLive=true;
		this.tankLevel=tankLevel;
		this.altitude=altitude;
		switch(tankDir)
		{
		case U:
			this.tankDir = TANK_DIR.U;
			break;
		case D:
			this.tankDir = TANK_DIR.D;
			break;
		case L:
			this.tankDir = TANK_DIR.L;
			break;
		case R:
			this.tankDir = TANK_DIR.R;
			break;
			default:break;
		}
	}

	abstract void draw(Graphics g);

	abstract void move();

	abstract void dead();

	public boolean isOutOfWindow()  //检查子弹是否跃出边界
	{
		if(sx>670||sx<5||sy>557||sy<5)
		{
			return true;
		}
		else{
			return false;
		}
	}

	public boolean isHitTank()  //检查炮弹是否击中对方Tank
	{
		Tank tempTk=null;
		for(int i=0;i<tk.fightPanel.tanksList.size();i++)
		{
			tempTk=tk.fightPanel.tanksList.get(i);

			if(tempTk.isLive&&getRect().intersects(tempTk.getRect())&&tempTk.camp!=tk.camp)
			{
				this.isBombWithoutDelve=true;
				if(!tempTk.isWuDi)
				{
					tempTk.tankHealthPoint.cutsHitPoint(shotPower,this);  //减血、控制tank的死亡
				}
				this.isBomb=true;
				return true;
			}
		}
		this.isBombWithoutDelve=false;
		return false;  //未打中任何Tank
	}
	
	public boolean isHitBase()  //检查炮弹是否击中基地
	{
		Base base = fightPanel.base;

		if(base.isLive && getRect().intersects(base.getRect()))
		{
//			System.out.println("111");
			this.isBombWithoutDelve=true;
			base.baseHealthPoint.cutsHitPoint(shotPower,this);  //减血、控制tank的死亡
			//System.out.println(shotPower);
			this.isBomb=true;
			return true;
		}
		
		this.isBombWithoutDelve=false;
		return false;  //未打中基地
	}
	
	public boolean isHitRoadBlock()
	{
		RoadBlock rd = null;

		for(int i=0;i<fightPanel.roadBlockList.size();i++)
		{
			rd = fightPanel.roadBlockList.get(i);
			if(rd.isHits(this))
			{
				this.isBombWithoutDelve=true;
				return true;
			}
		}
		return false;
	}
	
	public Rectangle getRect() //碰撞检测时用于判断
	{
		if(tankLevel==4 || tankLevel==5)
		{
			if(tankDir==TANK_DIR.U || tankDir==TANK_DIR.D)
			{
				return new Rectangle(sx-1,sy-1,3,3);
			}else{
				return new Rectangle(sx-2,sy-2,5,5);
			}
		}else{
			return new Rectangle(sx-1,sy-1,3,3);
		}
	}
	
	public Rectangle getHitWallRect() //碰撞检测时用于判断
	{
		int rX=0,rY=0,rWidth=0,rHeight=0;
		switch(tankLevel)
		{
			case 0:
			case 1:
				if(tk.camp)
				{
					switch(tankDir)
					{
						case U:
							rX = -18;
							rY = 0;
							rWidth = 38;
							rHeight = 3;
							break;
						case D:
							rX = -17;
							rY = 0;
							rWidth = 38;
							rHeight = 3;
							break;
						case L:
							rX = 0;
							rY = -9;
							rWidth = 3;
							rHeight = 33;
							break;
						case R:
							rX = -1;
							rY = -8;
							rWidth = 3;
							rHeight = 33;
							break;
						default:break;
					}
				}else{
					switch(tankDir)
					{
						case U:
							rX = -20;
							rY = 0;
							rWidth = 42;
							rHeight = 3;
							break;
						case D:
							rX = -19;
							rY = 0;
							rWidth = 42;
							rHeight = 3;
							break;
						case L:
							rX = 0;
							rY = -6;
							rWidth = 3;
							rHeight = 34;
							break;
						case R:
							rX = 0;
							rY = -5;
							rWidth = 3;
							rHeight = 34;
							break;
						default:break;
					}
				}
				break;
			case 2:
			case 3:
				switch(tankDir)
				{
					case U:
						rX = -18;
						rY = 0;
						rWidth = 40;
						rHeight = 3;
						break;
					case D:
						rX = -18;
						rY = 0;
						rWidth = 39;
						rHeight = 3;
						break;
					case L:
						rX = 0;
						rY = -8;
						rWidth = 3;
						rHeight = 36;
						break;
					case R:
						rX = 0;
						rY = -8;
						rWidth = 3;
						rHeight = 36;
						break;
					default:break;
				}
				break;
			case 4:
			case 5:
				switch(tankDir)
				{
					case U:
						rX = -21;
						rY = 0;
						rWidth = 42;
						rHeight = 3;
						break;
					case D:
						rX = -20;
						rY = 0;
						rWidth = 42;
						rHeight = 3;
						break;
					case L:
						rX = 0;
						rY = -8;
						rWidth = 3;
						rHeight = 47;
						break;
					case R:
						rX = 0;
						rY = -10;
						rWidth = 3;
						rHeight = 47;
						break;
					default:break;
				}
				break;
			case 6:
			case 7:
				switch(tankDir)
				{
					case U:
						rX = -16;
						rY = 0;
						rWidth = 35;
						rHeight = 3;
						break;
					case D:
						rX = -16;
						rY = -1;
						rWidth = 35;
						rHeight = 3;
						break;
					case L:
						rX = 0;
						rY = -8;
						rWidth = 3;
						rHeight = 34;
						break;
					case R:
						rX = 0;
						rY = -8;
						rWidth = 3;
						rHeight = 34;
						break;
					default:break;
				}
				break;
			default:break;
		}
		return new Rectangle(sx+rX,sy+rY,rWidth,rHeight);
	}
}

class NormalShot extends Shot  //普通炮弹
{
	int oldx,oldy;  //起始坐标
	int moveRange;  //发射后移动此距离后爆炸
	int SHOTTYPE; //锁定子弹类型
	
	static final Image NORMALSHOT=TLK.getImage(Shot.class.getClassLoader().getResource("images/shot/bullet.png"));

	public NormalShot(int tankLevel,int altitude,Tank tk,Tank.TANK_DIR tankDir)
	{
		super(tankLevel,altitude,tk,tankDir);
		switch(tk.level)
		{
		case 0:
			this.shotSpeed=4;
			this.shotPower=400;
			this.moveRange=150;
			break;
		case 1:
			this.shotSpeed=4;
			this.shotPower=450;
			this.moveRange=180;
			break;
		case 2:
			this.shotSpeed=5;
			this.shotPower=600;
			this.moveRange=210;
			break;
		case 3:
			this.shotSpeed=5;
			this.shotPower=650;
			this.moveRange=240;
			break;
		case 6:
			this.shotSpeed=6;
			this.shotPower=800;
			this.moveRange=360;
			break;
		case 7:
			this.shotSpeed=6;
			this.shotPower=850;
			this.moveRange=420;
			break;
			default:break;
		}
		
		
		//this.shotRadius=4;
		
		this.oldx=sx;
		this.oldy=sy;
//		System.out.println("NormalShot"+tk.x);
//		System.out.println("NormalShot"+tk.turretX);
	}

	public NormalShot(int tankLevel,int altitude,Tank tk,Tank.TANK_DIR tankDir,boolean isFirst)
	{
		super(tankLevel,altitude,tk,tankDir);
		this.sx=tk.turretX2;
		this.sy=tk.turretY2;
		
		switch(tk.level)
		{
		case 6:
			this.shotSpeed=4;
			this.shotPower=400;
			this.moveRange=360;
			break;
		case 7:
			this.shotSpeed=4;
			this.shotPower=450;
			this.moveRange=420;
			break;
			default:break;
		}
		
		this.oldx=sx;
		this.oldy=sy;
		this.isFirst=isFirst;
	}
	
	public void move()
	{
		if(isFire)
		{
			isFire = false;
			fireBomb = new FireBomb(ox, oy, sx, sy, this);
			
			switch(tk.level)
			{
			case 0:
			case 2:
				SHOTTYPE=1;
				break;
			case 1:
			case 3:
				SHOTTYPE=2;
				break;
			case 4:
			case 5:
				SHOTTYPE=3;
				break;
			case 6:
				SHOTTYPE=4;
				break;
			case 7:
				SHOTTYPE=5;
				break;
				default:break;
			}
			
		}
		
//		System.out.println("11");
		if(isLive)
		{
			if(sy<oy)
			{
				sy -= shotSpeed;
			}else if(sy>oy+20)
			{
				sy += shotSpeed;
			}else if(sx<ox)
			{
				sx -= shotSpeed;
			}else if(sx>ox+30)
			{
				sx += shotSpeed;
			}

			if(isOutOfWindow() || (int)Math.abs(sx-oldx)>moveRange || (int)Math.abs(sy-oldy)>moveRange || isHitTank() || isHitBase() || isHitRoadBlock())  //子弹爆炸条件
			{
				this.isBomb=true;
				this.dead();
			}
		}
	}
	
	public void dead()
	{
		if(isLive)
		{
			this.isLive=false;
			
			//当到达射程、撞到墙或Tank时子弹才爆炸
			if(isBomb)
			{
				shotBomb=new SuperShotBomb(sx,sy,SHOTTYPE,isBombWithoutDelve,this,tk);
			}
			else
			{
				this.fightPanel.shotsList.remove(this);
			}
			
			//System.out.println("over..");
		}
	}
	
	public void draw(Graphics g)
	{
		//System.out.println("draw");
		
		if(fireBomb != null)
		{
			fireBomb.draw(g);
		}
		
		if(isLive)
		{
			g.drawImage(NORMALSHOT,sx,sy,null); 
		}
		else
		{
			if(isBomb&&shotBomb!=null)
			{
				shotBomb.draw(g); 
			}
		}
	}
}


class LightShot extends Shot
{
	int oldx,oldy;  //起始坐标
	int moveRange;  //发射后移动此距离后爆炸
	int len;  //光稜光束实际长度
	int halfTankLength;//光束打到TANK时，光束长度加半个车身位
	boolean isExplore = true;  //检测光束射程内的障碍物
	
	List<ShotBomb> shotBombs = Collections.synchronizedList(new ArrayList<ShotBomb>());//存储光稜子弹在爆炸点X圆形范围内所有敌方坦克处的爆炸
	
	static final Image NORMALSHOT=TLK.getImage(Shot.class.getClassLoader().getResource("images/shot/bullet.png"));

	public LightShot(int tankLevel,int altitude,Tank tk,Tank.TANK_DIR tankDir)
	{
		super(tankLevel,altitude,tk,tankDir);
		switch(tk.level)
		{
		case 4:
			this.shotPower=500;
			this.moveRange=270;
			break;
		case 5:
			this.shotPower=600;
			this.moveRange=300;
			break;
			default:break;
		}
		
		this.oldx=sx;
		this.oldy=sy;
	}
	
//	public boolean isHitTankG()  //检查炮弹是否击中对方Tank
//	{
//		Tank tempTk=null;
//		for(int i=0;i<tk.fightPanel.tanksList.size();i++)
//		{
//			tempTk=tk.fightPanel.tanksList.get(i);
//
//			if(tempTk.isLive&&getRect().intersects(tempTk.getRect())&&tempTk.camp!=tk.camp)
//			{
//				this.isHitTank=true;
//				tempTk.tankHealthPoint.cutsHitPoint(shotPower,this);  //减血、控制tank的死亡
//				//System.out.println(shotPower);
//				this.isBomb=true;
//				return true;
//			}
//		}
//		this.isHitTank=false;
//		return false;  //未打中任何Tank
//	}
	
	public void dead()
	{
		if(isLive)
		{
			this.isLive=false;
			
			if(isBomb)
			{
				shotBombs.add(new SuperShotBomb(sx,sy,3,halfTankLength,isBombWithoutDelve,this,tk));
				
				RangeEnemyTank rET = null;
				for(int i=0;i<rangeEnemyTank.size();i++)
				{
					rET = rangeEnemyTank.get(i);
					shotBombs.add(new SuperShotBomb(rET.rETx,rET.rETy+12,3,halfTankLength,true,this,tk));
				}
			}
		}
	}
	
	public void move()
	{
		if(isExplore)//检测光束射程内的障碍物
		{
			isExplore = false;
				switch(tk.tankDir)
				{
				case U:
					for(int y=sy;(y-sy)<=moveRange; sy--)
					{
						if(isHitTank())
						{
							len = y-sy;
							halfTankLength = 17;  //光束打到TANK时，光束长度加半个车身位;
							getOval(sx,sy);
							break;
						}else if(isOutOfWindow())
						{
							len = y-sy;
							break;
						}else if(isHitBase())
						{
							len = y-sy;
							halfTankLength = 23;  //光束打到基地时，光束长度加半个基地长;
							break;
						}else if(isHitRoadBlock())
						{
							len = y-sy;
							halfTankLength = 8;  //光束打到墙时，光束长度加半个墙体长;
							break;
						}
						len = y-sy;
					}
					break;
				case D:
					for(int y=sy;(sy-y)<=moveRange; sy++)
					{
						if(isHitTank())
						{
							len = sy-y;
							halfTankLength = 17;  //光束打到TANK时，光束长度加半个车身位;
							getOval(sx,sy);
							break;
						}else if(isOutOfWindow())
						{
							sy += 12;
							len = sy-y;
							break;
						}else if(isHitBase())
						{
							len = sy-y;
							halfTankLength = 23;  //光束打到基地时，光束长度加半个基地长;
							break;
						}else if(isHitRoadBlock())
						{
							len = sy-y;
							halfTankLength = 8;  //光束打到墙时，光束长度加半个墙体长;
							break;
						}
						len = sy-y;
					}
					break;
				case L:
					for(int x=sx;(x-sx)<=moveRange; sx--)
					{
						if(isHitTank())
						{
							len = x-sx;
							halfTankLength = 20;  //光束打到TANK时，光束长度加半个车身位;
							getOval(sx,sy);
							break;
						}else if(isOutOfWindow())
						{
							len = x-sx;
							break;
						}else if(isHitBase())
						{
							len = x-sx;
							halfTankLength = 40; //光束打到基地时，光束长度加半个基地长;
							break;
						}else if(isHitRoadBlock())
						{
							len = x-sx;
							halfTankLength = 8;  //光束打到墙时，光束长度加半个墙体长;
							break;
						}
						len = x-sx;
					}
					break;
				case R:
					for(int x=sx;(sx-x)<=moveRange; sx++)
					{
						if(isHitTank())
						{
							len = sx-x;
							halfTankLength = 20;  //光束打到TANK时，光束长度加半个车身位;
							getOval(sx,sy);
							break;
						}else if(isOutOfWindow())
						{
							len = sx-x;
							break;
						}else if(isHitBase())
						{
							len = sx-x;
							halfTankLength = 40; //光束打到基地时，光束长度加半个基地长;
							break;
						}else if(isHitRoadBlock())
						{
							len = sx-x;
							halfTankLength = 8;  //光束打到墙时，光束长度加半个墙体长;
							break;
						}
						len = sx-x;
					}
					break;
					default:break;
				}
		}
		if(isFire)
		{
			isFire = false;
			fireBomb = new FireBomb(ox, oy, sx, sy, len, halfTankLength, this);
		}
		if(isLive)
		{
			this.isBomb=true;
			this.dead();
		}
		
	}
	
	public void draw(Graphics g)
	{
		if(fireBomb != null)
		{
			fireBomb.draw(g);
		}
		if(isBomb && !shotBombs.isEmpty())
		{
			ShotBomb sBm = null;
			for(int i=0;i<shotBombs.size();i++)
			{
				sBm = shotBombs.get(i);
				sBm.draw(g); 
			}
		}
	}
	public void getOval(int sx, int sy) //检测子弹爆炸点X圆形范围内是否有坦克
	{
		Tank tempTk=null;
		for(int i=0;i<tk.fightPanel.tanksList.size();i++)
		{
			tempTk=tk.fightPanel.tanksList.get(i);
			
			switch(tk.tankDir)
			{
			case U:
				if(tempTk.isLive && tempTk.camp!=tk.camp && !getRect().intersects(tempTk.getRect()) && Math.sqrt((tempTk.x+18-sx)*(tempTk.x-sx)+(tempTk.y+15-(sy-15))*(tempTk.y-(sy-15)))<=moveRange/2)
				{
					rETank.add(tempTk);
					//System.out.println(shotPower);
					rangeEnemyTank.add(new RangeEnemyTank(tempTk.x+15,tempTk.y+20));
				}
				break;
			case D:
				if(tempTk.isLive && tempTk.camp!=tk.camp && !getRect().intersects(tempTk.getRect()) && Math.sqrt((tempTk.x+18-sx)*(tempTk.x-sx)+(tempTk.y+15-(sy+17))*(tempTk.y-(sy+17)))<=moveRange/2)
				{
					rETank.add(tempTk);
					//System.out.println(shotPower);
					rangeEnemyTank.add(new RangeEnemyTank(tempTk.x+15,tempTk.y+25));
				}
				break;
			case L:
				if(tempTk.isLive && tempTk.camp!=tk.camp && !getRect().intersects(tempTk.getRect()) && Math.sqrt((tempTk.x+18-(sx-20))*(tempTk.x-(sx-20))+(tempTk.y+15-sy)*(tempTk.y-sy))<=moveRange/2)
				{
					rETank.add(tempTk);
					//System.out.println(shotPower);
					rangeEnemyTank.add(new RangeEnemyTank(tempTk.x+17,tempTk.y+23));
				}
				break;
			case R:
				if(tempTk.isLive && tempTk.camp!=tk.camp && !getRect().intersects(tempTk.getRect()) && Math.sqrt((tempTk.x+18-(sx+20))*(tempTk.x-(sx+20))+(tempTk.y+15-sy)*(tempTk.y-sy))<=moveRange/2)
				{
					rETank.add(tempTk);
					//System.out.println(shotPower);
					rangeEnemyTank.add(new RangeEnemyTank(tempTk.x+17,tempTk.y+23));
				}
				break;
				default: break;
			}
		}
	}
}

class RangeEnemyTank //存储光稜子弹爆炸点X圆形范围内的敌方坦克
{
	int rETx,rETy;
	public RangeEnemyTank(int rETx, int rETy)
	{
		this.rETx = rETx;
		this.rETy = rETy;
	}
}