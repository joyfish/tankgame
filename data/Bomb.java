package data;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

abstract class Bomb
{
	int x, y;
	int step = 0;
	int fireStep = 0;
	
	static final Toolkit TLK=Toolkit.getDefaultToolkit();

	public Bomb(int x,int y)
	{
		this.x=x;
		this.y=y;
	}

	abstract void draw(Graphics g);
}

class FireBomb extends Bomb
{
	int asd = 1;
	int tx, ty;//tank坐标
	int length;//光稜光束的长度
	int halfTankLength;//光束打到TANK时，光束长度加半个车身位
	int StepRET[] = new int[400];//辅助画出散开的光束
	boolean isFirst = false;//天啓只为第一颗子弹爆炸完成2/3时创建第二颗子弹
	Shot s;

	static boolean init=false; //第一次画是将图片载入内存
	public static final Image[] FIREBOMBIMGS_0= //level 0、2
	{
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/fire/0/1.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/fire/0/1.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/fire/0/2.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/fire/0/2.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/fire/0/2.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/fire/0/3.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/fire/0/3.png"))
	};
	public static final Image[] FIREBOMBIMGS_1= //level 1、3、7
	{
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/fire/1/1.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/fire/1/2.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/fire/1/3.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/fire/1/4.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/fire/1/5.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/fire/1/6.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/fire/1/7.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/fire/1/8.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/fire/1/9.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/fire/1/10.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/fire/1/11.png"))
	};
	public static final Image[] FIREBOMBIMGS_2= //level 4、5 U/D
	{
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/fire/2/1.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/fire/2/1.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/fire/2/1.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/fire/2/1.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/fire/2/1.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/fire/2/1.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/fire/2/1.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/fire/2/1.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/fire/2/1.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/fire/2/1.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/fire/2/1.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/fire/2/1.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/fire/2/2.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/fire/2/2.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/fire/2/3.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/fire/2/3.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/fire/2/4.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/fire/2/4.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/fire/2/5.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/fire/2/5.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/fire/2/6.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/fire/2/6.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/fire/2/7.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/fire/2/7.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/fire/2/8.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/fire/2/8.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/fire/2/9.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/fire/2/9.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/fire/2/10.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/fire/2/10.png"))
	};
	public static final Image[] FIREBOMBIMGS_3= //level 4、5 L/R
	{
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/fire/3/1.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/fire/3/1.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/fire/3/1.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/fire/3/1.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/fire/3/1.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/fire/3/1.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/fire/3/1.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/fire/3/1.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/fire/3/1.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/fire/3/1.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/fire/3/1.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/fire/3/1.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/fire/3/2.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/fire/3/2.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/fire/3/3.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/fire/3/3.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/fire/3/4.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/fire/3/4.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/fire/3/5.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/fire/3/5.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/fire/3/6.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/fire/3/6.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/fire/3/7.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/fire/3/7.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/fire/3/8.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/fire/3/8.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/fire/3/9.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/fire/3/9.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/fire/3/10.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/fire/3/10.png"))
	};
	public static final Image[] FIREBOMBIMGS_4= //level 6
	{
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/fire/4/1.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/fire/4/1.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/fire/4/2.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/fire/4/2.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/fire/4/3.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/fire/4/3.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/fire/4/4.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/fire/4/4.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/fire/4/5.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/fire/4/5.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/fire/4/6.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/fire/4/6.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/fire/4/7.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/fire/4/7.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/fire/4/8.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/fire/4/9.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/fire/4/10.png"))
	};
	
