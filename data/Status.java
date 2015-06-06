package data;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Panel;
import java.awt.Toolkit;

public class Status {

	Image statusBar = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/images/panel/statusBar.png"));
	Image statusBarGray = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/images/panel/statusBarGray.png"));
	
	private static int stage;
	private static int topFight;
	private static int fight;
	private static int score;	
	private static int enemyTank_xiNiu;
	private static int enemyTank_shaShou;
	private static int enemyTank_guangLing;
	private static int enemyTank_tianQi;
	private static int enemyTankNumber;
	private static int play1_life;
	private static boolean update;
	
	private boolean isFirstUpdate;
	
	FightPanel fightPanel;
	
	public Status(FightPanel fightPanel)
	{
		stage = 0;
		play1_life = 5;
		update = true;
		isFirstUpdate = true;
		
		this.fightPanel = fightPanel;
		
		new Thread(new UpdateStatus()).start();
	}
	
	public void draw(Graphics g,UserTank ut,boolean gameOver)
	{
		if(!gameOver)
		{
			g.drawImage(statusBar, 680, 0,114,568,null);
			g.setColor(Color.yellow);
		}else{
			g.drawImage(statusBarGray, 680, 0,114,568,null);
			g.setColor(Color.gray);
		}
		Font f = new Font("", Font.PLAIN, 18);
		g.setFont(f);
		
		if(stage>4)
		{
			g.drawString("4", 763, 46);
		}else{
			g.drawString(stage+" ", 763, 46);
		}
		
		if(topFight!=0)
		{
			g.drawString(fight+" ", 747, 70);
//			g.drawString(topFight+" ", 769, 70);
			g.drawString("8 ", 769, 70);
		}
		
		if(score<10)
		{
			g.drawString(score+" ", 734, 128);
		}else if(score<100){
			g.drawString(score+" ", 729, 128);
		}else if(score<1000){
			g.drawString(score+" ", 724, 128);
		}else if(score<10000){
			g.drawString(score+" ", 719, 128);
		}else if(score<100000){
			g.drawString(score+" ", 714, 128);
		}else{
			g.drawString(score+" ", 709, 128);
		}
		
		if(enemyTank_xiNiu<10)
		{
			g.drawString(enemyTank_xiNiu+" ", 764, 194);
		}else{
			g.drawString(enemyTank_xiNiu+" ", 759, 194);
		}
		if(enemyTank_shaShou<10)
		{
			g.drawString(enemyTank_shaShou+" ", 764, 224);
		}else{
			g.drawString(enemyTank_shaShou+" ", 759, 224);
		}
		if(enemyTank_guangLing<10)
		{
			g.drawString(enemyTank_guangLing+" ", 764, 255);
		}else{
			g.drawString(enemyTank_guangLing+" ", 759, 255);
		}
		if(enemyTank_tianQi<10)
		{
			g.drawString(enemyTank_tianQi+" ", 764, 283);
		}else{
			g.drawString(enemyTank_tianQi+" ", 759, 283);
		}
		
		if(!gameOver)
		{
			if(play1_life>=3)
			{
				g.setColor(Color.green);
			}else{
				g.setColor(Color.red);
			}
		}else{
			g.setColor(Color.gray);
		}
		
		if(play1_life<10)
		{
			g.drawString(play1_life+" ", 735, 372);
		}else{
			g.drawString(play1_life+" ", 730, 372);
		}
		
		//Color c=g.getColor();
		
		if(!gameOver)
		{
			if(ut.tankHealthPoint.getHealthPoint()<= 400)
			{
				g.setColor(Color.red);
			}else if(ut.tankHealthPoint.getHealthPoint() <= 900)
			{
				g.setColor(Color.orange);
			}else
			{
				g.setColor(Color.green);
			}
		}else{
			g.setColor(Color.gray);
		}
		
		if(ut.tankHealthPoint.getHealthPoint()>=1000)
		{
			g.drawString(ut.tankHealthPoint.getHealthPoint()+" ", 694, 412);
		}else if(ut.tankHealthPoint.getHealthPoint()>=100)
		{
			g.drawString(ut.tankHealthPoint.getHealthPoint()+" ", 704, 412);
		}else if(ut.tankHealthPoint.getHealthPoint()>=10)
		{
			g.drawString(ut.tankHealthPoint.getHealthPoint()+" ", 714, 412);
		}else{
			g.drawString(ut.tankHealthPoint.getHealthPoint()+" ", 724, 412);
		}
		
		if(!gameOver)
		{
			if(ut.tankHealthPoint.getTopHealthPoint()<= 400)
			{
				g.setColor(Color.red);
			}else if(ut.tankHealthPoint.getTopHealthPoint() <= 900)
			{
				g.setColor(Color.orange);
			}else
			{
				g.setColor(Color.green);
			}
		}else{
			g.setColor(Color.gray);
		}
		
		g.drawString(ut.tankHealthPoint.getTopHealthPoint()+" ", 745, 412);
		
		//g.setColor(c);
	}
	
	public static void updateEnemyTankNumber()
	{
		enemyTankNumber = enemyTank_xiNiu+enemyTank_shaShou+enemyTank_guangLing+enemyTank_tianQi;
	}
	
	public static void addScore(int score) //加分
	{
		Status.score += score;
	}
	
	public static void addLife() //加生命
	{
		play1_life++;
	}
	
	public static void cutEnemyTankNumber(int tankType) //减少敌方等待区tank数量
	{
		switch(tankType)
		{
		case 0:
			enemyTank_xiNiu--;
			break;
		case 2:
			enemyTank_shaShou--;
			break;
		case 4:
			enemyTank_guangLing--;
			break;
		case 6:
			enemyTank_tianQi--;
			break;
			default:break;
		}
		Status.updateEnemyTankNumber();
	}
	
