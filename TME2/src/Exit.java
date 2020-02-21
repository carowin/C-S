import java.util.List;

public class Exit implements Command{

	private int arg;
	
	public Exit(List<String> arg) {
		this.arg = Integer.parseInt(arg.get(1));
	}
	
	@Override
	public void execute() {
		System.out.println("Fin");
		System.exit(arg);
	}

}
