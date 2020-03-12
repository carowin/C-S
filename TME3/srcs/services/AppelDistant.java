package services;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;

public interface AppelDistant extends Service{

	public default void execute(Socket connexion) {
		try {
			ObjectInputStream is = new ObjectInputStream(connexion.getInputStream());
			ObjectOutputStream os = new ObjectOutputStream(connexion.getOutputStream());
			String method = is.readUTF();
			Method[] m = this.getClass().getMethods();
			for(Method meth: m) {
				if(meth.getName() == method) {
					int len = meth.getParameterCount();
					Object obj[] = new Object[len];
					for(int i=0; i<len; i++) {
						obj[i] = is.readObject();
					}
					meth.invoke(this, obj);
					break;
				}
			}
		} catch (IOException | ClassNotFoundException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		}
	}
}
