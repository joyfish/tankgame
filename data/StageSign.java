package data;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

public class StageSign{
	boolean isDrawStart=true; //游戏开始、换关卡、攻击次数时绘制标志
	boolean repaintFlag=true; //标志状态是否停滞（暂停游戏时）
	int step;
	int stepDelay;
	int numStep;
	
	FightPanel fightPanel=null;
	Tank tk = null;

	static boolean init=false; //第一次画是将图片载入内存
	static boolean isCheckStage=true; //初始化图片位置
	static boolean isCheckFight=false;
	static boolean drawStageOver=false;
	static final Toolkit TLK=Toolkit.getDefaultToolkit();
	
	public static final Image[] STAGE=
	{
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/stage/stage1.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/stage/stage1.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/stage/stage1.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/stage/stage1.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/stage/stage1.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/stage/stage1.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/stage/stage1.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/stage/stage2.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/stage/stage3.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/stage/stage4.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/stage/stage5.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/stage/stage6.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/stage/stage7.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/stage/stage8.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/stage/stage9.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/stage/stage10.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/stage/1.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/stage/1.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/stage/1.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/stage/1.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/stage/1.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/stage/1.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/stage/1.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/stage/2.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/stage/3.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/stage/4.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/stage/5.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/stage/6.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/stage/7.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/stage/8.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/stage/9.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/stage/10.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/stage/11.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/stage/11.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/stage/11.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/stage/11.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/stage/11.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/stage/11.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/stage/11.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/stage/12.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/stage/13.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/stage/14.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/stage/15.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/stage/16.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/stage/17.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/stage/18.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/stage/19.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/stage/20.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/stage/21.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/stage/21.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/stage/21.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/stage/21.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/stage/21.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/stage/21.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/stage/21.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/stage/22.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/stage/23.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/stage/24.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/stage/25.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/stage/26.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/stage/27.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/stage/28.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/stage/29.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/stage/30.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/stage/31.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/stage/31.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/stage/31.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/stage/31.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/stage/31.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/stage/31.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/stage/31.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/stage/32.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/stage/33.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/stage/34.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/stage/35.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/stage/36.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/stage/37.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/stage/38.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/stage/39.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/stage/40.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/stage/41.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/stage/41.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/stage/41.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/stage/41.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/stage/41.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/stage/41.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/stage/41.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/stage/42.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/stage/43.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/stage/44.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/stage/45.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/stage/46.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/stage/47.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/stage/48.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/stage/49.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/stage/50.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/stage/51.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/stage/51.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/stage/51.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/stage/51.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/stage/51.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/stage/51.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/stage/51.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/stage/52.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/stage/53.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/stage/54.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/stage/55.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/stage/56.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/stage/57.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/stage/58.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/stage/59.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/stage/60.png"))
	};
	public static final Image[] FIGHT=
	{
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/fight/fight1.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/fight/fight1.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/fight/fight1.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/fight/fight1.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/fight/fight1.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/fight/fight1.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/fight/fight1.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/fight/fight2.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/fight/fight3.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/fight/fight4.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/fight/fight5.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/fight/fight6.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/fight/fight7.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/fight/fight8.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/fight/fight9.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/fight/fight10.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/fight/1.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/fight/1.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/fight/1.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/fight/1.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/fight/1.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/fight/1.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/fight/1.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/fight/2.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/fight/3.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/fight/4.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/fight/5.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/fight/6.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/fight/7.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/fight/8.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/fight/9.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/fight/10.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/fight/11.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/fight/11.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/fight/11.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/fight/11.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/fight/11.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/fight/11.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/fight/11.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/fight/12.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/fight/13.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/fight/14.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/fight/15.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/fight/16.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/fight/17.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/fight/18.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/fight/19.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/fight/20.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/fight/21.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/fight/21.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/fight/21.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/fight/21.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/fight/21.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/fight/21.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/fight/21.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/fight/22.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/fight/23.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/fight/24.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/fight/25.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/fight/26.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/fight/27.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/fight/28.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/fight/29.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/fight/30.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/fight/31.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/fight/31.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/fight/31.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/fight/31.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/fight/31.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/fight/31.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/fight/31.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/fight/32.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/fight/33.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/fight/34.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/fight/35.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/fight/36.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/fight/37.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/fight/38.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/fight/39.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/fight/40.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/fight/41.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/fight/41.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/fight/41.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/fight/41.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/fight/41.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/fight/41.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/fight/41.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/fight/42.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/fight/43.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/fight/44.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/fight/45.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/fight/46.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/fight/47.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/fight/48.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/fight/49.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/fight/50.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/fight/51.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/fight/51.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/fight/51.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/fight/51.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/fight/51.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/fight/51.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/fight/51.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/fight/52.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/fight/53.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/fight/54.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/fight/55.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/fight/56.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/fight/57.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/fight/58.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/fight/59.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/fight/60.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/fight/61.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/fight/61.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/fight/61.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/fight/61.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/fight/61.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/fight/61.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/fight/61.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/fight/62.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/fight/63.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/fight/64.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/fight/65.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/fight/66.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/fight/67.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/fight/68.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/fight/69.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/fight/70.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/fight/71.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/fight/71.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/fight/71.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/fight/71.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/fight/71.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/fight/71.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/fight/71.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/fight/72.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/fight/73.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/fight/74.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/fight/75.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/fight/76.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/fight/77.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/fight/78.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/fight/79.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/fight/80.png")),
	};
	public static final Image[] DANGER=
	{
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/danger/danger1.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/danger/danger1.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/danger/danger1.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/danger/danger1.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/danger/danger1.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/danger/danger1.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/danger/danger1.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/danger/danger2.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/danger/danger3.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/danger/danger4.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/danger/danger5.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/danger/danger6.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/danger/danger7.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/danger/danger8.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/danger/danger9.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/danger/danger10.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/danger/1.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/danger/1.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/danger/1.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/danger/1.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/danger/1.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/danger/1.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/danger/1.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/danger/2.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/danger/3.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/danger/4.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/danger/5.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/danger/6.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/danger/7.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/danger/8.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/danger/9.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/danger/11.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/danger/11.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/danger/11.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/danger/11.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/danger/11.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/danger/11.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/danger/11.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/danger/12.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/danger/13.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/danger/14.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/danger/15.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/danger/16.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/danger/17.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/danger/18.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/danger/19.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/danger/20.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/danger/21.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/danger/21.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/danger/21.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/danger/21.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/danger/21.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/danger/21.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/danger/21.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/danger/22.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/danger/23.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/danger/24.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/danger/25.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/danger/26.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/danger/27.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/danger/28.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/danger/29.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/danger/30.png"))
	};
	

