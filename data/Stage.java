package data;

public class Stage {
	
	private static int date[][]={ //关卡数据
		{1,0,1,1,1,1},
		{2,0,1,1,1,1},
		{3,0,1,1,1,1},
		{4,1,1,0,0,0},
		{4,2,0,1,0,0},
		{4,3,0,0,1,0},
		{4,4,0,0,0,1},
		{4,5,1,0,0,0},
		{4,6,0,1,0,0},
		{4,7,0,0,1,0},
		{4,8,2,2,2,8},
		{5,0,0,0,0,0},
	};
	
	/**
	 * {A,B,C,D,E,F},
	 * @param A 关卡
	 * @param B 1：开启轮番攻击基地模式
	 * @param C 该关卡内 犀牛tank 的数量
	 * @param D 该关卡内 杀手tank 的数量
	 * @param E 该关卡内 光稜tank 的数量
	 * @param F 该关卡内 犀牛tank 的数量
	 */
	
	public static int[][] getDate() {
		return date;
	}
}
