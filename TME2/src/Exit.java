
public class Exit implements Command{

	private int arg;
	
	public Exit(int arg) {
		this.arg = arg;
	}
	
	@Override
	public void execute() {
		System.out.println("Fin");
		System.exit(arg);
	}

}