	public StageSign(FightPanel fightPanel)
	{
		this.fightPanel = fightPanel;
		
//		new Thread(new deletDelve(this)).start(); //延时弹坑消失
	}
	
	public void draw(Graphics g)
	{
		if(!init)
		{
			for (int i=0;i<STAGE.length;i++)
			{
				g.drawImage(STAGE[i],1000,1000,null);
			}
			
			for (int i=0;i<FIGHT.length;i++)
			{
				g.drawImage(FIGHT[i],1000,1000,null);
			}
			
			for (int i=0;i<DANGER.length;i++)
			{
				g.drawImage(DANGER[i],1000,1000,null);
			}
			init = true;
		}
		
		if(Status.getStage()<4 && !drawStageOver || Status.getStage()==4 && Status.getFight()==1 && !drawStageOver) //画出关卡
		{
			if(isCheckStage)
			{
				isCheckStage = false;
				
				step=-3;
				switch(Status.getStage())
				{
				case 1:numStep=13;break;
				case 2:numStep=29;break;
				case 3:numStep=45;break;
				case 4:numStep=61;break;
				default:break;
				}
			}
			
			if(step>=14 && Status.getFight()==0)  //标志画完
			{
				fightPanel.isDrawStart = false;
				isCheckStage = true;
				return;
			}else if(step>=14 && Status.getFight()==1){
				drawStageOver = true;
				isCheckFight = true;
			}
			
			if(step>=0)
			{
				g.drawImage(STAGE[step],206,130,null);
				g.drawImage(STAGE[numStep],206,130,null);
			}
		}else{  //画出攻击次数
			if(isCheckFight)
			{
				isCheckFight = false;
				
				step=-3;
				switch(Status.getFight())
				{
				case 1:numStep=13;break;
				case 2:numStep=29;break;
				case 3:numStep=45;break;
				case 4:numStep=61;break;
				case 5:numStep=77;break;
				case 6:numStep=93;break;
				case 7:numStep=109;break;
				case 8:numStep=125;break;
				default:break;
				}
			}
			
			if(step>=14)
			{
				fightPanel.isDrawStart = false;
				isCheckFight = true;
				return;
			}
			
			if(step>=0)
			{
				g.drawImage(FIGHT[step],206,130,null);
				g.drawImage(FIGHT[numStep],206,130,null);
				
				switch(Status.getFight())
				{
				case 5:
				{
					g.drawImage(DANGER[step],193,195,null);	
				}break;
				case 6:
				{
					g.drawImage(DANGER[step],180,195,null);
					g.drawImage(DANGER[numStep-80],180,195,null);
				}break;
				case 7:
				{
					g.drawImage(DANGER[step],167,195,null);
					g.drawImage(DANGER[numStep-80],167,195,null);
				}break;
				case 8:
				{
					g.drawImage(DANGER[step],154,195,null);
					g.drawImage(DANGER[numStep-80],154,195,null);
				}break;
				default:break;
				}
			}
		}
		
		//g.drawImage(DANGER[0],193,195,null);
		
//		g.drawImage(DANGER[0],180,195,null);
//		g.drawImage(DANGER[16],180,195,null);
//
//		g.drawImage(DANGER[0],167,195,null);
//		g.drawImage(DANGER[32],167,195,null);
//		
//		g.drawImage(DANGER[0],154,195,null);
//		g.drawImage(DANGER[48],154,195,null);
		
		if(repaintFlag)
		{
			stepDelay++;
			if(stepDelay%6==0)
			{
				step++;
				numStep++;
			}
		}
	}

	public static boolean getCheckStage() {
		return isCheckStage;
	}

	public static void setCheckStage(boolean isCheckStage) {
		StageSign.isCheckStage = isCheckStage;
	}
	
//	class deletDelve implements Runnable
//	{
//		Delve delve;
//		public deletDelve(Delve delve)
//		{
//			this.delve = delve;
//		}
//		public void run() {
//			while(true)
//			{
//				try {
//					Thread.sleep(2500);
//					
//					FightPanel.delvesList.remove(delve);
//				} catch (InterruptedException e) {
//					// TODO 自动生成 catch 块
//					e.printStackTrace();
//				}
//			}
//		}
//	}
}