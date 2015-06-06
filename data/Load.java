package data;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class Load {
	public int sign; //是否为有效存档
	public int state;
	public int fight;
	public String name;
	public String year;
	public String month;
	public String day;
	public String hours;
	public String minutes;
	public String seconds;

	public Load(int sign, int state, int fight, String name, String year,	String month, String day, String hours, String minutes,	String seconds)
	{
		this.sign = sign;
		this.state = state;
		this.fight = fight;
		this.name = name;
		this.year = year;
		this.month = month;
		this.day = day;
		this.hours = hours;
		this.minutes = minutes;
		this.seconds = seconds;
	}
}