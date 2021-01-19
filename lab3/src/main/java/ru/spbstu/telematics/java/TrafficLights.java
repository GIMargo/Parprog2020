package ru.spbstu.telematics.java;
import java.util.ArrayDeque;
import java.util.Vector;

public class TrafficLights{
	
	ArrayDeque<Car> qNS = new ArrayDeque<Car>(100);
	ArrayDeque<Car> qWE = new ArrayDeque<Car>(100);
	ArrayDeque<Car> qSW = new ArrayDeque<Car>(100);
	
	boolean lNS;
	boolean lWE;
	boolean lSW;

	public TrafficLights() {
		lNS=false;
		lWE=false;
		lSW=false;
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
	
	public boolean putCar(Car c){
		if(c.getDir()==0) {
			qNS.add(c);
			System.out.println("The car number " + c.getName() + " with direction "+ c.getDirName()+ " has arrived to the crossroad!");
			c.start();
			return true;
		}else if(c.getDir()==1) {
			qWE.addLast(c);
			System.out.println("The car number " + c.getName() + " with direction "+ c.getDirName()+ " has arrived to the crossroad!");
			c.start();
			return true;
		}else if(c.getDir()==2) {
			qSW.addLast(c);
			System.out.println("The car number " + c.getName() + " with direction "+ c.getDirName()+ " has arrived to the crossroad!");
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
	
	public boolean getLightNS() {
		return lNS;
	}
	public boolean getLightWE() {
		return lWE;
	}
	public boolean getLightSW() {
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
	
	public void RedLight(int dir) {
		if(dir==0) {
			System.out.println("Red light to the North-to-South direction!");
			lNS=false;
		}
		if(dir==1) {
			System.out.println("Red light to the West-to-East direction!");
			lWE=false;
		}
		if(dir==2) {
			System.out.println("Red light to the South-to-West direction!");
			lSW=false;
		}
	}
	
	public void GreenLight(int dir) { 
		if(dir==0) {
			System.out.println("Green light to the North-to-South direction!");
			lNS=true;
		}
		if(dir==1) {
			System.out.println("Green light to the West-to-East direction!");
			lWE=true;
		}
		if(dir==2) {
			System.out.println("Green light to the South-to-West direction!");
			lSW=true;
		}
	
	}
	


}
