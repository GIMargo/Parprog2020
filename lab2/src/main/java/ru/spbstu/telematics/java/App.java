package ru.spbstu.telematics.java;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        MyHashSet<String> s = new MyHashSet<String>();
        
        s.add("y");
        s.add("u");
        s.add("Hello");
        s.add("Hi");
        s.add("Здравствуйте!");
        MyHashSet<String> s2 = new MyHashSet<String>();  
     
        s2.add("Hi");
        s2.add("Hello");
        s2.add("привет");
        s2.add("y");
        s2.add("a");
        
    }
}
