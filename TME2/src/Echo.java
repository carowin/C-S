import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Echo implements Command  {
	
	private List<String> arg;
	public Echo(List<String> arg) {
		this.arg = arg;
	}
	
	@Override
	public void execute() {
		for(int i=1; i<arg.size(); i++) {
			System.out.println(arg.get(i) + ", ");
		}
		//Class<?> classe=this.getClass().getConstructor(ArrayList )
	}

}
