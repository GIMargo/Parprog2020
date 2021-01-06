package ru.spbstu.telematics.java;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    
   
    public void testAdd()/** �������� ������ ������� add, addAll, contains, containsAll, toArray, size */
    {
    	System.out.println("Test 1\n");
    	MyHashSet<String> s = new MyHashSet<String>();
    	MyHashSet<String> s2 = new MyHashSet<String>();
    	
    	 assertTrue(!s.addAll(s)); /** �������� �� �������������� ������ ���������*/
    	 assertTrue(s2.containsAll(s2)); /** �������� �� ������������� ������ ���������*/
    	
        assertTrue(!s.addAll(s2)); /** ����� addAll ������ false, �.�. �� ������ �������� �� ������ ��������� s2 �� ���� ��������� � ������ ��������� s */    	
        assertTrue(s2.containsAll(s)); /** � ������ ��������� s2 ���������� ��� �������� ������ ��������� s, �.�. ��������� ���� ��������� � ��� - ������ */
        
        assertTrue(!s.addAll(null)); /** �������� �� null */
        
        assertEquals(s.size(),0); /** ������ ������ ��������� = 0 */
        assertTrue(s.add("Hello"));
        assertEquals(s.size(),1); /** �������� ������ ������� */
        assertTrue(s.add("������"));
        assertEquals(s.size(),2); /** �������� ������ ������� */
        assertTrue(s.add("hi"));
        assertTrue(!s.add("Hello")); /** ������ �������� �������� ��� ������ */
        assertTrue(!s.add("Hello")); /** � ��� ��� */
        assertTrue(!s.add("������")); /** � ��� */
        assertTrue(!s.add(null)); /** null �������� � ��������� ������ */
        assertEquals(s.size(),3); /** ����� ���� ��������� ��� ��������� ������ */
        
        assertTrue(!s.contains(null)); /** �������� �� null */
        
        assertTrue(s.containsAll(s2)); /** s2 - ������, � ������ ��������� ���������� � ��������� ��������� s */
        assertTrue(!s2.containsAll(s)); /** ������ ��������� s2 �� �������� � ���� ���� ��������� ��������� s */
        
        assertTrue(!s.addAll(s2)); /** ����� addAll ������ false, �.�. �� ������ �������� �� ������ ��������� s2 �� ���� ��������� � ��������� s */
        
        assertTrue(s2.add("Hello"));
        assertTrue(s2.add("Bye"));
        assertTrue(s2.add("����"));
        String a = "Bonjour";
        assertTrue(s2.add(a));
        assertTrue(s2.addAll(s)); /** ��������� � ��������� s2 ��� �������� ��������� s */
        assertEquals(s2.size(),6); /** ������ � s2 ������ ���� 6 ��������� */
        
        assertTrue(s2.containsAll(s)); /** � ��������� s2 ���������� ��� �������� ��������� s */
        assertTrue(!s.containsAll(s2)); /** �������� ������� */
        assertTrue(s2.containsAll(s2)); /** ��������� s2 �������� ��� ���� ��������*/
        
        Object[] arr = s2.toArray(); /** �������������� ��������� � ������ �������� � ����� �������� */
        for(Object o:arr) {
        	System.out.println(o); /** ������� ����� � ������� ����� ��, ��� � ���-������� ��������� */
        }
        
        assertTrue(!s2.addAll(s2)); /** ������ �� ���������, ��������� ��� �������� ��������� s2 ��� � ��� ���������� */
        
        assertTrue(s.contains("������"));
        assertTrue(!s.contains("")); /** � ��������� s ��� ������ ������ */
        
        assertTrue(s.add(new String())); /** ����� �������� ������ ������ */
        assertTrue(!s.add("")); /** �� ������ ������ */
        assertTrue(s.contains(""));  /** ������ ��� ����! */
        
    }
    
    public void testRemove()/** �������� ������ ������� clear, remove, removeAll, retainAll, isEmpty */
    {
    	System.out.println("\nTest 2\n");
    	MyHashSet<String> s = new MyHashSet<String>();
    	MyHashSet<String> s2 = new MyHashSet<String>();
    	
    	assertTrue(s.isEmpty()); /** ��������� s ������ */
    	s.clear(); /** ������� �������� ������ ��������� */
    	s.add("a");
    	assertTrue(!s.isEmpty()); /** ��������� s ������ �������� */
    	s.add("b");
    	s.clear(); 
    	assertTrue(s.isEmpty()); /** ��������� s ����� ������ */
    	assertEquals(s.size(),0);  /** � ������ = 0 */
    	
    	s.add("a");
    	s.add("b");
    	s.add("c");
    	
    	assertTrue(s.remove("a")); /** ������ "a" ���� ������� �� ��������� s �� �������� */
    	assertTrue(!s.remove("d")); /** ������ "d" � ��������� s �� ����, ��� ��� ������� � �� ������� */
    	assertTrue(!s.remove(null));
    	assertTrue(!s.remove(new String()));

    	
    	for(Object o:s) {
        	System.out.println(o); /** ������� ��� �������� ��������� s */
        }
    	
    	s2.add("d");
    	s2.add("b");
    	
    	assertTrue(s.removeAll(s2)); /** ������� �� ��������� s ��� �������� ��������� s2 */
    	
    	System.out.println("\nNow s is:");
    	for(Object o:s) {
        	System.out.println(o); /** ����� ������� ��� �������� ��������� s */
        }
    	
    	assertTrue(!s2.removeAll(s)); /** � ��������� s ������� ������������ ������� "c", �������� ��� � ��������� s2, ������� �� ������ �������� �� ���� �������*/
    	assertTrue(!s2.removeAll(new MyHashSet<String>())); /** �������� ������� ��������� ��������� �� ��������� s2 ��������, ��� �� ������ �������� �� ���� ������� */
        
    	assertTrue(!s2.removeAll(null));  /** �������� �� null */
    	
    	assertTrue(s.removeAll(s));
    	assertTrue(s.isEmpty()); /** ������ s ������ ��������� */
    	assertTrue(!s.removeAll(s)); /** ������ �� ���������, �.�. ��������� s ��� ������ */
    	
    	s.add("b");
    	s.add("e");
    	
    	assertTrue(s.retainAll(s2)); /** � ��������� s �������� ������ �� ��������, ������� ���� � ��������� s2 */
    	
    	System.out.println("\nCommon of s and s2 is:");
    	for(Object o:s) {
        	System.out.println(o);
        }
    	
    	assertTrue(s2.retainAll(s)); /** � ��������� s2 �������� ������ �� ��������, ������� ���� � ��������� s */
    	
    	System.out.println("\nCommon of s2 and s is:");
    	for(Object o:s2) {
        	System.out.println(o);
        }
    	
    	assertTrue(!s2.retainAll(null));
    	assertTrue(!s.retainAll(s));/** ������ �� �������, �.�. � s � ��� ������ �������� s*/
    	assertTrue(s.retainAll(new MyHashSet<String>())); /** �� s ������� ��� ��������, ������� ��� � ������ ���������, �.�. ��� */
    	assertTrue(s.isEmpty());
    	assertTrue(!s.retainAll(new MyHashSet<String>()));
    	assertTrue(!s.retainAll(s2));
    }
}
