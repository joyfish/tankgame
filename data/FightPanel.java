package data;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.JPanel;

import data.Tank.TANK_DIR;
import data.panel.FightExitPanel;

public class FightPanel extends JPanel implements  KeyListener, MouseListener, MouseMotionListener
{	
	boolean repaintFlag=true;  //控制重画线程的结束
	boolean moveFlag=true;  //控制tank、shot等移动线程的结束
	boolean isDrawStart=true; //游戏开始、换关卡、攻击次数时绘制标志
	boolean gameOver=false; //控制背景画笔
	boolean popUp=false; //用于控制监听（战斗界面弹出的面板）
	static boolean enter = false;//用于鼠标移动对条目的选择
	int control=1;//用于控制监听（战斗界面弹出的面板）
	
	Image bkImage=null;  //用于双缓冲的缓存图片
	
	public Main mainFrame = null;

	//panel上的各种组件
	RoadBlock roadBlock=null;
	Delve fpDelves = null;
	Item tcItems=null;
	Tank tcTanks = null;
	Shot tcShots=null;
	BackGround backGround = new BackGround(this);
	Base base = new Base(300, 506, this);
	public Status status = new Status(this);
//	Base base = new Base(296, 256, this);
	StageSign stageSign = new StageSign(this);
	public FightExitPanel fePanel = new data.panel.FightExitPanel(this);
	

//	UserTank ut=new UserTank(210,520,0,Tank.TANK_DIR.U,true,this); //玩家控制的Tank
	UserTank ut=new UserTank(210,500,0,Tank.TANK_DIR.U,true,this); //玩家控制的Tank
	
	List<Tank> tanksList=Collections.synchronizedList(new ArrayList<Tank>());//存储界面上的Tank
	List<Tank> eTanksList=Collections.synchronizedList(new ArrayList<Tank>());//暂时储存待创建的敌方Tank
	List<Shot> shotsList=Collections.synchronizedList(new ArrayList<Shot>());//存储界面上的子弹
	List<RoadBlock> roadBlockList=Collections.synchronizedList(new ArrayList<RoadBlock>());//界面上的障碍物
	List<Item> itemList=Collections.synchronizedList(new ArrayList<Item>());//界面上的道具
	public static List<Delve> delvesList=Collections.synchronizedList(new ArrayList<Delve>());//存储界面上弹坑
	
	public FightPanel(Main mainFrame)
	{
		this.mainFrame = mainFrame;
		tanksList.add(ut);
		ut.isWuDi = true;
		new Thread(new WuDi(ut,true)).start();
		
		this.roadBlockList.add(new Mountain(-110,66,Mountain.MOUNTAINTYPE.STAGE1,this));
		this.roadBlockList.add(new Wall(140,110,Wall.WALLTYPE.STAGE1,this));
		
//		this.roadBlockList.add(new Mountain(0,-6,Mountain.MOUNTAINTYPE.STAGE2,this));
//		this.roadBlockList.add(new Wall(140,110,Wall.WALLTYPE.STAGE2,this));
//		this.roadBlockList.add(new Forest(0,0,Forest.FORESTTYPE.STAGE2,this));
		
//		this.roadBlockList.add(new Mountain(0,0,Mountain.MOUNTAINTYPE.STAGE3,this));
//		this.roadBlockList.add(new Forest(0,0,Forest.FORESTTYPE.STAGE3,this));
//		this.roadBlockList.add(new River(0,0,River.RIVERTYPE.STAGE3,this));

//		this.roadBlockList.add(new Mountain(0,0,Mountain.MOUNTAINTYPE.STAGE4,this));
//		this.roadBlockList.add(new Wall(136,-140,Wall.WALLTYPE.STAGE4,this));
//		this.roadBlockList.add(new Forest(0,0,Forest.FORESTTYPE.STAGE4,this));
		
//		this.itemList.add(new Item(50,460,this));//添加一个道具
//		this.itemList.add(new Item(140,460,3,this));//添加一个道具
//		this.itemList.add(new Item(230,460,3,this));//添加一个道具
		
		new Thread(new RepaintThread()).start(); //定时重画的线程
		new Thread(new MoveThread()).start(); //定时移动的线程
		new Thread(new CreateRobotTanksThread(this)).start();
		new Thread(new PlaySound()).start(); //循环播放背景音乐
	}
	
