package ru.spbstu.telematics.java;
import java.util.ArrayDeque;
import java.util.Vector;
import java.util.concurrent.ForkJoinPool.ManagedBlocker;
import java.util.concurrent.locks.ReentrantLock;

public class TrafficLights{
	
	ArrayDeque<Car> qNS = new ArrayDeque<Car>(100);
	ArrayDeque<Car> qWE = new ArrayDeque<Car>(100);
	ArrayDeque<Car> qSW = new ArrayDeque<Car>(100);
	
	int lNS;
	int lWE;
	int lSW;
	
	ReentrantLock locker = new ReentrantLock();
	
	int m_greentime;

	public TrafficLights(int green) {
		lNS=0;
		lWE=0;
		lSW=0;
		if(green>30) {
			m_greentime=green;	
		}else {
			m_greentime=100;
		}
	}
	
	public boolean ifFirst(Car c) {
		if(c.getDir()==0&&qNS.size()>0) {
			return qNS.peekFirst().getName()==c.getName();
		}
		if(c.getDir()==1&&qWE.size()>0) {
			return qWE.peekFirst().getName()==c.getName();
		}
		if(c.getDir()==2&&qSW.size()>0) {
			return qSW.peekFirst().getName()==c.getName();
		}
		return false;
	}
	public int getGreenTime() {
		return m_greentime;
	}
	public boolean putCar(Car c){
		if(c.getDir()==0) {
			qNS.add(c);
			c.start();
			return true;
		}else if(c.getDir()==1) {
			qWE.addLast(c);
			//System.out.println("The car number " + c.getName() + " with direction "+ c.getDirName()+ " has arrived to the crossroad!");
			c.start();
			return true;
		}else if(c.getDir()==2) {
			qSW.addLast(c);
			//System.out.println("The car number " + c.getName() + " with direction "+ c.getDirName()+ " has arrived to the crossroad!");
			c.start();
			return true;
		}
		return false;
	}
	
	public int getNS() {
		return qNS.size();
	}
	public int getWE() {
		return qWE.size();
	}
	public int getSW() {
		return qSW.size();
	}
	
	public int getLightNS() {
		return lNS;
	}
	public int getLightWE() {
		return lWE;
	}
	public int getLightSW() {
		return lSW;
	}
	
	public void removeCar(int dir) {
		if(dir==0&&qNS.size()>0) {
			qNS.pop();
		}
		if(dir==1&&qWE.size()>0) {
			qWE.pop();
		}
		if(dir==2&&qSW.size()>0) {
			qSW.pop();
		}
	}
	public boolean passing(Car c) {  
		 if(locker.tryLock()) {
		 System.out.println("The car number " + c.getName() + " with direction "+ c.getDirName()+ " is passing the crossroad!");
		 try {
		          c.sleep(c.getPassTime());
		      }
		      catch(InterruptedException e) {					
	          }
		 System.out.println("The car number " + c.getName() + " with direction "+ c.getDirName()+ " has passed the crossroad!");
		 locker.unlock();
		 removeCar(c.getDir()); 
		 return true;
		 }
		 return false;
	}
	
	public void RedLight(int dir) {
		if(dir==0) {
			System.out.println("Red light to the North-to-South direction!");
			lNS=0;
		}
		if(dir==1) {
			System.out.println("Red light to the West-to-East direction!");
			lWE=0;
		}
		if(dir==2) {
			System.out.println("Red light to the South-to-West direction!");
			lSW=0;
		}
	}
	
	public void GreenLight(int dir) { 
		if(dir==0) {
			System.out.println("Green light to the North-to-South direction!");
			lNS=2;
		}
		if(dir==1) {
			System.out.println("Green light to the West-to-East direction!");
			lWE=2;
		}
		if(dir==2) {
			System.out.println("Green light to the South-to-West direction!");
			lSW=2;
		}
	}
	public void YellowLight(int dir) { 
		if(dir==0) {
			System.out.println("Yellow light to the North-to-South direction!");
			lNS=1;
		}
		if(dir==1) {
			System.out.println("Yellow light to the West-to-East direction!");
			lWE=1;
		}
		if(dir==2) {
			System.out.println("Yellow light to the South-to-West direction!");
			lSW=1;
		}
	
	}
	

}