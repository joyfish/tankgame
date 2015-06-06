package data;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Panel;
import java.awt.Toolkit;
import javax.swing.JPanel;

class IntroducePanel extends JPanel
{
//	记录选中的是哪个选项
	int button = 1;
	 public void paint(Graphics g)
	 {
		 super.paint(g);
		 Image background = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/images/panel/introduce.png"));
		 Image return0 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/images/panel/return0.png"));
		 Image return1 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/images/panel/return1.png"));
		

		 g.drawImage(background, 0, 0,this);
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