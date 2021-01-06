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
    
   
    public void testAdd()/** проверка работы методов add, addAll, contains, containsAll, toArray, size */
    {
    	System.out.println("Test 1\n");
    	MyHashSet<String> s = new MyHashSet<String>();
    	MyHashSet<String> s2 = new MyHashSet<String>();
    	
    	 assertTrue(!s.addAll(s)); /** проверка на самодобавление пустых коллекций*/
    	 assertTrue(s2.containsAll(s2)); /** проверка на самовключение пустых коллекций*/
    	
        assertTrue(!s.addAll(s2)); /** метод addAll вернул false, т.к. ни одного элемента из пустой коллекции s2 не было добавлено в пустую коллекцию s */    	
        assertTrue(s2.containsAll(s)); /** в пустой коллекции s2 содержатся все элементы пустой коллекции s, т.к. множество всех элементов в них - пустое */
        
        assertTrue(!s.addAll(null)); /** проверка на null */
        
        assertEquals(s.size(),0); /** размер пустой коллекции = 0 */
        assertTrue(s.add("Hello"));
        assertEquals(s.size(),1); /** добавили первый элемент */
        assertTrue(s.add("привет"));
        assertEquals(s.size(),2); /** добавили второй элемент */
        assertTrue(s.add("hi"));
        assertTrue(!s.add("Hello")); /** нельзя повторно добавить эту строку */
        assertTrue(!s.add("Hello")); /** и ещё раз */
        assertTrue(!s.add("привет")); /** и эту */
        assertTrue(!s.add(null)); /** null добавить в коллекцию нельзя */
        assertEquals(s.size(),3); /** всего было добавлено три различных строки */
        
        assertTrue(!s.contains(null)); /** проверка на null */
        
        assertTrue(s.containsAll(s2)); /** s2 - пустая, а пустое множество содержится в множестве элементов s */
        assertTrue(!s2.containsAll(s)); /** пустая коллекция s2 не содержит в себе всех элементов коллекции s */
        
        assertTrue(!s.addAll(s2)); /** метод addAll вернул false, т.к. ни одного элемента из пустой коллекции s2 не было добавлено в коллекцию s */
        
        assertTrue(s2.add("Hello"));
        assertTrue(s2.add("Bye"));
        assertTrue(s2.add("Пока"));
        String a = "Bonjour";
        assertTrue(s2.add(a));
        assertTrue(s2.addAll(s)); /** добавляем в коллекцию s2 все элементы коллекции s */
        assertEquals(s2.size(),6); /** теперь в s2 должно быть 6 элементов */
        
        assertTrue(s2.containsAll(s)); /** в коллекции s2 содержатся все элементы коллекции s */
        assertTrue(!s.containsAll(s2)); /** обратное неверно */
        assertTrue(s2.containsAll(s2)); /** коллекция s2 содержит все свои элементы*/
        
        Object[] arr = s2.toArray(); /** преобразование коллекции в массив объектов и вывод значений */
        for(Object o:arr) {
        	System.out.println(o); /** порядок строк в массиве такой же, как в хеш-таблице коллекции */
        }
        
        assertTrue(!s2.addAll(s2)); /** ничего не добавится, поскольку все элементы коллекции s2 уже в ней содержатся */
        
        assertTrue(s.contains("привет"));
        assertTrue(!s.contains("")); /** в коллекции s нет пустой строки */
        
        assertTrue(s.add(new String())); /** можно добавить пустую строку */
        assertTrue(!s.add("")); /** но дважды нельзя */
        assertTrue(s.contains(""));  /** теперь она есть! */
        
    }
    
    public void testRemove()/** проверка работы методов clear, remove, removeAll, retainAll, isEmpty */
    {
    	System.out.println("\nTest 2\n");
    	MyHashSet<String> s = new MyHashSet<String>();
    	MyHashSet<String> s2 = new MyHashSet<String>();
    	
    	assertTrue(s.isEmpty()); /** коллекция s пустая */
    	s.clear(); /** попытка очистить пустую коллекцию */
    	s.add("a");
    	assertTrue(!s.isEmpty()); /** коллекция s теперь непустая */
    	s.add("b");
    	s.clear(); 
    	assertTrue(s.isEmpty()); /** коллекция s снова пустая */
    	assertEquals(s.size(),0);  /** её размер = 0 */
    	
    	s.add("a");
    	s.add("b");
    	s.add("c");
    	
    	assertTrue(s.remove("a")); /** строка "a" была удалена из коллекции s по значению */
    	assertTrue(!s.remove("d")); /** строки "d" в коллекции s не было, так что удалить её не удалось */
    	assertTrue(!s.remove(null));
    	assertTrue(!s.remove(new String()));

    	
    	for(Object o:s) {
        	System.out.println(o); /** выведем все элементы коллекции s */
        }
    	
    	s2.add("d");
    	s2.add("b");
    	
    	assertTrue(s.removeAll(s2)); /** удалили из коллекции s все элементы коллекции s2 */
    	
    	System.out.println("\nNow s is:");
    	for(Object o:s) {
        	System.out.println(o); /** снова выведем все элементы коллекции s */
        }
    	
    	assertTrue(!s2.removeAll(s)); /** в коллекции s остался единственный элемент "c", которого нет в коллекции s2, поэтому ни одного элемента не было удалено*/
    	assertTrue(!s2.removeAll(new MyHashSet<String>())); /** удаление пустого множества элементов из коллекции s2 означает, что ни одного элемента не было удалено */
        
    	assertTrue(!s2.removeAll(null));  /** проверка на null */
    	
    	assertTrue(s.removeAll(s));
    	assertTrue(s.isEmpty()); /** теперь s пустая коллекция */
    	assertTrue(!s.removeAll(s)); /** ничего не удалилось, т.к. коллекция s уже пустая */
    	
    	s.add("b");
    	s.add("e");
    	
    	assertTrue(s.retainAll(s2)); /** в коллекции s остались только те элементы, которые есть в коллекции s2 */
    	
    	System.out.println("\nCommon of s and s2 is:");
    	for(Object o:s) {
        	System.out.println(o);
        }
    	
    	assertTrue(s2.retainAll(s)); /** в коллекции s2 остались только те элементы, которые есть в коллекции s */
    	
    	System.out.println("\nCommon of s2 and s is:");
    	for(Object o:s2) {
        	System.out.println(o);
        }
    	
    	assertTrue(!s2.retainAll(null));
    	assertTrue(!s.retainAll(s));/** ничего не удалено, т.к. в s и так только элементы s*/
    	assertTrue(s.retainAll(new MyHashSet<String>())); /** из s удалили все элементы, которых нет в пустой коллекции, т.е. все */
    	assertTrue(s.isEmpty());
    	assertTrue(!s.retainAll(new MyHashSet<String>()));
    	assertTrue(!s.retainAll(s2));
    }
}
