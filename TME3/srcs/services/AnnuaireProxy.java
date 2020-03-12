package services;

import java.util.HashMap;

public class AnnuaireProxy extends ClientProxy implements Annuaire{
	HashMap<String, String> map = new HashMap<String,String>();
	Object mutex = new Object();
	
	
	public AnnuaireProxy(String name, int port) {
		super(name, port);
	}
	
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
	

}
