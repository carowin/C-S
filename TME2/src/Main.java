import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class Main {

	public static void main(String[] args) {
		
		Interpreteur inter = new Interpreteur();
		
		try {
			inter.run();
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException | ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
