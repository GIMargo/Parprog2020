package ru.spbstu.telematics.java;
import java.util.ArrayList;
import java.util.Random;

public class App 
{
    public static void main( String[] args )
    {
    	TrafficLights t=new TrafficLights(100);
    	int passtime=30;
    	 t.putCar(new Car(1,t,50,passtime));
    	 t.putCar(new Car(0,t,50,passtime));
    	 t.putCar(new Car(0,t,50,passtime));
    	 t.putCar(new Car(0,t,50,passtime));
    	 t.putCar(new Car(0,t,50,passtime));
    	 t.putCar(new Car(2,t,50,passtime));
    	 t.putCar(new Car(2,t,50,passtime));
    	 t.putCar(new Car(1,t,50,passtime));
    	 t.putCar(new Car(1,t,50,passtime));
    	 t.putCar(new Car(1,t,50,passtime));
    	 t.putCar(new Car(1,t,50,passtime));
    	 t.putCar(new Car(1,t,50,passtime));
    	 t.putCar(new Car(1,t,50,passtime));
    	 t.putCar(new Car(2,t,50,passtime));
    	 t.putCar(new Car(2,t,50,passtime));
    	 t.putCar(new Car(2,t,50,passtime));
    	 t.putCar(new Car(2,t,50,passtime));
    	 t.putCar(new Car(2,t,50,passtime));
        Random r= new Random();  	
		 while(true) {
	            if(t.getNS()!=0) {
	            	t.GreenLight(0);
	            	try {
	            	   Thread.sleep(t.getGreenTime());
	            	}
	            	catch(InterruptedException e) {	            		
	            	}
	            	t.YellowLight(0);
	            	try {
		            	   Thread.sleep(passtime);
		            	}
		            	catch(InterruptedException e) {	            		
		            	}
                    t.RedLight(0);
	            }
	            if(t.getWE()!=0) {
	            	t.GreenLight(1);
	            	try {
	            		 Thread.sleep(t.getGreenTime());
		            	}
		            	catch(InterruptedException e) {
		            		
		            	}
	            	t.YellowLight(1);
	            	try {
	            		Thread.sleep(passtime);
		            	}
		            	catch(InterruptedException e) {	            		
		            	}
	            	t.RedLight(1);
	            }
	            if(t.getSW()!=0) {
	            	t.GreenLight(2);
	            	try {
	            		 Thread.sleep(t.getGreenTime());
		            	}
		            	catch(InterruptedException e) {	
		            	}
	            	t.YellowLight(2);
	            	try {
	            		Thread.sleep(passtime);
		            	}
		            	catch(InterruptedException e) {	            		
		            	}
	            	t.RedLight(2);
	            }
	            t.putCar(new Car(r.nextInt()%3,t,50,passtime));
	            t.putCar(new Car(r.nextInt()%3,t,50,passtime));
	            t.putCar(new Car(r.nextInt()%3,t,50,passtime));
	            t.putCar(new Car(r.nextInt()%3,t,50,passtime));
	            t.putCar(new Car(r.nextInt()%3,t,50,passtime));

	        }
    }
}
    