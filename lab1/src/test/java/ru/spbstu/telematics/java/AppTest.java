package ru.spbstu.telematics.java;

import static org.junit.Assert.assertNotEquals;

import java.util.Arrays;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;


public class AppTest 
    extends TestCase
{
	
    public void testRandomEquality() {
    	
    	int size = 100;//длина последовательности
    	MyRand[] r= new MyRand[size];
    	
        for(int i=1;i<6075;i++) { //различные начальные значения
    	   MyRand t = new MyRand(i);
    	   for(int j=0;j<size;j++) {
    		   t.NextRand();
    		   r[j] = new MyRand(i);
    		   r[j].setRand(t.getRand(), t.getVal()); 
    	   }
           
    	   for(int k=0;k<size;k++) {
    		   for(int j=0;j<size;j++){
    			  if(j!=k) {
    				 assertNotEquals(r[k],r[j]);
    			  }
    		   }
    	   }
 
       }
    }
    
    public void testNormalRand() {
    	
    	for(int i=1;i<6075;i++) {
    		System.out.println(i);
    		MyRand r = new MyRand(i);
        	int size=100;//размер выборки
            int count=0;//счётчик выбросов
            
            for(int k=0;k<100;k++) {//генерация 100 выборок
                 float sample[];
                 int c1=size;
                 sample = new float[size];//массив, хранящий выборку
                 int j=0;//индекс массива sample
                     while (c1 > 0) {
                    	r.NextRand();
        	            sample[j]=r.getRand();    
                        c1--;
                        j++;
                      } 
           
                   Arrays.sort(sample); //сортировка выборки
                   
           
                   float q1=sample[24];
                   float q3=sample[74];
                   float x1=q1-1.5f*(q3-q1);
                   float x2=q3+1.5f*(q3-q1);
           
                    for(int s = 0; s < size; s++) {
        	              if(sample[s]>x2||sample[s]<x1)
        	                {
        		              count++;
        	                }
                  	}
           
            }//end for
            
          System.out.println("Среднее число выбросов:");
          float v=(float)(count)/(100*size);
          System.out.println(v);
          boolean f=false;
          if(Math.abs(v-0.007f)<=0.003f) {
        	  f=true;
          }
          assertTrue(f);
        }
    	
    	
    	
    	}//end of test
    	
    
}
