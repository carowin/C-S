package srcs.services;

public interface Calculatrice {
	
	public int add(int a, int b);
	
	public int sous(int a, int b);
	
	public int mult(int a, int b);
	
	public ResDiv div(int a, int b);
	
	
	public static class ResDiv{
		int quotient;
		int reste;
		
		public int getQuotient() {
			return quotient;
		}
		
		public int getReste() {
			return reste;
		}
	}
}
