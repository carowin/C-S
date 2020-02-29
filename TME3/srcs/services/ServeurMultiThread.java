package srcs.services;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServeurMultiThread {
	private final int port;
	private final Class<? extends Service> classe;
	private Service serv;
	
	public ServeurMultiThread(int port, Class<? extends Service> classe){
		this.port = port;
		this.classe = classe;
		for(Annotation anot : classe.getAnnotations()) {
			if(anot instanceof EtatGlobal) {
				try {
					this.serv = classe.newInstance();
				} catch (InstantiationException | IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
		
	}

	public void listen() throws IOException {
		ServerSocket s = new ServerSocket(port);
		while(true) {
			Socket c = s.accept();
			//reception d'une requete
			Service service = getService();
			
			Thread th = new Thread(() -> {
			    service.execute(c);
			});
			
			th.start();
			
			c.close();
		}
	}
	
	private Service getService() {
		for(Annotation anot : classe.getAnnotations()) {
			if(anot instanceof EtatGlobal) {
				return serv;
			}
			if(anot instanceof SansEtat) {
				try {
					return classe.getConstructor().newInstance();
				} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
						| InvocationTargetException | NoSuchMethodException | SecurityException e) {
					e.printStackTrace();
				}
			}
		}
		throw new IllegalStateException();
	}
}
