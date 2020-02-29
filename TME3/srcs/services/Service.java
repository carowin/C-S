package srcs.services;

import java.net.Socket;

public interface Service {
	
	public void execute(Socket connexion);
}
