package services;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;

public class ServeurMultiThread {
	private final int port;
	private final Class<? extends Service> classe;
	private Service serv;
	private ServerSocket s;
	
	public ServeurMultiThread(int port, Class<? extends Service> classe){
		this.port = port;
		this.classe = classe;
		try {
			this.s = new ServerSocket(port);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		for(Annotation anot : classe.getAnnotations()) {
			System.out.println("yoyoy");
			if(anot instanceof EtatGlobal) {
				try {
					this.serv = classe.newInstance();
				} catch (InstantiationException | IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
		
	}

	/**
	 * Instancie la socket d'écoute
	 * Dans une boucle infini: -se met en attente sur la socket
	 * 						   -A la reception d'une request appel methode getService()
	 * 						   -Lance dans un nouveau thread la methode execute
	 */
	public void listen() throws IOException {
		while(true) {
			Socket c = s.accept();
			//reception d'une requete
			Service service = getService();
			
			Thread th = new Thread(() -> {
			    service.execute(c);
			});
			
			th.start();
			
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
		throw new IllegalStateException("Erreur");
	}
}
