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
    
	double L;  /** длина пластины по Ох = L м */
	double time; /** время измерений = time секунд */
	
	int N; /** количество промежутков по х*/
    int ntime; /** количество временных промежутков */
    
    double a; /** коэффициент теплопроводности*/
    double c; /** удельная теплоёмкость */
    double rho; /** плотность*/
    
    double T0; /** начальная температура всех точек, кроме крайних */
    
    double TLeft; /** температура левого конца (постоянная)*/
    double TRight; /** температура правого конца (постоянная)*/    
    
    double[][] T; /** здесь хранятся все значения температур в узлах */
    
    int numberOfThreads; /** желаемое число используемых потоков */

	public TempCalculator() {
		   L=10; 
	       time=60; 	       
	       N=50; 
	       ntime=10; 	       
	       a = 46; /** коэффициент теплопроводности стали */
	       c = 460; /** удельная теплоёмкость стали */
	       rho = 7800; /** плотность стали */	       
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
			   a = 46; /** коэффициент теплопроводности стали */
		   }
		   if(cOfObject>0) {
			   c=cOfObject;
		   }else {
			   c = 460; /** удельная теплоёмкость стали */
		   }
           if(rhoOfObject>0) {
        	   rho=rhoOfObject;
           }else {
        	   rho = 7800; /** плотность стали */	
           }
           if(TAll>=-273.16&&TAll<3422) { /**Абсолютный ноль и темпераутра плавления вольфрама*/
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
		double tau = time/ntime; /** временной шаг */ 
		double h = L/N; /** пространственный шаг */
		return (a*tau)/(h*h*rho*c);
	}
	private double getC() {
		double tau = time/ntime; /** временной шаг */ 
		double h = L/N; /** пространственный шаг */
		return (rho*c/tau-2*a/(h*h))*tau/(rho*c);
	}
	
	public void getTable() {
		 System.out.println("Итоговый массив температур:");
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

	       System.out.println("Начальные условия: " + Arrays.toString(iteration));
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
	               } catch (InterruptedException e) { /** если работа была прервана */
	                   executor.shutdownNow(); /** завершаем работу потоков */
	               } catch (ExecutionException e) { /** если произошла ошибка вычисления */
	                   System.out.println("Error! " + e.getMessage());
	                   executor.shutdownNow();
	               } catch (TimeoutException e) { /** если результат не был получен за 10 секунд */
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
	       System.out.printf("Время работы алгоритма = %d миллисекунд %n", Duration.between(start, finish).toMillis());
	}

}
