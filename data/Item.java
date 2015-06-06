package data;
import java.awt.*;

import data.Tank.TANK_DIR;


class Item
{
	public static Toolkit TLK=Toolkit.getDefaultToolkit();
	
	public static final Image[] GIFTS=
	{
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/gift/1.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/gift/2.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/gift/3.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/gift/4.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/gift/5.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/gift/6.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/gift/7.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/gift/8.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/gift/9.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/gift/10.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/gift/11.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/gift/12.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/gift/13.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/gift/14.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/gift/15.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/gift/16.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/gift/17.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/gift/18.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/gift/19.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/gift/20.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/gift/21.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/gift/22.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/gift/23.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/gift/24.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/gift/25.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/gift/26.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/gift/27.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/gift/28.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/gift/29.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/gift/30.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/gift/31.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/gift/32.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/gift/33.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/gift/34.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/gift/35.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/gift/36.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/gift/37.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/gift/38.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/gift/39.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/gift/40.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/gift/41.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/gift/42.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/gift/43.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/gift/44.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/gift/45.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/gift/46.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/gift/47.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/gift/48.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/gift/49.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/gift/50.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/gift/51.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/gift/52.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/gift/53.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/gift/54.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/gift/55.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/gift/56.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/gift/57.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/gift/58.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/gift/59.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/gift/60.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/gift/61.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/gift/62.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/gift/63.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/gift/64.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/gift/65.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/gift/66.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/gift/67.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/gift/68.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/gift/69.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/gift/70.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/gift/71.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/gift/72.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/gift/73.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/gift/74.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/gift/75.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/gift/76.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/gift/77.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/gift/78.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/gift/79.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/gift/80.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/gift/81.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/gift/82.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/gift/83.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/gift/84.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/gift/85.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/gift/86.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/gift/87.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/gift/88.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/gift/89.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/gift/90.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/gift/91.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/gift/92.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/gift/93.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/gift/94.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/gift/95.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/gift/96.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/gift/97.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/gift/98.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/gift/99.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/gift/100.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/gift/101.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/gift/102.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/gift/103.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/gift/104.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/gift/105.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/gift/106.png")),
	};
	public static final Image[] GIFT_LEVEL=
	{
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/level/1.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/level/1.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/level/2.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/level/2.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/level/3.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/level/3.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/level/4.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/level/4.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/level/5.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/level/5.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/level/6.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/level/7.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/level/7.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/level/7.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/level/8.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/level/9.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/level/9.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/level/9.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/level/9.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/level/10.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/level/10.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/level/11.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/level/11.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/level/12.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/level/13.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/level/14.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/level/14.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/level/15.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/level/15.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/level/16.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/level/16.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/level/17.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/level/17.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/level/18.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/level/18.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/level/18.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/level/18.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/level/18.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/level/18.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/level/18.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/level/18.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/level/18.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/level/18.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/level/18.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/level/18.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/level/18.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/level/18.png"))
	};
	public static final Image[] GIFT_RAISEHP=
	{
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/HP/1.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/HP/1.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/HP/2.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/HP/2.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/HP/3.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/HP/3.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/HP/4.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/HP/4.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/HP/5.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/HP/5.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/HP/6.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/HP/7.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/HP/7.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/HP/7.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/HP/8.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/HP/9.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/HP/9.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/HP/9.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/HP/9.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/HP/10.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/HP/10.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/HP/11.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/HP/11.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/HP/12.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/HP/13.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/HP/14.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/HP/14.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/HP/15.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/HP/15.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/HP/16.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/HP/16.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/HP/17.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/HP/17.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/HP/18.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/HP/18.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/HP/19.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/HP/19.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/HP/20.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/HP/20.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/HP/20.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/HP/20.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/HP/20.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/HP/20.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/HP/20.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/HP/20.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/HP/20.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/HP/20.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/HP/20.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/HP/20.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/HP/20.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/HP/20.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/HP/20.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/HP/20.png"))
	};
	public static final Image[] GIFT_RAISEHP_TANK=
	{
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/tank/me/wudi/7.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/tank/me/wudi/14.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/tank/me/wudi/21.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/tank/me/wudi/28.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/tank/me/wudi/35.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/tank/me/wudi/42.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/tank/me/wudi/49.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/tank/me/wudi/56.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/tank/me/wudi/63.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/tank/me/wudi/70.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/tank/me/wudi/77.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/tank/me/wudi/84.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/tank/me/wudi/91.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/tank/me/wudi/98.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/tank/me/wudi/105.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/tank/me/wudi/112.png")),
	};
	public static final Image[] GIFT_LIFE=
	{
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/life/1.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/life/1.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/life/1.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/life/2.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/life/2.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/life/2.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/life/3.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/life/3.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/life/3.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/life/4.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/life/4.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/life/4.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/life/5.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/life/5.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/life/5.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/life/6.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/life/6.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/life/6.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/life/7.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/life/7.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/life/7.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/life/8.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/life/8.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/life/8.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/life/9.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/life/9.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/life/9.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/life/10.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/life/10.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/life/10.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/life/11.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/life/11.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/life/11.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/life/12.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/life/12.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/life/12.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/life/12.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/life/12.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/life/12.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/life/13.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/life/13.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/life/13.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/life/13.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/life/13.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/life/14.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/life/14.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/life/14.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/life/14.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/life/14.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/life/15.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/life/15.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/life/15.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/life/15.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/life/15.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/life/16.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/life/16.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/life/16.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/life/16.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/life/16.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/life/17.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/life/17.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/life/17.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/life/17.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/life/17.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/life/18.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/life/18.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/life/18.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/life/18.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/life/18.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/life/19.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/life/19.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/life/19.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/life/19.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/life/19.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/life/20.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/life/20.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/life/20.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/life/20.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/life/20.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/life/21.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/life/21.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/life/21.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/life/21.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/life/21.png"))
	};
	public static final Image[] GIFT_WUDI=
	{
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/wudi/1.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/wudi/1.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/wudi/2.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/wudi/2.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/wudi/3.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/wudi/3.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/wudi/4.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/wudi/4.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/wudi/5.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/wudi/5.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/wudi/6.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/wudi/7.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/wudi/7.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/wudi/7.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/wudi/8.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/wudi/9.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/wudi/9.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/wudi/9.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/wudi/9.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/wudi/10.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/wudi/10.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/wudi/11.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/wudi/11.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/wudi/12.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/wudi/13.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/wudi/14.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/wudi/14.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/wudi/15.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/wudi/15.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/wudi/16.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/wudi/16.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/wudi/17.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/wudi/17.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/wudi/17.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/wudi/17.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/wudi/17.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/wudi/17.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/wudi/17.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/wudi/17.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/wudi/17.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/wudi/17.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/wudi/17.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/wudi/17.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/wudi/17.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/wudi/17.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/wudi/17.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/wudi/17.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/wudi/17.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/wudi/17.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/wudi/17.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/wudi/17.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/wudi/17.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/wudi/17.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/wudi/17.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/wudi/17.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/wudi/17.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/wudi/17.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/wudi/17.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/wudi/17.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/wudi/17.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/wudi/17.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/wudi/17.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/wudi/17.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/wudi/17.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/wudi/17.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/wudi/17.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/wudi/17.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/wudi/17.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/wudi/17.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/wudi/17.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/wudi/17.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/wudi/17.png")),
		TLK.getImage(Bomb.class.getClassLoader().getResource("images/item/wudi/17.png"))
	};
	
