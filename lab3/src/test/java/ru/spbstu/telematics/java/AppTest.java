package ru.spbstu.telematics.java;

import java.util.Random;
import java.util.Vector;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class AppTest 
    extends TestCase
{
   
    public void testDelay()
    {
  
    	TrafficLights t=new TrafficLights();
    	int waitTime=50;
    	Random r = new Random();
    	for(int i=0;i<100;i++) {
    		t.putCar(new Car(r.nextInt()%3,t,waitTime));
    	}
    	int delay = 100;
    	System.out.println("Ожидание зелёного сигнала для машин = " + waitTime + "; время горения зелёного сигнала светофора = " + delay);
    	 while(t.getNS()>0||t.getSW()>0||t.getWE()>0) {
	            if(t.getNS()!=0) {
	            	t.GreenLight(0);
	            	try {
	            	   Thread.sleep(delay);
	            	}
	            	catch(InterruptedException e) {
	            		
	            	}
                 t.RedLight(0);
	            }
	            if(t.getWE()!=0) {
	            	t.GreenLight(1);
	            	try {
		            	   Thread.sleep(delay);
		            	}
		            	catch(InterruptedException e) {
		            		
		            	}
	            	t.RedLight(1);
	            }
	            if(t.getSW()!=0) {
	            	t.GreenLight(2);
	            	try {
		            	   Thread.sleep(delay);
		            	}
		            	catch(InterruptedException e) {
		            		
		            	}
	            	t.RedLight(2);
	            }
	            waitTime=100;
	        	for(int i=0;i<100;i++) {
	        		t.putCar(new Car(r.nextInt()%3,t,waitTime));
	        	}
	             delay = 50;
	            System.out.println("\nОжидание зелёного сигнала для машин = " + waitTime + "; время горения зелёного сигнала светофора = " + delay);
	       	 while(t.getNS()>0||t.getSW()>0||t.getWE()>0) {
		            if(t.getNS()!=0) {
		            	t.GreenLight(0);
		            	try {
		            	   Thread.sleep(delay);
		            	}
		            	catch(InterruptedException e) {
		            		
		            	}
	                 t.RedLight(0);
		            }
		            if(t.getWE()!=0) {
		            	t.GreenLight(1);
		            	try {
			            	   Thread.sleep(delay);
			            	}
			            	catch(InterruptedException e) {
			            		
			            	}
		            	t.RedLight(1);
		            }
		            if(t.getSW()!=0) {
		            	t.GreenLight(2);
		            	try {
			            	   Thread.sleep(delay);
			            	}
			            	catch(InterruptedException e) {
			            		
			            	}
		            	t.RedLight(2);
		            }
	       	 }
	            assertTrue( true );
    }
    }
}
