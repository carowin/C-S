import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Interpreteur {
	
	Map<String, Class<?extends Command>> map = new HashMap<>();
	
	public Interpreteur() {
			map.put("Echo", Echo.class);
			map.put("Exit", Exit.class);
			map.put("Deploy", Deploy.class);
			map.put("Undeploy", Undeploy.class);
	}
	
	public void run() throws IOException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader buff= new BufferedReader(isr);

		System.out.println("yo");
		//String s=buff.readLine();
		String s;
		//Deploy Add /users/Etu5/3520765/TME2bis/bin Add
		while((s = buff.readLine()) != "EOF") {
			if(s != " ") {
				//System.out.println(s);
				String[] split = s.split(" ");
				String cmd = split[0];
				Object object = null;
				//if(map.containsKey(cmd.toLowerCase()) == true) {
				if(map.containsKey(cmd) == true) {
					if(cmd.equals("Deploy")) {
						object = Class.forName(cmd).getConstructor(this.getClass(),List.class).newInstance(this,Arrays.asList(split));
						Class.forName(cmd).getMethod("execute").invoke(object);
					}
					else {
						object = Class.forName(cmd).getConstructor(List.class).newInstance(Arrays.asList(split));
						Class.forName(cmd).getMethod("execute").invoke(object);
					}
				}else {
					new IllegalArgumentException("La commande n'existe pas");
					System.out.println("ERROR no such a command");
				}
			}
		}
		
	}
	
	
	/*
	 * Classe interne de Interpreteur car on souhaite manipuler les attributs de l'interpreteur
	 * (la map) afin d'ajouter/retirer une commande
	 */
	class Deploy implements Command {
		private String cmd; //nom cmd
		private URLClassLoader classLoader; //path
		private String className;
		URL[] tabUrl = new URL[1];
		List<String> arg;
		
		public Deploy(List<String> arg) {
			this.arg = arg;
			this.cmd = arg.get(1);
			this.className = arg.get(3);
			try {
				System.out.println("cc");
				tabUrl[0] = new File(arg.get(2)).toURI().toURL();
				System.out.println("cc");
				classLoader = new URLClassLoader(tabUrl);
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
		}
		
		@SuppressWarnings("unchecked")
		@Override
		public void execute() {
			try {
				System.out.println("cc");
				Class<?> classe = classLoader.loadClass(className);
				System.out.println("ccc");
				map.put(cmd, (Class<? extends Command>) classe);
				
			} catch (ClassNotFoundException | IllegalArgumentException | SecurityException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	class Undeploy implements Command {
		private String cmd;
		private URLClassLoader classLoader;
		private String className;
		URL[] tabUrl = new URL[1];
		List<String> arg;
		
		public Undeploy(List<String> arg) {
			this.arg = arg;
			this.cmd = arg.get(1);
			this.className = arg.get(3);
			try {
				tabUrl[0] = new File(arg.get(2)).toURI().toURL();
				classLoader = new URLClassLoader(tabUrl);
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
		}
		
		@SuppressWarnings("unchecked")
		@Override
		public void execute() {
			try {
				Class<?> classe = classLoader.loadClass(className);
				map.remove(cmd, (Class<? extends Command>) classe);
				
			} catch (ClassNotFoundException | IllegalArgumentException | SecurityException e) {
				e.printStackTrace();
			}
			
		}
		
	}
}