	public void paint(Graphics g)
	{
		super.paint(g);
		
		//背景
		backGround.draw(g);
		
////		森林（测试）
//		for(int i=0;i<roadBlockList.size();i++)
//		{
//			roadBlock=roadBlockList.get(i);
//			if(roadBlock.isForest)
//			{
//				roadBlock.draw(g);
//			}
//		}
		
		//障碍物
		//RoadBlock roadBlock=null;
		for(int i=0;i<roadBlockList.size();i++)
		{
			roadBlock=roadBlockList.get(i);
			if(!roadBlock.isForest) //不画森林
			{
				roadBlock.draw(g);
			}
			//System.out.println(roadBlock.mountainNodesList.size());
		}
		 
		for(int i=delvesList.size()-1;i>=0;i--)
		{
			fpDelves=delvesList.get(i);
			fpDelves.draw(g);
		}

		for(int i=0;i<itemList.size();i++)
		{
			tcItems=itemList.get(i);
			tcItems.draw(g);
		}
		
		for(int i=tanksList.size()-1;i>=0;i--)
		{
			tcTanks=tanksList.get(i);
			tcTanks.draw(g);
		}
		
		for(int i=0;i<shotsList.size();i++)
		{
			tcShots=shotsList.get(i);
			tcShots.draw(g);
		}
		
		//森林
		for(int i=0;i<roadBlockList.size();i++)
		{
			roadBlock=roadBlockList.get(i);
			if(roadBlock.isForest)
			{
				roadBlock.draw(g);
			}
		}
		
		if(!gameOver)
		{
			//基地
			base.draw(g);
			//状态栏
			status.draw(g, ut,false);
		}else{
			status.draw(g, ut,true);
		}
		
		//游戏开始、换关卡、攻击次数时绘制标志
		if(isDrawStart)
		{
			stageSign.draw(g);
		}
		
		if(popUp)
		{
			fePanel.draw(g,control);
		}
		
//		
//		//观察第三关海拔为2的区域
//		g.setColor(Color.green);
//		g.drawRect(0, 0, 96,35*16);
//		
//		g.drawRect(6*16,31*16, 27*16,4*16);
//		
//		g.drawRect(6*16,0, 18*16,22*16);
//		
//		g.drawRect(24*16,0, 11*16,19*16);
	}

////
//		
//		class FrameClose extends WindowAdapter  //窗口关闭事件监听者
//		{
//			public void windowClosing(WindowEvent e)
//			{
//				Frame f=(Frame)e.getWindow();
//				repaintFlag=false;
//				f.dispose();
//			}
//		}
//		
//
//		
//		public void update(Graphics g)
//		{
//			if(null==bkImage) { bkImage=this.createImage(794,568); }
//	
//			g.drawImage(bkImage,0,0,null);  //把虚拟图片的内容画到当前窗口
//	
//			Graphics gBkImg=bkImage.getGraphics();
//			gBkImg.clearRect(0,0,794,568); //先清空图片
//			this.paint(gBkImg);  //将图像先画到虚拟图片上
//		}
//
//		
		public void popUp() //控制菜单界面弹出/隐藏
		{
			if(popUp)//没有弹出界面
			{
				moveFlag = true;
				stageSign.repaintFlag = true;
				repaintFlag = true;
				popUp = false;
				WuDi.isPause = false;
				Main.showCursor(false);
			}else{
				moveFlag = false;
				stageSign.repaintFlag = false;
				repaintFlag = false;
				popUp = true;
				control = 1;
				WuDi.isPause = true;
				Main.showCursor(true);
			}
		}
		
		public void backMainPanel(){ //退出战斗，返回主菜单
			popUp();
			WuDi.exit = true;
			mainFrame.mp = new MenuPanel();
			mainFrame.remove(this);
			mainFrame.add(mainFrame.mp);
			mainFrame.whichPanle = "menu";
			mainFrame.mp.repaint();
			mainFrame.setVisible(true);
			mainFrame.removeKeyListener(this);
			mainFrame.removeMouseListener(this);
			mainFrame.removeMouseMotionListener(this);
			Main.showCursor(true);
			Status.setScore(0);
		}
		