	public FireBomb(int tx, int ty, int x, int y, Shot s) {
		super(x, y);
		this.tx = tx;
		this.ty = ty;
		this.s = s;
	}
	public FireBomb(int tx, int ty, int x, int y, int len, int halfTankLength, Shot s) {
		super(x, y);
		this.tx = tx;
		this.ty = ty;
		this.s = s;
		this.length = len;
		this.halfTankLength = halfTankLength;
	}
	void draw(Graphics g)
	{
		if(!init)
		{
			for (int i=0;i<FIREBOMBIMGS_0.length;i++)
			{
				g.drawImage(FIREBOMBIMGS_0[i],1000,1000,null);
			}
			
			for (int i=0;i<FIREBOMBIMGS_1.length;i++)
			{
				g.drawImage(FIREBOMBIMGS_1[i],1000,1000,null);
			}
			
			for (int i=0;i<FIREBOMBIMGS_2.length;i++)
			{
				g.drawImage(FIREBOMBIMGS_2[i],1000,1000,null);
			}
			
			for (int i=0;i<FIREBOMBIMGS_3.length;i++)
			{
				g.drawImage(FIREBOMBIMGS_3[i],1000,1000,null);
			}
			
			for (int i=0;i<FIREBOMBIMGS_4.length;i++)
			{
				g.drawImage(FIREBOMBIMGS_4[i],1000,1000,null);
			}
			init = true;
		}
		
		switch(s.tk.getLevel())
		{
		case 0:
		case 2:
			if(fireStep == FIREBOMBIMGS_0.length)  //开火完成
			{
				s.fireBomb=null;
				s.tk.ismove = true;
//				Tank.allowPress = true;
				return;
			}
			
			if(s.tk.getLevel() == 0)
			{
				if(y<ty)
				{
					g.drawImage(FIREBOMBIMGS_0[fireStep],x-6,y-5,null);
				}else if(y>ty+20)
				{
					g.drawImage(FIREBOMBIMGS_0[fireStep],x-5,y-7,null);
				}else if(x<tx)
				{
					g.drawImage(FIREBOMBIMGS_0[fireStep],x-4,y-5,null);
				}else if(x>tx+30)
				{
					g.drawImage(FIREBOMBIMGS_0[fireStep],x-7,y-5,null);
				}
			}else{
				if(y<ty)
				{
					g.drawImage(FIREBOMBIMGS_0[fireStep],x-6,y-7,null);
				}else if(y>ty+20)
				{
					g.drawImage(FIREBOMBIMGS_0[fireStep],x-6,y-6,null);
				}else if(x<tx)
				{
					g.drawImage(FIREBOMBIMGS_0[fireStep],x-6,y-5,null);
				}else if(x>tx+30)
				{
					g.drawImage(FIREBOMBIMGS_0[fireStep],x-5,y-5,null);
				}
			}
			break;
		case 1:
		case 3:
			if(fireStep == FIREBOMBIMGS_1.length)  //开火完成
			{
				s.fireBomb=null;
				s.tk.ismove = true;
//				Tank.allowPress = true;
				return;
			}
			if(s.tk.getLevel() == 1)
			{
				if(y<ty)
				{
					g.drawImage(FIREBOMBIMGS_1[fireStep],x-17,y-20,null);
				}else if(y>ty+20)
				{
					g.drawImage(FIREBOMBIMGS_1[fireStep],x-17,y-27,null);
				}else if(x<tx)
				{
					g.drawImage(FIREBOMBIMGS_1[fireStep],x-15,y-24,null);
				}else if(x>tx+30)
				{
					g.drawImage(FIREBOMBIMGS_1[fireStep],x-19,y-24,null);
				}
			}else{
				if(y<ty)
				{
					g.drawImage(FIREBOMBIMGS_1[fireStep],x-17,y-23,null);
				}else if(y>ty+30)
				{
					g.drawImage(FIREBOMBIMGS_1[fireStep],x-17,y-25,null);
				}else if(x<tx)
				{
					g.drawImage(FIREBOMBIMGS_1[fireStep],x-16,y-24,null);
				}else if(x>tx+40)
				{
					g.drawImage(FIREBOMBIMGS_1[fireStep],x-19,y-24,null);
				}
			}
			break;
		case 4:
		case 5:
			if(fireStep == FIREBOMBIMGS_2.length/10)
			{
				Tank tempTk=null;
				for(int i=0;i<s.rETank.size();i++)
				{
					tempTk=s.rETank.get(i);
					tempTk.tankHealthPoint.cutsHitPoint(s.shotPower,s);  //减血、控制tank的死亡
				}
				s.rETank.clear();
			}
			if(fireStep == FIREBOMBIMGS_2.length)  //开火完成
			{
				s.fireBomb = null;
				s.tk.ismove = true;
				s.rangeEnemyTank.clear();
//				Tank.allowPress = true;
				return;
			}
			switch(s.tk.tankDir)
			{
			case U:
				g.drawImage(FIREBOMBIMGS_2[fireStep],x-2,y+3-halfTankLength,5,length+halfTankLength,null);
//				g.setColor(Color.white);
//				g.draw3DRect(x, y, 1, 1,false);
				break;
			case D:
				g.drawImage(FIREBOMBIMGS_2[fireStep],x-2,y-6-length,5,length+halfTankLength,null);
//				g.setColor(Color.white);
//				g.draw3DRect(x, y, 1, 1,false);
				break;
			case L:
				g.drawImage(FIREBOMBIMGS_3[fireStep],x-halfTankLength+2,y-1,length+halfTankLength,5,null);
//				g.setColor(Color.white);
//				g.draw3DRect(x, y, 1, 1,false);
				break;
			case R:
				g.drawImage(FIREBOMBIMGS_3[fireStep],x-length,y-1,length+halfTankLength,5,null);
//				g.setColor(Color.white);
//				g.draw3DRect(x, y, 1, 1,false);
				break;
				default:break;
			}
			if(!s.rangeEnemyTank.isEmpty())
			{
				RangeEnemyTank rET = null;
				int rETx,rETy;
				int hTLx=0,hTLy=0;//光束打到TANK时，光束末端加半个车身位
				
				switch(s.tk.tankDir)
				{
				case U:
					hTLy = -13;
					break;
				case D:
					hTLy = +17;
					break;
				case L:
					hTLx = -20;
					break;
				case R:
					hTLx = +20;
					break;
				default:break;
				}
				
				//System.out.println("sx  xy : "+s.sx+"  "+s.sy);
				for(int i=0; i<s.rangeEnemyTank.size(); i++)
				{
					rET = s.rangeEnemyTank.get(i);
					rETx = rET.rETx;
					rETy = rET.rETy;
					
//					System.out.println(s.rangeEnemyTank.size());
					
					if(rETy-(s.sy+hTLy)<0 && rETx-(s.sx+hTLx)<0 && ((s.sy+hTLy)-rETy)>((s.sx+hTLx)-rETx))//第八卦限
					{
//						System.out.println("第八卦限");
						
						double width = (s.sx+hTLx)-rETx;
						double heitht = (s.sy+hTLy)-rETy;
						
						int steprETy = (int)Math.round(heitht/width);
						
						stepRET(heitht, width, steprETy);
						
						for(int j=0;rETx<(s.sx+hTLx); rETx++, rETy+=StepRET[j],j++)
						{
							g.drawImage(FIREBOMBIMGS_2[fireStep],rETx,rETy,5,StepRET[j],null);
						}
					}else if(rETy-(s.sy+hTLy)<0 && rETx-(s.sx+hTLx)>0 && ((s.sy+hTLy)-rETy)>(rETx-(s.sx+hTLx)))//第一卦限
					{
//						System.out.println("第一卦限");
						double width = rETx-(s.sx+hTLx);
						double heitht = (s.sy+hTLy)-rETy;
						int steprETy = (int)Math.round(heitht/width);
						//System.out.println(steprETy);
						
						stepRET(heitht, width, steprETy);
						for(int j=0;rETx>(s.sx+hTLx); rETx--, rETy+=StepRET[j],j++)
						{
							g.drawImage(FIREBOMBIMGS_2[fireStep],rETx,rETy,5,StepRET[j],null);
						}
					}else if(rETy-(s.sy+hTLy)<0 && rETx-(s.sx+hTLx)>0 && ((s.sy+hTLy)-rETy)<(rETx-(s.sx+hTLx)))//第二卦限
					{
//						System.out.println("第二卦限");
						double width = rETx-(s.sx+hTLx);
						double heitht = (s.sy+hTLy)-rETy;
						int steprETx = (int)Math.round(width/heitht);
						//System.out.println(steprETy);
						
						stepRET(width, heitht, steprETx);
						for(int j=0;rETy<(s.sy+hTLy); rETx-=StepRET[j], rETy++,j++)
						{
							g.drawImage(FIREBOMBIMGS_3[fireStep],rETx,rETy,-StepRET[j],5,null);
						}
						//System.out.println("`````rETx  rETy : "+rETx+"  "+rETy);
					}else if(rETy-(s.sy+hTLy)>0 && rETx-(s.sx+hTLx)>0 && (rETy-(s.sy+hTLy))<(rETx-(s.sx+hTLx)))//第三卦限
					{
//						System.out.println("第三卦限");
						double width = rETx-(s.sx+hTLx);
						double heitht = rETy-(s.sy+hTLy);
						int steprETx = (int)Math.round(width/heitht);
						//System.out.println(steprETy);
						
						stepRET(width, heitht, steprETx);
						for(int j=0;rETy>(s.sy+hTLy); rETx-=StepRET[j], rETy--,j++)
						{
							g.drawImage(FIREBOMBIMGS_3[fireStep],rETx,rETy,-StepRET[j],5,null);
						}
						//System.out.println("`````rETx  rETy : "+rETx+"  "+rETy);
					}else if(rETy-(s.sy+hTLy)>0 && rETx-(s.sx+hTLx)>0 && (rETy-(s.sy+hTLy))>(rETx-(s.sx+hTLx)))//第四卦限
					{
//						System.out.println("第四卦限");
						double width = rETx-(s.sx+hTLx);
						double heitht = rETy-(s.sy+hTLy);
						int steprETy = (int)Math.round(heitht/width);
						//System.out.println(steprETy);
						
						stepRET(heitht, width, steprETy);
						for(int j=0;rETx>(s.sx+hTLx); rETx--, rETy-=StepRET[j],j++)
						{
							g.drawImage(FIREBOMBIMGS_2[fireStep],rETx,rETy,5,-StepRET[j],null);
						}
						//System.out.println("`````rETx  rETy : "+rETx+"  "+rETy);
					}else if(rETy-(s.sy+hTLy)>0 && rETx-(s.sx+hTLx)<0 && (rETy-(s.sy+hTLy))>((s.sx+hTLx)-rETx))//第五卦限
					{
//						System.out.println("第五卦限");
						double width = (s.sx+hTLx)-rETx;
						double heitht = rETy-(s.sy+hTLy);
						int steprETy = (int)Math.round(heitht/width);
						//System.out.println(steprETy);
						
						stepRET(heitht, width, steprETy);
						for(int j=0;rETx<(s.sx+hTLx); rETx++, rETy-=StepRET[j],j++)
						{
							g.drawImage(FIREBOMBIMGS_2[fireStep],rETx,rETy,5,-StepRET[j],null);
						}
						//System.out.println("`````rETx  rETy : "+rETx+"  "+rETy);
					}else if(rETy-(s.sy+hTLy)>0 && rETx-(s.sx+hTLx)<0 && (rETy-(s.sy+hTLy))<((s.sx+hTLx)-rETx))//第六卦限
					{
//						System.out.println("第六卦限");
						double width = (s.sx+hTLx)-rETx;
						double heitht = rETy-(s.sy+hTLy);
						int steprETx = (int)Math.round(width/heitht);
						//System.out.println(steprETy);
						
						stepRET(width, heitht, steprETx);
						for(int j=0;rETy>(s.sy+hTLy); rETx+=StepRET[j], rETy--,j++)
						{
							g.drawImage(FIREBOMBIMGS_3[fireStep],rETx,rETy,StepRET[j],5,null);
						}
						//System.out.println("`````rETx  rETy : "+rETx+"  "+rETy);
					}else if(rETy-(s.sy+hTLy)<0 && rETx-(s.sx+hTLx)<0 && (rETy-(s.sy+hTLy))>(rETx-(s.sx+hTLx)))//第七卦限
					{
//						System.out.println("第七卦限");
//						System.out.println("77 rETx  rETy : "+rETx+"  "+rETy);
//						System.out.println("77 hTLx  hTLy : "+hTLx+"  "+hTLy);
						double width = (s.sx+hTLx)-rETx;
						double heitht = (s.sy+hTLy)-rETy;
						int steprETx = (int)Math.round(width/heitht);
						//System.out.println(steprETx);
						
						stepRET(width, heitht, steprETx);
						for(int j=0;rETy<(s.sy+hTLy); rETx+=StepRET[j], rETy++,j++)
						{
							g.drawImage(FIREBOMBIMGS_3[fireStep],rETx,rETy,StepRET[j],5,null);
						}
						//System.out.println("`````rETx  rETy : "+rETx+"  "+rETy);
					}else if(rETy-(s.sy+hTLy)<0 && rETx-(s.sx+hTLx)==0)//正上
					{
						g.drawImage(FIREBOMBIMGS_2[fireStep],rETx,rETy,5,((s.sy+hTLy)-rETy)+2,null);
					}else if(rETy-(s.sy+hTLy)>0 && rETx-(s.sx+hTLx)==0)//正下
					{
						g.drawImage(FIREBOMBIMGS_2[fireStep],s.sx,s.sy,5,(rETy-(s.sy+hTLy))+2,null);
					}else if(rETy-(s.sy+hTLy)==0 && rETx-(s.sx+hTLx)<0)//正左
					{
						g.drawImage(FIREBOMBIMGS_3[fireStep],rETx,rETy,(s.sx+hTLx)-rETx,5,null);
					}else if(rETy-(s.sy+hTLy)==0 && rETx-(s.sx+hTLx)>0)//正右
					{
						g.drawImage(FIREBOMBIMGS_3[fireStep],s.sx,s.sy,rETx-(s.sx+hTLx),5,null);
					}
//					g.setColor(Color.white);
//					g.drawOval(rETx-60, rETy-60, 120, 120);
				}
			}
			break;
		case 6:
			if(fireStep == FIREBOMBIMGS_4.length)  //开火完成
			{
				s.fireBomb = null;
				s.tk.ismove = true;
//				Tank.allowPress = true;
				return;
			}
			
			if(s.isFirst && fireStep == FIREBOMBIMGS_4.length*1/3)  //创建第二颗子弹
			{
				if(Status.getStage()==3)
				{
					s.fightPanel.shotsList.add(new NormalShot(s.tk.level,s.tk.getAltitudeRect(),s.tk,s.tk.tankDir,false));
				}else{
					s.fightPanel.shotsList.add(new NormalShot(s.tk.level,1,s.tk,s.tk.tankDir,false));
				}
			}
			
			if(y<ty)
			{
				g.drawImage(FIREBOMBIMGS_4[fireStep],x-11,y-11,null);
			}else if(y>ty+30)
			{
				g.drawImage(FIREBOMBIMGS_4[fireStep],x-11,y-13,null);
			}else if(x<tx)
			{
				g.drawImage(FIREBOMBIMGS_4[fireStep],x-8,y-12,null);
			}else if(x>tx+40)
			{
				g.drawImage(FIREBOMBIMGS_4[fireStep],x-13,y-12,null);
			}
			break;
		case 7:
			if(fireStep == FIREBOMBIMGS_1.length)  //开火完成
			{
				s.fireBomb=null;
				s.tk.ismove = true;
//				Tank.allowPress = true;
				return;
			}
			
			if(s.isFirst && fireStep == FIREBOMBIMGS_1.length*1/3)  //创建第二颗子弹
			{
				if(Status.getStage()==3)
				{
					s.fightPanel.shotsList.add(new NormalShot(s.tk.level,s.tk.getAltitudeRect(),s.tk,s.tk.tankDir,false));
				}else{
					s.fightPanel.shotsList.add(new NormalShot(s.tk.level,1,s.tk,s.tk.tankDir,false));
				}
			}
		
			if(y<ty)
			{
				g.drawImage(FIREBOMBIMGS_1[fireStep],x-17,y-22,null);
			}else if(y>ty+30)
			{
				g.drawImage(FIREBOMBIMGS_1[fireStep],x-17,y-24,null);
			}else if(x<tx)
			{
				g.drawImage(FIREBOMBIMGS_1[fireStep],x-15,y-24,null);
			}else if(x>tx+40)
			{
				g.drawImage(FIREBOMBIMGS_1[fireStep],x-19,y-24,null);
			}
			break;
		default:
			break;
		}
		
		fireStep++;
	}
	
