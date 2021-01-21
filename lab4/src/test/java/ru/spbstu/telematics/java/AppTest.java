package ru.spbstu.telematics.java;

import java.time.Duration;
import java.time.Instant;

import java.util.Arrays;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class AppTest 
    extends TestCase
{
    
    public void testApp()
    {
    	TempCalculator t = new TempCalculator(10,60,1000,1000,46,460,7800,30,0,100,1);
    	t.run();
    	//t.getTable();
    	
    	TempCalculator t1 = new TempCalculator(10,60,1000,1000,46,460,7800,30,0,100,2);
    	t1.run();
    	//t1.getTable();
    	
    	TempCalculator t2 = new TempCalculator(10,60,1000,1000,46,460,7800,30,0,100,3);
    	t2.run();
    	//t2.getTable();
    	
    	TempCalculator t3 = new TempCalculator(10,60,1000,1000,46,460,7800,30,0,100,4);
    	t3.run();
    	//t3.getTable();
    	
    	TempCalculator t4 = new TempCalculator(10,60,1000,1000,46,460,7800,30,0,100,5);
    	t4.run();
    	//t4.getTable();
    	
    	TempCalculator t5 = new TempCalculator(10,60,1000,1000,46,460,7800,30,0,100,10);
    	t5.run();
    	//t5.getTable();
    	
    	TempCalculator tt = new TempCalculator(10,60,1000,1000,46,460,7800,30,0,100,15);
    	tt.run();
    	//t5.getTable();
        
        TempCalculator t6 = new TempCalculator(10,60,1000,1000,46,460,7800,30,0,100,20);
    	t6.run();
    	//t6.getTable();
    	
    	TempCalculator t7 = new TempCalculator(10,60,1000,1000,46,460,7800,30,0,100,50);
    	t7.run();
    	//t7.getTable();
    	
    	TempCalculator t8 = new TempCalculator(-8,-5,-34534,0,0,0,-250,-250,-273.16,50000,-6); /** �������� �� ��������� ��������� */
    	//t8.run();
    	//t8.getTable();

    	/** ���� ���������� ��� �� �������� ��� ��������������� */
	
    	double L=10;  /** ����� �������� �� �� = L � */
    	double time=60; /** ����� ��������� = time ������ */
    	
    	int N=1000; /** ���������� ����������� �� �*/
        int ntime=1000; /** ���������� ��������� ����������� */
        
        double a=46; /** ����������� ����������������*/
        double c=460; /** �������� ����������� */
        double rho=7800; /** ���������*/
        
        double T0=30; /** ��������� ����������� ���� �����, ����� ������� */
        
        double TLeft=0; /** ����������� ������ ����� (����������)*/
        double TRight=100; /** ����������� ������� ����� (����������)*/    
        
        double[][] T = new double[ntime][N]; /** ����� �������� ��� �������� ���������� � ����� */
        double[] iteration = new double[N];
        T[0][0]=TLeft;
	    T[0][N-1]=TRight;
        for(int i=0;i<N;i++) {
        	if(i>0&&i<N-1) {
  	            T[0][i]=T0;
        	}
        	iteration[i] = T[0][i];
        }
	    
	    double tau = time/ntime; /** ��������� ��� */ 
	    double h = L/N; /** ���������������� ��� */
	    double B = (a*tau)/(h*h*rho*c);
	    double C = (rho*c/tau-2*a/(h*h))*tau/(rho*c);
	    
	    
	    System.out.println("��������� �������: " + Arrays.toString(iteration));
	       Instant start = Instant.now();	       
	       for (int i = 1; i < ntime; i++) {
	          double[] prev = Arrays.copyOf(iteration, N);
              func myFunc = (int position) -> (prev[position - 1]*B + prev[position]*C + prev[position + 1]*B);
	           for (int j = 1; j < N - 1; j++) {
	        	   iteration[j] = myFunc.updated(j);
	        	   T[i][j]=iteration[j];
	           }
	           T[i][N-1]=TRight;
	       }
	       
	       Instant finish = Instant.now();
	       System.out.printf("����� ������ ��������� = %d ����������� %n", Duration.between(start, finish).toMillis());
	    
	     //  System.out.println("�������� ������ ����������:");
	   //    for(int i=0;i<ntime;i++) {
	    //	   for(int j=0;j<N;j++) {
	    //	       iteration[j]=T[i][j];
	    //	   }
	    //      System.out.println(Arrays.toString(iteration));    	      	  
	     //  }
	           
	           
	    
        assertTrue( true );
        
        
     }
}
