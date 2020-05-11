package beans;

import javax.ejb.Remote;

@Remote
public interface SayHelloRemote {

	public String hello(String name);
}