	public void stepRET(double Long, double Short , int steprET)  //辅助画出散开的光束
	{
//		System.out.println(Long+"   "+Short+"   "+steprET);
		if(((double)steprET)<(Long/Short))
		{
			int j = (int)Short;
			int k = 0;
			while(steprET*j+(steprET+1)*k!=(int)Long)
			{
				j--;
				k++;
			}

			if(j>=k)
			{
				for(int i=0; i<(int)Short; i++)
				{
					StepRET[i] = steprET;
				}
				for(int i=j/k; i<(int)Short; i+=j/k+1)
				{
					StepRET[i] = (steprET+1);
				}
			}else{
				for(int i=0; i<(int)Short; i++)
				{
					StepRET[i] = (steprET+1);
				}
				for(int i=k/j; i<(int)Short; i+=k/j+1)
				{
					StepRET[i] = steprET;
				}
			}
		}else if(((double)steprET)>(Long/Short)){
			int j = (int)Short;
			int k = 0;
			while(steprET*j+(steprET-1)*k!=(int)Long)
			{
				j--;
				k++;
			}
			
			if(j>=k)
			{
				for(int i=0; i<(int)Short; i++)
				{
					StepRET[i] = steprET;
				}
				for(int i=j/k; i<(int)Short; i+=j/k+1)
				{
					StepRET[i] = (steprET-1);
				}
			}else{
				for(int i=0; i<(int)Short; i++)
				{
					StepRET[i] = (steprET-1);
				}
				for(int i=k/j; i<(int)Short; i+=k/j+1)
				{
					StepRET[i] = steprET;
				}
			}
		}else{
			for(int i=0; i<(int)Short; i++)
			{
				StepRET[i] = steprET;
			}
		}
	}
}

