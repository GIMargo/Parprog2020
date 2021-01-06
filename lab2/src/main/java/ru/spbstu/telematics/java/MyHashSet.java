package ru.spbstu.telematics.java;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import java.util.ArrayList;

import static java.lang.Math.abs;

public class MyHashSet<E> implements Set<E> {

	private int m_size;/** размер коллекции */
	
    private int m_hash_size = 16; /** размер хеш-таблицы */
    
    private ArrayList[] m_table; 
    
	public MyHashSet() { /**построение пустого множества*/
		m_table = new ArrayList[m_hash_size];
		m_size=0;
		for(int i=0;i<m_hash_size;i++) {
			m_table[i]=new ArrayList<E>();
		}
	}
	
	public boolean add(E arg0) {
	    if(!this.contains(arg0)&&arg0!=null) {
	    	int i=abs(arg0.hashCode())%m_hash_size;
	    	//System.out.println("Current index is: "+i+" with hash = "+arg0.hashCode());
	    	m_table[i].add(arg0);
	    	m_size++;
	    	return true;
	    }
		return false;
	}

	public boolean addAll(Collection<? extends E> c) { /** если хотя бы один элемент коллекции был добавлен в текущую, возвратит true*/
		boolean res = false;
		if(c!=null) {
		if(!c.isEmpty()) {
		  for(E el:c) {
			 if(add(el)) {
				res=true;
			 }
		}
		}
		}
		return res;
	}

	public void clear() {
	    if(m_size>0) {
	    	for(int i=0;i<m_hash_size;i++) {
	    		m_table[i].clear();
	    	}
	    	m_size=0;
	    }
	}

	public boolean contains(Object arg0) {
		if(m_size!=0&&arg0!=null) {
			int i=abs(arg0.hashCode())%m_hash_size;
			for(Object o:m_table[i]) {
				if(o.equals(arg0)) {
					return true;
				}
			}
		}
		return false;
	}

	public boolean containsAll(Collection<?> c) {

        boolean res=true;	
        for(Object o: c) {
        	if(!contains(o)) {
        		res=false;
        	}
        }
		return res;
	}

	public boolean isEmpty() {
        if(m_size==0) {
		return true;
		}
        return false;
	}

	public Iterator<E> iterator() {
		return new MyHashSetIterator();
	}

	public boolean remove(Object arg0) {
		if(this.contains(arg0)) {
			int i=abs(arg0.hashCode())%m_hash_size;
			m_table[i].remove(arg0);
			m_size--;
			return true;
		}
		return false;
	}

	public boolean removeAll(Collection<?> c) { /** если хоть один элемент удалён - вернётся true */
		boolean res = false;
		if(c!=null) {
		if(c!=this) {
		  for(Object o:c) {
			 if(remove(o)) {
				res=true;
			 }
		  }
		  return res;
		}
		if(m_size>0) {
		clear();
		return true;
		}
		return false;
		}
		return false;
	}

	public boolean retainAll(Collection<?> c) {
		
		boolean res = false;
		if(c!=null) {
	        for (Object o : this) {
	            if (!c.contains(o)) {	            	
	                if(remove(o)) {
	                	res=true;
	                }
	            }
	        }
		} 
		return res;
	}

	public int size() {
		return m_size;
	}

	public Object[] toArray() {
        Object[] arr = new Object[m_size];        
        int i = 0;
        for (Object o : this) {
            arr[i] = o;
            i++;
        }
        return arr;
	}

	public <T> T[] toArray(T[] arr0) {
		T[] arr;
		if (arr0.length < m_size) {
		     arr = (T[]) new Object[m_size];
        }
        else {
             arr = arr0;
        }

        int i = 0;
        for (Object o : this) {
            arr[i] = (T) o;
            i++;
        }

        return arr;
	}
	
    private class MyHashSetIterator implements Iterator<E> {
        
    	private Iterator<E> m_current;
    	private int index;

        public MyHashSetIterator() {
        	index=-1;
        	m_current=null;
        	while(m_current==null&&index<m_hash_size-1) {
        	index++;
            m_current = m_table[index].iterator();
            if(!m_current.hasNext()) {
            	m_current=null;
            }

        	}
        }
        
        public boolean hasNext() {
        	if(m_current==null) {
        		return false;
        	}
        	if(m_current.hasNext()){
        		return true;
        	}
  
        	int i=index;
        	while(i<m_hash_size-1) {
        	  i++;
        	  Iterator<E> it=m_table[i].iterator();
        	  if(it.hasNext()) {
        		  return true;
        	  }
        	}
            return false;
        }

    
        public E next() {
        	
            E data = m_current.next();
 
            if (!m_current.hasNext()&&index<m_hash_size-1) {
            	m_current=null;
            	while(index<m_hash_size-1&&m_current==null) {
                      index++;
                      m_current=m_table[index].iterator();
                      if(!m_current.hasNext()) {
             	            m_current=null;
                      }
            	}
            }
            return data;
        }

    } 
}
