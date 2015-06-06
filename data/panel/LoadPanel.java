package data.panel;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Panel;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.Vector;
import javax.swing.JPanel;

import data.FightPanel;
import data.Load;

public class LoadPanel extends JPanel
{
	 Image background = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/images/panel/load.png"));
	 Image return0 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/images/panel/return0.png"));
	 Image return1 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/images/panel/return1.png"));

	 Font myFont;
	 FileReader fr;
	 BufferedReader br;
	 public String s;
	 public Load rED;
	 
	 boolean init = false; //初始化
	 public int button; //记录选中的是哪个选项
	 public Vector<Load> rEDs = null;  //存储每行数据
	
	 public LoadPanel(){
		 button = 1;
		 rEDs = new Vector<Load>();
		 
		 try {  //从文件中读取记录
				fr = new FileReader("records/state.txt");
				br = new BufferedReader(fr);
				
				while((s=br.readLine())!=null)
				{
					String []array = s.split(" "); 
					rED = new Load(Integer.parseInt(array[0]),Integer.parseInt(array[1]),Integer.parseInt(array[2]),array[3],array[4],array[5],array[6],array[7],array[8],array[9]);
					rEDs.add(rED);
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
	 
	 public void paint(Graphics g)
	 {
		 super.paint(g);
		 
		 if(!init) //初始化
		 {
			 g.drawImage(background, 1000, 1000,this);
			 g.drawImage(return0, 1000, 1000,this);
			 g.drawImage(return1, 1000, 1000,this);
			 init = true;
		 }
		 
		myFont = new Font("Arial",Font.BOLD,20);
		g.setFont(myFont);

		g.drawImage(background, 0, 0,this);

		g.setColor(Color.white);
		
		if(rEDs.get(0).sign==0){
			g.setColor(Color.gray);
			g.drawString("NULL ", 260, 162);
			g.setColor(Color.white);
		}else{
			if(button == 1)
			{
				g.setColor(Color.yellow);
			}
			if(rEDs.get(0).state<4){
				g.drawString("STAGE "+rEDs.get(0).state+"", 125, 162);
			}else{
				g.drawString("S "+rEDs.get(0).state+"", 125, 162);
				g.drawString("F "+rEDs.get(0).fight+"", 180, 162);
			}
			g.drawString(rEDs.get(0).name, 260, 162);
			if(FightExitPanel.intradayRecord(rEDs.get(0).year,rEDs.get(0).month,rEDs.get(0).day))
			{
				g.drawString(rEDs.get(0).hours+":"+rEDs.get(0).minutes+":"+rEDs.get(0).seconds, 392, 162);
			}else{
				g.drawString(rEDs.get(0).year+"-"+rEDs.get(0).month+"-"+rEDs.get(0).day, 392, 162);
			}
			g.setColor(Color.white);
		}
		
		if(rEDs.get(1).sign==0){
			g.setColor(Color.gray);
			g.drawString("NULL ", 260, 223);
			g.setColor(Color.white);
		}else{
			if(button == 2)
			{
				g.setColor(Color.yellow);
			}
			if(rEDs.get(1).state<4){
				g.drawString("STAGE "+rEDs.get(1).state+"", 125, 223);
			}else{
				g.drawString("S "+rEDs.get(1).state+"", 125, 223);
				g.drawString("F "+rEDs.get(1).fight+"", 180, 223);
			}
			g.drawString(rEDs.get(1).name, 260, 223);
			if(FightExitPanel.intradayRecord(rEDs.get(1).year,rEDs.get(1).month,rEDs.get(1).day))
			{
				g.drawString(rEDs.get(1).hours+":"+rEDs.get(1).minutes+":"+rEDs.get(1).seconds, 392, 223);
			}else{
				g.drawString(rEDs.get(1).year+"-"+rEDs.get(1).month+"-"+rEDs.get(1).day, 392, 223);
			}
			g.setColor(Color.white);
		}

		if(rEDs.get(2).sign==0){
			g.setColor(Color.gray);
			g.drawString("NULL ", 260, 284);
			g.setColor(Color.white);
		}else{
			if(button == 3)
			{
				g.setColor(Color.yellow);
			}
			if(rEDs.get(2).state<4){
				g.drawString("STAGE "+rEDs.get(2).state+"", 125, 284);
			}else{
				g.drawString("S "+rEDs.get(2).state+"", 125, 284);
				g.drawString("F "+rEDs.get(2).fight+"", 180, 284);
			}
			g.drawString(rEDs.get(2).name, 260, 284);
			if(FightExitPanel.intradayRecord(rEDs.get(2).year,rEDs.get(2).month,rEDs.get(2).day))
			{
				g.drawString(rEDs.get(2).hours+":"+rEDs.get(2).minutes+":"+rEDs.get(2).seconds, 392, 284);
			}else{
				g.drawString(rEDs.get(2).year+"-"+rEDs.get(2).month+"-"+rEDs.get(2).day, 392, 284);
			}
			g.setColor(Color.white);
		}

		if(rEDs.get(3).sign==0){
			g.setColor(Color.gray);
			g.drawString("NULL ", 260, 345);
			g.setColor(Color.white);
		}else{
			if(button == 4)
			{
				g.setColor(Color.yellow);
			}
			if(rEDs.get(3).state<4){
				g.drawString("STAGE "+rEDs.get(3).state+"", 125, 345);
			}else{
				g.drawString("S "+rEDs.get(3).state+"", 125, 345);
				g.drawString("F "+rEDs.get(3).fight+"", 180, 345);
			}
			g.drawString(rEDs.get(3).name, 260, 345);
			if(FightExitPanel.intradayRecord(rEDs.get(3).year,rEDs.get(3).month,rEDs.get(3).day))
			{
				g.drawString(rEDs.get(3).hours+":"+rEDs.get(3).minutes+":"+rEDs.get(3).seconds, 392, 345);
			}else{
				g.drawString(rEDs.get(3).year+"-"+rEDs.get(3).month+"-"+rEDs.get(3).day, 392, 345);
			}
			g.setColor(Color.white);
		}
		
		if(rEDs.get(4).sign==0){
			g.setColor(Color.gray);
			g.drawString("NULL ", 260, 409);
			g.setColor(Color.white);
		}else{
			if(button == 5)
			{
				g.setColor(Color.yellow);
			}
			if(rEDs.get(4).state<4){
				g.drawString("STAGE "+rEDs.get(4).state+"", 125, 409);
			}else{
				g.drawString("S "+rEDs.get(4).state+"", 125, 409);
				g.drawString("F "+rEDs.get(4).fight+"", 180, 409);
			}
			g.drawString(rEDs.get(4).name, 260, 409);
			if(FightExitPanel.intradayRecord(rEDs.get(4).year,rEDs.get(4).month,rEDs.get(4).day))
			{
				g.drawString(rEDs.get(4).hours+":"+rEDs.get(4).minutes+":"+rEDs.get(4).seconds, 392, 409);
			}else{
				g.drawString(rEDs.get(4).year+"-"+rEDs.get(4).month+"-"+rEDs.get(4).day, 392, 409);
			}
			g.setColor(Color.white);
		}
		
		if(rEDs.get(5).sign==0){
			g.setColor(Color.gray);
			g.drawString("NULL ", 260, 460);
			g.setColor(Color.white);
		}else{
			if(button == 6)
			{
				g.setColor(Color.yellow);
			}
			if(rEDs.get(5).state<4){
				g.drawString("STAGE "+rEDs.get(5).state+"", 125, 460);
			}else{
				g.drawString("S "+rEDs.get(5).state+"", 125, 460);
				g.drawString("F "+rEDs.get(5).fight+"", 180, 460);
			}
			g.drawString(rEDs.get(5).name, 260, 460);
			if(FightExitPanel.intradayRecord(rEDs.get(5).year,rEDs.get(5).month,rEDs.get(5).day))
			{
				g.drawString(rEDs.get(5).hours+":"+rEDs.get(5).minutes+":"+rEDs.get(5).seconds, 392, 460);
			}else{
				g.drawString(rEDs.get(5).year+"-"+rEDs.get(5).month+"-"+rEDs.get(5).day, 392, 460);
			}
			g.setColor(Color.white);
		}
		
		if(button != FightExitPanel.getRecordNumber("records/state.txt"))
		{
			g.drawImage(return0, 225, 500,119,38,this);
		}else{
			g.drawImage(return1, 225, 500,119,38,this);
		}
	 }
}