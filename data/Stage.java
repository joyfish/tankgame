package data;

public class Stage {
	
	private static int date[][]={ //�ؿ�����
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
	 * @param A �ؿ�
	 * @param B 1�������ַ���������ģʽ
	 * @param C �ùؿ��� Ϭţtank ������
	 * @param D �ùؿ��� ɱ��tank ������
	 * @param E �ùؿ��� �ⶠtank ������
	 * @param F �ùؿ��� Ϭţtank ������
	 */
	
	public static int[][] getDate() {
		return date;
	}
}