		public void keyPressed(KeyEvent e) {
			int key = e.getKeyCode();
			if(!popUp)//没有弹出界面
			{
				switch(key)
				{
					//改变方向
					case KeyEvent.VK_UP:
					case KeyEvent.VK_W:
					case KeyEvent.VK_DOWN:
					case KeyEvent.VK_S:
					case KeyEvent.VK_LEFT:
					case KeyEvent.VK_A:
					case KeyEvent.VK_RIGHT:
					case KeyEvent.VK_D:
					{
						if(ut.isLive)
						{
							ut.keyDispose(key,true);
						}
					}
					break;
					
					case KeyEvent.VK_0:
						ut.setLevel(0);
						FireBomb.init = false;
						SuperShotBomb.init = false;
						
						Tank.imgMap0.clear();
						
						Tank.imgMap0.put(TANK_DIR.U,Tank.TANKBODY0[0]);
						Tank.imgMap0.put(TANK_DIR.D,Tank.TANKBODY0[1]);
						Tank.imgMap0.put(TANK_DIR.L,Tank.TANKBODY0[2]);
						Tank.imgMap0.put(TANK_DIR.R,Tank.TANKBODY0[3]);
						
						break;
					case KeyEvent.VK_1:
						ut.setLevel(0);
						FireBomb.init = false;
						SuperShotBomb.init = false;
						
						Tank.imgMap0.clear();
						
						Tank.imgMap0.put(TANK_DIR.U,Tank.TANKBODY0[0]);
						Tank.imgMap0.put(TANK_DIR.D,Tank.TANKBODY0[1]);
						Tank.imgMap0.put(TANK_DIR.L,Tank.TANKBODY0[2]);
						Tank.imgMap0.put(TANK_DIR.R,Tank.TANKBODY0[3]);
						
						break;
					case KeyEvent.VK_2:
						ut.setLevel(2);
						ut.turretX=ut.x+12;
						ut.turretY=ut.y-8;
						
						Tank.imgMap0.clear();
						
						Tank.imgMap0.put(TANK_DIR.U,Tank.TANKBODY0[4]);
						Tank.imgMap0.put(TANK_DIR.D,Tank.TANKBODY0[5]);
						Tank.imgMap0.put(TANK_DIR.L,Tank.TANKBODY0[6]);
						Tank.imgMap0.put(TANK_DIR.R,Tank.TANKBODY0[7]);
						
						break;
					case KeyEvent.VK_3:
						ut.setLevel(3);
						ut.turretX=ut.x+12;
						ut.turretY=ut.y-8;
						
						Tank.imgMap0.clear();
						
						Tank.imgMap0.put(TANK_DIR.U,Tank.TANKBODY0[4]);
						Tank.imgMap0.put(TANK_DIR.D,Tank.TANKBODY0[5]);
						Tank.imgMap0.put(TANK_DIR.L,Tank.TANKBODY0[6]);
						Tank.imgMap0.put(TANK_DIR.R,Tank.TANKBODY0[7]);
						
						break;
					case KeyEvent.VK_4:
						ut.setLevel(4);
						Tank.imgMap0.clear();
						
						Tank.imgMap0.put(TANK_DIR.U,Tank.TANKBODY0[8]);
						Tank.imgMap0.put(TANK_DIR.D,Tank.TANKBODY0[9]);
						Tank.imgMap0.put(TANK_DIR.L,Tank.TANKBODY0[10]);
						Tank.imgMap0.put(TANK_DIR.R,Tank.TANKBODY0[11]);
						
						break;
					case KeyEvent.VK_5:
						ut.setLevel(5);
						Tank.imgMap0.clear();
						
						Tank.imgMap0.put(TANK_DIR.U,Tank.TANKBODY0[8]);
						Tank.imgMap0.put(TANK_DIR.D,Tank.TANKBODY0[9]);
						Tank.imgMap0.put(TANK_DIR.L,Tank.TANKBODY0[10]);
						Tank.imgMap0.put(TANK_DIR.R,Tank.TANKBODY0[11]);
						
						break;
					case KeyEvent.VK_6:
						ut.setLevel(6);
						Tank.imgMap0.clear();
						
						Tank.imgMap0.put(TANK_DIR.U,Tank.TANKBODY0[12]);
						Tank.imgMap0.put(TANK_DIR.D,Tank.TANKBODY0[13]);
						Tank.imgMap0.put(TANK_DIR.L,Tank.TANKBODY0[14]);
						Tank.imgMap0.put(TANK_DIR.R,Tank.TANKBODY0[15]);
						
						break;
					case KeyEvent.VK_7:
						ut.setLevel(7);
						Tank.imgMap0.clear();
						
						Tank.imgMap0.put(TANK_DIR.U,Tank.TANKBODY0[12]);
						Tank.imgMap0.put(TANK_DIR.D,Tank.TANKBODY0[13]);
						Tank.imgMap0.put(TANK_DIR.L,Tank.TANKBODY0[14]);
						Tank.imgMap0.put(TANK_DIR.R,Tank.TANKBODY0[15]);
						break;
					case KeyEvent.VK_9:
						new Thread(new read()).start(); //血量测试
						break;
					case KeyEvent.VK_SPACE:	
						if(ut.isLive && ut.allowFire)
						{
							ut.fire();
						}
						break;
					case KeyEvent.VK_ENTER:	
						if(moveFlag)
						{
							moveFlag = false;
						}else{
							moveFlag = true;
						}

						if(repaintFlag)
						{
							stageSign.repaintFlag = false;
							repaintFlag = false;
							WuDi.isPause = true;
						}else{
							stageSign.repaintFlag = true;
							repaintFlag = true;
							WuDi.isPause = false;
						}
						break;
					case KeyEvent.VK_P:	
						if(MountainNode.isDraw)
						{
							MountainNode.isDraw = false;
						}else{
							MountainNode.isDraw = true;
						}
						break;
					case KeyEvent.VK_ESCAPE:
						popUp();
						AePlayWave apw=new AePlayWave("sounds/Menu_Click.wav");
						apw.start();
						break;
					default:break;
				}
			}else{ 			//操作弹出的菜单
				switch(key)
				{
					case KeyEvent.VK_UP:
					case KeyEvent.VK_LEFT:
					{
						AePlayWave apw=new AePlayWave("sounds/Menu_MoveON.wav");
						apw.start();
						
						switch(control)
						{
						case 1:control = 5;break;
						case 11:control = 17;break;
						case 21:control = FightExitPanel.getRecordNumber("records/state.txt")+20;break;
						case 31:
						case 32:break;
						case 41:control = 44;break;
						case 111:control = 112;break;
						case 112:control = 111;break;
						default:control--;break;
						}	
					}break;
					case KeyEvent.VK_DOWN:
					case KeyEvent.VK_RIGHT:
					{
						AePlayWave apw=new AePlayWave("sounds/Menu_MoveON.wav");
						apw.start();
						
						switch(control)
						{
						case 5:control = 1;break;
						case 11:
						case 12:
						case 13:
						case 14:
						case 15:
						case 16:
						case 17:
							if(control==17)
							{
								control = 11;
							}else{
								control++;
							}
							break;
						case 21:
						case 22:
						case 23:
						case 24:
						case 25:
						case 26:
						case 27:
							if(control==FightExitPanel.getRecordNumber("records/state.txt")+20)
							{
								control = 21;
							}else{
								control++;
							}
							break;
						case 31:
						case 32:break;
						case 44:control = 41;break;
						case 111:control = 112;break;
						case 112:control = 111;break;
						default:control++;break;
						}
					}break;
					case KeyEvent.VK_ENTER:	
					{
						AePlayWave apw=new AePlayWave("sounds/Menu_Click.wav");
						apw.start();	
			
						switch(control)
						{
						case 1:control = 11;break;
						case 2:	control = 21;break;
						case 3:control = 31;break;
						case 4:control = 41;break;
						case 5:popUp();break;//菜单隐藏
						case 11:
							if(fePanel.rEDs.get(0).sign == 0) //无效存档、空存档
							{
								control = 111;
							}else{	//覆盖进度
								control = 113;
							}
							break;
						case 12:
							if(fePanel.rEDs.get(1).sign == 0) //无效存档、空存档
							{
								control = 111;
							}else{	//覆盖进度
								control = 113;
							}
							break;
						case 13:
							if(fePanel.rEDs.get(2).sign == 0) //无效存档、空存档
							{
								control = 111;
							}else{	//覆盖进度
								control = 113;
							}
							break;
						case 14:
							if(fePanel.rEDs.get(3).sign == 0) //无效存档、空存档
							{
								control = 111;
							}else{	//覆盖进度
								control = 113;
							}
							break;
						case 15:
							if(fePanel.rEDs.get(4).sign == 0) //无效存档、空存档
							{
								control = 111;
							}else{	//覆盖进度
								control = 113;
							}
							break;
						case 16:
							if(fePanel.rEDs.get(5).sign == 0) //无效存档、空存档
							{
								control = 111;
							}else{	//覆盖进度
								control = 113;
							}
							break;
						case 17:control = 1;break;
						case 21:
						case 22:
						case 23:
						case 24:
						case 25:
						case 26:
						case 27:
							if(control==FightExitPanel.getRecordNumber("records/state.txt")+20 || control==27)
							{
								control = 1;
							}else{
								System.out.println("Load..");
							}
							break;
						case 31:
						case 32:break;
						case 42:
							if(fePanel.needSave()){
								fePanel.reSRds();
								if(Status.getScore()>fePanel.sCOs.get(0).score){
									control = 421;
								}else{
									control = 422;
								}
							}else{
								apw=new AePlayWave("sounds/Menu_Click.wav");
								apw.start();
								backMainPanel();
							}
							break;
						case 43:System.exit(0);break;
						case 44:control = 1;break;
						case 111:
							System.out.println("Save..");
							break;
						case 112:
							switch(fePanel.drawRecordControl)
							{
							case 1:control = 11;break;	
							case 2:control = 12;break;	
							case 3:control = 13;break;	
							case 4:control = 14;break;	
							case 5:control = 15;break;
							case 6:control = 16;break;	
							default:break;
							}
							fePanel.inputChars.clear();
							mainFrame.removeKeyListener(fePanel.input);
							fePanel.creatInput = false;
							fePanel.finishInput = true;
							break;
						case 113:control = 111;break;
						case 114:
							switch(fePanel.drawRecordControl)
							{
							case 1:	control = 11;break;	
							case 2:control = 12;break;	
							case 3:control = 13;break;	
							case 4:control = 14;break;	
							case 5:control = 15;break;	
							case 6:control = 16;break;	
							default:break;
							}
							break;
						case 421:
						case 422:
							backMainPanel();
							fePanel.inputChars.clear();
							mainFrame.removeKeyListener(fePanel.input);
							fePanel.creatInput = false;
							fePanel.finishInput = true;
							break;
						default:
							System.out.println("未定义！");
							break;
						}
					}break;
					case KeyEvent.VK_ESCAPE:
					{
						switch(control)
						{
						case 1:
						case 2:
						case 3:
						case 4:
						case 5:
							popUp();
							break;
						case 11:
						case 12:
						case 13:
						case 14:
						case 15:
						case 16:
						case 17:
						case 21:
						case 22:
						case 23:
						case 24:
						case 25:
						case 26:
						case 27:
						case 31:
						case 32:
						case 41:
						case 42:
						case 43:
						case 44:
							control = 1;
							break;
						case 111:
						case 112:
						case 113:
						case 114:
							switch(fePanel.drawRecordControl)
							{
							case 1:	control = 11;break;	
							case 2:control = 12;break;	
							case 3:control = 13;break;	
							case 4:control = 14;break;	
							case 5:control = 15;break;	
							case 6:control = 16;break;	
							default:break;
							}
							break;
						default:
							System.out.println("出现错误！");
							break;
						}
					}
					default:break;
				}
				this.repaint();
			}
		}
		
