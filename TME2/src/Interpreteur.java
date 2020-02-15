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
			map.put("echo", Echo.class);
			map.put("exit", Exit.class);
	}
	
	public void run() throws IOException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException {
		BufferedReader buff= new BufferedReader(new InputStreamReader(System.in));
		String s;
		while((s = buff.readLine()) != "\n") {
			if(s != " ") {
				//System.out.println(s);
				String[] split = s.split(" ");
				String cmd = split[0];
				
				map.get(cmd.toLowerCase());
				
				Class.forName(cmd).getConstructor(List.class).newInstance(Arrays.asList(split));
		
				System.out.println(cmd);
			}
		}
	}
	
}
