package data;

import java.awt.*; 

import javax.swing.*; 

import data.panel.FightExitPanel;
import data.panel.LoadPanel;
import data.panel.RecordPanel;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.MemoryImageSource;

public class Main extends JFrame implements KeyListener, MouseListener, MouseMotionListener, Runnable
{
	static Main main = null;
	StartPanel sp = null;
	MenuPanel mp = null;
	IntroducePanel introduce = null;
	KeyPanel key = null;
	FightPanel fight = null;
	RecordPanel record = null;
	LoadPanel load = null;
	
	String whichPanle = "start";
	static boolean enter = false;//用于鼠标移动对条目的选择
	
	public static void main(String args[]){
		//换边框： JFrame.setDefaultLookAndFeelDecorated(true);
		main = new Main(); 
	}
	
	public Main(){
				
		sp = new StartPanel();
		
		this.add(sp);
 		
 		this.addKeyListener(this);
 		this.addMouseListener(this);
 		this.addMouseMotionListener(this);
 		
 		this.setCursor(Toolkit.getDefaultToolkit().createCustomCursor(new ImageIcon("images/item/mouse.gif").getImage(),new Point(10,20), "stick"));
 		this.setIconImage((new ImageIcon("images/tank/icon/icon.png")).getImage());
		this.setTitle("坦克大战 1.0");
		this.setSize(800,600);
		this.setBackground(Color.black);
		//将窗体定位在屏幕中央 
		MidScr ms=new MidScr(this); 
		this.setLocation(ms.getX(), ms.getY()); 
		this.setResizable(false);
		this.setVisible(true);
		//3秒后由 开始面板 转调至 菜单面板
		Thread t = new Thread(this);
		t.start();
		this.setDefaultCloseOperation(EXIT_ON_CLOSE); 
	}
	
