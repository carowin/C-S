package services;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.HashMap;

public class AnnuaireService implements Service,Annuaire {
	HashMap<String, String> map = new HashMap<String,String>();
	Object mutex = new Object();

	@Override
	public String lookup(String name) {
		if(map.containsKey(name)) {
			return map.get(name);
		}
		return "";
	}

	@Override
	public void bind(String key, String value) {
		synchronized(mutex) {
			map.put(key, value);
		}
	}

	@Override
	public void unbind(String name) {
		synchronized(mutex) {
			if(map.containsKey(name)) {
				map.remove(name);
			}
		}
	}

	@Override
	public void execute(Socket connexion) {
		DataInputStream in;
		try {
			in = new DataInputStream(connexion.getInputStream());
			DataOutputStream out = new DataOutputStream(connexion.getOutputStream());
			String method = in.readUTF(); //lecture methode
			String arg1 = in.readUTF(); //lecture premier arg
			String arg2 = null;
			if(method == "bind") {
				arg2 = in.readUTF(); //lecture deuxième arg
			}
		
			switch (method) {
			case "lookup" : 
				out.writeUTF(lookup(arg1));
				break;
			case "bind" :
				bind(arg1,arg2);
				out.writeUTF(new VoidResponse().affichage());
				break;
			case "unbind" : 
				unbind(arg1);
				out.writeUTF(new VoidResponse().affichage());
				break;
			}
		}catch (IOException e) {
			e.printStackTrace();
		}
		
	}


}
