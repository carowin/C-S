package services;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public abstract class ClientProxy {
	private final String name;
	private final int port;
	
	public ClientProxy(String name, int port) {
		this.name = name;
		this.port = port;
	}
	
	public String getName() {
		return name;
	}
	
	public int getPort() {
		return port;
	}
	
	public Object invokeService(String name, Object[] params) {
		try {
			Socket socket = new Socket(name, port);
			ObjectOutputStream os = new ObjectOutputStream(socket.getOutputStream());
			ObjectInputStream is = new ObjectInputStream(socket.getInputStream());
			os.writeUTF(name);
			for(int i=0; i<params.length; i++) {
				os.writeObject(params[i]);
			}
			Object classe = is.readObject();
			if(classe instanceof MyProtocolException) {
				throw new MyProtocolException();
			}
		} catch (IOException | ClassNotFoundException | MyProtocolException e) {
			e.printStackTrace();
		}
		
		return params;
		
	}
}
