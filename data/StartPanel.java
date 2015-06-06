package data;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Panel;
import java.awt.Toolkit;
import javax.swing.JPanel;

class StartPanel extends JPanel
{
	 public void paint(Graphics g)
	 {
		 super.paint(g);
		 Image bg = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/images/panel/pn1.jpg"));
		 g.drawImage(bg, 0, 0,794,568,this);
	 }
}