	int x,y;
	int step = 0;
	int stepLevel = 0;
	int type; //随机礼物
	int raiseHPTank; //碰到礼物时tank的样式
	FightPanel fp = null;
	Tank tk = null;

	static boolean init=false; //第一次画是将图片载入内存
	boolean touch = false;//记录是否碰撞（得到物品动画播放完毕后才将gift从容器中移除）
	
	public Item(int x,int y,FightPanel fp)
	{
		this.x=x;
		this.y=y;
		this.fp=fp;
	}
	
	public void draw(Graphics g)
	{
		//写入内存
		if(!init)
		{
			for (int i=0;i<GIFTS.length;i++)
			{
				g.drawImage(GIFTS[i],x,y,null);
			}
			for (int i=0;i<GIFT_LEVEL.length;i++)
			{
				g.drawImage(GIFT_LEVEL[i],x,y,null);
			}
			for (int i=0;i<GIFT_RAISEHP.length;i++)
			{
				g.drawImage(GIFT_RAISEHP[i],x,y,null);
			}
			for (int i=0;i<GIFT_RAISEHP_TANK.length;i++)
			{
				g.drawImage(GIFT_RAISEHP_TANK[i],x,y,null);
			}
			for (int i=0;i<GIFT_LIFE.length;i++)
			{
				g.drawImage(GIFT_LIFE[i],x,y,null);
			}
			for (int i=0;i<GIFT_WUDI.length;i++)
			{
				g.drawImage(GIFT_WUDI[i],x,y,null);
			}
			init=true;
		}
		
		if(!touch)
		{
			g.drawImage(GIFTS[step],x,y,null);
			
			step++;
			
			if(step>=GIFTS.length)
			{
				step = 0;
			}
		}else
		{
			this.drawGift(g);
		}
	}
	
