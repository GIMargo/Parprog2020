package ru.spbstu.telematics.java;

public class MyRand {
	
	int val;
	float random;

	public MyRand() {
		val=0;
		this.NextRand();
	}
	public MyRand(int start) {
		if(start<6075||start<0){//��������� �������� ��������������� � �� ������ ��������� m=6075
		val=start;
		}
		else {
			val=0;//��������� �������� �� ���������
		}
		this.NextRand(); 
	}
	public void NextInt() {
		val=(val*106+1283)%6075;	
	}
	public void NextRand() {
		
		float sum=0; //����� 12-�� ����� � ��������� [0;1]
		for(int i=0;i<12;i++) {
			float r=(float)(val)/6074;//��������� ����� � ��������� [0;1] �� ������ val
			sum+=r;
			this.NextInt();//����� �������� val
		}
		random=sum-6;//������� ��������� ��������
	}
	
	public void printRand() {
		 System.out.println(random);
	}
	public float getRand() {
		return random;
	}
	public int getVal() {
		return val;
	}
	public void setRand(float r,int v) {
		if(v>=0&&v<6075) {
		val=v;
		} else {
			val=0;
		}
		random=r;
	}

}
