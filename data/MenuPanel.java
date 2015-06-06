package data;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Panel;
import java.awt.Toolkit;

import javax.swing.JPanel;

class MenuPanel extends JPanel implements Runnable
{
	//记录选中的是哪个选项
	int button = 1;
	static boolean once = true;
	boolean goon = true;
	
	public static AePlayWave apw =new AePlayWave("sounds/Menu.wav"); 
	
	public MenuPanel()
	{
		if(once)
		{
			Thread t = new Thread(this); //背景音乐
			t.start();
		}
		once = false;
	}
	public void paint(Graphics g)
	 {
		 super.paint(g);
		 
		 Image background = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/images/panel/pnMenu.jpg"));
		 Image single0 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/images/panel/01-0.png"));
		 Image single1 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/images/panel/01-1.png"));
		 //Image double0 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/images/panel/02-0.png"));
		 //Image double1 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/images/panel/02-1.png"));
		 //Image undeveloped = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/images/panel/02.png"));
		 //Image load0 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/images/panel/03-0.png"));
		 //Image load1 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/images/panel/03-1.png"));
		 Image key0 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/images/panel/04-0.png"));
		 Image key1 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/images/panel/04-1.png"));
		 Image mark0 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/images/panel/05-0.png"));
		 Image mark1 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/images/panel/05-1.png"));
		 Image introduce0 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/images/panel/06-0.png"));
		 Image introduce1 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/images/panel/06-1.png"));
		 Image exit0 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/images/panel/07-0.png"));
		 Image exit1 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/images/panel/07-1.png"));
		 
		 g.drawImage(background, 0, 0,this);
		 
		 switch(button)
			{
			case 1:
				g.drawImage(single1, 103, 71,this);
				//g.drawImage(double0, 103, 129,this);
				//g.drawImage(load0, 103, 203,this);
				g.drawImage(key0, 103, 165,this);
				g.drawImage(mark0, 103, 260,this);
				g.drawImage(introduce0, 103, 355,this);
				g.drawImage(exit0, 103, 452,this);
				break;
			case 2:
				g.drawImage(single0, 103, 71,this);
				//g.drawImage(double0, 103, 129,this);
				//g.drawImage(load1, 103, 203,this);
				g.drawImage(key0, 103, 165,this);
				g.drawImage(mark0, 103, 260,this);
				g.drawImage(introduce0, 103, 355,this);
				g.drawImage(exit0, 103, 452,this);
				break;
			case 3:
				g.drawImage(single0, 103, 71,this);
				//g.drawImage(double0, 103, 129,this);
				//g.drawImage(load0, 103, 203,this);
				g.drawImage(key1, 103, 165,this);
				g.drawImage(mark0, 103, 260,this);
				g.drawImage(introduce0, 103, 355,this);
				g.drawImage(exit0, 103, 452,this);
				break;
			case 4:
				g.drawImage(single0, 103, 71,this);
				//g.drawImage(double0, 103, 129,this);
				//g.drawImage(load0, 103, 203,this);
				g.drawImage(key0, 103, 165,this);
				g.drawImage(mark1, 103, 260,this);
				g.drawImage(introduce0, 103, 355,this);
				g.drawImage(exit0, 103, 452,this);
				break;
			case 5:
				g.drawImage(single0, 103, 71,this);
				//g.drawImage(double0, 103, 129,this);
				//g.drawImage(load0, 103, 203,this);
				g.drawImage(key0, 103, 165,this);
				g.drawImage(mark0, 103, 260,this);
				g.drawImage(introduce1, 103, 355,this);
				g.drawImage(exit0, 103, 452,this);
				break;
			case 6:
				g.drawImage(single0, 103, 71,this);
				//g.drawImage(double0, 103, 129,this);
				//g.drawImage(load0, 103, 203,this);
				g.drawImage(key0, 103, 165,this);
				g.drawImage(mark0, 103, 260,this);
				g.drawImage(introduce0, 103, 355,this);
				g.drawImage(exit1, 103, 452,this);
				break;
			default:
				System.out.println("出现错误！");
				break;
			}
	 }

	public void run() {
		while(goon)
		{	
			apw.start();
			
			try {
				Thread.sleep(15000);
			} catch (InterruptedException e) {
				// TODO 自动生成 catch 块
				e.printStackTrace();
			}
		}
	}
}