	//键盘监听
	public void keyPressed(KeyEvent e)
	{
		if(whichPanle.equals("start")) //开始面板
		{
			if(e.getKeyCode()==KeyEvent.VK_ENTER)
			{
				//在此为什么不能 调用对象 t ? (设想t.stop;结束界面转换线程)
				mp = new MenuPanel();
				this.remove(sp);
				this.add(mp);
				whichPanle = "menu";
				this.setVisible(true);
			}
		}else if(whichPanle.equals("menu")) //主菜单面板
		{
			if(e.getKeyCode()==KeyEvent.VK_UP)
			{
				AePlayWave apw=new AePlayWave("sounds/Menu_MoveON.wav");
				apw.start();
				
				switch(mp.button)
				{
				case 1:
					mp.button = 6;
					break;
				default:
					mp.button--;
					break;
				}
				mp.repaint();
			}else if(e.getKeyCode()==KeyEvent.VK_DOWN)
			{
				AePlayWave apw=new AePlayWave("sounds/Menu_MoveON.wav");
				apw.start();
				
				switch(mp.button)
				{
				case 6:
					mp.button = 1;
					break;
				default:
					mp.button++;
					break;
				}
				mp.repaint();
			}else if(e.getKeyCode()==KeyEvent.VK_ENTER)
			{
				if(mp.button==1)
				{
					whichPanle = "fight";
					mp.goon = false;
					MenuPanel.apw.stop();
					
					AePlayWave apw=new AePlayWave("sounds/FightStart.wav");
					apw.start();
					
					this.hideCursor();
					
					this.remove(mp);
					fight = new FightPanel(this);
					this.add(fight);
					this.addKeyListener(fight);
					this.addMouseListener(fight);
					this.addMouseMotionListener(fight);
					this.setVisible(true);
				}else
				{
					AePlayWave apw=new AePlayWave("sounds/Menu_Click.wav");
					apw.start();
				}	
	
				switch(mp.button)
				{
				case 1:
					break;
				case 2:					
					load = new LoadPanel();
					this.remove(mp);
					this.add(load);
					whichPanle = "load";
					this.setVisible(true);
					break;
				case 3:
					key = new KeyPanel();
					this.remove(mp);
					this.add(key);
					whichPanle = "key";
					this.setVisible(true);
					break;
				case 4:
					record = new RecordPanel();
					this.remove(mp);
					this.add(record);
					whichPanle = "record";
					this.setVisible(true);
					break;
				case 5:
					introduce = new IntroducePanel();
					this.remove(mp);
					this.add(introduce);
					whichPanle = "introduce";
					this.setVisible(true);
					break;
				case 6:
					System.exit(0);
					break;
				default:
					System.out.println("出现错误！");
					break;
				}
			}else if(e.getKeyCode()==KeyEvent.VK_ESCAPE)
			{
				AePlayWave apw=new AePlayWave("sounds/Menu_MoveON.wav");
				apw.start();
				
				mp.button = 6;
				mp.repaint();
			}
		}else if(whichPanle.equals("load")) //载入进度
		{
			if(e.getKeyCode()==KeyEvent.VK_ESCAPE)
			{
				AePlayWave apw=new AePlayWave("sounds/Menu_Click.wav");
				apw.start();
				
				mp = new MenuPanel();
				this.remove(load);
				this.add(mp);
				whichPanle = "menu";
				this.setVisible(true);
			}else if(e.getKeyCode()==KeyEvent.VK_UP)
			{
				AePlayWave apw=new AePlayWave("sounds/Menu_MoveON.wav");
				apw.start();
				
				switch(load.button)
				{
				case 1:
					load.button = FightExitPanel.getRecordNumber("records/state.txt");
					break;
				default:
					load.button--;
					break;
				}
				load.repaint();
			}else if(e.getKeyCode()==KeyEvent.VK_DOWN)
			{
				AePlayWave apw=new AePlayWave("sounds/Menu_MoveON.wav");
				apw.start();
				
				if(load.button == FightExitPanel.getRecordNumber("records/state.txt"))
				{
					load.button = 1;
				}else{
					load.button++;
				}
				load.repaint();
			}
		}else if(whichPanle.equals("key")) //键盘设置
		{
			if(e.getKeyCode()==KeyEvent.VK_ESCAPE)
			{
				AePlayWave apw=new AePlayWave("sounds/Menu_Click.wav");
				apw.start();
				
				mp = new MenuPanel();
				this.remove(key);
				this.add(mp);
				whichPanle = "menu";
				this.setVisible(true);
			}
		}else if(whichPanle.equals("record")) //积分排名
		{
			if(e.getKeyCode()==KeyEvent.VK_ESCAPE)
			{
				AePlayWave apw=new AePlayWave("sounds/Menu_Click.wav");
				apw.start();
				
				mp = new MenuPanel();
				this.remove(record);
				this.add(mp);
				whichPanle = "menu";
				this.setVisible(true);
			}
		}else if(whichPanle.equals("introduce")) //积分排名
		{
			if(e.getKeyCode()==KeyEvent.VK_ESCAPE)
			{
				AePlayWave apw=new AePlayWave("sounds/Menu_Click.wav");
				apw.start();
				
				mp = new MenuPanel();
				this.remove(introduce);
				this.add(mp);
				whichPanle = "menu";
				this.setVisible(true);
			}
		}
		
	}
	public void keyReleased(KeyEvent e) {}
	public void keyTyped(KeyEvent e) {}
	
    //隐藏鼠标
	public void hideCursor() {
		  Image image = Toolkit.getDefaultToolkit().createImage(new MemoryImageSource(0, 0, new int[0], 0, 0));
		  this.setCursor(Toolkit.getDefaultToolkit().createCustomCursor(image, new Point(0, 0), null));
	}
	
	//显示鼠标
	public static void showCursor(boolean visible)
	{
		if(visible){
			main.setCursor(Toolkit.getDefaultToolkit().createCustomCursor(new ImageIcon("images/item/mouse.gif").getImage(),new Point(10,20), "stick"));
		}else{
			main.hideCursor();
		}
	}
	
