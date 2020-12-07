package ru.spbstu.telematics.java;

import java.util.Arrays; 

public class App 
{
    public static void main( String[] args )
    {
        MyRand r = new MyRand();
        for(int i=0;i<10;i++) {
        	r.printRand();
        	r.NextRand();
        }
        
        int size=100;//������ �������
        int count=0;//������� ��������
        
        for(int k=0;k<1000;k++) {//��������� 1000 �������
             float sample[];
             int c1=size;
             sample = new float[size];//������, �������� �������
             int i=0;//������ ������� sample
                 while (c1 > 0) {
                	r.NextRand();
    	            sample[i]=r.getRand();    
                    //System.out.println(sample[i]);
                    c1--;
                    i++;
                  } 
       
               Arrays.sort(sample); //���������� �������
       
              // String floatArrayString = Arrays.toString(sample);
              // System.out.println(floatArrayString);
       
               float q1=sample[24];
               float q3=sample[74];
               float x1=q1-1.5f*(q3-q1);
               float x2=q3+1.5f*(q3-q1);
       
                for(int j = 0; j < size; j++) {
    	              if(sample[j]>x2||sample[j]<x1)
    	                {
    		              count++;
    	                }
              	}
       
        }//end for
        
      System.out.println("������� ����� ��������: ");
      float v=(float)(count)/(1000*size);
      System.out.println(v);
      
    }//end main
}//end App
