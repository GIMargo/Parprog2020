package ru.spbstu.telematics.java;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

interface func {
    double updated(int position);
}

public class TempCalculator implements Runnable{
    
	double L;  /** ����� �������� �� �� = L � */
	double time; /** ����� ��������� = time ������ */
	
	int N; /** ���������� ����������� �� �*/
    int ntime; /** ���������� ��������� ����������� */
    
    double a; /** ����������� ����������������*/
    double c; /** �������� ����������� */
    double rho; /** ���������*/
    
    double T0; /** ��������� ����������� ���� �����, ����� ������� */
    
    double TLeft; /** ����������� ������ ����� (����������)*/
    double TRight; /** ����������� ������� ����� (����������)*/    
    
    double[][] T; /** ����� �������� ��� �������� ���������� � ����� */
    
    int numberOfThreads; /** �������� ����� ������������ ������� */

	public TempCalculator() {
		   L=10; 
	       time=60; 	       
	       N=50; 
	       ntime=10; 	       
	       a = 46; /** ����������� ���������������� ����� */
	       c = 460; /** �������� ����������� ����� */
	       rho = 7800; /** ��������� ����� */	       
           T0=50; 	       
	       TLeft = 0; 
	       TRight = 100;
	       numberOfThreads=5;
	       T = new double[ntime][N];
	       for(int i=1;i<N-1;i++) {
	    	      T[0][i]=T0;
	       }
	       T[0][0]=TLeft;
		   T[0][N-1]=TRight;
	}
	
	public TempCalculator(double lenght, double fulltime,int num, int timesteps,double lambda, double cOfObject, double rhoOfObject, double TAll, double TLeftSide, double TRightSide, int threads) {
		   if(lenght>0) {
		   L=lenght; 
		   }else {
			   L=10;
		   }
		   if(fulltime>0) {
			   time=fulltime;
		   }else {
			   time=60;    
		   }
		   if(num>99&&L<=1) {
			   N=90;
		   }else if(num>0&&L>1){
			   N=num;
		   }else {
		       N=50; 
		   }
		   if(timesteps>0) {
			   ntime=timesteps;
		   }else {
	           ntime=10;
		   }
		   if(lambda>0) {
			   a=lambda;
		   }else {
			   a = 46; /** ����������� ���������������� ����� */
		   }
		   if(cOfObject>0) {
			   c=cOfObject;
		   }else {
			   c = 460; /** �������� ����������� ����� */
		   }
           if(rhoOfObject>0) {
        	   rho=rhoOfObject;
           }else {
        	   rho = 7800; /** ��������� ����� */	
           }
           if(TAll>=-273.16&&TAll<3422) { /**���������� ���� � ����������� ��������� ���������*/
        	   T0=TAll;
           }else {
               T0=50; 
           }   
           if(TLeftSide>=-273.16&&TLeftSide<3422) {
        	   TLeft=TLeftSide;
           }else {
    	       TLeft = 0; 
           }
           if(TRightSide>=-273.16&&TRightSide<3422) {
        	   TRight=TRightSide;
           }else {
    	       TRight = 100;
           }
           if(threads>0) {
        	   numberOfThreads=threads;
           }else {
    	       numberOfThreads=5;
           }
	       T = new double[ntime][N];
	       for(int i=1;i<N-1;i++) {
	    	      T[0][i]=T0;
	       }
	       T[0][0]=TLeft;
		   T[0][N-1]=TRight;
	}
	
	private double getB() {
		double tau = time/ntime; /** ��������� ��� */ 
		double h = L/N; /** ���������������� ��� */
		return (a*tau)/(h*h*rho*c);
	}
	private double getC() {
		double tau = time/ntime; /** ��������� ��� */ 
		double h = L/N; /** ���������������� ��� */
		return (rho*c/tau-2*a/(h*h))*tau/(rho*c);
	}
	
	public void getTable() {
		 System.out.println("�������� ������ ����������:");
		 double[] iteration = new double[N];
	       for(int i=0;i<ntime;i++) {
	    	   for(int j=0;j<N;j++) {
	    	       iteration[j]=T[i][j];
	    	   }
	          System.out.println(Arrays.toString(iteration));    	      	  
	       }
	}

	@Override
	public void run() {
		 double[] iteration = new double[N];
		 for(int i=0;i<N;i++) {
	    	   iteration[i]=T[0][i];
	       }
		 
		 final ExecutorService executor = Executors.newFixedThreadPool(numberOfThreads);

	       System.out.println("��������� �������: " + Arrays.toString(iteration));
	       Instant start = Instant.now();
	       for (int i = 1; i < ntime; i++) {
	           double[] prev = Arrays.copyOf(iteration, N);

	           func myFunc = (int position) -> (prev[position - 1]*getB() + prev[position]*getC() + prev[position + 1]*getB());

	           final List<CompletableFuture<Double>> futures = new ArrayList<>();

	           for (int j = 1; j < N - 1; j++) {
	               final int position = j;
	               final CompletableFuture<Double> oneFuture = new CompletableFuture<Double>();
	               executor.submit(() -> {
	                   double value = myFunc.updated(position);
	                   oneFuture.complete(value);
	               });
	               futures.add(oneFuture);
	           }

	           for (int j = 1; j < N - 1; j++) {
	               try {
	                   iteration[j] = futures.get(j - 1).get(10_000, TimeUnit.MILLISECONDS);
	               } catch (InterruptedException e) { /** ���� ������ ���� �������� */
	                   executor.shutdownNow(); /** ��������� ������ ������� */
	               } catch (ExecutionException e) { /** ���� ��������� ������ ���������� */
	                   System.out.println("Error! " + e.getMessage());
	                   executor.shutdownNow();
	               } catch (TimeoutException e) { /** ���� ��������� �� ��� ������� �� 10 ������ */
	                   System.out.println("Timeout error!");
	                   executor.shutdownNow();
	               }
	           }           
	           for(int j=0;j<N;j++) {
	        	   T[i][j]=iteration[j];
	           }
	       }
	       executor.shutdownNow();
	       Instant finish = Instant.now();
	       System.out.printf("����� ������ ��������� = %d ����������� %n", Duration.between(start, finish).toMillis());
	}

}
