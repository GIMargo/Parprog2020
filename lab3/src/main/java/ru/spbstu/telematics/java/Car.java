package ru.spbstu.telematics.java;

public class Car extends Thread {
	
	int m_dir; /** направление движения машины */
	TrafficLights m_t; /** светофор */
	int m_wait; /** время ожидания зелёного сигнала светофора */

	public Car() {
		m_dir=0; /** направление движения с севера на юг (NS) */
	}
	
	public Car(int dir, TrafficLights t, int wait) {
		if(dir>=0&&dir<3) {
			m_dir=dir;
		}else if(dir>0){
			m_dir=dir%3;
		}
		else {
			m_dir=0;
		}
		m_t=t;
		if(wait>0) {
		m_wait=wait;
		}else {
			m_wait=50;
		}
	}

	public Car(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}
	
	public int getDir() {
		return m_dir;
	}
	
	public String getDirName() {
		if(m_dir==0) {
			return "North-to-South";
		}
		if(m_dir==1) {
			return "West-to-East";
		}
		if(m_dir==2) {
			return "South-to-West";
		}
		return "Error direction!";
	}
	
	public void setDir(int dir) {
		if(dir>=0&&dir<3) {
			m_dir=dir;
		}else if(dir>0){
			m_dir=dir%3;
		}else {
			m_dir=0;
		}
	}

	
	public synchronized void run(){
		boolean ifI = ifI=m_t.ifFirst(this);
		while(!ifI) {
			try {
		        sleep(20);
			}
			catch(InterruptedException e) {		
			}
			ifI=m_t.ifFirst(this);
		}
	    boolean pass=false;
		if(m_dir==0) {
		    while(!pass) {
			     if(m_t.getLightNS()){
				    System.out.println("The car number " + getName() + " with direction "+ getDirName()+ " is passing the crossroad!");
				    m_t.removeCar(m_dir);
				    pass=true;
			     }else {
				      try {
				          sleep(m_wait);
				      }
				      catch(InterruptedException e) {					
			          }
			      }
		 
		   }
		}
		if(m_dir==1) {
		    while(!pass) {
			     if(m_t.getLightWE()){
				    System.out.println("The car number " + getName() + " with direction "+ getDirName()+ " is passing the crossroad!");
				    m_t.removeCar(m_dir);
				    pass=true;
			     }else {
				      try {
					      //System.out.println("The car number " + getName() + " with direction "+ getDirName()+ " is waiting on the crossroad!");
				          sleep(m_wait);
				      }
				      catch(InterruptedException e) {					
			          }
			      }
		 
		   }
		}
		if(m_dir==2) {
		    while(!pass) {
			     if(m_t.getLightSW()){
				    System.out.println("The car number " + getName() + " with direction "+ getDirName()+ " is passing the crossroad!");
				    m_t.removeCar(m_dir);
				    pass=true;
			     }else {
				      try {
					      //System.out.println("The car number " + getName() + " with direction "+ getDirName()+ " is waiting on the crossroad!");
				          sleep(m_wait);
				      }
				      catch(InterruptedException e) {					
			          }
			      }
		 
		   }
		}
		
        }
}
