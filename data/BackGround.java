package data;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Panel;
import java.awt.Toolkit;

public class BackGround {
	
	static final Toolkit TLK=Toolkit.getDefaultToolkit(); //用来将图片读入Image数组
	Image bg = TLK.getImage(Panel.class.getResource("/images/panel/backGround.png"));
	Image stage2 = TLK.getImage(Panel.class.getResource("/images/panel/stage2Ground.png"));
	Image stage3 = TLK.getImage(Panel.class.getResource("/images/panel/stage3Ground.png"));
	Image over = TLK.getImage(Panel.class.getResource("/images/panel/gameOver.png"));
	
	static boolean init=false; //第一次画是将图片载入内存
	FightPanel fp = null;
	
	public BackGround(FightPanel fp)
	{
		this.fp = fp;
	}
	
	
	public void draw(Graphics g)
	{
		if(!init)
		{
			g.drawImage(bg,1000,1000,null);
			g.drawImage(stage2,1000,1000,null);
			g.drawImage(stage3,1000,1000,null);
			g.drawImage(over,1000,1000,null);
			init = true;
		}
		
		//背景
		if(!fp.gameOver)
		{
			switch(Status.getStage())
			{
			case 2:
				g.drawImage(bg, 0, 0,794,568,fp);
				g.drawImage(stage2, 0, 0,794,568,fp);
				break;
			case 3:
				g.drawImage(stage3, 0, 0,794,568,fp);
				break;
			default:
				g.drawImage(bg, 0, 0,794,568,fp);
				break;
			}
		}else{
			g.drawImage(over, 0, 0,794,568,fp);
		}
	}
}
