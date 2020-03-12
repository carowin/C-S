package services;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class CalculatriceService implements Calculatrice, Service{
	
	public Integer add(Integer a, Integer b) {
		return a+b;
	}

	public Integer sous(Integer a, Integer b) {
		return a-b;
	}

	public Integer mult(Integer a, Integer b) {
		return a*b;
	}

	public ResDiv div(Integer a, Integer b) {
		return new ResDiv(a%b,a%b);
	}

	public void execute(Socket connexion) {
		
		try {
			ObjectInputStream in = new ObjectInputStream(connexion.getInputStream());
			ObjectOutputStream out = new ObjectOutputStream(connexion.getOutputStream());
			String method = in.readUTF(); //lecture methode
			int arg1 = in.readInt(); //lecture premier arg
			int arg2 = in.readInt(); //lecture deuxième arg
			
			switch (method) {
			case "add" : 
				out.writeObject(add(arg1,arg2));
				break;
			case "sous" :
				out.writeObject(sous(arg1,arg2));
				break;
			case "mult" : 
				out.writeObject(mult(arg1,arg2));
				break;
			case "div" :
				out.writeObject(div(arg1,arg2).getQuotient());
				out.writeObject(div(arg1,arg2).getReste());
				break;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}