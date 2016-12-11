import java.util.List;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import java.util.Iterator;

public class LRUCache {

	private Map<Integer,CacheElement> Cache = new HashMap<Integer,CacheElement>();
	private Deque<CacheElement> DataList = new LinkedList<CacheElement>();
	private int Size = -1;
	private int elementCount = -1;
	
    /*Initialize an LRU cache with size N */
    public LRUCache(int N) {
       this.Size = N;
       this.elementCount = 0;
    }

    /*Returns the value of the key x if 
     present else returns -1 */
    public int get(int x) {
    	if(this.elementCount < 0){
    		return -1;
    	}else{
    		CacheElement element = this.Cache.get(x);
    		if(element == null)
    			return -1;
    		else{
    			boolean isremoved = this.DataList.remove(element);
    			if(isremoved){
    				this.DataList.addFirst(element);
    			}
    			return element.getValue();
    		}		
    	}       
    }

    /*Sets the key x with value y in the LRU cache */
    public void set(int x, int y) {
       if(this.elementCount < this.Size){    	   
    	   CacheElement value = this.Cache.get(new Integer(x));
    	   
    	   if(value == null){
    		   CacheElement element = new CacheElement();
        	   element.setKey(x);
        	   element.setValue(y);
    		   this.Cache.put(new Integer(x), element);
        	   this.DataList.addFirst(element);
        	   this.elementCount++;
    	   }
    	   else{
    		   boolean isremoved = this.DataList.remove(value);
    		   value.setValue(y);
    		   if(isremoved){
   					this.DataList.addFirst(value);
   				}
    		   this.Cache.put(value.getKey(), value);
    	   }   	   
       }else{
    	   CacheElement value = this.Cache.get(new Integer(x));    	   
    	   if(value == null){
        	   CacheElement removedElement = this.DataList.removeLast();
        	   this.Cache.remove(removedElement.getKey());
    		   CacheElement element = new CacheElement();
        	   element.setKey(x);
        	   element.setValue(y);
    		   this.Cache.put(new Integer(x), element);
        	   this.DataList.addFirst(element);   
    	   }
    	   else{
    		   boolean isremoved = this.DataList.remove(value);
    		   value.setValue(y);
    		   if(isremoved){
   					this.DataList.addFirst(value);
   				}
    		   this.Cache.put(value.getKey(), value);
    	   }
       }       
    }
    
    public void display(){
    	java.util.Iterator<CacheElement> itr = this.DataList.iterator();
    	while(itr.hasNext()){
    		CacheElement elem = itr.next();
    		System.out.print(elem.key + "(" + elem.value + ")" + " ");
    	}
    	System.out.println();
    }

    class CacheElement {
    	int key;
    	int value;
    	
    	public int getKey() {
			return key;
		}

		public void setKey(int key) {
			this.key = key;
		}		

		public int getValue() {
			return value;
		}

		public void setValue(int value) {
			this.value = value;
		}
    	
    }
}