class TankBomb extends Bomb
{
	Tank tk;
	Delve delve;
	public static final Image[] TANKBOMBINTG=
	{
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/tank/1.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/tank/2.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/tank/3.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/tank/4.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/tank/5.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/tank/6.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/tank/7.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/tank/8.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/tank/9.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/tank/10.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/tank/11.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/tank/12.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/tank/13.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/tank/14.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/tank/15.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/tank/16.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/tank/17.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/tank/18.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/tank/19.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/tank/20.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/tank/21.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/tank/22.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/tank/23.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/tank/24.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/tank/25.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/tank/26.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/tank/27.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/tank/28.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/tank/29.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/tank/30.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/tank/31.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/tank/32.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/tank/33.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/tank/34.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/tank/35.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/tank/36.png"))
	};
	static boolean init=false; //第一次画时将图片载入内存

	public TankBomb(int x,int y,Tank tk)
	{
		super(x, y);
		this.tk=tk;
	}

	void draw(Graphics g)
	{
		if(!init)
		{
			for (int i=0;i<TANKBOMBINTG.length;i++)
			{
				g.drawImage(TANKBOMBINTG[i],1000,1000,null);
				init=true;
			}
		}
		
		if(step==TANKBOMBINTG.length/3)  //产生弹坑
		{
			delve=new Delve(x+62,y+65,1,tk);
			FightPanel.delvesList.add(delve);
		}

		if(step==TANKBOMBINTG.length)  //爆炸完成
		{
			tk.tankBomb=null;

			if(tk.isNPC)
			{
				tk.fightPanel.tanksList.remove(tk); //将机器人Tank从tankList中去除
				tk=null;
			}else{
				new Thread(new TankRebirth((UserTank)tk)).start();
				//((UserTank)tk).rebirth();
			}
			return;
		}
		g.drawImage(TANKBOMBINTG[step],x,y,null);
		step++;
	}
}