		public void keyReleased(KeyEvent e) {
			int key = e.getKeyCode();
			switch(key)
			{
				//改变方向
				case KeyEvent.VK_UP:
				case KeyEvent.VK_W:
				case KeyEvent.VK_DOWN:
				case KeyEvent.VK_S:
				case KeyEvent.VK_LEFT:
				case KeyEvent.VK_A:
				case KeyEvent.VK_RIGHT:
				case KeyEvent.VK_D:
				{
					if(ut.isLive)
					{
						ut.keyDispose(key,false);
					}
				}
				break;
				default:break;
			
			}
		}
		
		public void keyTyped(KeyEvent e) {}
		


		public void mouseClicked(MouseEvent e) {}
		public void mouseEntered(MouseEvent e) {}
		public void mouseExited(MouseEvent e) {}
		
		public void mousePressed(MouseEvent e)
		{
			if(popUp)
			{
				int x = e.getX();
				int y = e.getY();
				
				switch(control)
				{
				case 1:
				case 2:
				case 3:
				case 4:
				case 5:
					if(x>=325 && x<=490 && y>=240 && y<=277){
						AePlayWave apw=new AePlayWave("sounds/Menu_Click.wav");
						apw.start();
						control = 11;
					}else if(x>=325 && x<=490 && y>=293 && y<=328){
						AePlayWave apw=new AePlayWave("sounds/Menu_Click.wav");
						apw.start();
						control = 21;
					}else if(x>=325 && x<=490 && y>=345 && y<=378){
						AePlayWave apw=new AePlayWave("sounds/Menu_Click.wav");
						apw.start();
						control = 31;
					}else if(x>=325 && x<=490 && y>=393 && y<=430){
						AePlayWave apw=new AePlayWave("sounds/Menu_Click.wav");
						apw.start();
						control = 41;
					}else if(x>=340 && x<=476 && y>=485 && y<=532){
						AePlayWave apw=new AePlayWave("sounds/Menu_Click.wav");
						apw.start();
						popUp(); //菜单隐藏
					}
					break;
				case 11:
				case 12:
				case 13:
				case 14:
				case 15:
				case 16:
				case 17:
					if(x>=253 && x<=563 && y>=205 && y<=245){
						if(fePanel.rEDs.get(0).sign == 0) //无效存档、空存档
						{
							control = 111;
						}else{	//覆盖进度
							control = 113;
						}
						AePlayWave apw=new AePlayWave("sounds/Menu_Click.wav");
						apw.start();
					}else if(x>=253 && x<=563 && y>=253 && y<=290){
						if(fePanel.rEDs.get(1).sign == 0) //无效存档、空存档
						{
							control = 111;
						}else{	//覆盖进度
							control = 113;
						}
						AePlayWave apw=new AePlayWave("sounds/Menu_Click.wav");
						apw.start();
					}else if(x>=253 && x<=563 && y>=300 && y<=335){
						if(fePanel.rEDs.get(2).sign == 0) //无效存档、空存档
						{
							control = 111;
						}else{	//覆盖进度
							control = 113;
						}
						AePlayWave apw=new AePlayWave("sounds/Menu_Click.wav");
						apw.start();
					}else if(x>=253 && x<=563 && y>=344 && y<=380){
						if(fePanel.rEDs.get(3).sign == 0) //无效存档、空存档
						{
							control = 111;
						}else{	//覆盖进度
							control = 113;
						}
						AePlayWave apw=new AePlayWave("sounds/Menu_Click.wav");
						apw.start();
					}else if(x>=253 && x<=563 && y>=388 && y<=424){
						if(fePanel.rEDs.get(4).sign == 0) //无效存档、空存档
						{
							control = 111;
						}else{	//覆盖进度
							control = 113;
						}
						AePlayWave apw=new AePlayWave("sounds/Menu_Click.wav");
						apw.start();
					}else if(x>=253 && x<=563 && y>=433 && y<=471){
						if(fePanel.rEDs.get(5).sign == 0) //无效存档、空存档
						{
							control = 111;
						}else{	//覆盖进度
							control = 113;
						}
						AePlayWave apw=new AePlayWave("sounds/Menu_Click.wav");
						apw.start();
					}else if(x>=340 && x<=476 && y>=485 && y<=532){
						AePlayWave apw=new AePlayWave("sounds/Menu_Click.wav");
						apw.start();
						control = 1;
					}
					break;
				case 21:
				case 22:
				case 23:
				case 24:
				case 25:
				case 26:
				case 27:
					if(x>=253 && x<=563 && y>=205 && y<=245 && FightExitPanel.getRecordNumber("records/state.txt")>1){
						AePlayWave apw=new AePlayWave("sounds/Menu_Click.wav");
						apw.start();
						System.out.println("Load..");
					}else if(x>=253 && x<=563 && y>=253 && y<=290 && FightExitPanel.getRecordNumber("records/state.txt")>2){
						AePlayWave apw=new AePlayWave("sounds/Menu_Click.wav");
						apw.start();
						System.out.println("Load..");
					}else if(x>=253 && x<=563 && y>=300 && y<=335 && FightExitPanel.getRecordNumber("records/state.txt")>3){
						AePlayWave apw=new AePlayWave("sounds/Menu_Click.wav");
						apw.start();
						System.out.println("Load..");
					}else if(x>=253 && x<=563 && y>=344 && y<=380 && FightExitPanel.getRecordNumber("records/state.txt")>4){
						AePlayWave apw=new AePlayWave("sounds/Menu_Click.wav");
						apw.start();
						System.out.println("Load..");
					}else if(x>=253 && x<=563 && y>=388 && y<=424 && FightExitPanel.getRecordNumber("records/state.txt")>5){
						AePlayWave apw=new AePlayWave("sounds/Menu_Click.wav");
						apw.start();
						System.out.println("Load..");
					}else if(x>=253 && x<=563 && y>=433 && y<=471 && FightExitPanel.getRecordNumber("records/state.txt")>6){
						AePlayWave apw=new AePlayWave("sounds/Menu_Click.wav");
						apw.start();
						System.out.println("Load..");
					}else if(x>=340 && x<=476 && y>=485 && y<=532){
						AePlayWave apw=new AePlayWave("sounds/Menu_Click.wav");
						apw.start();
						control = 1;
					}
					break;
				case 31:
				case 32:
					if(x>=340 && x<=476 && y>=485 && y<=532)
					{
						AePlayWave apw=new AePlayWave("sounds/Menu_Click.wav");
						apw.start();
						control = 1;
					}
					break;
				case 41:
				case 42:
				case 43:
				case 44:
					if(x>=325 && x<=490 && y>=240 && y<=277){
						AePlayWave apw=new AePlayWave("sounds/Menu_Click.wav");
						apw.start();
						System.out.println("New Game..");
					}else if(x>=325 && x<=490 && y>=293 && y<=328){
						if(fePanel.needSave()){
							fePanel.reSRds();
							if(Status.getScore()>fePanel.sCOs.get(0).score){
								control = 421;
							}else{
								control = 422;
							}
						}else{
							AePlayWave apw=new AePlayWave("sounds/Menu_Click.wav");
							apw.start();
							backMainPanel();
						}
					}else if(x>=325 && x<=490 && y>=345 && y<=378){
						AePlayWave apw=new AePlayWave("sounds/Menu_Click.wav");
						apw.start();
						System.exit(0);
					}else if(x>=340 && x<=476 && y>=485 && y<=532){
						AePlayWave apw=new AePlayWave("sounds/Menu_Click.wav");
						apw.start();
						control = 1;
					}
					break;
				case 111:
				case 112:
					if(x>=265 && x<=396 && y>=396 && y<=442)
					{
						AePlayWave apw=new AePlayWave("sounds/Menu_Click.wav");
						apw.start();
						System.out.println("Save..");
					}else if(x>=417 && x<=550 && y>=396 && y<=442)
					{
						AePlayWave apw=new AePlayWave("sounds/Menu_Click.wav");
						apw.start();
						switch(fePanel.drawRecordControl)
						{
						case 1:control = 11;break;	
						case 2:control = 12;break;	
						case 3:control = 13;break;	
						case 4:control = 14;break;	
						case 5:control = 15;break;	
						case 6:control = 16;break;	
						default:break;
						}
						fePanel.inputChars.clear();
						mainFrame.removeKeyListener(fePanel.input);
						fePanel.creatInput = false;
						fePanel.finishInput = true;
					}
					break;
				case 113:
				case 114:
					if(x>=265 && x<=396 && y>=396 && y<=442)
					{
						AePlayWave apw=new AePlayWave("sounds/Menu_Click.wav");
						apw.start();
						control = 111;
					}else if(x>=417 && x<=550 && y>=396 && y<=442)
					{
						AePlayWave apw=new AePlayWave("sounds/Menu_Click.wav");
						apw.start();
						switch(fePanel.drawRecordControl)
						{
						case 1:	control = 11;break;	
						case 2:control = 12;break;	
						case 3:control = 13;break;	
						case 4:control = 14;break;	
						case 5:control = 15;break;	
						case 6:control = 16;break;	
						default:break;
						}
					}
					break;
				case 421:
				case 422:
					if(x>=340 && x<=476 && y>=485 && y<=532){
						AePlayWave apw=new AePlayWave("sounds/Menu_Click.wav");
						apw.start();
						backMainPanel();
						mainFrame.removeKeyListener(fePanel.input);
						fePanel.creatInput = false;
						fePanel.finishInput = true;
						fePanel.save();
					}
					break;
				default:break;
				}
			}
		}
		public void mouseReleased(MouseEvent e) {}
		public void mouseDragged(MouseEvent e) {}
		public void mouseMoved(MouseEvent e)
		{
			if(popUp)
			{
//				System.out.println(e.getX()+"  "+e.getY());
				int x = e.getX();
				int y = e.getY();
				
				switch(control)
				{
				case 1:
				case 2:
				case 3:
				case 4:
				case 5:
					if(x<325 || x>490 || y<240 || y>532 || y>277 && y<293 || y>328 && y<345 || y>378 && y<393 || y>430 && y<485 || x>325 && x<340 && y>430 || x>476 && x<490 && y>430)
					{
						enter = false;
					}else{
						if(x>=325 && x<=490 && y>=240 && y<=277 && !enter){
							control = 1;
							AePlayWave apw=new AePlayWave("sounds/Menu_MoveON.wav");
							apw.start();
							enter = true;
						}else if(x>=325 && x<=490 && y>=293 && y<=328 && !enter){
							control = 2;
							AePlayWave apw=new AePlayWave("sounds/Menu_MoveON.wav");
							apw.start();
							enter = true;
						}else if(x>=325 && x<=490 && y>=345 && y<=378 && !enter){
							control = 3;
							AePlayWave apw=new AePlayWave("sounds/Menu_MoveON.wav");
							apw.start();
							enter = true;
						}else if(x>=325 && x<=490 && y>=393 && y<=430 && !enter){
							control = 4;
							AePlayWave apw=new AePlayWave("sounds/Menu_MoveON.wav");
							apw.start();
							enter = true;
						}else if(x>=340 && x<=476 && y>=485 && y<=532 && !enter){
							control = 5;
							AePlayWave apw=new AePlayWave("sounds/Menu_MoveON.wav");
							apw.start();
							enter = true;
						}
					}
					break;
				case 11:
				case 12:
				case 13:
				case 14:
				case 15:
				case 16:
				case 17:
					if(x<253 || x>563 || y<205 || y>532 || y>245 && y<253 || y>290 && y<300 || y>335 && y<344 || y>380 && y<388 || y>424 && y<433 || y>471 && y<485 || x>253 && x<340 && y>471 || x>476 && x<563 && y>471)
					{
						enter = false;
					}else{
						if(x>=253 && x<=563 && y>=205 && y<=245 && !enter){
							control = 11;
							AePlayWave apw=new AePlayWave("sounds/Menu_MoveON.wav");
							apw.start();
							enter = true;
						}else if(x>=253 && x<=563 && y>=253 && y<=290 && !enter){
							control = 12;
							AePlayWave apw=new AePlayWave("sounds/Menu_MoveON.wav");
							apw.start();
							enter = true;
						}else if(x>=253 && x<=563 && y>=300 && y<=335 && !enter){
							control = 13;
							AePlayWave apw=new AePlayWave("sounds/Menu_MoveON.wav");
							apw.start();
							enter = true;
						}else if(x>=253 && x<=563 && y>=344 && y<=380 && !enter){
							control = 14;
							AePlayWave apw=new AePlayWave("sounds/Menu_MoveON.wav");
							apw.start();
							enter = true;
						}else if(x>=253 && x<=563 && y>=388 && y<=424 && !enter){
							control = 15;
							AePlayWave apw=new AePlayWave("sounds/Menu_MoveON.wav");
							apw.start();
							enter = true;
						}else if(x>=253 && x<=563 && y>=433 && y<=471 && !enter){
							control = 16;
							AePlayWave apw=new AePlayWave("sounds/Menu_MoveON.wav");
							apw.start();
							enter = true;
						}else if(x>=340 && x<=476 && y>=485 && y<=532 && !enter){
							control = 17;
							AePlayWave apw=new AePlayWave("sounds/Menu_MoveON.wav");
							apw.start();
							enter = true;
						}
					}
					break;
				case 21:
				case 22:
				case 23:
				case 24:
				case 25:
				case 26:
				case 27:
					if(x<253 || x>563 || y<205 || y>532 || y>245 && y<253 || y>290 && y<300 || y>335 && y<344 || y>380 && y<388 || y>424 && y<433 || y>471 && y<485 || x>253 && x<340 && y>471 || x>476 && x<563 && y>471)
					{
						enter = false;
					}else{
						if(x>=253 && x<=563 && y>=205 && y<=245 && FightExitPanel.getRecordNumber("records/state.txt")>1 && !enter){
							control = 21;
							AePlayWave apw=new AePlayWave("sounds/Menu_MoveON.wav");
							apw.start();
							enter = true;
						}else if(x>=253 && x<=563 && y>=253 && y<=290 && FightExitPanel.getRecordNumber("records/state.txt")>2 && !enter){
							control = 22;
							AePlayWave apw=new AePlayWave("sounds/Menu_MoveON.wav");
							apw.start();
							enter = true;
						}else if(x>=253 && x<=563 && y>=300 && y<=335 && FightExitPanel.getRecordNumber("records/state.txt")>3 && !enter){
							control = 23;
							AePlayWave apw=new AePlayWave("sounds/Menu_MoveON.wav");
							apw.start();
							enter = true;
						}else if(x>=253 && x<=563 && y>=344 && y<=380 && FightExitPanel.getRecordNumber("records/state.txt")>4 && !enter){
							control = 24;
							AePlayWave apw=new AePlayWave("sounds/Menu_MoveON.wav");
							apw.start();
							enter = true;
						}else if(x>=253 && x<=563 && y>=388 && y<=424 && FightExitPanel.getRecordNumber("records/state.txt")>5 && !enter){
							control = 25;
							AePlayWave apw=new AePlayWave("sounds/Menu_MoveON.wav");
							apw.start();
							enter = true;
						}else if(x>=253 && x<=563 && y>=433 && y<=471 && FightExitPanel.getRecordNumber("records/state.txt")>6 && !enter){
							control = 26;
							AePlayWave apw=new AePlayWave("sounds/Menu_MoveON.wav");
							apw.start();
							enter = true;
						}else if(x>=340 && x<=476 && y>=485 && y<=532 && !enter){
							control = 27;
							AePlayWave apw=new AePlayWave("sounds/Menu_MoveON.wav");
							apw.start();
							enter = true;
						}
					}
					break;
				case 31:
				case 32:
					if(x<340 || x>476 || y<485 || y>532)
					{
						control = 31;
						enter = false;
					}else{
						if(x>=340 && x<=476 && y>=485 && y<=532 && !enter)
						{
							control = 32;
							AePlayWave apw=new AePlayWave("sounds/Menu_MoveON.wav");
							apw.start();
							enter = true;
						}
					}
					break;
				case 41:
				case 42:
				case 43:
				case 44:
					if(x<325 || x>490 || y<240 || y>532 || y>277 && y<293 || y>328 && y<345 || y>378 && y<393 || x>325 && x<340 && y>348 || x>476 && x<490 && y>348)
					{
						enter = false;
					}else{
						if(x>=325 && x<=490 && y>=240 && y<=277 && !enter){
							control = 41;
							AePlayWave apw=new AePlayWave("sounds/Menu_MoveON.wav");
							apw.start();
							enter = true;
						}else if(x>=325 && x<=490 && y>=293 && y<=328 && !enter){
							control = 42;
							AePlayWave apw=new AePlayWave("sounds/Menu_MoveON.wav");
							apw.start();
							enter = true;
						}else if(x>=325 && x<=490 && y>=345 && y<=378 && !enter){
							control = 43;
							AePlayWave apw=new AePlayWave("sounds/Menu_MoveON.wav");
							apw.start();
							enter = true;
						}else if(x>=340 && x<=476 && y>=485 && y<=532 && !enter){
							control = 44;
							AePlayWave apw=new AePlayWave("sounds/Menu_MoveON.wav");
							apw.start();
							enter = true;
						}
					}
					break;
				case 111:
				case 112:
				case 113:
				case 114:
					if(x<265 || x>550 || y<396 || y>442 || x>396 && x<417)
					{
						enter = false;
					}else{
						if(x>=265 && x<=396 && y>=396 && y<=442 && !enter){
							AePlayWave apw=new AePlayWave("sounds/Menu_MoveON.wav");
							apw.start();
							if(control==112)
							{
								control = 111;
							}else if(control==114)
							{
								control = 113;
							}
							enter = true;
						}else if(x>=417 && x<=550 && y>=396 && y<=442 && !enter){
							AePlayWave apw=new AePlayWave("sounds/Menu_MoveON.wav");
							apw.start();
							if(control==111)
							{
								control = 112;
							}else if(control==113)
							{
								control = 114;
							}
							enter = true;
						}
					}
					break;
				default:break;
				}
			}
		}
		
