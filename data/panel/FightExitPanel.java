package data.panel;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Panel;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.Vector;

import data.Load;
import data.Records;
import data.Status;

public class FightExitPanel {
	static final Toolkit TLK=Toolkit.getDefaultToolkit();
	public static final Image[] PANELITEMS=
	{
		TLK.getImage(FightExitPanel.class.getClassLoader().getResource("images/panel/fightExitPanel/dark.png")),
		TLK.getImage(FightExitPanel.class.getClassLoader().getResource("images/panel/fightExitPanel/panel.png")),
		TLK.getImage(FightExitPanel.class.getClassLoader().getResource("images/panel/fightExitPanel/m1.png")),
		TLK.getImage(FightExitPanel.class.getClassLoader().getResource("images/panel/fightExitPanel/m2.png")),
		TLK.getImage(FightExitPanel.class.getClassLoader().getResource("images/panel/fightExitPanel/m3.png")),
		TLK.getImage(FightExitPanel.class.getClassLoader().getResource("images/panel/fightExitPanel/m4.png")),
		TLK.getImage(FightExitPanel.class.getClassLoader().getResource("images/panel/fightExitPanel/m0.png")),
		TLK.getImage(FightExitPanel.class.getClassLoader().getResource("images/panel/fightExitPanel/back0.png")),
		TLK.getImage(FightExitPanel.class.getClassLoader().getResource("images/panel/fightExitPanel/back1.png")),
		TLK.getImage(FightExitPanel.class.getClassLoader().getResource("images/panel/fightExitPanel/save.png")),
		TLK.getImage(FightExitPanel.class.getClassLoader().getResource("images/panel/fightExitPanel/smallPanel.png")),
		TLK.getImage(FightExitPanel.class.getClassLoader().getResource("images/panel/fightExitPanel/saveName0.png")),
		TLK.getImage(FightExitPanel.class.getClassLoader().getResource("images/panel/fightExitPanel/saveName1.png")),
		TLK.getImage(FightExitPanel.class.getClassLoader().getResource("images/panel/fightExitPanel/fuGai0.png")),
		TLK.getImage(FightExitPanel.class.getClassLoader().getResource("images/panel/fightExitPanel/fuGai1.png")),
		TLK.getImage(FightExitPanel.class.getClassLoader().getResource("images/panel/fightExitPanel/load.png")),
		TLK.getImage(FightExitPanel.class.getClassLoader().getResource("images/panel/fightExitPanel/help.png")),
		TLK.getImage(FightExitPanel.class.getClassLoader().getResource("images/panel/fightExitPanel/exit1.png")),
		TLK.getImage(FightExitPanel.class.getClassLoader().getResource("images/panel/fightExitPanel/exit2.png")),
		TLK.getImage(FightExitPanel.class.getClassLoader().getResource("images/panel/fightExitPanel/exit3.png")),
		TLK.getImage(FightExitPanel.class.getClassLoader().getResource("images/panel/fightExitPanel/exit0.png")),
		TLK.getImage(FightExitPanel.class.getClassLoader().getResource("images/panel/fightExitPanel/record0.png")),
		TLK.getImage(FightExitPanel.class.getClassLoader().getResource("images/panel/fightExitPanel/record1.png")),
		TLK.getImage(FightExitPanel.class.getClassLoader().getResource("images/panel/fightExitPanel/swhich.png")),
		TLK.getImage(FightExitPanel.class.getClassLoader().getResource("images/panel/fightExitPanel/red.png")),
		TLK.getImage(FightExitPanel.class.getClassLoader().getResource("images/panel/fightExitPanel/yellow.png")),
		TLK.getImage(FightExitPanel.class.getClassLoader().getResource("images/panel/fightExitPanel/score0.png")),
		TLK.getImage(FightExitPanel.class.getClassLoader().getResource("images/panel/fightExitPanel/score1.png"))
	};
	
	static boolean init = false; //第一次画是将图片载入内存
	boolean visible = true; //控制显示输入存档时的提示符"_"
	public boolean creatInput = false; //控制显示输入存档时的提示符"_"(创建线程)
	public boolean finishInput = false; //完成输入
	public boolean hasSaved = false; //是否已经保存过游戏
	public Vector<Load> rEDs; //存储每行数据(存档、暂存读取用于显示)
	public Vector<Records> sCOs;//存储每行数据（得分、暂存读取用于显示）
	public Vector<Load> savREDs; //存储每行数据(存档、暂存读取用于显示)
	public Vector<Records> savSCOs;//存储每行数据（得分、暂存读取用于显示）
	public Vector<Character> inputChars; //记录用户输入字符
	public String saveName;
	public Input input;
	
	data.FightPanel fightPanel;

	Font myFont;
	FileReader fr;
	BufferedReader br;
	FileWriter fw;
	BufferedWriter bw;
	public String s;
	public Load rED;
	public Records sCO;
	public int drawRecordControl;	//记录选择的哪一条记录
	