	public static void cutEnemyTankNumber(int tankType, int num) //减少敌方待出tank数量
	{
		switch(tankType)
		{
		case 0:
		case 1:
			enemyTank_xiNiu -= num;
			break;
		case 2:
		case 3:
			enemyTank_shaShou -= num;
			break;
		case 4:
		case 5:
			enemyTank_guangLing -= num;
			break;
		case 6:
		case 7:
			enemyTank_tianQi -= num;
			break;
			default:break;
		}
		Status.updateEnemyTankNumber();
	}
	
	public static boolean cutPlay1_life() //减少玩家生命数
	{
		if(play1_life>0){
			play1_life--;
			return true;
		}else{
			return false;
		}
	}
	
	class UpdateStatus implements Runnable  //加载关卡数据
	{
		public void run() {
			try
			{
				while(true)
				{
					if(update)
					{
						update = false;
//						stage = Stage.getDate()[stage][0];
						stage++;
						Status.topFight = Stage.getDate()[stage-1][1];
						Status.fight = Stage.getDate()[stage-1][1];
						Status.enemyTank_xiNiu = Stage.getDate()[stage-1][2];
						Status.enemyTank_shaShou = Stage.getDate()[stage-1][3];
						Status.enemyTank_guangLing = Stage.getDate()[stage-1][4];
						Status.enemyTank_tianQi = Stage.getDate()[stage-1][5];
						Status.updateEnemyTankNumber();
						
						if(isFirstUpdate)
						{
							isFirstUpdate = false;
						}else{
							if(stage<5)
							{
								fightPanel.itemList.clear();
								fightPanel.roadBlockList.clear();

								fightPanel.ut.setXY(210, 520);
								fightPanel.ut.tankDir = Tank.TANK_DIR.U;
							}
//							fightPanel.itemList.clear();
//							fightPanel.roadBlockList.clear();
//							fightPanel.roadBlockList.clear();
//							fightPanel.roadBlockList.clear();
							switch(stage)
							{
							case 2:
								fightPanel.roadBlockList.add(new Wall(140,110,Wall.WALLTYPE.STAGE2,fightPanel));
								fightPanel.roadBlockList.add(new Mountain(0,-6,Mountain.MOUNTAINTYPE.STAGE2,fightPanel));
								fightPanel.roadBlockList.add(new Forest(0,0,Forest.FORESTTYPE.STAGE2,fightPanel));
								break;
							case 3:
								fightPanel.roadBlockList.add(new Mountain(0,0,Mountain.MOUNTAINTYPE.STAGE3,fightPanel));
								fightPanel.roadBlockList.add(new Forest(0,0,Forest.FORESTTYPE.STAGE3,fightPanel));
								fightPanel.roadBlockList.add(new River(0,0,River.RIVERTYPE.STAGE3,fightPanel));
								break;
							case 4:
								fightPanel.roadBlockList.add(new Wall(136,-140,Wall.WALLTYPE.STAGE4,fightPanel));
								fightPanel.roadBlockList.add(new Mountain(0,0,Mountain.MOUNTAINTYPE.STAGE4,fightPanel));
								fightPanel.roadBlockList.add(new Forest(0,0,Forest.FORESTTYPE.STAGE4,fightPanel));
								fightPanel.base.setXY(296, 256); //改变基地位置
								fightPanel.ut.setXY(210,495);//改变玩家Tank位置
								break;
							default:break;
							}
						}
						
						StageSign.setCheckStage(true); //画出关卡标志
						fightPanel.isDrawStart = true;
						fightPanel.repaint();
					}
					
					if(stage>11)
					{
						break;
					}
					
					Thread.sleep(2000);
				}
			} catch (InterruptedException e)
			{
				e.printStackTrace();
				System.exit(-1);
			}
		}
	}

	public static int getEnemyTank_guangLing() {
		return enemyTank_guangLing;
	}

	public static void setEnemyTank_guangLing(int enemyTank_guangLing) {
		Status.enemyTank_guangLing = enemyTank_guangLing;
	}

	public static int getEnemyTank_shaShou() {
		return enemyTank_shaShou;
	}

	public static void setEnemyTank_shaShou(int enemyTank_shaShou) {
		Status.enemyTank_shaShou = enemyTank_shaShou;
	}

	public static int getEnemyTank_tianQi() {
		return enemyTank_tianQi;
	}

	public static void setEnemyTank_tianQi(int enemyTank_tianQi) {
		Status.enemyTank_tianQi = enemyTank_tianQi;
	}

	public static int getEnemyTank_xiNiu() {
		return enemyTank_xiNiu;
	}

	public static void setEnemyTank_xiNiu(int enemyTank_xiNiu) {
		Status.enemyTank_xiNiu = enemyTank_xiNiu;
	}

	public static int getEnemyTankNumber() {
		return enemyTankNumber;
	}

	public static void setEnemyTankNumber(int enemyTankNumber) {
		Status.enemyTankNumber = enemyTankNumber;
	}

	public static int getFight() {
		return fight;
	}

	public static void setFight(int fight) {
		Status.fight = fight;
	}

	public static int getPlay1_life() {
		return play1_life;
	}

	public static void setPlay1_life(int play1_life) {
		Status.play1_life = play1_life;
	}

	public static int getScore() {
		return score;
	}

	public static void setScore(int score) {
		Status.score = score;
	}

	public static int getStage() {
		return stage;
	}

	public static void setStage(int stage) {
		Status.stage = stage;
	}

	public static int getTopFight() {
		return topFight;
	}

	public static void setTopFight(int topFight) {
		Status.topFight = topFight;
	}

	public static boolean getUpdate() {
		return update;
	}

	public static void setUpdate(boolean update) {
		Status.update = update;
	}
}