	void drawGift(Graphics g)
	{
		switch(type)
		{
		case 0:
			if(stepLevel >= GIFT_RAISEHP.length-1)
			{
				fp.itemList.remove(this);
			}
			g.drawImage(GIFT_RAISEHP[stepLevel],x,y-36,null);
			
			switch(tk.level)
			{
			case 0:
			case 1:
				raiseHPTank = 0;
				break;
			case 2:
			case 3:
				raiseHPTank = 4;
				break;
			case 4:
			case 5:
				raiseHPTank = 8;
				break;
			case 6:
			case 7:
				raiseHPTank = 12;
				break;
			default:break;
			}
			
			if(stepLevel == 0)
			{
				Tank.imgMap0.clear();
				Tank.imgMap0.put(Tank.TANK_DIR.U,GIFT_RAISEHP_TANK[raiseHPTank]);
				Tank.imgMap0.put(Tank.TANK_DIR.D,GIFT_RAISEHP_TANK[raiseHPTank+1]);
				Tank.imgMap0.put(Tank.TANK_DIR.L,GIFT_RAISEHP_TANK[raiseHPTank+2]);
				Tank.imgMap0.put(Tank.TANK_DIR.R,GIFT_RAISEHP_TANK[raiseHPTank+3]);
			}
			
			if(stepLevel == GIFT_RAISEHP.length*3/5)
			{
				Tank.imgMap0.clear();
				switch(tk.level)
				{
				case 0:
				case 1:
					Tank.imgMap0.put(Tank.TANK_DIR.U,Tank.TANKBODY0[0]);
					Tank.imgMap0.put(Tank.TANK_DIR.D,Tank.TANKBODY0[1]);
					Tank.imgMap0.put(Tank.TANK_DIR.L,Tank.TANKBODY0[2]);
					Tank.imgMap0.put(Tank.TANK_DIR.R,Tank.TANKBODY0[3]);
					break;
				case 2:
				case 3:
					Tank.imgMap0.put(Tank.TANK_DIR.U,Tank.TANKBODY0[4]);
					Tank.imgMap0.put(Tank.TANK_DIR.D,Tank.TANKBODY0[5]);
					Tank.imgMap0.put(Tank.TANK_DIR.L,Tank.TANKBODY0[6]);
					Tank.imgMap0.put(Tank.TANK_DIR.R,Tank.TANKBODY0[7]);
					break;
				case 4:
				case 5:
					Tank.imgMap0.put(Tank.TANK_DIR.U,Tank.TANKBODY0[8]);
					Tank.imgMap0.put(Tank.TANK_DIR.D,Tank.TANKBODY0[9]);
					Tank.imgMap0.put(Tank.TANK_DIR.L,Tank.TANKBODY0[10]);
					Tank.imgMap0.put(Tank.TANK_DIR.R,Tank.TANKBODY0[11]);
					break;
				case 6:
				case 7:
					Tank.imgMap0.put(Tank.TANK_DIR.U,Tank.TANKBODY0[12]);
					Tank.imgMap0.put(Tank.TANK_DIR.D,Tank.TANKBODY0[13]);
					Tank.imgMap0.put(Tank.TANK_DIR.L,Tank.TANKBODY0[14]);
					Tank.imgMap0.put(Tank.TANK_DIR.R,Tank.TANKBODY0[15]);
					break;
					default:break;
				}
				tk.isRaiseHP = false;
			}
			break;
		case 1:
			if(stepLevel >= GIFT_LIFE.length-1)
			{
				fp.itemList.remove(this);
			}
			g.drawImage(GIFT_LIFE[stepLevel],x,y-36,null);
			break;
		case 2:
			if(stepLevel >= GIFT_LEVEL.length-1)
			{
				fp.itemList.remove(this);
			}
			g.drawImage(GIFT_LEVEL[stepLevel],x,y-36,null);
			break;
		case 3:
			if(stepLevel >= GIFT_WUDI.length-1)
			{
				fp.itemList.remove(this);
			}
			g.drawImage(GIFT_WUDI[stepLevel],x,y-36,null);
			break;
		default:break;
		}
		stepLevel++;
	}
	