class TankRebirth implements Runnable
{
	UserTank tk;
	public TankRebirth(UserTank tk)
	{
		this.tk = tk;
	}
	public void run() {
		try {
			Thread.sleep(1000);
			tk.rebirth();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
class ShotBomb extends Bomb
{
	Shot s;
	private static final Image[] SBIMGS=
	{
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/shot/normal/1.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/shot/normal/2.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/shot/normal/3.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/shot/normal/4.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/shot/normal/5.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/shot/normal/6.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/shot/normal/7.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/shot/normal/8.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/shot/normal/9.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/shot/normal/10.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/shot/normal/11.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/shot/normal/12.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/shot/normal/13.png"))
	};
	static boolean init=false; //第一次画是将图片载入内存
	
	public ShotBomb(int x,int y,Shot s)
	{
		super(x, y);
		this.s=s;
	}

	void draw(Graphics g)
	{
		if(!init)
		{
			for (int i=0;i<SBIMGS.length;i++)
			{
				g.drawImage(SBIMGS[i],1000,1000,null);
				init=true;
			}
		}

		if(step==SBIMGS.length)  //爆炸完成
		{
			s.fightPanel.shotsList.remove(s);
			s.shotBomb=null;  //清除垃圾
			s=null;
			return;
		}
		
		g.drawImage(SBIMGS[step],x,y,null);
		step++;
	}
}

class SuperShotBomb extends ShotBomb
{
	public static final Image[] SHOTBOMBIMGS_1=
	{
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/shot/1/1.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/shot/1/1.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/shot/1/1.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/shot/1/2.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/shot/1/2.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/shot/1/2.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/shot/1/3.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/shot/1/3.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/shot/1/3.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/shot/1/4.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/shot/1/5.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/shot/1/6.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/shot/1/7.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/shot/1/8.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/shot/1/9.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/shot/1/10.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/shot/1/11.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/shot/1/12.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/shot/1/13.png"))
	};
	public static final Image[] SHOTBOMBIMGS_2=
	{
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/shot/2/1.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/shot/2/2.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/shot/2/3.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/shot/2/4.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/shot/2/5.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/shot/2/6.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/shot/2/7.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/shot/2/8.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/shot/2/9.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/shot/2/10.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/shot/2/11.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/shot/2/12.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/shot/2/13.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/shot/2/14.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/shot/2/15.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/shot/2/16.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/shot/2/17.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/shot/2/18.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/shot/2/19.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/shot/2/20.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/shot/2/21.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/shot/2/22.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/shot/2/23.png"))
	};
	public static final Image[] SHOTBOMBIMGS_3=
	{
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/shot/3/1.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/shot/3/1.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/shot/3/2.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/shot/3/2.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/shot/3/3.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/shot/3/3.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/shot/3/4.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/shot/3/4.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/shot/3/4.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/shot/3/5.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/shot/3/5.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/shot/3/5.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/shot/3/6.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/shot/3/6.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/shot/3/6.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/shot/3/7.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/shot/3/7.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/shot/3/7.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/shot/3/8.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/shot/3/9.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/shot/3/10.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/shot/3/11.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/shot/3/12.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/shot/3/13.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/shot/3/14.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/shot/3/15.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/shot/3/16.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/shot/3/17.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/shot/3/18.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/shot/3/19.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/shot/3/20.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/shot/3/21.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/shot/3/22.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/shot/3/23.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/shot/3/24.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/shot/3/25.png"))
	};
	public static final Image[] SHOTBOMBIMGS_4=
	{
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/shot/4/1.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/shot/4/1.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/shot/4/2.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/shot/4/2.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/shot/4/3.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/shot/4/3.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/shot/4/4.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/shot/4/4.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/shot/4/5.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/shot/4/5.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/shot/4/6.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/shot/4/6.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/shot/4/7.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/shot/4/7.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/shot/4/8.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/shot/4/8.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/shot/4/9.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/shot/4/9.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/shot/4/10.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/shot/4/10.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/shot/4/11.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/shot/4/12.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/shot/4/13.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/shot/4/14.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/shot/4/15.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/shot/4/16.png"))
	};
	
	static boolean init=false;//第一次画是将图片载入内存
	boolean isBombWithoutDelve;//子弹碰到tank、基地、障碍物时，爆炸后不产生弹坑
	boolean once = true;//控制paint()光稜时方向不发生变化
	int DIRECTION = 0;//控制paint()光稜时方向不发生变化
	int SHOTTYPE;//锁定子弹爆炸效果的类型
	int halfTankLength;//光束打到TANK时，光束长度加半个车身位
	
	Tank tk;
	Delve delve;
	
	public SuperShotBomb(int x,int y,int SHOTTYPE,boolean isBombWithoutDelve,Shot s,Tank tk)
	{
		super(x, y, s);
		this.isBombWithoutDelve = isBombWithoutDelve;
		this.SHOTTYPE = SHOTTYPE;
		this.tk = tk;
	}
	public SuperShotBomb(int x,int y,int SHOTTYPE,int halfTankLength,boolean isBombWithoutDelve,Shot s,Tank tk)
	{
		super(x, y, s);
		this.isBombWithoutDelve = isBombWithoutDelve;
		this.SHOTTYPE = SHOTTYPE;
		this.tk = tk;
		this.halfTankLength = halfTankLength;
	}
	
	void draw(Graphics g)
	{
		if(!init)
		{
			for (int i=0;i<SHOTBOMBIMGS_1.length;i++)
			{
				g.drawImage(SHOTBOMBIMGS_1[i],1000,1000,null);
			}
			for (int i=0;i<SHOTBOMBIMGS_2.length;i++)
			{
				g.drawImage(SHOTBOMBIMGS_2[i],1000,1000,null);
			}
			for (int i=0;i<SHOTBOMBIMGS_3.length;i++)
			{
				g.drawImage(SHOTBOMBIMGS_3[i],1000,1000,null);
			}
			for (int i=0;i<SHOTBOMBIMGS_4.length;i++)
			{
				g.drawImage(SHOTBOMBIMGS_4[i],1000,1000,null);
			}
			init=true;
		}
		
		switch(SHOTTYPE)
		{
		case 1:
			if(step==SHOTBOMBIMGS_1.length/3 && !isBombWithoutDelve)  //产生弹坑
			{
				delve=new Delve(x,y,SHOTTYPE,tk);
				FightPanel.delvesList.add(delve);
			}
			
			if(step==SHOTBOMBIMGS_1.length)  //爆炸完成
			{
				new Thread(new Defer()).start();
				s.fightPanel.shotsList.remove(s);
				s.shotBomb=null;  //清除垃圾
				s=null;
				return;
			}
			g.drawImage(SHOTBOMBIMGS_1[step],x-16,y-26,null);
			break;
		case 2:
			if(step==SHOTBOMBIMGS_2.length/3 && !isBombWithoutDelve)  //产生弹坑
			{
				delve=new Delve(x,y,SHOTTYPE,tk);
				FightPanel.delvesList.add(delve);
			}
			
			if(step==SHOTBOMBIMGS_2.length)  //爆炸完成
			{
				new Thread(new Defer()).start();
				s.fightPanel.shotsList.remove(s);
				s.shotBomb=null;  //清除垃圾
				s=null;
				return;
			}			
			g.drawImage(SHOTBOMBIMGS_2[step],x-36,y-48,null);
			break;
		case 3:
			if(step==SHOTBOMBIMGS_3.length/3 && !isBombWithoutDelve)  //产生弹坑
			{
				delve=new Delve(x,y,SHOTTYPE,tk);
				FightPanel.delvesList.add(delve);
			}
			
			if(step==SHOTBOMBIMGS_3.length)  //爆炸完成
			{
				new Thread(new Defer()).start();
				s.fightPanel.shotsList.remove(s);
//				s.tk.allowFire=true;
				s.shotBomb=null;  //清除垃圾
				s=null;
				return;
			}
			
			if(once)
			{
				once = false;
				switch(s.tk.tankDir)
				{
				case U:
					DIRECTION = 0;
					break;
				case D:
					DIRECTION = 1;
					break;
				case L:
					DIRECTION = 2;
					break;
				case R:
					DIRECTION = 3;
					break;
					default:break;
				}
			}
			switch(DIRECTION)
			{
			case 0:
				g.drawImage(SHOTBOMBIMGS_3[step],x-80,y-135-halfTankLength,null);
				break;
			case 1:
				g.drawImage(SHOTBOMBIMGS_3[step],x-80,y-143+halfTankLength,null);
				break;
			case 2:
				g.drawImage(SHOTBOMBIMGS_3[step],x-79-halfTankLength,y-137,null);
				break;
			case 3:
				g.drawImage(SHOTBOMBIMGS_3[step],x-79+halfTankLength,y-137,null);
				break;
				default:break;
			}
			break;
		case 4:
			if(step==SHOTBOMBIMGS_4.length/3 && !isBombWithoutDelve)  //产生弹坑
			{
				delve=new Delve(x,y,SHOTTYPE,tk);
				FightPanel.delvesList.add(delve);
			}
			if(step==SHOTBOMBIMGS_4.length)  //爆炸完成
			{

				if(!s.isFirst)
				{
					new Thread(new Defer()).start();
				}
				s.fightPanel.shotsList.remove(s);
				s.shotBomb=null;  //清除垃圾
				s=null;
				return;
			}
			g.drawImage(SHOTBOMBIMGS_4[step],x-39,y-50,null);
			break;
		case 5:
			if(step==SHOTBOMBIMGS_2.length/3 && !isBombWithoutDelve)  //产生弹坑
			{
				delve=new Delve(x,y,SHOTTYPE,tk);
				FightPanel.delvesList.add(delve);
			}
			if(step==SHOTBOMBIMGS_2.length)  //爆炸完成
			{
				if(!s.isFirst)
				{
					new Thread(new Defer()).start();
				}
				s.fightPanel.shotsList.remove(s);
				s.shotBomb=null;  //清除垃圾
				s=null;
				return;
			}			
			g.drawImage(SHOTBOMBIMGS_2[step],x-36,y-48,null);
			break;
			default:break;
		}
		step++;
	}
	
	class Defer implements Runnable //子弹延时消失
	{
		public void run() {
			try {
				if(!tk.camp)
				{
					switch(tk.level)
					{
					case 0:
					case 1:
						Thread.sleep(500);
						break;
					case 2:
					case 3:
						Thread.sleep(500);
						break;
					case 6:
					case 7:
						Thread.sleep(1000);
						break;
						default:break;
					}
				}else{
					if(tk.level==4 || tk.level==5){
						Thread.sleep(1000);
					}
				}
				tk.allowFire=true;
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

class BaseBomb extends Bomb
{
	Base base;
	Delve delve;
	public static final Image[] BASEBOMBIMGS=
	{
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/shot/3/1.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/shot/3/1.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/shot/3/2.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/shot/3/2.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/shot/3/3.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/shot/3/3.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/shot/3/4.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/shot/3/4.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/shot/3/4.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/shot/3/5.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/shot/3/5.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/shot/3/5.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/shot/3/6.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/shot/3/6.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/shot/3/6.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/shot/3/7.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/shot/3/7.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/shot/3/7.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/shot/3/8.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/shot/3/9.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/shot/3/10.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/shot/3/11.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/shot/3/12.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/shot/3/13.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/shot/3/14.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/shot/3/15.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/shot/3/16.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/shot/3/17.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/shot/3/18.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/shot/3/19.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/shot/3/20.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/shot/3/21.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/shot/3/22.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/shot/3/23.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/shot/3/24.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/bomb/shot/3/25.png"))
	};
	static boolean init=false; //第一次画时将图片载入内存

	public BaseBomb(int x,int y, Base base)
	{
		super(x, y);
		this.base = base;
	}

	void draw(Graphics g)
	{
		if(!init)
		{
			for (int i=0;i<BASEBOMBIMGS.length;i++)
			{
				g.drawImage(BASEBOMBIMGS[i],1000,1000,null);
				init=true;
			}
		}
		
		if(step==BASEBOMBIMGS.length/3)  //产生弹坑
		{
			delve = new Delve(x+70,y+135,1);	FightPanel.delvesList.add(delve);
			delve = new Delve(x+100,y+135,1);	FightPanel.delvesList.add(delve);
			delve = new Delve(x+130,y+135,1);	FightPanel.delvesList.add(delve);
			delve = new Delve(x+85,y+155,1);	FightPanel.delvesList.add(delve);
			delve = new Delve(x+115,y+155,1);	FightPanel.delvesList.add(delve);
			delve = new Delve(x+70,y+170,1);	FightPanel.delvesList.add(delve);
			delve = new Delve(x+100,y+170,1);	FightPanel.delvesList.add(delve);
			delve = new Delve(x+130,y+170,1);	FightPanel.delvesList.add(delve);
		}

		if(step==BASEBOMBIMGS.length)  //爆炸完成
		{
			base.baseBomb=null;

			base.fp.ut.allowFire = false;
			base.fp.ut.ismove = false;
			new Thread(new GameOver(base)).start();
			return;
		}
		
		g.drawImage(BASEBOMBIMGS[step],x-10,y,null);
		g.drawImage(BASEBOMBIMGS[step],x+20,y,null);
		g.drawImage(BASEBOMBIMGS[step],x+50,y,null);
		
		g.drawImage(BASEBOMBIMGS[step],x+5,y+20,null);
		g.drawImage(BASEBOMBIMGS[step],x+35,y+20,null);
		
		g.drawImage(BASEBOMBIMGS[step],x-10,y+35,null);
		g.drawImage(BASEBOMBIMGS[step],x+20,y+35,null);
		g.drawImage(BASEBOMBIMGS[step],x+50,y+35,null);
		
		step++;
	}
}

class GameOver implements Runnable
{
	Base base;
	public GameOver(Base base)
	{
		this.base = base;
	}
	public void run() {
		try {
			Thread.sleep(1000);
			System.out.println("GAME OVER");
			
			base.fp.roadBlockList.clear();
			base.fp.itemList.clear();
			base.fp.shotsList.clear();
			FightPanel.delvesList.clear();
			Tank tk;
			for(int i=0;i<base.fp.tanksList.size();i++)
			{
				tk=base.fp.tanksList.get(i);
				tk.isLive=false;
			}
			base.fp.tanksList.clear();
			base.fp.gameOver=true;
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}