	//鼠标监听
	public void mouseClicked(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mousePressed(MouseEvent e) {
		//首界面被 单击 立即转调至菜单面板
		if(whichPanle.equals("start"))
		{
			mp = new MenuPanel();
			this.remove(sp);
			this.add(mp);
			whichPanle = "menu";
			this.setVisible(true);
		}else if(whichPanle.equals("menu"))
		{
			int x = e.getX();
			int y = e.getY();
			
			if(x>=200 && x<=369 && y>=118 && y<=160){
				mp.goon = false;
				MenuPanel.apw.stop();
				AePlayWave apw=new AePlayWave("sounds/FightStart.wav");
				apw.start();
				
				this.hideCursor();
				this.remove(mp);
				fight = new FightPanel(this);
				this.add(fight);
				
				whichPanle = "fight";
				this.addKeyListener(fight);
				this.addMouseListener(fight);
				this.addMouseMotionListener(fight);
				this.setVisible(true);
			}//else if(x>=200 && x<=369 && y>=251 && y<=288)
			//{
				//AePlayWave apw=new AePlayWave("sounds/Menu_Click.wav");
				//apw.start();
				
				//load = new LoadPanel();
				//this.remove(mp);
				//this.add(load);
				//whichPanle = "load";
				//this.setVisible(true);
			//}
			else if(x>=200 && x<=369 && y>=213 && y<=255)
			{
				AePlayWave apw=new AePlayWave("sounds/Menu_Click.wav");
				apw.start();
				
				key = new KeyPanel();
				this.remove(mp);
				this.add(key);
				whichPanle = "key";
				this.setVisible(true);
			}
			if(x>=200 && x<=369 && y>=308 && y<=350)
			{
				AePlayWave apw=new AePlayWave("sounds/Menu_Click.wav");
				apw.start();
				
				record = new RecordPanel();
				this.remove(mp);
				this.add(record);
				whichPanle = "record";
				this.setVisible(true);
			}else if(x>=200 && x<=369 && y>=403 && y<=445)
			{
				AePlayWave apw=new AePlayWave("sounds/Menu_Click.wav");
				apw.start();
				
				introduce = new IntroducePanel();
				this.remove(mp);
				this.add(introduce);
				whichPanle = "introduce";
				this.setVisible(true);
			}else if(x>=200 && x<=369 && y>=500 && y<=533)
			{
				AePlayWave apw=new AePlayWave("sounds/Menu_Click.wav");
				apw.start();
				
				System.exit(0);
			}
		}
		/*else if(whichPanle.equals("load"))
		{
			int x = e.getX();
			int y = e.getY();
			
			if(x>=108 && x<=534 && y>=180 && y<=219 && FightExitPanel.getRecordNumber("records/state.txt")>1){
				System.out.println(load.rEDs.get(0).name);
				AePlayWave apw=new AePlayWave("sounds/Menu_MoveON.wav");
				apw.start();
			}else if(x>=108 && x<=534 && y>=239 && y<=280 && FightExitPanel.getRecordNumber("records/state.txt")>2){
				System.out.println(load.rEDs.get(1).name);
				AePlayWave apw=new AePlayWave("sounds/Menu_MoveON.wav");
				apw.start();
			}else if(x>=108 && x<=534 && y>=300 && y<=341 && FightExitPanel.getRecordNumber("records/state.txt")>3){
				System.out.println(load.rEDs.get(2).name);
				AePlayWave apw=new AePlayWave("sounds/Menu_MoveON.wav");
				apw.start();
			}else if(x>=108 && x<=534 && y>=361 && y<=402 && FightExitPanel.getRecordNumber("records/state.txt")>4){
				System.out.println(load.rEDs.get(3).name);
				AePlayWave apw=new AePlayWave("sounds/Menu_MoveON.wav");
				apw.start();
			}else if(x>=108 && x<=534 && y>=429 && y<=466 && FightExitPanel.getRecordNumber("records/state.txt")>5){
				System.out.println(load.rEDs.get(4).name);
				AePlayWave apw=new AePlayWave("sounds/Menu_MoveON.wav");
				apw.start();
			}else if(x>=108 && x<=534 && y>=476 && y<=517 && FightExitPanel.getRecordNumber("records/state.txt")>6){
				System.out.println(load.rEDs.get(5).name);
				AePlayWave apw=new AePlayWave("sounds/Menu_MoveON.wav");
				apw.start();
			}else if(x>=230 && x<=357 && y>=543 && y<=586)
			{
				AePlayWave apw=new AePlayWave("sounds/Menu_Click.wav");
				apw.start();
				mp = new MenuPanel();
				this.remove(load);
				this.add(mp);
				whichPanle = "menu";
				this.setVisible(true);
			}
			load.repaint();
		}*/
		else if(whichPanle.equals("key"))
		{
			int x = e.getX();
			int y = e.getY();
			if(x>=609 && x<=738 && y>=497 && y<=544)
			{
				AePlayWave apw=new AePlayWave("sounds/Menu_Click.wav");
				apw.start();
								
				mp = new MenuPanel();
				this.remove(key);
				this.add(mp);
				whichPanle = "menu";
				this.setVisible(true);
			}
		}else if(whichPanle.equals("record"))
		{
			int x = e.getX();
			int y = e.getY();
			if(x>=230 && x<=357 && y>=543 && y<=586)
			{
				AePlayWave apw=new AePlayWave("sounds/Menu_Click.wav");
				apw.start();
				
				mp = new MenuPanel();
				this.remove(record);
				this.add(mp);
				whichPanle = "menu";
				this.setVisible(true);
			}
		}
			else if(whichPanle.equals("introduce"))
		{
			int x = e.getX();
			int y = e.getY();
			if(x>=230 && x<=357 && y>=543 && y<=586)
			{
				AePlayWave apw=new AePlayWave("sounds/Menu_Click.wav");
				apw.start();
				
				mp = new MenuPanel();
				this.remove(introduce);
				this.add(mp);
				whichPanle = "menu";
				this.setVisible(true);
			}
		}
	}
	public void mouseReleased(MouseEvent e) {}
	public void mouseDragged(MouseEvent e) {}
	public void mouseMoved(MouseEvent e) {
		
		//System.out.println(e.getY());
		
		if(whichPanle.equals("menu"))
		{
			int x = e.getX();
			int y = e.getY();
						
			if(x>=200 && x<=369 && y>=118 && y<=160 && !enter){
				AePlayWave apw=new AePlayWave("sounds/Menu_MoveON.wav");
				apw.start();
				enter = true;
				
				mp.button = 1;
			}/*else if(x>=200 && x<=369 && y>=251 && y<=288 && !enter)
			{
				AePlayWave apw=new AePlayWave("sounds/Menu_MoveON.wav");
				apw.start();
				enter = true;

				mp.button = 2;
			}
			*/
			else if(x>=200 && x<=369 && y>=213 && y<=255 && !enter)
			{
				AePlayWave apw=new AePlayWave("sounds/Menu_MoveON.wav");
				apw.start();
				enter = true;

				mp.button = 3;
			}else if(x>=200 && x<=369 && y>=308 && y<=350 && !enter)
			{
				AePlayWave apw=new AePlayWave("sounds/Menu_MoveON.wav");
				apw.start();
				enter = true;

				mp.button = 4;
			}else if(x>=200 && x<=369 && y>=403 && y<=455 && !enter)
			{
				AePlayWave apw=new AePlayWave("sounds/Menu_MoveON.wav");
				apw.start();
				enter = true;

				mp.button = 5;
			}else if(x>=200 && x<=369 && y>=500 && y<=533 && !enter)
			{
				AePlayWave apw=new AePlayWave("sounds/Menu_MoveON.wav");
				apw.start();
				enter = true;

				mp.button = 6;
			}else if(x<200 || x>=200 && y<118 || x>=200 && y>533 || x>369 || y>160 && y<251 || y>288 && y<306 || y>342 && y<360 || y>396 && y<446 || y>483 && y<500)
			{
				enter = false;
			}
			mp.repaint();
		}else if(whichPanle.equals("load"))
		{
			int x = e.getX();
			int y = e.getY();
			
			if(x<108 || x>534 || y<180 || y>586 || y>219 && y<239 || y>280 && y<300 || y>341 && y<361 || y>402 && y<429 || y>466 && y<476 || y>517 && y<543 || x>108 && x<230 && y>517 || x>357 && x<534 && y>517)
			{
				enter = false;
			}else{
				if(x>=108 && x<=534 && y>=180 && y<=219 && FightExitPanel.getRecordNumber("records/state.txt")>1 && !enter){
					AePlayWave apw=new AePlayWave("sounds/Menu_MoveON.wav");
					apw.start();
					load.button = 1;
					enter = true;
				}else if(x>=108 && x<=534 && y>=239 && y<=280 && FightExitPanel.getRecordNumber("records/state.txt")>2 && !enter){
					AePlayWave apw=new AePlayWave("sounds/Menu_MoveON.wav");
					apw.start();
					load.button = 2;
					enter = true;
				}else if(x>=108 && x<=534 && y>=300 && y<=341 && FightExitPanel.getRecordNumber("records/state.txt")>3 && !enter){
					AePlayWave apw=new AePlayWave("sounds/Menu_MoveON.wav");
					apw.start();
					load.button = 3;
					enter = true;
				}else if(x>=108 && x<=534 && y>=361 && y<=402 && FightExitPanel.getRecordNumber("records/state.txt")>4 && !enter){
					AePlayWave apw=new AePlayWave("sounds/Menu_MoveON.wav");
					apw.start();
					load.button = 4;
					enter = true;
				}else if(x>=108 && x<=534 && y>=429 && y<=466 && FightExitPanel.getRecordNumber("records/state.txt")>5 && !enter){
					AePlayWave apw=new AePlayWave("sounds/Menu_MoveON.wav");
					apw.start();
					load.button = 5;
					enter = true;
				}else if(x>=108 && x<=534 && y>=476 && y<=517 && FightExitPanel.getRecordNumber("records/state.txt")>6 && !enter){
					AePlayWave apw=new AePlayWave("sounds/Menu_MoveON.wav");
					apw.start();
					load.button = 6;
					enter = true;
				}else if(x>=230 && x<=357 && y>=543 && y<=586 && FightExitPanel.getRecordNumber("records/state.txt")>0 && !enter){
					AePlayWave apw=new AePlayWave("sounds/Menu_MoveON.wav");
					apw.start();
					load.button = FightExitPanel.getRecordNumber("records/state.txt");
					enter = true;
				}
			}
			load.repaint();
		}else if(whichPanle.equals("key"))
		{
			int x = e.getX();
			int y = e.getY();
			if(x>=609 && x<=738 && y>=497 && y<=544 && !enter){
				AePlayWave apw=new AePlayWave("sounds/Menu_MoveON.wav");
				apw.start();
				enter = true;
				
				key.button = 2;
			}else if(x<609 || x>738 || y<497 || y>544)
			{
				enter = false;
				
				key.button = 1;
			}
			key.repaint();
		}else if(whichPanle.equals("record"))
		{
			int x = e.getX();
			int y = e.getY();
			if(x>=230 && x<=357 && y>=543 && y<=586 && !enter){
				AePlayWave apw=new AePlayWave("sounds/Menu_MoveON.wav");
				apw.start();
				enter = true;
				
				record.button = 2;
			}else if(x<230 || x>357 || y<543 || y>586)
			{
				enter = false;
				
				record.button = 1;
			}
			record.repaint();
		}else if(whichPanle.equals("introduce"))
		{
			int x = e.getX();
			int y = e.getY();
			if(x>=230 && x<=357 && y>=543 && y<=586 && !enter){
				AePlayWave apw=new AePlayWave("sounds/Menu_MoveON.wav");
				apw.start();
				enter = true;
				
				introduce.button = 2;
			}else if(x<230 || x>357 || y<543 || y>586)
			{
				enter = false;
				
				introduce.button = 1;
			}
			introduce.repaint();
		}
		//System.out.println(e.getX()+"  "+e.getY());
	}
	
	//首界面延迟3秒转调至菜单面板
	public void run() {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		if(whichPanle.equals("start")){
			mp = new MenuPanel();
			this.remove(sp);
			this.add(mp);
			whichPanle = "menu";
			this.setVisible(true);
		}
	}	
}


