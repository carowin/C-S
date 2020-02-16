import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
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
	}
	
	public void run() throws IOException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader buff= new BufferedReader(isr);

		System.out.println("yo");
		//String s=buff.readLine();
		String s;
		while((s = buff.readLine()) != "EOF") {
			if(s != " ") {
				//System.out.println(s);
				String[] split = s.split(" ");
				String cmd = split[0];
				Object object = null;
				//if(map.containsKey(cmd.toLowerCase()) == true) {
				if(map.containsKey(cmd) == true) {
					if(cmd.equals("Echo")) {
						object = Class.forName(cmd).getConstructor(List.class).newInstance(Arrays.asList(split));
					}
					if(cmd.equals("Exit")) {
						object = Class.forName(cmd).getConstructor(int.class).newInstance(Integer.parseInt(split[1]));
					}
					Class.forName(cmd).getMethod("execute").invoke(object);
					//Class.forName(cmd).getMethod("execute").invoke(null);
				}else {
					new IllegalArgumentException("La commande n'existe pas");
				}
			}
		}
		
	}
	
}
