package srcs.services;

public interface Annuaire {

	public String lookup(String name);
	
	public void bind(String name);
	
	public void unbind(String name);
}
