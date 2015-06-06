package data;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Panel;
import java.awt.Toolkit;

import javax.swing.JPanel;

class KeyPanel extends JPanel
{
//	记录选中的是哪个选项
	int button = 1;
	 public void paint(Graphics g)
	 {
		 super.paint(g);
		 Image background = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/images/panel/key.png"));
		 Image return0 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/images/panel/return0.png"));
		 Image return1 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/images/panel/return1.png"));

		 Font myFont = null;
		 
		 switch(button)
			{
			case 1:
				g.drawImage(background, 0, 0, 794, 568, this);
				g.drawImage(return0, 604, 456,119,38,this);

				//战斗面板控制键设置
				g.setColor(Color.white);
				myFont = new Font("Arial",Font.BOLD,20);
				g.setFont(myFont);
				g.drawString("UP", 110, 178);
				g.drawString("DOWN", 90, 226);
				g.drawString("LEFT", 98, 278);
				g.drawString("RIGHT", 91, 330);
				g.drawString("SPACE", 87, 381);
				g.drawString("ENTER", 86, 430);
				g.drawString("ESC", 103, 480);
				
				break;
			case 2:
				g.drawImage(background, 0, 0, 794, 568, this);
				g.drawImage(return1, 604, 456,119,38,this);
				
				//战斗面板控制键设置
				g.setColor(Color.white);
				myFont = new Font("Arial",Font.BOLD,20);
				g.setFont(myFont);
				g.drawString("UP", 110, 178);
				g.drawString("DOWN", 90, 226);
				g.drawString("LEFT", 98, 278);
				g.drawString("RIGHT", 91, 330);
				g.drawString("SPACE", 87, 381);
				g.drawString("ENTER", 86, 430);
				g.drawString("ESC", 103, 480);
				
				break;
			default:
				System.out.println("出现错误！");
				break;
			}
	 }
}