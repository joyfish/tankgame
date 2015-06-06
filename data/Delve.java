package data;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

public class Delve {
	int x,y;
	
	int i = (int)(Math.random()*9); //随机弹坑类型
	
	Tank tk = null;
	
	int SHOTTYPE;//锁定弹坑类型
	boolean once = true;//控制paint()光稜时方向不发生变化
	int DIRECTION = 0;//控制paint()光稜时方向不发生变化
	
	static final Toolkit TLK=Toolkit.getDefaultToolkit();

	public Delve(int x,int y,int SHOTTYPE,Tank tk)
	{
		this.x = x;
		this.y = y;
		this.SHOTTYPE = SHOTTYPE;
		this.tk = tk;
		
		new Thread(new deletDelve(this)).start(); //延时弹坑消失
	}
	
	public Delve(int x,int y,int SHOTTYPE)
	{
		this.x = x;
		this.y = y;
		this.SHOTTYPE = SHOTTYPE;
		
		new Thread(new deletDelve(this)).start(); //延时弹坑消失
	}
	
	public static final Image[] DELVES_1=
	{
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/delve/1/1.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/delve/1/2.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/delve/1/3.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/delve/1/4.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/delve/1/5.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/delve/1/6.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/delve/1/7.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/delve/1/8.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/delve/1/9.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/delve/1/10.png"))
	};
	public static final Image[] DELVES_2=
	{
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/delve/2/1.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/delve/2/2.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/delve/2/3.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/delve/2/4.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/delve/2/5.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/delve/2/6.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/delve/2/7.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/delve/2/8.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/delve/2/9.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/delve/2/10.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/delve/2/11.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/delve/2/12.png"))
	};
	
	void draw(Graphics g)
	{
		switch(SHOTTYPE)
		{
		case 1:
		case 2:
		case 4:
		case 5:
			g.drawImage(DELVES_1[i],x-18,y-10,null);
			break;
		case 3:
			if(once)
			{
				once = false;
				switch(tk.tankDir)
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
				g.drawImage(DELVES_2[i],x-32,y-15,null);
				break;
			case 1:
				g.drawImage(DELVES_2[i],x-33,y-24,null);
				break;
			case 2:
				g.drawImage(DELVES_2[i],x-30,y-19,null);
				break;
			case 3:
				g.drawImage(DELVES_2[i],x-31,y-19,null);
				break;
			default:break;
			}
			break;
		default:break;
		}
	}
	
	class deletDelve implements Runnable
	{
		Delve delve;
		public deletDelve(Delve delve)
		{
			this.delve = delve;
		}
		public void run() {
			while(true)
			{
				try {
					Thread.sleep(2500);
					
					FightPanel.delvesList.remove(delve);
				} catch (InterruptedException e) {
					// TODO 自动生成 catch 块
					e.printStackTrace();
				}
			}
		}
	}
}
