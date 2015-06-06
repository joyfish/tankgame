package data;

public class Records
{
	public int sign; //是否为有效战绩
	public int score;
	public String name;

	public Records(int sign, int score, String name)
	{
		this.sign = sign;
		this.score = score;
		this.name = name;
	}
}