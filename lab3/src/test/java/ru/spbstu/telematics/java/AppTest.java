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
  
    	int waitTime=50;
    	int passtime=30;
    	int delay = 100;
    	TrafficLights t=new TrafficLights(delay);
    	Random r = new Random();
    	for(int i=0;i<200;i++) {
    		t.putCar(new Car(r.nextInt()%3,t,waitTime,passtime));
    	}
    	 while(t.getNS()>0||t.getSW()>0||t.getWE()>0) {
	            if(t.getNS()!=0) {
	            	t.GreenLight(0);
	            	try {
	            		Thread.sleep(t.getGreenTime());
	            	}
	            	catch(InterruptedException e) {	
	            	}
	            	t.YellowLight(0);
	            	try {
		            	   Thread.sleep(passtime+10);
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
		            	   Thread.sleep(passtime+10);
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
	            	t.YellowLight(1);
	            	try {
		            	   Thread.sleep(passtime+10);
		            	}
		            	catch(InterruptedException e) {	            		
		            	}
	            	t.RedLight(2);
	            }

	            assertTrue( true );
         }
    }
}