	public FightExitPanel(data.FightPanel fightPanel)
	{
		this.fightPanel = fightPanel;
		rEDs = new Vector<Load>();
		sCOs = new Vector<Records>(); 
		savREDs = new Vector<Load>();
		savSCOs = new Vector<Records>(); 
		inputChars = new Vector<Character>();
		
		 //从文件中读取记录
		 try {
				fr = new FileReader("records/state.txt");
				br = new BufferedReader(fr);
				
				while((s=br.readLine())!=null)
				{
					String []array = s.split(" "); 
					rED = new Load(Integer.parseInt(array[0]),Integer.parseInt(array[1]),Integer.parseInt(array[2]),array[3],array[4],array[5],array[6],array[7],array[8],array[9]);
					rEDs.add(rED);
				}
				
				fr = new FileReader("records/score.txt");
				br = new BufferedReader(fr);
				
				while((s=br.readLine())!=null)
				{
					String []array = s.split(" "); 
					sCO = new Records(Integer.parseInt(array[0]),Integer.parseInt(array[1]),array[2]);
					sCOs.add(sCO);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}finally{
				try {
					br.close();
					fr.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
	}
	
	public void draw(Graphics g,int control)
	{
		if(!init)
		{
			for (int i=0;i<PANELITEMS.length;i++)
			{
				g.drawImage(PANELITEMS[i],1000,1000,null);
			}
			init = true;
		}
		
		switch(control)
		{
		case 1://主菜单
			g.drawImage(PANELITEMS[0], 0, 0,null);
			g.drawImage(PANELITEMS[1], 211, 74,null);
			g.drawImage(PANELITEMS[2], 211, 74,null);
			g.drawImage(PANELITEMS[7], 211, 74,null);
			break;
		case 2:
			g.drawImage(PANELITEMS[0], 0, 0,null);
			g.drawImage(PANELITEMS[1], 211, 74,null);
			g.drawImage(PANELITEMS[3], 211, 74,null);
			g.drawImage(PANELITEMS[7], 211, 74,null);
			break;
		case 3:
			g.drawImage(PANELITEMS[0], 0, 0,null);
			g.drawImage(PANELITEMS[1], 211, 74,null);
			g.drawImage(PANELITEMS[4], 211, 74,null);
			g.drawImage(PANELITEMS[7], 211, 74,null);
			break;
		case 4:
			g.drawImage(PANELITEMS[0], 0, 0,null);
			g.drawImage(PANELITEMS[1], 211, 74,null);
			g.drawImage(PANELITEMS[5], 211, 74,null);
			g.drawImage(PANELITEMS[7], 211, 74,null);
			break;
		case 5:
			g.drawImage(PANELITEMS[0], 0, 0,null);
			g.drawImage(PANELITEMS[1], 211, 74,null);
			g.drawImage(PANELITEMS[6], 211, 74,null);
			g.drawImage(PANELITEMS[8], 211, 74,null);
			break;
		case 11://保存进度
		case 12:
		case 13:
		case 14:
		case 15:
		case 16:
		case 17:
			if(control==11)
			{
				drawRecordControl = 1;
			}else if(control==12){
				drawRecordControl = 2;
			}else if(control==13){
				drawRecordControl = 3;
			}else if(control==14){
				drawRecordControl = 4;
			}else if(control==15){
				drawRecordControl = 5;
			}else if(control==16){
				drawRecordControl = 6;
			}
			g.drawImage(PANELITEMS[0], 0, 0,null);
			g.drawImage(PANELITEMS[1], 211, 74,null);
			g.drawImage(PANELITEMS[9], 211, 74,null);
			drawRecord(g,control);
			if(control != 17)
			{
				g.drawImage(PANELITEMS[7], 211, 74,null);
			}else{
				drawRecordControl = 0;
				g.drawImage(PANELITEMS[8], 211, 74,null);
			}
			break;
		case 21://装载进度
		case 22:
		case 23:
		case 24:
		case 25:
		case 26:
		case 27:
			if(control==21)
			{
				drawRecordControl = 1;
			}else if(control==22){
				drawRecordControl = 2;
			}else if(control==23){
				drawRecordControl = 3;
			}else if(control==24){
				drawRecordControl = 4;
			}else if(control==25){
				drawRecordControl = 5;
			}else if(control==26){
				drawRecordControl = 6;
			}
			g.drawImage(PANELITEMS[0], 0, 0,null);
			g.drawImage(PANELITEMS[1], 211, 74,null);
			g.drawImage(PANELITEMS[15], 211, 74,null);
			drawRecord(g,control);
			if(control == 27)
			{
				drawRecordControl = 0;
				g.drawImage(PANELITEMS[8], 211, 74,null);
			}else{
				if(control != getRecordNumber("records/state.txt")+20)
				{
					g.drawImage(PANELITEMS[7], 211, 74,null);
				}else{
					drawRecordControl = 0;
					g.drawImage(PANELITEMS[8], 211, 74,null);
				}
			}
			break;
		case 28:
			g.drawImage(PANELITEMS[0], 0, 0,null);
			g.drawImage(PANELITEMS[1], 211, 74,null);
			g.drawImage(PANELITEMS[15], 211, 74,null);
			g.drawImage(PANELITEMS[8], 211, 74,null);
			break;
		case 31://游戏帮助
			g.drawImage(PANELITEMS[0], 0, 0,null);
			g.drawImage(PANELITEMS[1], 211, 74,null);
			g.drawImage(PANELITEMS[16], 211, 74,null);
			g.drawImage(PANELITEMS[7], 211, 74,null);
			break;
		case 32:
			g.drawImage(PANELITEMS[0], 0, 0,null);
			g.drawImage(PANELITEMS[1], 211, 74,null);
			g.drawImage(PANELITEMS[16], 211, 74,null);
			g.drawImage(PANELITEMS[8], 211, 74,null);
			break;
		case 41://结束游戏
			g.drawImage(PANELITEMS[0], 0, 0,null);
			g.drawImage(PANELITEMS[1], 211, 74,null);
			g.drawImage(PANELITEMS[17], 211, 74,null);
			g.drawImage(PANELITEMS[7], 211, 74,null);
			break;
		case 42:
			g.drawImage(PANELITEMS[0], 0, 0,null);
			g.drawImage(PANELITEMS[1], 211, 74,null);
			g.drawImage(PANELITEMS[18], 211, 74,null);
			g.drawImage(PANELITEMS[7], 211, 74,null);
			break;
		case 43:
			g.drawImage(PANELITEMS[0], 0, 0,null);
			g.drawImage(PANELITEMS[1], 211, 74,null);
			g.drawImage(PANELITEMS[19], 211, 74,null);
			g.drawImage(PANELITEMS[7], 211, 74,null);
			break;
		case 44:
			g.drawImage(PANELITEMS[0], 0, 0,null);
			g.drawImage(PANELITEMS[1], 211, 74,null);
			g.drawImage(PANELITEMS[20], 211, 74,null);
			g.drawImage(PANELITEMS[8], 211, 74,null);
			break;
		case 111:
		case 112://存档
			g.drawImage(PANELITEMS[1], 211, 74,null);
			g.drawImage(PANELITEMS[9], 211, 74,null);
			drawRecord(g,control);
			if(control != getRecordNumber("records/state.txt")+10)
			{
				g.drawImage(PANELITEMS[7], 211, 74,null);
			}else{
				g.drawImage(PANELITEMS[8], 211, 74,null);
			}
			g.drawImage(PANELITEMS[0], 0, 0,null);
			
			g.drawImage(PANELITEMS[10], 211, 74,null);
			if(control==111){
				g.drawImage(PANELITEMS[12], 211, 74,null);
			}else{
				g.drawImage(PANELITEMS[11], 211, 74,null);
			}
			
			
			g.setFont(new Font("Arial",Font.BOLD,24));
			g.setColor(Color.GRAY); //阴影效果
			if(Status.getStage()<4){
				g.drawString("STAGE "+Status.getStage(), 272, 307);
			}else{
				g.drawString("S "+Status.getStage(), 272, 307);
				g.drawString("F "+Status.getFight(), 332, 307);
			}
			
			g.setColor(Color.DARK_GRAY); //阴影效果
			if(Status.getStage()<4){
				g.drawString("STAGE "+Status.getStage(), 271, 306);
			}else{
				g.drawString("S "+Status.getStage(), 271, 306);
				g.drawString("F "+Status.getFight(), 331, 306);
			}
			
			g.setColor(Color.yellow);
			if(Status.getStage()<4){
				g.drawString("STAGE "+Status.getStage(), 270, 305);
			}else{
				g.drawString("S "+Status.getStage(), 270, 305);
				g.drawString("F "+Status.getFight(), 330, 305);
			}
			
			g.setColor(Color.white);
			if(visible)
			{
				switch(inputChars.size())
				{
				case 0:g.drawString("_", 417, 307);break;		
				case 1:g.drawString("_", 434, 307);break;		
				case 2:g.drawString("_", 451, 307);break;		
				case 3:g.drawString("_", 468, 307);break;		
				case 4:g.drawString("_", 485, 307);break;		
				case 5:
					g.setColor(Color.gray);
					g.drawString("_", 502, 307);
					break;
				default:break;
				}
			}
			
			g.setColor(Color.gray);
			for(int i=0; i<inputChars.size();i++){
				char ch = inputChars.get(i);
				g.drawString(ch+"", 417+i*17, 307);
			}
			g.setColor(Color.darkGray);
			for(int i=0; i<inputChars.size();i++){
				char ch = inputChars.get(i);
				g.drawString(ch+"", 416+i*17, 306);
			}
			g.setColor(Color.white);
			for(int i=0; i<inputChars.size();i++){
				char ch = inputChars.get(i);
				g.drawString(ch+"", 415+i*17, 305);
			}
			
			if(!creatInput)
			{
				System.out.println("创建 。。");
				creatInput = true;
				finishInput = false;
				input = new Input();  //显示用户所输入存档的名称，存档输入
				new Thread(input).start();
				fightPanel.mainFrame.addKeyListener(input);
			}
			
			break;
		case 113:
		case 114://覆盖存档
			//System.out.println(control);
			g.drawImage(PANELITEMS[1], 211, 74,null);
			g.drawImage(PANELITEMS[9], 211, 74,null);
			
			drawRecord(g,control);
			
			if(control != getRecordNumber("records/state.txt")+10)
			{
				g.drawImage(PANELITEMS[7], 211, 74,null);
			}else{
				g.drawImage(PANELITEMS[8], 211, 74,null);
			}
			
			g.drawImage(PANELITEMS[0], 0, 0,null);
			
			g.drawImage(PANELITEMS[10], 211, 74,null);
			if(control==113){
				g.drawImage(PANELITEMS[14], 211, 74,null);
			}else{
				g.drawImage(PANELITEMS[13], 211, 74,null);
			}
			
			Color c=g.getColor();	
			Font f = g.getFont();
			
			g.setFont(new Font("Arial",Font.BOLD,24));

			g.setColor(Color.GRAY); //阴影效果
			if(rEDs.get(drawRecordControl-1).state<4){
				g.drawString("STAGE "+rEDs.get(drawRecordControl-1).state+"", 272, 307);
			}else{
				g.drawString("S "+rEDs.get(drawRecordControl-1).state+"", 272, 307);
				g.drawString("F "+rEDs.get(drawRecordControl-1).fight+"", 332, 307);
			}
			g.drawString(rEDs.get(drawRecordControl-1).name, 417, 307);
			
			g.setColor(Color.DARK_GRAY); //阴影效果
			if(rEDs.get(drawRecordControl-1).state<4){
				g.drawString("STAGE "+rEDs.get(drawRecordControl-1).state+"", 271, 306);
			}else{
				g.drawString("S "+rEDs.get(drawRecordControl-1).state+"", 271, 306);
				g.drawString("F "+rEDs.get(drawRecordControl-1).fight+"", 331, 306);
			}
			g.drawString(rEDs.get(drawRecordControl-1).name, 416, 306);
			
			g.setColor(Color.yellow);
			if(rEDs.get(drawRecordControl-1).state<4){
				g.drawString("STAGE "+rEDs.get(drawRecordControl-1).state+"", 270, 305);
			}else{
				g.drawString("S "+rEDs.get(drawRecordControl-1).state+"", 270, 305);
				g.drawString("F "+rEDs.get(drawRecordControl-1).fight+"", 330, 305);
			}
			g.drawString(rEDs.get(drawRecordControl-1).name, 415, 305);
			g.setFont(f);
			g.setColor(c);
			break;
		case 421: //保存战绩
		case 422:
			g.drawImage(PANELITEMS[0], 0, 0,null);
			g.drawImage(PANELITEMS[1], 211, 74,null);
			
			switch(drawRecordControl)
			{  //位置提示红色区域
			case 0:
				g.drawImage(PANELITEMS[23], 211, 202,null);
				break;
			case 1:g.drawImage(PANELITEMS[23], 211, 238,null);
				break;
			case 2:
				g.drawImage(PANELITEMS[23], 211, 274,null);
				break;
			case 3:
				g.drawImage(PANELITEMS[23], 211, 310,null);
				break;
			case 4:
				g.drawImage(PANELITEMS[23], 211, 345,null);
				break;
			case 5:
				g.drawImage(PANELITEMS[23], 211, 381,null);
				break;
			default:break;
			}
			
			if(control==421) //是否新纪录
			{
				g.drawImage(PANELITEMS[22], 211, 74,null);
			}else{
				g.drawImage(PANELITEMS[27], 211, 74,null);
			}
			
			drawScores(g,control);
			
			g.setColor(Color.white);
			if(visible)
			{
				switch(inputChars.size())
				{
				case 0:g.drawString("_", 470, 232+drawRecordControl*36);break;		
				case 1:g.drawString("_", 487, 232+drawRecordControl*36);break;		
				case 2:g.drawString("_", 504, 232+drawRecordControl*36);break;		
				case 3:g.drawString("_", 521, 232+drawRecordControl*36);break;		
				case 4:g.drawString("_", 538, 232+drawRecordControl*36);break;		
				case 5:
					g.setColor(Color.gray);
					g.drawString("_", 555, 232+drawRecordControl*36);
					break;	
				default:break;			
				}
			}
			
			g.setColor(Color.gray);
			for(int i=0; i<inputChars.size();i++){
				char ch = inputChars.get(i);
				g.drawString(ch+"", 468+i*17, 234+drawRecordControl*36);
			}
			g.setColor(Color.darkGray);
			for(int i=0; i<inputChars.size();i++){
				char ch = inputChars.get(i);
				g.drawString(ch+"", 469+i*17, 233+drawRecordControl*36);
			}
			g.setColor(Color.white);
			for(int i=0; i<inputChars.size();i++){
				char ch = inputChars.get(i);
				g.drawString(ch+"", 470+i*17, 232+drawRecordControl*36);
			}
			
			if(!creatInput)
			{
				creatInput = true;
				finishInput = false;
				input = new Input();  //显示用户所输入存档的名称，存档输入
				new Thread(input).start();
				fightPanel.mainFrame.addKeyListener(input);
			}
			
			break;
		default:break;
		}
	}
	
	public void drawRecord(Graphics g,int control) //画出存档
	{
//		System.out.println(drawRecordControl);
		myFont = new Font("Arial",Font.BOLD,18);
		g.setFont(myFont);
		
		g.setColor(Color.white);
		if(rEDs.get(0).sign==0){
			if(control!=11 && control!=12 && control!=13 && control!=14 && control!=15 && control!=16 && control!=17){  //储存面板 空存档位 可见
				System.out.println("变灰..");
				g.setColor(Color.gray);
			}else if(drawRecordControl==1){
				g.setColor(Color.yellow);
			}
			g.drawString("NULL ", 350, 189);
			g.setColor(Color.white);
		}else{
			if(drawRecordControl==1)
			{
				g.setColor(Color.yellow);
			}
			if(rEDs.get(0).state<4){
				g.drawString("STA "+rEDs.get(0).state+"", 270, 189);
			}else{
				g.drawString("S"+rEDs.get(0).state+"", 270, 189);
				g.drawString(" F"+rEDs.get(0).fight+"", 295, 189);
			}
			g.drawString(rEDs.get(0).name, 350, 189);
			if(intradayRecord(rEDs.get(0).year,rEDs.get(0).month,rEDs.get(0).day))
			{
				g.drawString(rEDs.get(0).hours+":"+rEDs.get(0).minutes+":"+rEDs.get(0).seconds, 430, 189);
			}else{
				g.drawString(rEDs.get(0).year+"-"+rEDs.get(0).month+"-"+rEDs.get(0).day, 430, 189);
			}
			g.setColor(Color.white);
		}
		
		if(rEDs.get(1).sign==0){
			if(control!=11 && control!=12 && control!=13 && control!=14 && control!=15 && control!=16 && control!=17){
				g.setColor(Color.gray);
			}else if(drawRecordControl==2){
				g.setColor(Color.yellow);
			}
			g.drawString("NULL ", 350, 234);
			g.setColor(Color.white);
		}else{
			if(drawRecordControl==2)
			{
				g.setColor(Color.yellow);
			}
			if(rEDs.get(1).state<4){
				g.drawString("STA "+rEDs.get(1).state+"", 270, 234);
			}else{
				g.drawString("S"+rEDs.get(1).state+"", 270, 234);
				g.drawString(" F"+rEDs.get(1).fight+"", 295, 234);
			}
			g.drawString(rEDs.get(1).name, 350, 234);
			if(intradayRecord(rEDs.get(1).year,rEDs.get(1).month,rEDs.get(1).day))
			{
				g.drawString(rEDs.get(1).hours+":"+rEDs.get(1).minutes+":"+rEDs.get(1).seconds, 430, 234);
			}else{
				g.drawString(rEDs.get(1).year+"-"+rEDs.get(1).month+"-"+rEDs.get(1).day, 430, 234);
			}
			g.setColor(Color.white);
		}

		if(rEDs.get(2).sign==0){
			if(control!=11 && control!=12 && control!=13 && control!=14 && control!=15 && control!=16 && control!=17){
				g.setColor(Color.gray);
			}else if(drawRecordControl==3){
				g.setColor(Color.yellow);
			}
			g.drawString("NULL ", 350, 279);
			g.setColor(Color.white);
		}else{
			if(drawRecordControl==3)
			{
				g.setColor(Color.yellow);
			}
			if(rEDs.get(2).state<4){
				g.drawString("STA "+rEDs.get(2).state+"", 270, 279);
			}else{
				g.drawString("S"+rEDs.get(2).state+"", 270, 279);
				g.drawString(" F"+rEDs.get(2).fight+"", 295, 279);
			}
			g.drawString(rEDs.get(2).name, 350, 279);
			if(intradayRecord(rEDs.get(2).year,rEDs.get(2).month,rEDs.get(2).day))
			{
				g.drawString(rEDs.get(2).hours+":"+rEDs.get(2).minutes+":"+rEDs.get(2).seconds, 430, 279);
			}else{
				g.drawString(rEDs.get(2).year+"-"+rEDs.get(2).month+"-"+rEDs.get(2).day, 430, 279);
			}
			g.setColor(Color.white);
		}

		if(rEDs.get(3).sign==0){
			if(control!=11 && control!=12 && control!=13 && control!=14 && control!=15 && control!=16 && control!=17){
				g.setColor(Color.gray);
			}else if(drawRecordControl==4){
				g.setColor(Color.yellow);
			}
			g.drawString("NULL ", 350, 324);
			g.setColor(Color.white);
		}else{
			if(drawRecordControl==4)
			{
				g.setColor(Color.yellow);
			}
			if(rEDs.get(3).state<4){
				g.drawString("STA "+rEDs.get(3).state+"", 270, 324);
			}else{
				g.drawString("S"+rEDs.get(3).state+"", 270, 324);
				g.drawString(" F"+rEDs.get(3).fight+"", 295, 324);
			}
			g.drawString(rEDs.get(3).name, 350, 324);
			if(intradayRecord(rEDs.get(3).year,rEDs.get(3).month,rEDs.get(3).day))
			{
				g.drawString(rEDs.get(3).hours+":"+rEDs.get(3).minutes+":"+rEDs.get(3).seconds, 430, 324);
			}else{
				g.drawString(rEDs.get(3).year+"-"+rEDs.get(3).month+"-"+rEDs.get(3).day, 430, 324);
			}
			g.setColor(Color.white);
		}
		
		if(rEDs.get(4).sign==0){
			if(control!=11 && control!=12 && control!=13 && control!=14 && control!=15 && control!=16 && control!=17){
				g.setColor(Color.gray);
			}else if(drawRecordControl==5){
				g.setColor(Color.yellow);
			}
			g.drawString("NULL ", 350, 369);
			g.setColor(Color.white);
		}else{
			if(drawRecordControl==5)
			{
				g.setColor(Color.yellow);
			}
			if(rEDs.get(4).state<4){
				g.drawString("STA "+rEDs.get(4).state+"", 270, 369);
			}else{
				g.drawString("S"+rEDs.get(4).state+"", 270, 369);
				g.drawString(" F"+rEDs.get(4).fight+"", 295, 369);
			}
			g.drawString(rEDs.get(4).name, 350, 369);
			if(intradayRecord(rEDs.get(4).year,rEDs.get(4).month,rEDs.get(4).day))
			{
				g.drawString(rEDs.get(4).hours+":"+rEDs.get(4).minutes+":"+rEDs.get(4).seconds, 430, 369);
			}else{
				g.drawString(rEDs.get(4).year+"-"+rEDs.get(4).month+"-"+rEDs.get(4).day, 430, 369);
			}
			g.setColor(Color.white);
		}
		
		if(rEDs.get(5).sign==0){
			if(control!=11 && control!=12 && control!=13 && control!=14 && control!=15 && control!=16 && control!=17){
				g.setColor(Color.gray);
			}else if(drawRecordControl==6){
				g.setColor(Color.yellow);
			}
			g.drawString("NULL ", 350, 414);
			g.setColor(Color.white);
		}else{
			if(drawRecordControl==6)
			{
				g.setColor(Color.yellow);
			}
			if(rEDs.get(5).state<4){
				g.drawString("STA "+rEDs.get(5).state+"", 270, 414);
			}else{
				g.drawString("S"+rEDs.get(5).state+"", 270, 414);
				g.drawString(" F"+rEDs.get(5).fight+"", 295, 414);
			}
			g.drawString(rEDs.get(5).name, 350, 414);
			if(intradayRecord(rEDs.get(5).year,rEDs.get(5).month,rEDs.get(5).day))
			{
				g.drawString(rEDs.get(5).hours+":"+rEDs.get(5).minutes+":"+rEDs.get(5).seconds, 430, 414);
			}else{
				g.drawString(rEDs.get(5).year+"-"+rEDs.get(5).month+"-"+rEDs.get(5).day, 430, 414);
			}
			g.setColor(Color.white);
		}
	}
	
	public void drawScores(Graphics g,int control) //画出战绩
	{
		 Font myFont = new Font("Arial",Font.BOLD,24);
		 g.setFont(myFont);

		 g.setColor(Color.white);
		
		 if(savSCOs.get(0).sign!=0){
			g.setColor(Color.GRAY); //阴影效果
		 	g.drawString(savSCOs.get(0).score+"", 360, 234);
		 	if(!savSCOs.get(0).name.equals("!")){
			 	g.drawString(savSCOs.get(0).name, 471, 234);
		 	}
			g.setColor(Color.DARK_GRAY); //阴影效果
		 	g.drawString(savSCOs.get(0).score+"", 359, 233);
		 	if(!savSCOs.get(0).name.equals("!")){
			 	g.drawString(savSCOs.get(0).name, 470, 233);
		 	}
			if(drawRecordControl==0){
				g.setColor(Color.yellow);
			}else{
				g.setColor(Color.white);
			}
		 	g.drawString(savSCOs.get(0).score+"", 358, 232);
		 	if(!savSCOs.get(0).name.equals("!")){
			 	g.drawString(savSCOs.get(0).name, 469, 232);
		 	}
		 }
		 if(savSCOs.get(1).sign!=0){
			g.setColor(Color.GRAY); //阴影效果
		 	g.drawString(savSCOs.get(1).score+"", 360, 270);
		 	if(!savSCOs.get(1).name.equals("!")){
				g.drawString(savSCOs.get(1).name, 471, 270);
		 	}
			g.setColor(Color.DARK_GRAY); //阴影效果
		 	g.drawString(savSCOs.get(1).score+"", 359, 269);
		 	if(!savSCOs.get(1).name.equals("!")){
				g.drawString(savSCOs.get(1).name, 470, 269);
		 	}
			if(drawRecordControl==1){
				g.setColor(Color.yellow);
			}else{
				g.setColor(Color.white);
			}
		 	g.drawString(savSCOs.get(1).score+"", 358, 268);
		 	if(!savSCOs.get(1).name.equals("!")){
				g.drawString(savSCOs.get(1).name, 469, 268);
		 	}
		 }
		 if(savSCOs.get(2).sign!=0){
			g.setColor(Color.GRAY); //阴影效果
			g.drawString(savSCOs.get(2).score+"", 360, 306);
		 	if(!savSCOs.get(2).name.equals("!")){
				g.drawString(savSCOs.get(2).name, 471, 306);
		 	}
			g.setColor(Color.DARK_GRAY); //阴影效果
			g.drawString(savSCOs.get(2).score+"", 359, 305);
		 	if(!savSCOs.get(2).name.equals("!")){
				g.drawString(savSCOs.get(2).name, 470, 305);
		 	}
			if(drawRecordControl==2){
				g.setColor(Color.yellow);
			}else{
				g.setColor(Color.white);
			}
			g.drawString(savSCOs.get(2).score+"", 358, 304);
		 	if(!savSCOs.get(2).name.equals("!")){
				g.drawString(savSCOs.get(2).name, 469, 304);
		 	}
		 }
		 if(savSCOs.get(3).sign!=0){
			g.setColor(Color.GRAY); //阴影效果
			g.drawString(savSCOs.get(3).score+"", 360, 342);
		 	if(!savSCOs.get(3).name.equals("!")){
				g.drawString(savSCOs.get(3).name, 471, 342);
		 	}
			g.setColor(Color.DARK_GRAY); //阴影效果
			g.drawString(savSCOs.get(3).score+"", 359, 341);
		 	if(!savSCOs.get(3).name.equals("!")){
				g.drawString(savSCOs.get(3).name, 470, 341);
		 	}
			if(drawRecordControl==3){
				g.setColor(Color.yellow);
			}else{
				g.setColor(Color.white);
			}
			g.drawString(savSCOs.get(3).score+"", 358, 340);
		 	if(!savSCOs.get(3).name.equals("!")){
				g.drawString(savSCOs.get(3).name, 469, 340);
		 	}
		 }
		 if(savSCOs.get(4).sign!=0){
			g.setColor(Color.GRAY); //阴影效果
			g.drawString(savSCOs.get(4).score+"", 360, 378);
		 	if(!savSCOs.get(4).name.equals("!")){
				g.drawString(savSCOs.get(4).name, 471, 378);
		 	}
			g.setColor(Color.DARK_GRAY); //阴影效果
			g.drawString(savSCOs.get(4).score+"", 359, 377);
		 	if(!savSCOs.get(4).name.equals("!")){
				g.drawString(savSCOs.get(4).name, 470, 377);
		 	}
			if(drawRecordControl==4){
				g.setColor(Color.yellow);
			}else{
				g.setColor(Color.white);
			}
			g.drawString(savSCOs.get(4).score+"", 358, 376);
		 	if(!savSCOs.get(4).name.equals("!")){
				g.drawString(savSCOs.get(4).name, 469, 376);
		 	}
		 }
		 if(savSCOs.get(5).sign!=0){
			g.setColor(Color.GRAY); //阴影效果
			g.drawString(savSCOs.get(5).score+"", 360, 414);
		 	if(!savSCOs.get(5).name.equals("!")){
				g.drawString(savSCOs.get(5).name, 471, 414);
		 	}
			g.setColor(Color.DARK_GRAY); //阴影效果
			g.drawString(savSCOs.get(5).score+"", 359, 413);
		 	if(!savSCOs.get(5).name.equals("!")){
				g.drawString(savSCOs.get(5).name, 470, 413);
		 	}
			if(drawRecordControl==5){
				g.setColor(Color.yellow);
			}else{
				g.setColor(Color.white);
			}
			g.drawString(savSCOs.get(5).score+"", 358, 412);
		 	if(!savSCOs.get(5).name.equals("!")){
				g.drawString(savSCOs.get(5).name, 469, 412);
		 	}
		 }
	}
	
	 public static int getRecordNumber(String path) //获得记录的条数
	 {
		 FileReader fr = null;
		 BufferedReader br = null;
		 String s = null;
		 int number = 0;
		 
		 //从文件中读取记录
		 try {
			fr = new FileReader(path);
			br = new BufferedReader(fr);
			
			while((s=br.readLine())!=null)
			{
				String []array = s.split(" "); 
				if(Integer.parseInt(array[0])!=0){
					number++;
				}
			}
			number++;
	 	} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				br.close();
				fr.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return number;
	 }
	 
	 public static boolean intradayRecord(String year,String month,String day) //判断存档的日期是否为今天
	 {
		 String intraYear;
		 String intraMonth;
		 String intraDay;
		 
		 Calendar c  =  Calendar.getInstance(Locale.CHINESE); 
		    
		 SimpleDateFormat simpleDateTimeFormat  =   new  SimpleDateFormat("yyyy");
		 intraYear =  simpleDateTimeFormat.format(c.getTime());
		 
		 simpleDateTimeFormat  =   new  SimpleDateFormat("MM");
		 intraMonth =  simpleDateTimeFormat.format(c.getTime());
		 
		 simpleDateTimeFormat  =   new  SimpleDateFormat("dd");
		 intraDay =  simpleDateTimeFormat.format(c.getTime());
		 
		 if(year.equals(intraYear) && month.equals(intraMonth) && day.equals(intraDay))
		 {
			 return true;
		 }else{
			 return false;
		 }
	 }
	
	public boolean needSave()
	{
		if(hasSaved==true){
			return false;
		}

		int score = Status.getScore();
		for(drawRecordControl=0; drawRecordControl<6; drawRecordControl++)
		{
			if(sCOs.get(drawRecordControl).score < score){
				break;
			}
		}
		if(drawRecordControl==6){
			return false;
		}else{
			return true;
		}
	}
	
	public void save()
	{
		for(int i=0; i<sCOs.size(); i++){
			System.out.println(sCOs.get(i).score);
		}
		
		String s = null;
		for(int i=0; i<inputChars.size(); i++){
			s = s.concat(inputChars.get(i).toString());
		}
		
		if(s!=null){
			System.out.println("111");
			savSCOs.get(drawRecordControl).name = s;
		}else{
			System.out.println("2222");
			savSCOs.get(drawRecordControl).name = "NULL";
		}

		inputChars.clear();
		
		try {
			fw = new FileWriter("records/score.txt");
			bw = new BufferedWriter(fw);
			
			for(int i=0; i<savSCOs.size(); i++)
			{
				String ss = savSCOs.get(i).sign+" "+savSCOs.get(i).score+" "+savSCOs.get(i).name+"\r\n";
				bw.write(ss);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				bw.close();
				fw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void reSRds(){ //刷新战绩存档,为新记录预留空位
		int i;
		for(i=0; i<drawRecordControl; i++)
		{
			savSCOs.add(sCOs.get(i));
		}
		
		savSCOs.add(new Records(1,Status.getScore(),"!"));
		
		for(i = drawRecordControl; i<5; i++)
		{
			savSCOs.add(sCOs.get(i));
		}
		
//		savSCOs.get(drawRecordControl).score = Status.getScore();
//		savSCOs.get(drawRecordControl).name = "!"; // name为“！”，则不会被画出
	}
	
	class Input implements KeyListener, Runnable //显示用户所输入存档的名称，存档输入
	{
		public void run()
		{
			while(!finishInput)
			{
				try
				{
					if(visible){
						visible = false;
					}else{
						visible = true;
					}
					Thread.sleep(600);
				} catch (InterruptedException e)
				{
					e.printStackTrace();
					System.exit(-1);
				}
			}
		}

		public void keyPressed(KeyEvent e) {
			int key = e.getKeyCode();
			
			if(inputChars.size()<5)
			{
				switch(key)
				{
				case KeyEvent.VK_1: inputChars.add('1');break;
				case KeyEvent.VK_2: inputChars.add('2');break;
				case KeyEvent.VK_3: inputChars.add('3');break;
				case KeyEvent.VK_4: inputChars.add('4');break;
				case KeyEvent.VK_5: inputChars.add('5');break;
				case KeyEvent.VK_6: inputChars.add('6');break;
				case KeyEvent.VK_7: inputChars.add('7');break;
				case KeyEvent.VK_8: inputChars.add('8');break;
				case KeyEvent.VK_9: inputChars.add('9');break;
				case KeyEvent.VK_0: inputChars.add('0');break;
				case KeyEvent.VK_Q: inputChars.add('Q');break;
				case KeyEvent.VK_W: inputChars.add('W');break;
				case KeyEvent.VK_E: inputChars.add('E');break;
				case KeyEvent.VK_R: inputChars.add('R');break;
				case KeyEvent.VK_T: inputChars.add('T');break;
				case KeyEvent.VK_Y: inputChars.add('Y');break;
				case KeyEvent.VK_U: inputChars.add('U');break;
				case KeyEvent.VK_I: inputChars.add('I');break;
				case KeyEvent.VK_O: inputChars.add('O');break;
				case KeyEvent.VK_P: inputChars.add('P');break;
				case KeyEvent.VK_A: inputChars.add('A');break;
				case KeyEvent.VK_S: inputChars.add('S');break;
				case KeyEvent.VK_D: inputChars.add('D');break;
				case KeyEvent.VK_F: inputChars.add('F');break;
				case KeyEvent.VK_G: inputChars.add('G');break;
				case KeyEvent.VK_H: inputChars.add('H');break;
				case KeyEvent.VK_J: inputChars.add('J');break;
				case KeyEvent.VK_K: inputChars.add('K');break;
				case KeyEvent.VK_L: inputChars.add('L');break;
				case KeyEvent.VK_Z: inputChars.add('Z');break;
				case KeyEvent.VK_X: inputChars.add('X');break;
				case KeyEvent.VK_C: inputChars.add('C');break;
				case KeyEvent.VK_V: inputChars.add('V');break;
				case KeyEvent.VK_B: inputChars.add('B');break;
				case KeyEvent.VK_N: inputChars.add('N');break;
				case KeyEvent.VK_M: inputChars.add('M');break;
					default:break;
				}
			}
			
			if(key==KeyEvent.VK_BACK_SPACE && inputChars.size()>0){
				inputChars.remove(inputChars.size()-1);
			}
		}
		public void keyReleased(KeyEvent e) {}
		public void keyTyped(KeyEvent e) {}
	}
}