		class RepaintThread implements Runnable //画面重画线程
		{
			public void run()
			{
				while(true)
				{
					try
					{
						repaint();
						Thread.sleep(20);
					} catch (InterruptedException e)
					{
						e.printStackTrace();
						System.exit(-1);
					}
				}
			}
		}
		
		class MoveThread implements Runnable //控制玩家tank、所有shot等移动速度
		{
			public void run()
			{
				while(true)
				{
					try
					{
						if(moveFlag)
						{
							ut.move(); //Tank定时移动
							
							Shot Shots=null;
							for(int i=0;i<shotsList.size();i++) //界面上的子弹定时移动
							{
								Shots=shotsList.get(i);
								Shots.move();
							}
						}
						Thread.sleep(ut.TANKSPEED);
					} catch (InterruptedException e)
					{
						e.printStackTrace();
						System.exit(-1);
					}
				}
			}
		}
		
		class CreateRobotTanksThread implements Runnable  //控制界面上的tank数量
		{
			FightPanel fightPanel;
			RobotTank rt;
			int tankType; //在范围内随机tank类型
			boolean isfinish; //确保创建一辆tank
			
			public CreateRobotTanksThread(FightPanel fightPanel)
			{
				this.fightPanel = fightPanel;
			}
			
			public void run()
			{
				while(true)
				{
					try
					{
						if(repaintFlag)
						{
							if(!isDrawStart)
							{
								int topTankNum = Status.getEnemyTankNumber(); //获取敌方tank剩余数量
								if(topTankNum>=4){
									topTankNum=4;
								}
								if(Status.getEnemyTankNumber()>0 && Status.getStage()>=4 && tanksList.size()<7 || Status.getEnemyTankNumber()>0 && Status.getStage()!=4 && tanksList.size()<5 )
								{
									if(Status.getStage()>=4)
									{
										switch(Status.getFight())
										{
										case 1:tankType=0;break;
										case 2:tankType=2;break;
										case 3:tankType=4;break;
										case 4:tankType=6;break;
										case 5:tankType=1;break;
										case 6:tankType=3;break;
										case 7:tankType=5;break;
										case 8:tankType=7;break;
										default:break;
										}
										Status.cutEnemyTankNumber(tankType,topTankNum);
									}else if(Status.getStage()<4){
										isfinish = false;
										while(!isfinish)
										{
											switch(Tank.rn.nextInt(8))
											{
											case 0:
											case 1:
											case 2:
												if(Status.getEnemyTank_xiNiu()>0){
													tankType = 0;
													isfinish = true;
												}
												break;
											case 3:
											case 4:
											case 5:
												if(Status.getEnemyTank_shaShou()>0){
													tankType = 2;
													isfinish = true;
												}
												break;
											case 6:
												if(Status.getEnemyTank_guangLing()>0){
													tankType = 4;
													isfinish = true;
												}
												break;
											case 7:
												if(Status.getEnemyTank_tianQi()>0){
													tankType = 6;
													isfinish = true;
												}
												break;
												default:break;
											}
										}
										Status.cutEnemyTankNumber(tankType);
									}
									
									if(Status.getStage()<4)  //一定概率将tank升级
									{
										switch(Tank.rn.nextInt(6))
										{
										case 1:
											tankType += 1;
											break;
										default:break;
										}
									}
	//								else if(Status.getFight()>4){
	//									tankType += 1;
	//								}
	
									if(Status.getStage()>=4)  //第四关,一次创建多辆tank
									{
										for(int count=0; count<topTankNum; count++)
										{
											Tank tempTk = null;
											RoadBlock block = null;
											MountainNode mn = null;
											
											boolean isTouchMountains = true;
											boolean isTouchTanks = true;
											
											rt = new RobotTank(0,0,tankType,tankDir(),false,fightPanel);
											
											while(isTouchMountains || isTouchTanks || rt.inMidArea())
											{
												isTouchMountains = false;
												isTouchTanks = false;
												
												rt.x = Tank.rn.nextInt(630);
												rt.y = Tank.rn.nextInt(530);
												
												for(int i=0;i<fightPanel.roadBlockList.size();i++)  //与山重叠
												{
													block = fightPanel.roadBlockList.get(i);
													for(int j=0;j<block.mountainNodesList.size();j++)
													{
														mn = block.mountainNodesList.get(j);
														
														if(rt.getRect().intersects(mn.getRect()))
														{
															isTouchMountains = true;
															break;
														}
													}
												}
												
												for(int i=0;i<fightPanel.tanksList.size();i++)  //与TANK重叠
												{
													tempTk=fightPanel.tanksList.get(i);
													if(tempTk.getMoveRect().intersects(rt.getRect()))
													{
														isTouchTanks = true;
														break;
													}
												}
											}
											rt.oldx = rt.x;  //初始化创建原点
											rt.oldy = rt.y;
											rt.iniShotLocation(); //初始化炮筒位置
											eTanksList.add(rt);
										}
									}else{
										switch(Tank.rn.nextInt(3)) //tank出现的位置随机
										{
	//									case 0:
	//										rt = new RobotTank(37,20,tankType,tankDir(),false,fightPanel);
	//										break;
	//									case 1:
	//										rt = new RobotTank(326,20,tankType,tankDir(),false,fightPanel);
	//										break;
	//									case 2:
	//										rt = new RobotTank(612,20,tankType,tankDir(),false,fightPanel);
	//										break;
										case 0:
											rt = new RobotTank(26,20,tankType,tankDir(),false,fightPanel);
											break;
										case 1:
											rt = new RobotTank(326,20,tankType,tankDir(),false,fightPanel);
											break;
										case 2:
											rt = new RobotTank(626,20,tankType,tankDir(),false,fightPanel);
											break;
										default:break;
										}
									}
									
									if(Status.getStage()>=4)
									{
										for(int i=0; i<eTanksList.size(); i++)
										{
											rt = (RobotTank)eTanksList.get(i);
											tanksList.add(rt);
											new Thread(new RobotTanksThread(rt)).start();
										}
										eTanksList.clear();
									}else{
										tanksList.add(rt);
										new Thread(new RobotTanksThread(rt)).start();
									}
								}else if(tanksList.size()==1 && Status.getEnemyTankNumber()<=0)
								{
									if(Status.getStage()<12)
									{
										Status.setUpdate(true);
									}else{
										System.out.println("WIN");
									}
								}
							}
						}
						Thread.sleep(3000);
					} catch (InterruptedException e)
					{
						e.printStackTrace();
						System.exit(-1);
					}
				}
			}
			