	public void touch(int type,Tank tk)
	{
		this.type = type;
		this.tk = tk;
		
		switch(type)
		{
		case 0:
			raiseHP(tk);
			tk.isRaiseHP = true;
			break;
		case 1:
			addLife();
			break;
		case 2:
			addLevel(tk);
			break;
		case 3:
			wuDi(tk);
			break;
		default:break;
		}
		touch = true;	
	}
	
	public void raiseHP(Tank tk) //回满血
	{
		tk.tankHealthPoint.raisesHitPoint(9999);
	}
	
	public void addLife() //生命+1
	{
		Status.addLife();
	}
	
	public void addLevel(Tank tk)
	{
		if(tk.camp)
		{
			switch(tk.getLevel())
			{
			case 0:
				tk.setLevel((tk.getLevel()+1));
				FireBomb.init = false;
				SuperShotBomb.init = false;
				
				tk.tankHealthPoint.raisesHitPoint(100);
				tk.tankHealthPoint.setTopHealthPoint(450);
				break;
			case 1:
				tk.setLevel((tk.getLevel()+1));
				switch(tk.tankDir)
				{
				case U:
					tk.turretX=tk.x+12;
					tk.turretY=tk.y-8;
					break;
				case D:
					tk.turretX=tk.x+13;
					tk.turretY=tk.y+32;
					break;
				case L:
					tk.turretX=tk.x-23;
					tk.turretY=tk.y+14;
					break;
				case R:
					tk.turretX=tk.x+49;
					tk.turretY=tk.y+14;
					break;
				default:break;
				}
				
				FireBomb.init = false;
				SuperShotBomb.init = false;
				
				Tank.imgMap0.clear();
				
				Tank.imgMap0.put(TANK_DIR.U,Tank.TANKBODY0[4]);
				Tank.imgMap0.put(TANK_DIR.D,Tank.TANKBODY0[5]);
				Tank.imgMap0.put(TANK_DIR.L,Tank.TANKBODY0[6]);
				Tank.imgMap0.put(TANK_DIR.R,Tank.TANKBODY0[7]);
				
				tk.tankHealthPoint.raisesHitPoint(60);
				tk.tankHealthPoint.setTopHealthPoint(600);
				break;
			case 2:
				tk.setLevel((tk.getLevel()+1));
				switch(tk.tankDir)
				{
				case U:
					tk.setTurretXY(x+14,y-8);
					break;
				case D:
					tk.setTurretXY(x+13,y+12);
					break;
				case L:
					tk.setTurretXY(x+6,y);
					break;
				case R:
					tk.setTurretXY(x+23,y+2);
					break;
				default:break;
				}
				
				FireBomb.init = false;
				SuperShotBomb.init = false;
				
				tk.tankHealthPoint.raisesHitPoint(60);
				tk.tankHealthPoint.setTopHealthPoint(700);
				break;
			case 3:
				tk.setLevel((tk.getLevel()+1));
				FireBomb.init = false;
				SuperShotBomb.init = false;
				tk.TANKSPEED=5;
				
				Tank.imgMap0.clear();
				
				Tank.imgMap0.put(TANK_DIR.U,Tank.TANKBODY0[8]);
				Tank.imgMap0.put(TANK_DIR.D,Tank.TANKBODY0[9]);
				Tank.imgMap0.put(TANK_DIR.L,Tank.TANKBODY0[10]);
				Tank.imgMap0.put(TANK_DIR.R,Tank.TANKBODY0[11]);
				
				tk.tankHealthPoint.raisesHitPoint(60);
				tk.tankHealthPoint.setTopHealthPoint(800);
				break;
			case 4:
				tk.setLevel((tk.getLevel()+1));
				FireBomb.init = false;
				SuperShotBomb.init = false;
				
				tk.tankHealthPoint.raisesHitPoint(60);
				tk.tankHealthPoint.setTopHealthPoint(900);
				break;
			case 5:
				tk.setLevel((tk.getLevel()+1));
				FireBomb.init = false;
				SuperShotBomb.init = false;
				
				switch(tk.tankDir)
				{
				case U:
					tk.turretX=tk.x+9;
					tk.turretY=tk.y-10;
					tk.turretX2=tk.x+16;
					tk.turretY2=tk.y-10;
					break;
				case D:
					tk.turretX=tk.x+16;
					tk.turretY=tk.y+32;
					tk.turretX2=tk.x+9;
					tk.turretY2=tk.y+32;
					break;
				case L:
					tk.turretX=tk.x-20;
					tk.turretY=tk.y+13;
					tk.turretX2=tk.x-20;
					tk.turretY2=tk.y+9;
					break;
				case R:
					tk.turretX=tk.x+45;
					tk.turretY=tk.y+9;
					tk.turretX2=tk.x+45;
					tk.turretY2=tk.y+13;
					break;
				default:break;
				}
				
				Tank.imgMap0.clear();
				
				Tank.imgMap0.put(TANK_DIR.U,Tank.TANKBODY0[12]);
				Tank.imgMap0.put(TANK_DIR.D,Tank.TANKBODY0[13]);
				Tank.imgMap0.put(TANK_DIR.L,Tank.TANKBODY0[14]);
				Tank.imgMap0.put(TANK_DIR.R,Tank.TANKBODY0[15]);
				
				tk.tankHealthPoint.raisesHitPoint(60);
				tk.tankHealthPoint.setTopHealthPoint(1000);
				break;
			case 6:
				tk.setLevel((tk.getLevel()+1));
				FireBomb.init = false;
				SuperShotBomb.init = false;
				
				tk.tankHealthPoint.raisesHitPoint(60);
				tk.tankHealthPoint.setTopHealthPoint(1200);
				break;
			case 7:
				break;
				default:break;
			}
		}
	}
	
