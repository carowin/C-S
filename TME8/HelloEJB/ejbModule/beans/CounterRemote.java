package beans;

import javax.ejb.Remote;

@Remote
public interface CounterRemote {

	public int getValue();
	public void increment();
	public void decrement();
}