			public Tank.TANK_DIR tankDir()
			{
				if(Status.getStage()>=4)
				{
					int random = Tank.rn.nextInt(3);
					if(random==0)
					{
						return Tank.TANK_DIR.D;
					}else if(random==1){
						return Tank.TANK_DIR.L;
					}else if(random==2){
						return Tank.TANK_DIR.R;
					}else if(random==3){
						return Tank.TANK_DIR.D;
					}
				}
				return Tank.TANK_DIR.D;
			}
		}
		
		class RobotTanksThread implements Runnable  //机器人Tank的行动线程
		{
			RobotTank rt;
			public RobotTanksThread(RobotTank rt)
			{
				this.rt = rt;
			}
			public void run()
			{
				while(rt.isLive)
				{
					try
					{
						if(moveFlag)
						{
							if(rt.isAppear)
							{
								rt.autoAction();
							}
						}
						Thread.sleep(rt.TANKSPEED);
					} catch (InterruptedException e)
					{
						e.printStackTrace();
						System.exit(-1);
					}
				}
			}
		}
		
		class read implements Runnable //血量测试
		{
			public void run()
			{
				while(true)
				{
					int i = ut.tankHealthPoint.getHealthPoint();
					i--;
					ut.tankHealthPoint.setHealthPoint(i);
					
					System.out.println(ut.tankHealthPoint.getHealthPoint());
					try
					{
						Thread.sleep(13);
					} catch (InterruptedException e)
					{
						e.printStackTrace();
						System.exit(-1);
					}
				}
			}
		}
	
	//	循环播放背景音乐
	class PlaySound implements Runnable
	{
		public void run() {
			while(true)
			{
				AePlayWave apw=new AePlayWave("sounds/stage1.wav");
				apw.start();
				try {
						Thread.sleep(118000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
}