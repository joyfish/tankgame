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
import java.util.Vector;

import javax.swing.JPanel;

import data.Records;

public class RecordPanel extends JPanel
{
	Image background = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/images/panel/record.png"));
	Image return0 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/images/panel/return0.png"));
	Image return1 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/images/panel/return1.png"));
	//记录选中的是哪个选项
	public int button = 1;
	
	FileReader fr = null;
	BufferedReader br = null;
	String s = null;
	Records sCO = null;
	Vector<Records> sCOs = null;//存储每行数据
	
	public RecordPanel()
	{
		sCOs = new Vector<Records>();//存储每行数据
		 
		 try {  //从文件中读取记录
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
	 public void paint(Graphics g)
	 {
		 super.paint(g);
		 g.drawImage(background, 0, 0, this);
		 
		 Font myFont = new Font("Arial",Font.BOLD,27);
		 g.setFont(myFont);

		 g.setColor(Color.white);
		
		 if(sCOs.get(0).sign!=0){
			g.setColor(Color.GRAY); //阴影效果
		 	g.drawString(sCOs.get(0).score+"", 241, 226);
		 	g.drawString(sCOs.get(0).name, 399, 226);
			g.setColor(Color.DARK_GRAY); //阴影效果
		 	g.drawString(sCOs.get(0).score+"", 240, 225);
		 	g.drawString(sCOs.get(0).name, 398, 225);
			g.setColor(Color.white);
		 	g.drawString(sCOs.get(0).score+"", 239, 224);
		 	g.drawString(sCOs.get(0).name, 397, 224);
		 }
		 if(sCOs.get(1).sign!=0){
			g.setColor(Color.GRAY); //阴影效果
		 	g.drawString(sCOs.get(1).score+"", 241, 268);
			g.drawString(sCOs.get(1).name, 399, 266);
			g.setColor(Color.DARK_GRAY); //阴影效果
		 	g.drawString(sCOs.get(1).score+"", 240, 267);
			g.drawString(sCOs.get(1).name, 398, 266);
			g.setColor(Color.white);
		 	g.drawString(sCOs.get(1).score+"", 239, 266);
			g.drawString(sCOs.get(1).name, 397, 266);
		 }
		 if(sCOs.get(2).sign!=0){
			g.setColor(Color.GRAY); //阴影效果
			g.drawString(sCOs.get(2).score+"", 241, 312);
			g.drawString(sCOs.get(2).name, 399, 310);
			g.setColor(Color.DARK_GRAY); //阴影效果
			g.drawString(sCOs.get(2).score+"", 240, 311);
			g.drawString(sCOs.get(2).name, 398, 310);
			g.setColor(Color.white);
			g.drawString(sCOs.get(2).score+"", 239, 310);
			g.drawString(sCOs.get(2).name, 397, 310);
		 }
		 if(sCOs.get(3).sign!=0){
			g.setColor(Color.GRAY); //阴影效果
			g.drawString(sCOs.get(3).score+"", 241, 355);
			g.drawString(sCOs.get(3).name, 399, 353);
			g.setColor(Color.DARK_GRAY); //阴影效果
			g.drawString(sCOs.get(3).score+"", 240, 354);
			g.drawString(sCOs.get(3).name, 398, 353);
			g.setColor(Color.white);
			g.drawString(sCOs.get(3).score+"", 239, 353);
			g.drawString(sCOs.get(3).name, 397, 353);
		 }
		 if(sCOs.get(4).sign!=0){
			g.setColor(Color.GRAY); //阴影效果
			g.drawString(sCOs.get(4).score+"", 241, 421);
			g.drawString(sCOs.get(4).name, 399, 419);
			g.setColor(Color.DARK_GRAY); //阴影效果
			g.drawString(sCOs.get(4).score+"", 240, 420);
			g.drawString(sCOs.get(4).name, 398, 419);
			g.setColor(Color.white);
			g.drawString(sCOs.get(4).score+"", 239, 419);
			g.drawString(sCOs.get(4).name, 397, 419);
		 }
		 if(sCOs.get(5).sign!=0){
			g.setColor(Color.GRAY); //阴影效果
			g.drawString(sCOs.get(5).score+"", 241, 463);
			g.drawString(sCOs.get(5).name, 399, 461);
			g.setColor(Color.DARK_GRAY); //阴影效果
			g.drawString(sCOs.get(5).score+"", 240, 462);
			g.drawString(sCOs.get(5).name, 398, 461);
			g.setColor(Color.white);
			g.drawString(sCOs.get(5).score+"", 239, 461);
			g.drawString(sCOs.get(5).name, 397, 461);
		 }
		
		 switch(button)
		 {
		 case 1:
		 	g.drawImage(return0, 225, 500,this);
			break;
		 case 2:
			g.drawImage(return1, 225, 500,this);
			break;
		 default:
			System.out.println("出现错误！");
			break;
		 }
	 }
}