	public void wuDi(Tank tk)
	{
		tk.isWuDi = true;
		new Thread(new WuDi((UserTank)tk,false)).start();
	}
	
	public Rectangle getRect()
	{
		return new Rectangle(x+5, y+5, 30, 30);
	}
}


//class HitPointItem extends Item  //涨血的道具
//{
//	static final Image box=TLK.getImage(Item.class.getClassLoader().getResource("images/item/box.png"));
//	
//	public HitPointItem(int x,int y,int raisesNum,FightPanel fp)
//	{
//		super(x,y,fp);
//	}
//
//	void draw(Graphics g)
//	{
//		g.drawImage(box,x,y,null);
//	}
//	
//	void eat(Tank tk)
//	{
////		tk.tankHitPoint.raisesHitPoint(raisesNum);  //Tank涨血
////		fp.itemList.remove(this);
//		System.out.println("碰撞..");
//	}
//}

//class ShotsItem extends Item  //涨炮弹的道具
//{
//	int type; //涨的炮弹类型
//	
//	public ShotsItem(int x,int y,int type,TankClient tc)
//	{
//		super(x,y,tc);
//		this.type=type;
//		this.width=10;
//		this.height=10;
//	}
//
//	void draw(Graphics g)
//	{
//		Color c=g.getColor();
//		
//		g.setColor(Color.orange);
//		g.fillOval(x,y,width,height);
//		
//		g.setColor(c);
//	}
//
//	void eat(Tank tk)
//	{
//		if(!tk.isNPC)  //玩家才可以吃
//		{
//			switch(type)
//			{
//				case 0:{((UserTank)tk).addHydraShots();}break;
//				case 1:{((UserTank)tk).addSuperShots();}break;
//				default:break;
//			}
//			tc.itemList.remove(this);
//		}
//	}
//}

