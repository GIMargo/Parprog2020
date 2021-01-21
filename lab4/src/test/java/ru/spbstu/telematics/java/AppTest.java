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
    	
    	TempCalculator t8 = new TempCalculator(-8,-5,-34534,0,0,0,-250,-250,-273.16,50000,-6); /** Проверка на абсурдные параметры */
    	//t8.run();
    	//t8.getTable();

    	/** ниже реализован тот же алгоритм без многопоточности */
	
    	double L=10;  /** длина пластины по Ох = L м */
    	double time=60; /** время измерений = time секунд */
    	
    	int N=1000; /** количество промежутков по х*/
        int ntime=1000; /** количество временных промежутков */
        
        double a=46; /** коэффициент теплопроводности*/
        double c=460; /** удельная теплоёмкость */
        double rho=7800; /** плотность*/
        
        double T0=30; /** начальная температура всех точек, кроме крайних */
        
        double TLeft=0; /** температура левого конца (постоянная)*/
        double TRight=100; /** температура правого конца (постоянная)*/    
        
        double[][] T = new double[ntime][N]; /** здесь хранятся все значения температур в узлах */
        double[] iteration = new double[N];
        T[0][0]=TLeft;
	    T[0][N-1]=TRight;
        for(int i=0;i<N;i++) {
        	if(i>0&&i<N-1) {
  	            T[0][i]=T0;
        	}
        	iteration[i] = T[0][i];
        }
	    
	    double tau = time/ntime; /** временной шаг */ 
	    double h = L/N; /** пространственный шаг */
	    double B = (a*tau)/(h*h*rho*c);
	    double C = (rho*c/tau-2*a/(h*h))*tau/(rho*c);
	    
	    
	    System.out.println("Начальные условия: " + Arrays.toString(iteration));
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
	       System.out.printf("Время работы алгоритма = %d миллисекунд %n", Duration.between(start, finish).toMillis());
	    
	     //  System.out.println("Итоговый массив температур:");
	   //    for(int i=0;i<ntime;i++) {
	    //	   for(int j=0;j<N;j++) {
	    //	       iteration[j]=T[i][j];
	    //	   }
	    //      System.out.println(Arrays.toString(iteration));    	      	  
	     //  }
	           
	           
	    
        assertTrue( true );
        
        
     }
}
