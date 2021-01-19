package ru.spbstu.telematics.java;
import java.util.ArrayList;
import java.util.Random;

public class App 
{
    public static void main( String[] args )
    {
    	TrafficLights t=new TrafficLights();
    	Car c = new Car(0,t,50);
    	Car c1 = new Car(0,t,50);
    	Car c2 = new Car(0,t,50);
    	Car c3 = new Car(1,t,50);
    	Car c4 = new Car(1,t,50);
    	Car c5 = new Car(2,t,50);
    	t.putCar(c2);
    	t.putCar(c);
    	t.putCar(c3);
    	t.putCar(c1);
    	t.putCar(c4);
    	t.putCar(c5);
        Random r= new Random();  	
		 while(true) {
	            if(t.getNS()!=0) {
	            	t.GreenLight(0);
	            	try {
	            	   Thread.sleep(100);
	            	}
	            	catch(InterruptedException e) {
	            		
	            	}
                    t.RedLight(0);
	            }
	            if(t.getWE()!=0) {
	            	t.GreenLight(1);
	            	try {
		            	   Thread.sleep(100);
		            	}
		            	catch(InterruptedException e) {
		            		
		            	}
	            	t.RedLight(1);
	            }
	            if(t.getSW()!=0) {
	            	t.GreenLight(2);
	            	try {
		            	   Thread.sleep(100);
		            	}
		            	catch(InterruptedException e) {
		            		
		            	}
	            	t.RedLight(2);
	            }
	            t.putCar(new Car(r.nextInt()%3,t,50));
	            t.putCar(new Car(r.nextInt()%3,t,50));
	            t.putCar(new Car(r.nextInt()%3,t,50));
	            t.putCar(new Car(r.nextInt()%3,t,50));
	            t.putCar(new Car(r.nextInt()%3,t,50));
	        }
	}
    
}
