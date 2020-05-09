package beans;

import javax.ejb.LocalBean;
import javax.ejb.Stateful;

/**
 * Session Bean implementation class Counter
 */
@Stateful
@LocalBean
public class Counter implements CounterRemote {
	private int value = 0;
    /**
     * Default constructor. 
     */
    public Counter() {
        // TODO Auto-generated constructor stub
    }
    
    public int getValue() {
    	return value;
    }
    
    public void increment() {
    	value++;
    }
    
    public void decrement() {
    	value--;
    }

}
