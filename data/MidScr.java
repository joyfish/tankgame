package data;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;

//使窗口居中显示
class MidScr 
{ 
	private int x=0; 
	private int y=0; 

	public MidScr (Component jc) 
	{ 
		//通过屏幕和控件的大小计算控件左上角的位置 
		Dimension d_c=jc.getSize(); 
		Dimension d_scr=Toolkit.getDefaultToolkit ().getScreenSize(); 
		double x1=(d_scr.getWidth()-d_c.getWidth())/2; 
		double y1=(d_scr.getHeight()-d_c.getHeight())/2; 
		x=new Double(x1).intValue (); 
		y=new Double(y1).intValue(); 
	} 

	public int getX() 
	{ 
		return x; 
	} 
	public int getY() 
	{ 
		return y; 
	} 
}
